/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.controllers;

import gui.Alerts;
import gui.stages.MyCollaborationsStage;
import gui.stages.ProfesorMenuStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author zaido
 */
public class CollaborationController implements Initializable {
    
    @FXML
    private Button btnBack;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    public void displayMyCollaborations() {
        Stage stage = (Stage) this.btnBack.getScene().getWindow();
        stage.close();
        try {
            MyCollaborationsStage myCollaborationsStage = new MyCollaborationsStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
        }
    }
    
    @FXML
    public void previousMenu() {
        Stage stage = (Stage) this.btnBack.getScene().getWindow();
        stage.close();
        try {
            ProfesorMenuStage menuStage = new ProfesorMenuStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
        }
    }
}
