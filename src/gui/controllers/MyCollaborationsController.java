package gui.controllers;

import gui.Alerts;
import gui.SessionManager;
import gui.stages.CollaborationHistoryStage;
import gui.stages.CollaborationStage;
import gui.stages.CollaborationConclusionStage;
import gui.stages.StartCollaborationStage;
import gui.stages.EvidenceUploaderStage;
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
    
    private static final ProfessorBelongsToCollaborationDAO PROFESSOR_BELONGS_TO_COLLABORATION_DAO = new ProfessorBelongsToCollaborationDAO();
    private static final CollaborationDAO COLLABORATION_DAO = new CollaborationDAO();
    
    private static final SessionManager CURRENT_SESSION = SessionManager.getInstance();
    private static final CollaborationInformation CURRENT_COLLABORATION = CollaborationInformation.getCollaboration();
    
    private static final Logger LOG = Logger.getLogger(MyCollaborationsController.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setProfessorCollaboration();
        if (CURRENT_COLLABORATION.getIdUser() == CURRENT_SESSION.getUserData().getIdUser()) {
            showCollaborationAvailable();
        } else {
            showNoCollaborationAvailable();
        }
    }
    
    private void setProfessorCollaboration() {
        try {
            ProfessorBelongsToCollaboration professorsBelong = PROFESSOR_BELONGS_TO_COLLABORATION_DAO.getProfessorPendingCollaboration(CURRENT_SESSION.getUserData().getIdUser());
            Collaboration collaborationObtained = COLLABORATION_DAO.getColaborationById(professorsBelong.getIdColaboration());
            CURRENT_COLLABORATION.setCollaborationStatus(professorsBelong.getColaborationStatus());
            CURRENT_COLLABORATION.setIdUser(professorsBelong.getIdUser());
            CURRENT_COLLABORATION.setIdMirrorUser(professorsBelong.getIdUserMirrorClass());
            CURRENT_COLLABORATION.setIdCollaboration(collaborationObtained.getIdColaboration());
            CURRENT_COLLABORATION.setCollaborationName(collaborationObtained.getColaborationName());
            CURRENT_COLLABORATION.setTopicsOfInterest(collaborationObtained.getInterestTopic());
            CURRENT_COLLABORATION.setLanguage(collaborationObtained.getLanguage());
        } catch (LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            LOG.error(logicException);
        }
    }
    
    private void showCollaborationAvailable() {
        this.lblCollaborationName.setText(CURRENT_COLLABORATION.getCollaborationName());
        this.lblCollaborationStatus.setText(CURRENT_COLLABORATION.getCollaborationStatus());
        this.collaborationAvailablePane.setVisible(true);
        if(CURRENT_COLLABORATION.getCollaborationStatus().equals("Iniciada")) {
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
        } catch (IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
    
    @FXML
    private void displayStartCollaboration() {
        Stage stage = (Stage) this.lblCollaborationName.getScene().getWindow();
        stage.close();
        try {
            StartCollaborationStage startCollaborationStage = new StartCollaborationStage();
        } catch (IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
    
    @FXML
    private void displayConcludeCollaboration() {
        Stage stage = (Stage) this.btnConcludeCollaboration.getScene().getWindow();
        stage.close();
        try {
            CollaborationConclusionStage concludeCollaborationStage = new CollaborationConclusionStage();
        } catch (IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
    
    @FXML
    private void displayUploadEvidence() {
        try {
            EvidenceUploaderStage uploadEvidencesStage = new EvidenceUploaderStage();
        } catch (IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
    
    @FXML
    private void displayCollaborationHistory() {
        Stage stage = (Stage) this.lblCollaborationName.getScene().getWindow();
        stage.close();
        try {
            CollaborationHistoryStage collaborationHistoryStage = new CollaborationHistoryStage();
        } catch (IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
}
