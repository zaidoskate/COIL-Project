/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.controllers;

import gui.Alerts;
import gui.stages.CollaborationHistoryStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

/**
 *
 * @author zaido
 */
public class DetailMyCollaborationController implements Initializable {

    @FXML
    private Label lblCollaborationName;
    
    @FXML
    private Label lblStartDate;
    
    @FXML
    private ImageView imageViewZipFile;
    
    @FXML
    private Label lblCertificatesAvailable;
    
    private static final Logger log = Logger.getLogger(DetailMyCollaborationController.class);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void downloadCertificates() {
        
    }
    
    @FXML
    private void previousMenu() {
        Stage stage = (Stage) this.lblCollaborationName.getScene().getWindow();
        stage.close();
        try {
            CollaborationHistoryStage historyStage = new CollaborationHistoryStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            log.error(ioException);
        }
    }
}
