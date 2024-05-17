/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.controllers;

import gui.Alerts;
import gui.stages.MyCollaborationsStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

/**
 *
 * @author zaido
 */
public class ConcludeCollaborationController implements Initializable {

    @FXML
    private ComboBox comboBoxFileType;
    
    private static final Logger log = Logger.getLogger(ConcludeCollaborationController.class);
    
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
    private void showHasUploadedFile() {
        
    }
    
    @FXML
    private void concludeCollaboration() {
        
    }
    
    @FXML
    private void deleteFile() {
        
    }
    
    @FXML
    private void previousMenu() {
        Stage stage = (Stage) this.comboBoxFileType.getScene().getWindow();
        stage.close();
        try {
            MyCollaborationsStage myCollaborationsStage = new MyCollaborationsStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            log.error(ioException);
        }
    }
}
