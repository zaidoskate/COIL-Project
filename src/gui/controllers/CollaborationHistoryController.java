/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.controllers;

import gui.Alerts;
import gui.stages.DetailMyCollaborationStage;
import gui.stages.MyCollaborationsStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

/**
 *
 * @author zaido
 */
public class CollaborationHistoryController implements Initializable {
    
    @FXML
    private TableView tblViewConcludedCollaborations;
    
    private static final Logger log = Logger.getLogger(CollaborationHistoryController.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void displayDetailMyCollaboration() {
        Stage stage = (Stage) this.tblViewConcludedCollaborations.getScene().getWindow();
        stage.close();
        try {
            DetailMyCollaborationStage detailCollaborationStage = new DetailMyCollaborationStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            log.error(ioException);
        }
    }
    
    @FXML
    private void previousMenu() {
        Stage stage = (Stage) this.tblViewConcludedCollaborations.getScene().getWindow();
        stage.close();
        try {
            MyCollaborationsStage myCollaborationsStage = new MyCollaborationsStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            log.error(ioException);
        }
    }
}
