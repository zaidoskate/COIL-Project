/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.controllers;

import gui.Alerts;
import gui.stages.CollaborationsCoordinatorMenuStage;
import gui.stages.ReviewConclusionCollaborationStage;
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
public class CollaborationsInConclusionController implements Initializable {
    
    @FXML
    private TableView tblViewPendingCollaborations;
    
    private static final Logger log = Logger.getLogger(CollaborationsInConclusionController.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void displayReviewConclusionCollaboration() {
        Stage stage = (Stage) this.tblViewPendingCollaborations.getScene().getWindow();
        stage.close();
        try {
            ReviewConclusionCollaborationStage reviewConclusionStage = new ReviewConclusionCollaborationStage();
        } catch (IOException ioException) {
            Alerts.displayAlertIOException();
            ioException.printStackTrace();
            //log.error(ioException);
        }
    }
    
    @FXML
    private void previousMenu() {
        Stage stage = (Stage) this.tblViewPendingCollaborations.getScene().getWindow();
        stage.close();
        try {
            CollaborationsCoordinatorMenuStage collaborationsCoordinatorStage = new CollaborationsCoordinatorMenuStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            log.error(ioException);
        }
    }
}
