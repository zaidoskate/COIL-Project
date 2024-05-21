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
public class CollaborationsInConclusionController implements Initializable {
    
    @FXML
    private TableView<CollaborationInformation> tblViewPendingCollaborations;
    
    @FXML
    private TableColumn<CollaborationInformation, String> clmCollaborationName;
    
    @FXML
    private TableColumn<CollaborationInformation, String> clmProfessorUniversity;
    
    @FXML
    private TableColumn<CollaborationInformation, String> clmMirrorProfessorUniversity;
    
    private ObservableList<CollaborationInformation> collaborationsToDisplay;
    
    private static final ProfessorBelongsToCollaborationDAO professorBelongsToCollaborationDAO = new ProfessorBelongsToCollaborationDAO();
    private static final CollaborationDAO collaborationDAO = new CollaborationDAO();
    private static final ProfessorDAO professorDAO = new ProfessorDAO();
    
    private static final CollaborationInformation selectedCollaboration = CollaborationInformation.getCollaboration();
    private static final Logger log = Logger.getLogger(CollaborationsInConclusionController.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeTable();
        setCollaborationsInConclusion();
        checkEmptyTable();
    }
    
    private void initializeTable() {
        collaborationsToDisplay = FXCollections.observableArrayList();
        this.clmCollaborationName.setCellValueFactory(new PropertyValueFactory("collaborationName"));
        this.clmProfessorUniversity.setCellValueFactory(new PropertyValueFactory("professorUniversity"));
        this.clmMirrorProfessorUniversity.setCellValueFactory(new PropertyValueFactory("mirrorProfessorUniversity"));
    }
    
    private void setCollaborationsInConclusion() {
        try {
            ArrayList<ProfessorBelongsToCollaboration> onHoldProfessorBelongs = professorBelongsToCollaborationDAO.getOnHoldCollaborations();
            ArrayList<Collaboration> onHoldCollaborations = collaborationDAO.getProfessorConcludedCollaborations(onHoldProfessorBelongs);

            for (ProfessorBelongsToCollaboration professorBelongsCollaboration : onHoldProfessorBelongs) {
                CollaborationInformation collaborationInfo = createCollaborationInformation(professorBelongsCollaboration, onHoldCollaborations);
                if (collaborationInfo != null) {
                    collaborationsToDisplay.add(collaborationInfo);
                }
            }

            this.tblViewPendingCollaborations.setItems(collaborationsToDisplay);
        } catch (LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            log.error(logicException);
        }
    }

    private CollaborationInformation createCollaborationInformation(ProfessorBelongsToCollaboration professorBelongsCollaboration, ArrayList<Collaboration> onHoldCollaborations) throws LogicException {
        for (Collaboration collaboration : onHoldCollaborations) {
            if (professorBelongsCollaboration.getIdColaboration() == collaboration.getIdColaboration()) {
                CollaborationInformation collaborationInfo = new CollaborationInformation();
                setBasicCollaborationInfo(collaborationInfo, collaboration, professorBelongsCollaboration);
                setUniversityInfo(collaborationInfo, professorBelongsCollaboration);
                return collaborationInfo;
            }
        }
        return null;
    }

    private void setBasicCollaborationInfo(CollaborationInformation collaborationInfo, Collaboration collaboration, ProfessorBelongsToCollaboration professorBelongsCollaboration) {
        collaborationInfo.setCollaborationName(collaboration.getColaborationName());
        collaborationInfo.setCloseDate(collaboration.getEndDate());
        collaborationInfo.setStartDate(collaboration.getStartDate());
        collaborationInfo.setLanguage(collaboration.getLanguage());
        collaborationInfo.setTopicsOfInterest(collaboration.getInterestTopic());
        collaborationInfo.setCollaborationStatus(professorBelongsCollaboration.getColaborationStatus());
        collaborationInfo.setIdCollaboration(collaboration.getIdColaboration());
        collaborationInfo.setIdUser(professorBelongsCollaboration.getIdUser());
        collaborationInfo.setIdMirrorUser(professorBelongsCollaboration.getIdUserMirrorClass());
    }

    private void setUniversityInfo(CollaborationInformation collaborationInfo, ProfessorBelongsToCollaboration professorBelongsCollaboration) throws LogicException {
        String professorUniversity = getUniversityName(professorBelongsCollaboration.getIdUser());
        collaborationInfo.setProfessorUniversity(professorUniversity);

        String mirrorProfessorUniversity = getUniversityName(professorBelongsCollaboration.getIdUserMirrorClass());
        collaborationInfo.setMirrorProfessorUniversity(mirrorProfessorUniversity);
    }

    private String getUniversityName(int userId) throws LogicException {
        ArrayList<String> universityInfo = professorDAO.getUniversityFromAProfessor(userId);
        String universityName = universityInfo.get(0);
        String universityCountry = universityInfo.get(1);
        return universityName + " (" + universityCountry + ")";
    }

    private void checkEmptyTable() {
        if(this.collaborationsToDisplay.isEmpty()) {
            this.tblViewPendingCollaborations.setPlaceholder(new Label("No hay colaboraciones por concluir"));
        }
    }
    
    private void setSelectedCollaboration() {
        selectedCollaboration.setCollaborationName(this.tblViewPendingCollaborations.getSelectionModel().getSelectedItem().getCollaborationName());
        selectedCollaboration.setCloseDate(this.tblViewPendingCollaborations.getSelectionModel().getSelectedItem().getCloseDate());
        selectedCollaboration.setStartDate(this.tblViewPendingCollaborations.getSelectionModel().getSelectedItem().getStartDate());
        selectedCollaboration.setLanguage(this.tblViewPendingCollaborations.getSelectionModel().getSelectedItem().getLanguage());
        selectedCollaboration.setTopicsOfInterest(this.tblViewPendingCollaborations.getSelectionModel().getSelectedItem().getLanguage());
        selectedCollaboration.setCollaborationStatus(this.tblViewPendingCollaborations.getSelectionModel().getSelectedItem().getCollaborationStatus());
        selectedCollaboration.setProfessorUniversity(this.tblViewPendingCollaborations.getSelectionModel().getSelectedItem().getProfessorUniversity());
        selectedCollaboration.setMirrorProfessorUniversity(this.tblViewPendingCollaborations.getSelectionModel().getSelectedItem().getMirrorProfessorUniversity());
        selectedCollaboration.setIdCollaboration(this.tblViewPendingCollaborations.getSelectionModel().getSelectedItem().getIdCollaboration());
        selectedCollaboration.setIdUser(this.tblViewPendingCollaborations.getSelectionModel().getSelectedItem().getIdUser());
        selectedCollaboration.setIdMirrorUser(this.tblViewPendingCollaborations.getSelectionModel().getSelectedItem().getIdMirrorUser());
    }
    
    @FXML
    private void displayReviewConclusionCollaboration() {
        if(this.tblViewPendingCollaborations.getSelectionModel().getSelectedItem() != null) {
            setSelectedCollaboration();
            Stage stage = (Stage) this.tblViewPendingCollaborations.getScene().getWindow();
            stage.close();
            try {
                ReviewConclusionCollaborationStage reviewConclusionStage = new ReviewConclusionCollaborationStage();
            } catch (IOException ioException) {
                Alerts.displayAlertIOException();
                log.error(ioException);
            }
        } else {
            Alerts.showWarningAlert("Seleccione una colaboración para evaluarla");
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
