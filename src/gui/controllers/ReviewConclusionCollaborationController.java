/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.controllers;

import gui.Alerts;
import gui.stages.CollaborationsInConclusionStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

/**
 *
 * @author zaido
 */
public class ReviewConclusionCollaborationController implements Initializable {
    
    @FXML
    private Label lblProfessorName;
    
    @FXML
    private Label lblProfessorEmail;
    
    @FXML
    private Label lblMirrorProfessorName;
    
    @FXML
    private Label lblMirrorProfessorEmail;
    
    @FXML
    private ComboBox comboBoxFeedbackType;
    
    @FXML
    private TableView tblViewEvidence;
    
    @FXML
    private TableColumn tblColumnEvidence;
    
    private static final Logger log = Logger.getLogger(ReviewConclusionCollaborationController.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void downloadFeedback() {
        
    }
    
    @FXML
    private void downloadEvidence() {
        
    }
    
    @FXML
    private void approveConclusion() {
        
    }
    
    @FXML
    private void declineConclusion() {
        
    }
    
    @FXML
    private void previousMenu() {
        Stage stage = (Stage) this.tblViewEvidence.getScene().getWindow();
        stage.close();
        try {
            CollaborationsInConclusionStage collaborationsInConclusionStage = new CollaborationsInConclusionStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            log.error(ioException);
        }
    }
}
