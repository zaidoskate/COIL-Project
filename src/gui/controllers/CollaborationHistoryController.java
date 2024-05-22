package gui.controllers;

import gui.Alerts;
import gui.SessionManager;
import gui.stages.DetailMyCollaborationStage;
import gui.stages.MyCollaborationsStage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logic.DAOs.CollaborationDAO;
import logic.DAOs.ProfessorBelongsToCollaborationDAO;
import logic.DAOs.ProfessorDAO;
import logic.LogicException;
import logic.domain.Collaboration;
import logic.domain.ProfessorBelongsToCollaboration;
import logic.model.CollaborationInformation;
import org.apache.log4j.Logger;

public class CollaborationHistoryController implements Initializable {
    
    @FXML
    private TableView<CollaborationInformation> tblViewConcludedCollaborations;
    
    @FXML
    private TableColumn<CollaborationInformation, String> clmCollaborationName;
    
    @FXML
    private TableColumn<CollaborationInformation, String> clmMirrorProfessorUniversity;
    
    private ObservableList<CollaborationInformation> collaborationsToDisplay;
    
    private static final ProfessorBelongsToCollaborationDAO PROFESSOR_BELONGS_TO_COLLABORATION_DAO = new ProfessorBelongsToCollaborationDAO();
    private static final CollaborationDAO COLLABORATION_DAO = new CollaborationDAO();
    private static final ProfessorDAO PROFESSOR_DAO = new ProfessorDAO();
    
    private static final SessionManager CURRENT_SESSION = SessionManager.getInstance();
    private static final CollaborationInformation SELECTED_COLLABORATION = CollaborationInformation.getCollaboration();
    
    private static final Logger LOG = Logger.getLogger(CollaborationHistoryController.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeTable();
        setCollaborationHistory();
        checkEmptyTable();
    }
    
    private void initializeTable() {
        collaborationsToDisplay = FXCollections.observableArrayList();
        this.clmCollaborationName.setCellValueFactory(new PropertyValueFactory("collaborationName"));
        this.clmMirrorProfessorUniversity.setCellValueFactory(new PropertyValueFactory("mirrorProfessorUniversity"));
    }
    
    private void setCollaborationHistory() {
        try {
            ArrayList<ProfessorBelongsToCollaboration> concludedProfessorBelongs = PROFESSOR_BELONGS_TO_COLLABORATION_DAO.getConcludedCollaborationsByIdUser(CURRENT_SESSION.getUserData().getIdUser());
            ArrayList<Collaboration> concludedCollaborations = COLLABORATION_DAO.getProfessorConcludedCollaborations(concludedProfessorBelongs);
            for(ProfessorBelongsToCollaboration professorBelongsCollaboration : concludedProfessorBelongs) {
                for(Collaboration collaboration : concludedCollaborations) {
                    if(professorBelongsCollaboration.getIdColaboration() == collaboration.getIdColaboration()) {
                        CollaborationInformation collaborationInfo = new CollaborationInformation();
                        collaborationInfo.setCollaborationName(collaboration.getColaborationName());
                        collaborationInfo.setCloseDate(collaboration.getEndDate());
                        collaborationInfo.setStartDate(collaboration.getStartDate());
                        collaborationInfo.setLanguage(collaboration.getLanguage());
                        collaborationInfo.setTopicsOfInterest(collaboration.getInterestTopic());
                        collaborationInfo.setCollaborationStatus(professorBelongsCollaboration.getColaborationStatus());
                        collaborationInfo.setIdCollaboration(collaboration.getIdColaboration());
                        collaborationInfo.setIdUser(professorBelongsCollaboration.getIdUser());
                        collaborationInfo.setIdMirrorUser(professorBelongsCollaboration.getIdUserMirrorClass());

                        ArrayList<String> universityInfo = PROFESSOR_DAO.getUniversityFromAProfessor(professorBelongsCollaboration.getIdUserMirrorClass());
                        String universityName = universityInfo.get(0);
                        String universityCountry = universityInfo.get(1);
                        String mirrorProfessorUniversity = universityName + " (" + universityCountry + ")";
                        collaborationInfo.setMirrorProfessorUniversity(mirrorProfessorUniversity);

                        collaborationsToDisplay.add(collaborationInfo);
                        this.tblViewConcludedCollaborations.setItems(collaborationsToDisplay);
                    }
                }
            }
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            LOG.error(logicException);
        }
    }
    
    private void checkEmptyTable() {
        if(this.collaborationsToDisplay.isEmpty()) {
            this.tblViewConcludedCollaborations.setPlaceholder(new Label("Aún no has hecho una colaboración"));
        }
    }
    
    private void setSelectedCollaboration() {
        SELECTED_COLLABORATION.setCollaborationName(this.tblViewConcludedCollaborations.getSelectionModel().getSelectedItem().getCollaborationName());
        SELECTED_COLLABORATION.setCloseDate(this.tblViewConcludedCollaborations.getSelectionModel().getSelectedItem().getCloseDate());
        SELECTED_COLLABORATION.setStartDate(this.tblViewConcludedCollaborations.getSelectionModel().getSelectedItem().getStartDate());
        SELECTED_COLLABORATION.setLanguage(this.tblViewConcludedCollaborations.getSelectionModel().getSelectedItem().getLanguage());
        SELECTED_COLLABORATION.setTopicsOfInterest(this.tblViewConcludedCollaborations.getSelectionModel().getSelectedItem().getLanguage());
        SELECTED_COLLABORATION.setCollaborationStatus(this.tblViewConcludedCollaborations.getSelectionModel().getSelectedItem().getCollaborationStatus());
        SELECTED_COLLABORATION.setMirrorProfessorUniversity(this.tblViewConcludedCollaborations.getSelectionModel().getSelectedItem().getMirrorProfessorUniversity());
        SELECTED_COLLABORATION.setIdCollaboration(this.tblViewConcludedCollaborations.getSelectionModel().getSelectedItem().getIdCollaboration());
        SELECTED_COLLABORATION.setIdUser(this.tblViewConcludedCollaborations.getSelectionModel().getSelectedItem().getIdUser());
        SELECTED_COLLABORATION.setIdMirrorUser(this.tblViewConcludedCollaborations.getSelectionModel().getSelectedItem().getIdMirrorUser());
    }
    
    @FXML
    private void displayDetailMyCollaboration() {
        if(this.tblViewConcludedCollaborations.getSelectionModel().getSelectedItem() != null) {
            setSelectedCollaboration();
            Stage stage = (Stage) this.tblViewConcludedCollaborations.getScene().getWindow();
            stage.close();
            try {
                DetailMyCollaborationStage detailCollaborationStage = new DetailMyCollaborationStage();
            } catch(IOException ioException) {
                Alerts.displayAlertIOException();
                LOG.error(ioException);
            }
        } else {
            Alerts.showWarningAlert("Selecciona una colaboración para ver su detalle");
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
            LOG.error(ioException);
        }
    }
}
