/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.controllers;

import gui.Alerts;
import gui.stages.ReviewConclusionCollaborationStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

/**
 *
 * @author zaido
 */
public class UploadCertificatesController implements Initializable {
    
    @FXML
    private Slider sliderGrade;
    
    @FXML
    private CheckBox checkBoxVisible;
    
    private static final Logger log = Logger.getLogger(UploadCertificatesController.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void onDragOver() {
        
    }
    
    @FXML
    private void onDragDropped() {
        
    }
    
    @FXML
    private void approveConclusion() {
        
    }
    
    @FXML
    private void previousMenu() {
        Stage stage = (Stage) this.sliderGrade.getScene().getWindow();
        stage.close();
        try {
            ReviewConclusionCollaborationStage reviewStage = new ReviewConclusionCollaborationStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            log.error(ioException);
        }
    }
}
