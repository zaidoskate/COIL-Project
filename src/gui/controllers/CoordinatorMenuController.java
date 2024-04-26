/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui.controllers;

import gui.SessionManager;
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
}
