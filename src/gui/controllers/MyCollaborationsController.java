/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.controllers;

import gui.Alerts;
import gui.SessionManager;
import gui.stages.CollaborationStage;
import gui.stages.StartCollaborationStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.DAOs.CollaborationDAO;
import logic.DAOs.ProfessorBelongsToCollaborationDAO;
import logic.LogicException;
import logic.domain.Collaboration;
import logic.domain.ProfessorBelongsToCollaboration;
import logic.model.CollaborationInformation;
import org.apache.log4j.Logger;

/**
 *
 * @author zaido
 */
public class MyCollaborationsController implements Initializable {
    
    @FXML
    private Label lblCollaborationName;
    
    @FXML
    private Label lblCollaborationStatus;
    
    @FXML
    private AnchorPane collaborationAvailablePane;
    
    @FXML
    private AnchorPane noCollaborationAvailablePane;
    
    @FXML
    private Button btnStartCollaboration;
    
    @FXML
    private Button btnConcludeCollaboration;
    
    @FXML
    private Button btnUploadEvidence;
    
    private static final ProfessorBelongsToCollaborationDAO professorBelongsToCollaborationDAO = new ProfessorBelongsToCollaborationDAO();
    private static final CollaborationDAO collaborationDAO = new CollaborationDAO();
    
    private static final SessionManager currentSession = SessionManager.getInstance();
    private static final CollaborationInformation currentCollaboration = CollaborationInformation.getCollaboration();
    
    private static final Logger log = Logger.getLogger(MyCollaborationsController.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setProfessorCollaboration();
        if(currentCollaboration.getIdUser() == currentSession.getUserData().getIdUser()) {
            showCollaborationAvailable();
        } else {
            showNoCollaborationAvailable();
        }
    }
    
    private void setProfessorCollaboration() {
        try {
            ProfessorBelongsToCollaboration professorsBelong = professorBelongsToCollaborationDAO.getProfessorPendingCollaboration(currentSession.getUserData().getIdUser());
            Collaboration collaborationObtained = collaborationDAO.getColaborationById(professorsBelong.getIdColaboration());
            currentCollaboration.setCollaborationStatus(professorsBelong.getColaborationStatus());
            currentCollaboration.setIdUser(professorsBelong.getIdUser());
            currentCollaboration.setIdMirrorUser(professorsBelong.getIdUserMirrorClass());
            currentCollaboration.setIdCollaboration(collaborationObtained.getIdColaboration());
            currentCollaboration.setCollaborationName(collaborationObtained.getColaborationName());
            currentCollaboration.setTopicsOfInterest(collaborationObtained.getInterestTopic());
            currentCollaboration.setLanguage(collaborationObtained.getLanguage());
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            log.error(logicException);
        }
    }
    
    private void showCollaborationAvailable() {
        this.lblCollaborationName.setText(currentCollaboration.getCollaborationName());
        this.lblCollaborationStatus.setText(currentCollaboration.getCollaborationStatus());
        this.collaborationAvailablePane.setVisible(true);
        if(currentCollaboration.getCollaborationStatus().equals("Iniciada")) {
            this.btnStartCollaboration.setVisible(false);
            this.btnConcludeCollaboration.setVisible(true);
            this.btnUploadEvidence.setVisible(true);
        }
    }
    
    private void showNoCollaborationAvailable() {
        this.noCollaborationAvailablePane.setVisible(true);
    }
    
    @FXML
    private void previousMenu() {
        Stage stage = (Stage) this.lblCollaborationName.getScene().getWindow();
        stage.close();
        try {
            CollaborationStage collaborationStage = new CollaborationStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            log.error(ioException);
        }
    }
    
    @FXML
    private void displayStartCollaboration() {
        Stage stage = (Stage) this.lblCollaborationName.getScene().getWindow();
        stage.close();
        try {
            StartCollaborationStage startCollaborationStage = new StartCollaborationStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            log.error(ioException);
        }
    }
    
    @FXML
    private void displayConcludeCollaboration() {
        
    }
    
    @FXML
    private void displayUploadEvidence() {
        
    }
    
    @FXML
    private void displayCollaborationHistory() {
        
    }
}
