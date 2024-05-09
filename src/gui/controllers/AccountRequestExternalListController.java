package gui.controllers;

import gui.Alerts;
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
import logic.LogicException;
import logic.domain.ExternalAccountRequest;
import logic.model.EmailNotification;
import logic.domain.ExternalAccountRequestData;
import org.apache.log4j.Logger;

public class AccountRequestExternalListController implements Initializable {
    private static final Logger log = Logger.getLogger(AccountRequestExternalListController.class);
    @FXML
    private TableView<ExternalAccountRequestData> tblViewExternalAccountRequest;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        loadUvAccountRequest();
    }
    
    private void loadUvAccountRequest() {
        tblViewExternalAccountRequest.getItems().clear();
        ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
        ArrayList<ExternalAccountRequestData> externalAccountRequestsData = new ArrayList();
        try{
            externalAccountRequestsData = externalAccountRequestDAO.getExternalAccountRequestsData();
        } catch(LogicException logicException) {
            log.error(logicException);
            Alerts.displayAlertLogicException(logicException);
        }
        tblViewExternalAccountRequest.getItems().addAll(externalAccountRequestsData);
    }
    
    private void deleteUvAccountRequest(ExternalAccountRequest externalAccountRequest) {
        ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
        try {
            externalAccountRequestDAO.deleteExternalAccountRequest(externalAccountRequest);
        } catch(LogicException logicException) {
            log.error(logicException);
            Alerts.displayAlertLogicException(logicException);
        }
    }
   
    @FXML
    private void previusMenu() {
        Stage stage = (Stage) tblViewExternalAccountRequest.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void acceptAccountRequest() {
        if(tblViewExternalAccountRequest.getSelectionModel().getSelectedItem() != null) {
            int idAccountRequestSelected = tblViewExternalAccountRequest.getSelectionModel().getSelectedItem().getIdRequest();
            ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
            ExternalAccountRequest externalAccountRequest;
            boolean result = false;
            try{
                externalAccountRequest = externalAccountRequestDAO.getExternalAccountRequestById(idAccountRequestSelected);
                if(externalAccountRequest.getName() != null && externalAccountRequest.getEmail()!= null){
                    result = AccountCreator.createExternalAccount(externalAccountRequest); 
                }
            } catch(LogicException logicException) {
                log.error(logicException);
                Alerts.displayAlertLogicException(logicException);
            }
            
            if(result == true) {
                Alerts.showInformationAlert("Exito", "El correo se ha enviado a su destino con la clave de acceso");
                loadUvAccountRequest();
            } else {
                Alerts.showWarningAlert("No se ha podido registrar ni enviar el correo a su destino, intentelo mas tarde.");
            }
        } else {
            Alerts.showWarningAlert("No se ha seleccionado ninguna solicitud.");
        }
    }
    
    @FXML
    private void declineAccountRequest() {
        if(tblViewExternalAccountRequest.getSelectionModel().getSelectedItem() != null) {
            int idAccountRequestSelected = tblViewExternalAccountRequest.getSelectionModel().getSelectedItem().getIdRequest();
            ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
            ExternalAccountRequest externalAccountRequest;
            try{
                externalAccountRequest = externalAccountRequestDAO.getExternalAccountRequestById(idAccountRequestSelected);
            } catch(LogicException logicException) {
                log.error(logicException);
                Alerts.displayAlertLogicException(logicException);
                return;
            }
            EmailNotification.getInstance().setEmail(externalAccountRequest.getEmail());
            EmailNotification.getInstance().setMessageCancel("No se ha rechazado esta solicitud de cuenta");
            EmailNotification.getInstance().setMessageSuccess("Se ha rechazado con exito la solicitud de cuenta");
            try {
                SendEmailStage sendEmailStage = new SendEmailStage();
            } catch(IOException ioexception) {
                log.warn(ioexception);
                Alerts.displayAlertIOException();
            }
            if(EmailNotification.getInstance().getSentStatus()) {
                deleteUvAccountRequest(externalAccountRequest);
                loadUvAccountRequest();
            }
        } else {
            Alerts.showWarningAlert("No se ha seleccionado ninguna solicitud.");
        }
    }
}
