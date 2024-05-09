package gui.controllers;

import gui.Alerts;
import gui.COILVICApplication;
import gui.SessionManager;
import gui.stages.AccountRequestMenuStage;
import gui.stages.ExternalAccountCreateStage;
import gui.stages.GenerateStatisticsStage;
import gui.stages.OfferCoordinatorStage;
import gui.stages.UniversitiesStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class CoordinatorMenuController implements Initializable {
    private static final Logger log = Logger.getLogger(CoordinatorMenuController.class);
    @FXML
    private Text txtName;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtName.setText(SessionManager.getInstance().getUserData().getName());
    }   

    @FXML
    private void displayUniversities() {
        UniversitiesStage universitiesStage = null;
        try {
            universitiesStage = new UniversitiesStage();
        } catch(IOException ioException) {
            log.warn(ioException);
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
            log.warn(ioException);
            Alerts.displayAlertIOException();
        }
    }
    
    @FXML
    private void displayExternalAccountCreate() {
        try {
            ExternalAccountCreateStage externalAccountCreateStage = new ExternalAccountCreateStage();
        } catch(IOException ioException) {
            log.warn(ioException);
            Alerts.displayAlertIOException();
        }
    }
    
    @FXML
    private void logout() {
        Stage stage = (Stage) txtName.getScene().getWindow();
        stage.close();
        SessionManager.getInstance().logout();
        try {
            COILVICApplication application = new COILVICApplication();
            Stage loginStage = new Stage();
            application.start(loginStage);
        } catch(IOException ioException) {
            log.warn(ioException);
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
            log.warn(ioException);
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
            ioException.printStackTrace();
            Alerts.displayAlertIOException();
        }
    }
}
