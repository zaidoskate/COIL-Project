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
import logic.domain.UvAccountRequest;
import logic.DAOs.UvAccountRequestDAO;
import logic.LogicException;
import logic.AccountCreator;
import logic.model.EmailNotification;
import org.apache.log4j.Logger;

public class AccountRequestUvListController implements Initializable{
    private static final Logger log = Logger.getLogger(AccountRequestUvListController.class);
    @FXML
    private TableView<UvAccountRequest> tblViewUvAccountRequest;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUvAccountRequest();
    }
    
    private void loadUvAccountRequest() {
        tblViewUvAccountRequest.getItems().clear();
        UvAccountRequestDAO uvAccountRequestDAO = new UvAccountRequestDAO();
        ArrayList<UvAccountRequest> uvAccountRequests = new ArrayList();
        try{
            uvAccountRequests = uvAccountRequestDAO.getUvAccountRequests();
        } catch(LogicException logicException) {
            log.error(logicException);
            Alerts.displayAlertLogicException(logicException);
        }
        tblViewUvAccountRequest.getItems().addAll(uvAccountRequests);
    }

    private void deleteUvAccountRequest(UvAccountRequest uvAccountRequest) {
        UvAccountRequestDAO uvAccountRequestDAO = new UvAccountRequestDAO();
        try {
            uvAccountRequestDAO.deleteUvAccountRequest(uvAccountRequest);
        } catch(LogicException logicException) {
            log.error(logicException);
            Alerts.displayAlertLogicException(logicException);
        }
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
        if(tblViewUvAccountRequest.getSelectionModel().getSelectedItem() != null) {
            UvAccountRequest uvaccountRequest = tblViewUvAccountRequest.getSelectionModel().getSelectedItem();
            EmailNotification.getInstance().setEmail(uvaccountRequest.getEmail());
            EmailNotification.getInstance().setMessageCancel("No se ha rechazado esta solicitud de cuenta");
            EmailNotification.getInstance().setMessageSuccess("Se ha rechazado con exito la solicitud de cuenta");
            try {
                SendEmailStage sendEmailStage = new SendEmailStage();
            } catch(IOException ioexception) {
                log.warn(ioexception);
                Alerts.displayAlertIOException();
            }
            if(EmailNotification.getInstance().getSentStatus()) {
                deleteUvAccountRequest(uvaccountRequest);
                loadUvAccountRequest();
            }
        } else {
            Alerts.showWarningAlert("No se ha seleccionado ninguna solicitud.");
        }
    }

}
