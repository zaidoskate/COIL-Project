package gui.controllers;

import gui.Alerts;
import gui.SessionManager;
import gui.stages.SendEmailStage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import logic.AccountCreator;
import logic.DAOs.ExternalAccountRequestDAO;
import logic.DAOs.PendingMailDAO;
import logic.LogicException;
import logic.domain.ExternalAccountRequest;
import logic.model.EmailNotification;
import logic.domain.ExternalAccountRequestData;
import logic.domain.PendingMail;
import org.apache.log4j.Logger;

public class AccountRequestExternalListController implements Initializable {
    private static final Logger LOG = Logger.getLogger(AccountRequestExternalListController.class);
    private static final ExternalAccountRequestDAO EXTERNAL_ACCOUNT_REQUEST_DAO = new ExternalAccountRequestDAO();
    private static final PendingMailDAO PENDING_MAIL_DAO = new PendingMailDAO();
    private final SessionManager currentSession = SessionManager.getInstance();

    @FXML
    private TableView<ExternalAccountRequestData> tblViewExternalAccountRequest;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        loadExternalAccountRequest();
    }
    
    private void loadExternalAccountRequest() {
        tblViewExternalAccountRequest.getItems().clear();
        ArrayList<ExternalAccountRequestData> externalAccountRequestsData = new ArrayList();
        try {
            externalAccountRequestsData = EXTERNAL_ACCOUNT_REQUEST_DAO.getExternalAccountRequestsData();
        } catch (LogicException logicException) {
            LOG.error(logicException);
            Alerts.displayAlertLogicException(logicException);
            Stage stage = (Stage) tblViewExternalAccountRequest.getScene().getWindow();
            stage.close();
        }
        tblViewExternalAccountRequest.getItems().addAll(externalAccountRequestsData);
    }
    
    private boolean deleteExternalAccountRequest(ExternalAccountRequest externalAccountRequest) {
        boolean result = false;
        try {
            if (EXTERNAL_ACCOUNT_REQUEST_DAO.deleteExternalAccountRequest(externalAccountRequest) == 1) {
                result = true;
            }
        } catch (LogicException logicException) {
            LOG.error(logicException);
            Alerts.displayAlertLogicException(logicException);
        }
        return result;
    }
    
    public void savePendingMail(ExternalAccountRequest externalAccountRequest) throws LogicException {
        PendingMail pendingMail = new PendingMail();
        pendingMail.setContent(EmailNotification.getInstance().getEmailBody());
        pendingMail.setDestinationEmail(externalAccountRequest.getEmail());
        pendingMail.setSubject("Rechazo de cuenta de acceso.");
        pendingMail.setIdUser(currentSession.getUserData().getIdUser());

        PENDING_MAIL_DAO.insertPendingMail(pendingMail);
    }
   
    @FXML
    private void previusMenu() {
        Stage stage = (Stage) tblViewExternalAccountRequest.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void acceptAccountRequest() {
        if (tblViewExternalAccountRequest.getSelectionModel().getSelectedItem() != null) {
            int idAccountRequestSelected = tblViewExternalAccountRequest.getSelectionModel().getSelectedItem().getIdRequest();
            ExternalAccountRequest externalAccountRequest;
            boolean result = false;
            try {
                externalAccountRequest = EXTERNAL_ACCOUNT_REQUEST_DAO.getExternalAccountRequestById(idAccountRequestSelected);
                if (externalAccountRequest.getName() != null && externalAccountRequest.getEmail()!= null){
                    result = AccountCreator.createExternalAccount(externalAccountRequest); 
                }
            } catch (LogicException logicException) {
                LOG.error(logicException);
                Alerts.displayAlertLogicException(logicException);
            }
            if (result == true) {
                Alerts.showInformationAlert("Éxito", "El correo se ha enviado a su destino con la clave de acceso");
            }
            loadExternalAccountRequest();
        } else {
            Alerts.showWarningAlert("No se ha seleccionado ninguna solicitud.");
        }
    }
    
    @FXML
    private void declineAccountRequest() {
        if (tblViewExternalAccountRequest.getSelectionModel().getSelectedItem() != null) {
            int idAccountRequestSelected = tblViewExternalAccountRequest.getSelectionModel().getSelectedItem().getIdRequest();
            ExternalAccountRequest externalAccountRequest;
            try {
                externalAccountRequest = EXTERNAL_ACCOUNT_REQUEST_DAO.getExternalAccountRequestById(idAccountRequestSelected);
            } catch (LogicException logicException) {
                LOG.error(logicException);
                Alerts.displayAlertLogicException(logicException);
                return;
            }
            if (deleteExternalAccountRequest(externalAccountRequest)) {
                loadExternalAccountRequest();
                EmailNotification.getInstance().setEmail(externalAccountRequest.getEmail());
                EmailNotification.getInstance().setMessageSuccess("Se ha rechazado con exito la solicitud de cuenta");
                try {
                    SendEmailStage sendEmailStage = new SendEmailStage();
                } catch (IOException ioexception) {
                    LOG.warn(ioexception);
                    Alerts.displayAlertIOException();
                }
                if (EmailNotification.getInstance().getSentStatus() == false) {
                    try {
                        savePendingMail(externalAccountRequest);
                    } catch (LogicException logicException) {
                        Alerts.displayAlertLogicException(logicException);
                    }
                }
                loadExternalAccountRequest();
            }
            
        } else {
            Alerts.showWarningAlert("No se ha seleccionado ninguna solicitud.");
        }
    }
}
