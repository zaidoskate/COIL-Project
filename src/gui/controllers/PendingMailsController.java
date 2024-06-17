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
    private static final Logger LOG = Logger.getLogger(PendingMailsController.class);
    private static final SessionManager currentSession = SessionManager.getInstance();
    private static final PendingMailDAO PENDING_MAIL_DAO = new PendingMailDAO();
    @FXML
    private TableView<PendingMail> tblViewPendingMails;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPendingMails();
    }
    
    private void loadPendingMails() {
        tblViewPendingMails.getItems().clear();
        int currentUserId = currentSession.getUserData().getIdUser();
        ArrayList<PendingMail> pendingMails;
        try {
            pendingMails = PENDING_MAIL_DAO.getPendingMailsByIdUser(currentUserId);
            tblViewPendingMails.getItems().addAll(pendingMails);
        } catch (LogicException logicException) {
            LOG.error(logicException);
            Alerts.displayAlertLogicException(logicException);
            previusMenu();
        }
    }
    
    private void deletePendingMailFromDB(PendingMail pendingMail) {
        try {
            PENDING_MAIL_DAO.deletePendingMail(pendingMail);
        } catch (LogicException logicException) {
            LOG.error(logicException);
            Alerts.displayAlertLogicException(logicException);
        }
    }
    
    @FXML
    private void deletePendingMail() {
        PendingMail pendingMailSelected = tblViewPendingMails.getSelectionModel().getSelectedItem();
        if (pendingMailSelected != null) {
            deletePendingMailFromDB(pendingMailSelected);
            loadPendingMails();
        } else {
            Alerts.showWarningAlert("No se ha seleccionado ninguna solicitud.");
        }
    }
    @FXML
    private void sendPendingMail() {
        PendingMail pendingMailSelected = tblViewPendingMails.getSelectionModel().getSelectedItem();
        if (pendingMailSelected != null) {
            boolean result = false;
            try {
                result = MailSender.sendEmail(pendingMailSelected.getContent(), pendingMailSelected.getDestinationEmail());
            } catch (LogicException logicException) {
                LOG.error(logicException);
                Alerts.displayAlertLogicException(logicException);
            }
            if (result == true) {
                deletePendingMailFromDB(pendingMailSelected);
                Alerts.showInformationAlert("Correo enviado", "El correo se ha reenviado con éxito a su destino.");
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
