package gui.controllers;

import gui.Alerts;
import gui.COILVICApplication;
import gui.SessionManager;
import gui.stages.AccountRequestMenuStage;
import gui.stages.CollaborationsCoordinatorMenuStage;
import gui.stages.ExternalAccountCreateStage;
import gui.stages.GenerateStatisticsStage;
import gui.stages.OfferCoordinatorStage;
import gui.stages.PendingMailsStage;
import gui.stages.UniversitiesStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class CoordinatorMenuController implements Initializable {
    private static final Logger LOG = Logger.getLogger(CoordinatorMenuController.class);
    private final SessionManager currentSession = SessionManager.getInstance();
    @FXML
    private Text txtName;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtName.setText(currentSession.getUserData().getName());
    }   

    @FXML
    private void displayUniversities() {
        UniversitiesStage universitiesStage = null;
        try {
            universitiesStage = new UniversitiesStage();
        } catch(IOException ioException) {
            LOG.warn(ioException);
            Alerts.displayAlertIOException();
        }
        if (universitiesStage != null) {
            Stage stage = (Stage) txtName.getScene().getWindow();
            stage.close();
        }
    }
    
    @FXML
    private void displayAccountRequestMenu() {
        Stage stage = (Stage) txtName.getScene().getWindow();
        stage.close();
        try {
            AccountRequestMenuStage accoountRequestMenuStage = new AccountRequestMenuStage();
        } catch(IOException ioException) {
            LOG.warn(ioException);
            Alerts.displayAlertIOException();
        }
    }
    
    @FXML
    private void displayExternalAccountCreate() {
        try {
            ExternalAccountCreateStage externalAccountCreateStage = new ExternalAccountCreateStage();
        } catch(IOException ioException) {
            LOG.warn(ioException);
            Alerts.displayAlertIOException();
        }
    }
    
    @FXML
    private void logout() {
        Stage stage = (Stage) txtName.getScene().getWindow();
        stage.close();
        currentSession.logout();
        try {
            COILVICApplication application = new COILVICApplication();
            Stage loginStage = new Stage();
            application.start(loginStage);
        } catch(IOException ioException) {
            LOG.warn(ioException);
            Alerts.displayAlertIOException();
        }
    }
    
    @FXML
    private void displayOfferCoordinator() {
        Stage stage = (Stage) this.txtName.getScene().getWindow();
        stage.close();
        try {
            OfferCoordinatorStage offerCoordinatorStage = new OfferCoordinatorStage();
        } catch(IOException ioException) {
            LOG.warn(ioException);
            Alerts.displayAlertIOException();
        }
    }
    
    @FXML
    private void displayGenerateStatistics() {
        Stage stage = (Stage) this.txtName.getScene().getWindow();
        stage.close();
        try {
            GenerateStatisticsStage generateStatisticsStage = new GenerateStatisticsStage();
        } catch(IOException ioException) {
            LOG.warn(ioException);
            Alerts.displayAlertIOException();
        }
    }
    
    @FXML
    private void displayCollaborations() {
        Stage stage = (Stage) this.txtName.getScene().getWindow();
        stage.close();
        try {
            CollaborationsCoordinatorMenuStage collaborationsCoordinatorMenuStage = new CollaborationsCoordinatorMenuStage();
        } catch(IOException ioException) {
            LOG.warn(ioException);
            Alerts.displayAlertIOException();
        }
    }
    
    @FXML
    private void openPendingMails() {
        try {
            PendingMailsStage pendingMailsStage = new PendingMailsStage();
        } catch(IOException ioException) {
            LOG.warn(ioException);
            Alerts.displayAlertIOException();
        }
    }
}
