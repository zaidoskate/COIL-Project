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
import logic.domain.UvAccountRequest;
import logic.DAOs.UvAccountRequestDAO;
import logic.LogicException;
import logic.AccountCreator;
import logic.DAOs.PendingMailDAO;
import logic.domain.PendingMail;
import logic.model.EmailNotification;
import org.apache.log4j.Logger;

public class AccountRequestUvListController implements Initializable{
    private static final Logger LOG = Logger.getLogger(AccountRequestUvListController.class);
    private static final UvAccountRequestDAO UV_ACCOUNT_REQUEST_DAO = new UvAccountRequestDAO();
    private static final PendingMailDAO PENDING_MAIL_DAO = new PendingMailDAO();
    private final SessionManager currentSession = SessionManager.getInstance();
    
    @FXML
    private TableView<UvAccountRequest> tblViewUvAccountRequest;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUvAccountRequest();
    }
    
    private void loadUvAccountRequest() {
        tblViewUvAccountRequest.getItems().clear();
        ArrayList<UvAccountRequest> uvAccountRequests = new ArrayList();
        try{
            uvAccountRequests = UV_ACCOUNT_REQUEST_DAO.getUvAccountRequests();
        } catch(LogicException logicException) {
            LOG.error(logicException);
            Alerts.displayAlertLogicException(logicException);
            Stage stage = (Stage) tblViewUvAccountRequest.getScene().getWindow();
            stage.close();
        }
        tblViewUvAccountRequest.getItems().addAll(uvAccountRequests);
    }

    private boolean deleteUvAccountRequest(UvAccountRequest uvAccountRequest) {
        boolean result = false;
        try {
            if(UV_ACCOUNT_REQUEST_DAO.deleteUvAccountRequest(uvAccountRequest) == 1) {
                result = true;
            }
        } catch(LogicException logicException) {
            LOG.error(logicException);
            Alerts.displayAlertLogicException(logicException);
        }
        return result;
    }
    
    public void savePendingMail(UvAccountRequest uvAccountRequest) throws LogicException {
        PendingMail pendingMail = new PendingMail();
        pendingMail.setContent(EmailNotification.getInstance().getEmailBody());
        pendingMail.setDestinationEmail(uvAccountRequest.getEmail());
        pendingMail.setSubject("Rechazo de cuenta de acceso.");
        pendingMail.setIdUser(currentSession.getUserData().getIdUser());

        PENDING_MAIL_DAO.insertPendingMail(pendingMail);
    }
    
    @FXML
    private void previusMenu() {
        Stage stage = (Stage) tblViewUvAccountRequest.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void acceptAccountRequest() {
        if(tblViewUvAccountRequest.getSelectionModel().getSelectedItem() != null) {
            UvAccountRequest uvaccountRequestSelected = tblViewUvAccountRequest.getSelectionModel().getSelectedItem();
            boolean result = false;
            try{
                result = AccountCreator.createUVAccount(uvaccountRequestSelected);   
            } catch(LogicException logicException) {
                LOG.error(logicException);
                Alerts.displayAlertLogicException(logicException);
            }
            
            if(result == true) {
                Alerts.showInformationAlert("Exito", "El correo se ha enviado a su destino con la clave de acceso");
                loadUvAccountRequest();
            }
            loadUvAccountRequest();
        } else {
            Alerts.showWarningAlert("No se ha seleccionado ninguna solicitud.");
        }
    }
    
    @FXML
    private void declineAccountRequest() {
        if(tblViewUvAccountRequest.getSelectionModel().getSelectedItem() != null) {
            UvAccountRequest uvaccountRequest = tblViewUvAccountRequest.getSelectionModel().getSelectedItem();
            if(deleteUvAccountRequest(uvaccountRequest)){
                EmailNotification.getInstance().setEmail(uvaccountRequest.getEmail());
                EmailNotification.getInstance().setMessageSuccess("Se ha rechazado con exito la solicitud de cuenta");
                try {
                    SendEmailStage sendEmailStage = new SendEmailStage();
                } catch(IOException ioexception) {
                    LOG.warn(ioexception);
                    Alerts.displayAlertIOException();
                }
                if(EmailNotification.getInstance().getSentStatus()) {
                    try {
                        savePendingMail(uvaccountRequest);
                    } catch(LogicException logicException) {
                        Alerts.displayAlertLogicException(logicException);
                    }
                }
                loadUvAccountRequest();
            }
        } else {
            Alerts.showWarningAlert("No se ha seleccionado ninguna solicitud.");
        }
    }

}
