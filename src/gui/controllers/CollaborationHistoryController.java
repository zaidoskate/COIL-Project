/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author zaido
 */
public class CollaborationHistoryController implements Initializable {
    
    @FXML
    private TableView<CollaborationInformation> tblViewConcludedCollaborations;
    
    @FXML
    private TableColumn<CollaborationInformation, String> clmCollaborationName;
    
    @FXML
    private TableColumn<CollaborationInformation, String> clmMirrorProfessorUniversity;
    
    private ObservableList<CollaborationInformation> collaborationsToDisplay;
    
    private static final ProfessorBelongsToCollaborationDAO professorBelongsToCollaborationDAO = new ProfessorBelongsToCollaborationDAO();
    private static final CollaborationDAO collaborationDAO = new CollaborationDAO();
    private static final ProfessorDAO professorDAO = new ProfessorDAO();
    
    private static final SessionManager currentSession = SessionManager.getInstance();
    private static final CollaborationInformation selectedCollaboration = CollaborationInformation.getCollaboration();
    
    private static final Logger log = Logger.getLogger(CollaborationHistoryController.class);

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
            ArrayList<ProfessorBelongsToCollaboration> concludedProfessorBelongs = professorBelongsToCollaborationDAO.getConcludedCollaborationsByIdUser(currentSession.getUserData().getIdUser());
            ArrayList<Collaboration> concludedCollaborations = collaborationDAO.getProfessorConcludedCollaborations(concludedProfessorBelongs);
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

                        ArrayList<String> universityInfo = professorDAO.getUniversityFromAProfessor(professorBelongsCollaboration.getIdUserMirrorClass());
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
            log.error(logicException);
        }
    }
    
    private void checkEmptyTable() {
        if(this.collaborationsToDisplay.isEmpty()) {
            this.tblViewConcludedCollaborations.setPlaceholder(new Label("Aún no has hecho una colaboración"));
        }
    }
    
    private void setSelectedCollaboration() {
        selectedCollaboration.setCollaborationName(this.tblViewConcludedCollaborations.getSelectionModel().getSelectedItem().getCollaborationName());
        selectedCollaboration.setCloseDate(this.tblViewConcludedCollaborations.getSelectionModel().getSelectedItem().getCloseDate());
        selectedCollaboration.setStartDate(this.tblViewConcludedCollaborations.getSelectionModel().getSelectedItem().getStartDate());
        selectedCollaboration.setLanguage(this.tblViewConcludedCollaborations.getSelectionModel().getSelectedItem().getLanguage());
        selectedCollaboration.setTopicsOfInterest(this.tblViewConcludedCollaborations.getSelectionModel().getSelectedItem().getLanguage());
        selectedCollaboration.setCollaborationStatus(this.tblViewConcludedCollaborations.getSelectionModel().getSelectedItem().getCollaborationStatus());
        selectedCollaboration.setMirrorProfessorUniversity(this.tblViewConcludedCollaborations.getSelectionModel().getSelectedItem().getMirrorProfessorUniversity());
        selectedCollaboration.setIdCollaboration(this.tblViewConcludedCollaborations.getSelectionModel().getSelectedItem().getIdCollaboration());
        selectedCollaboration.setIdUser(this.tblViewConcludedCollaborations.getSelectionModel().getSelectedItem().getIdUser());
        selectedCollaboration.setIdMirrorUser(this.tblViewConcludedCollaborations.getSelectionModel().getSelectedItem().getIdMirrorUser());
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
                log.error(ioException);
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
            log.error(ioException);
        }
    }
}
