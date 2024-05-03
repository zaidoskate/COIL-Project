/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui.controllers;

import gui.COILVICApplication;
import gui.SessionManager;
import gui.stages.CollaborationStage;
import gui.stages.OfferProfessorStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
public class ProfesorMenuController implements Initializable {

    @FXML
    private Text textName;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textName.setText(SessionManager.getInstance().getUserData().getName());
    }
    
    @FXML
    public void displayOfferProfessor() {
        Stage menuStage = (Stage) textName.getScene().getWindow();
        menuStage.close();
        try {
            OfferProfessorStage offerProfessorStage = new OfferProfessorStage();
        } catch(IOException ioException) {
            
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
    public void displayCollaboration() {
        Stage menuStage = (Stage) textName.getScene().getWindow();
        menuStage.close();
        try {
            CollaborationStage collaborationStage = new CollaborationStage();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
