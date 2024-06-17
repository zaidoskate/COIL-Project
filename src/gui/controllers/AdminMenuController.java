package gui.controllers;

import gui.Alerts;
import gui.COILVICApplication;
import gui.SessionManager;
import gui.stages.CoordinatorAccountCreatorStage;
import gui.stages.PendingMailsStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class AdminMenuController implements Initializable {
    private static final Logger LOG = Logger.getLogger(AdminMenuController.class);
    @FXML
    private Button btnLogOut;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    @FXML
    public void createCoordinatorAccount() {
        try {
            CoordinatorAccountCreatorStage createCoordinatorAccountStage = new CoordinatorAccountCreatorStage();
        } catch (IOException ioexception) {
            LOG.warn(ioexception);
            Alerts.displayAlertIOException();
        }
    }
    
    @FXML
    private void logout() {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.close();
        SessionManager.getInstance().logout();
        try {
            COILVICApplication application = new COILVICApplication();
            Stage loginStage = new Stage();
            application.start(loginStage);
        } catch (IOException ioException) {
            LOG.warn(ioException);
            Alerts.displayAlertIOException();
        }
    }
     @FXML
    private void openPendingMails() {
        try {
            PendingMailsStage pendingMailsStage = new PendingMailsStage();
        } catch (IOException ioException) {
            LOG.warn(ioException);
            Alerts.displayAlertIOException();
        }
    }
}
