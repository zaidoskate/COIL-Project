package gui.controllers;

import gui.Alerts;
import gui.SessionManager;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import logic.DAOs.PendingMailDAO;
import logic.LogicException;
import logic.MailSender;
import logic.domain.PendingMail;
import org.apache.log4j.Logger;

public class PendingMailsController implements Initializable {
    private static final Logger log = Logger.getLogger(PendingMailsController.class);
    @FXML
    private TableView<PendingMail> tblViewPendingMails;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPendingMails();
    }
    
    private void loadPendingMails() {
        tblViewPendingMails.getItems().clear();
        int currentUserId = SessionManager.getInstance().getUserData().getIdUser();
        PendingMailDAO pendingMailDAO = new PendingMailDAO();
        ArrayList<PendingMail> pendingMails;
        try{
            pendingMails = pendingMailDAO.getPendingMailsByIdUser(currentUserId);
            tblViewPendingMails.getItems().addAll(pendingMails);
        } catch(LogicException logicException) {
            log.error(logicException);
            Alerts.displayAlertLogicException(logicException);
            previusMenu();
        }
    }
    
    private void deletePendingMailFromDB(PendingMail pendingMail) {
        PendingMailDAO pendingMailDAO = new PendingMailDAO();
        try{
            pendingMailDAO.deletePendingMail(pendingMail);
        } catch(LogicException logicException) {
            log.error(logicException);
            Alerts.displayAlertLogicException(logicException);
        }
    }
    
    @FXML
    private void deletePendingMail() {
        PendingMail pendingMailSelected = tblViewPendingMails.getSelectionModel().getSelectedItem();
        if(pendingMailSelected != null) {
            deletePendingMailFromDB(pendingMailSelected);
            loadPendingMails();
        } else {
            Alerts.showWarningAlert("No se ha seleccionado ninguna solicitud.");
        }
    }
    @FXML
    private void sendPendingMail() {
        PendingMail pendingMailSelected = tblViewPendingMails.getSelectionModel().getSelectedItem();
        if(pendingMailSelected != null) {
            boolean result = false;
            try {
                result = MailSender.sendEmail(pendingMailSelected.getContent(), pendingMailSelected.getDestinationEmail());
            } catch(LogicException logicException) {
                log.error(logicException);
                Alerts.displayAlertLogicException(logicException);
            }
            if(result == true) {
                deletePendingMailFromDB(pendingMailSelected);
                Alerts.showInformationAlert("Correo enviado", "El correo se ha reenviado con exito a su destino.");
                loadPendingMails();
            } else {
                Alerts.showWarningAlert("El correo no se ha enviado a su destino.");
            }
        } else {
            Alerts.showWarningAlert("No se ha seleccionado ninguna solicitud.");
        }
    }
    @FXML
    private void previusMenu() {
        Stage stage = (Stage) tblViewPendingMails.getScene().getWindow();
        stage.close();
    }
    
}
