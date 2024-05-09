/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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

/**
 * FXML Controller class
 *
 * @author chuch
 */
public class CoordinatorMenuController implements Initializable {

    @FXML
    private Text textName;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textName.setText(SessionManager.getInstance().getUserData().getName());
    }   

    @FXML
    private void displayUniversities() {
        UniversitiesStage universitiesStage = null;
        try {
            universitiesStage = new UniversitiesStage();
        } catch(IOException ioException) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ERROR, intentalo mas tarde.");
            alert.showAndWait();
        }
        if (universitiesStage != null) {
            Stage stage = (Stage) textName.getScene().getWindow();
            stage.close();
        }
    }
    
    @FXML
    private void displayAccountRequestMenu() {
        Stage stage = (Stage) textName.getScene().getWindow();
        stage.close();
        try {
            AccountRequestMenuStage accoountRequestMenuStage = new AccountRequestMenuStage();
        } catch(IOException ioException) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ERROR, intentalo mas tarde.");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void displayExternalAccountCreate() {
        try {
            ExternalAccountCreateStage externalAccountCreateStage = new ExternalAccountCreateStage();
        } catch(IOException ioException) {
            ioException.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ERROR, intentalo mas tarde.");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void logout() {
        Stage stage = (Stage) textName.getScene().getWindow();
        stage.close();
        SessionManager.getInstance().logout();
        try {
            COILVICApplication application = new COILVICApplication();
            Stage loginStage = new Stage();
            application.start(loginStage);
        } catch(IOException ioException) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ERROR, intentalo mas tarde.");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void displayOfferCoordinator() {
        Stage stage = (Stage) this.textName.getScene().getWindow();
        stage.close();
        try {
            OfferCoordinatorStage offerCoordinatorStage = new OfferCoordinatorStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
        }
    }
    
    @FXML
    private void displayGenerateStatistics() {
        Stage stage = (Stage) this.textName.getScene().getWindow();
        stage.close();
        try {
            GenerateStatisticsStage generateStatisticsStage = new GenerateStatisticsStage();
        } catch(IOException ioException) {
            ioException.printStackTrace();
            Alerts.displayAlertIOException();
        }
    }
}
