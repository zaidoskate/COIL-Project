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
    
    private final ProfessorBelongsToCollaborationDAO professorBelongsToCollaborationDAO = new ProfessorBelongsToCollaborationDAO();
    private final CollaborationDAO collaborationDAO = new CollaborationDAO();
    
    private SessionManager currentSession = SessionManager.getInstance();
    private CollaborationInformation currentCollaboration = CollaborationInformation.getCollaboration();

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
        }
    }
    
    private void showCollaborationAvailable() {
        this.lblCollaborationName.setText(currentCollaboration.getCollaborationName());
        this.lblCollaborationStatus.setText(currentCollaboration.getCollaborationStatus());
        this.collaborationAvailablePane.setVisible(true);
        if(currentCollaboration.getCollaborationStatus().equals("Iniciada")) {
            this.btnStartCollaboration.setVisible(false);
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
        }
    }
    
}
