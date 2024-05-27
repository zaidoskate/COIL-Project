package gui.controllers;

import gui.Alerts;
import gui.stages.EvidenceListStage;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.DAOs.CollaborationDAO;
import logic.DAOs.ConcludedColaborationDAO;
import logic.DAOs.ProfessorBelongsToCollaborationDAO;
import logic.DAOs.ProfessorDAO;
import logic.LogicException;
import logic.domain.Collaboration;
import logic.domain.ConcludedCollaboration;
import logic.domain.User;
import logic.model.ConcludedCollaborationInformation;
import logic.model.EvidenceListInformation;
import org.apache.log4j.Logger;

public class DetailCollaborationController {
    @FXML
    private Button btnBack;
    @FXML
    private Label professorName;
    @FXML
    private Label universityProfessor;
    @FXML
    private TextArea topicsOfInterest;
    @FXML
    private TextArea conclusion;
    @FXML
    private HBox hBoxRating;
    @FXML
    private Label mirrorProfessorName;
    @FXML
    private Label mirrorUniversity;
    @FXML
    private ComboBox<String> cmbBoxVisibility;
    @FXML
    private Button btnDownloadEvidences;
    @FXML
    private AnchorPane anchorPaneChangeVisibility;
    
    private static final Logger LOG = Logger.getLogger(CollaborationController.class);
    private ConcludedCollaborationInformation CURRENT_CONCLUDED_COLLABORATION = ConcludedCollaborationInformation.getInstance();
    private static final ProfessorDAO PROFESSOR_DAO = new ProfessorDAO();
    private static final ProfessorBelongsToCollaborationDAO PROFESSOR_BELONGS_DAO = new ProfessorBelongsToCollaborationDAO();
    private static final ConcludedColaborationDAO CONCLUDED_COLLABORATION_DAO = new ConcludedColaborationDAO();
    private static final CollaborationDAO COLLABORATION_DAO = new CollaborationDAO();
    
    @FXML
    public void initialize() {
        anchorPaneChangeVisibility.setVisible(CURRENT_CONCLUDED_COLLABORATION.getChangeVisibility());
        btnDownloadEvidences.setVisible(CURRENT_CONCLUDED_COLLABORATION.getDownloadEvidences());
        loadData();
    }
    
    private void loadData() {
        int idCollaboration = CURRENT_CONCLUDED_COLLABORATION.getIdCollaboration();
        try {
            loadProfessorsData(idCollaboration);
            loadCollaborationData(idCollaboration);
        } catch(LogicException logicException) {
            LOG.error(logicException);
            Alerts.displayAlertLogicException(logicException);
            previousMenu();
        }
    }
    private void loadProfessorsData(int idCollaboration) throws LogicException {
        ArrayList<User> professorsData = PROFESSOR_BELONGS_DAO.getProfessorsDataByCollaboration(idCollaboration);
        User professor = professorsData.get(0);
        ArrayList<String> university = PROFESSOR_DAO.getUniversityFromAProfessor(professor.getIdUser());
        professorName.setText(professor.getName());
        universityProfessor.setText(university.get(0));
            
        professor = professorsData.get(1);
        university = PROFESSOR_DAO.getUniversityFromAProfessor(professor.getIdUser());
        mirrorProfessorName.setText(professor.getName());
        mirrorUniversity.setText(university.get(0));
    }
    private void loadCollaborationData(int idCollaboration) throws LogicException {
        Collaboration collaboration = COLLABORATION_DAO.getColaborationById(idCollaboration);
        ConcludedCollaboration concludedCollaboration = CONCLUDED_COLLABORATION_DAO.getConcludedCollaborationById(idCollaboration);
        
        topicsOfInterest.setText(collaboration.getInterestTopic());
        conclusion.setText(concludedCollaboration.getConclusion());
        
        loadStars(concludedCollaboration.getRating());
        loadComboBox(concludedCollaboration.getVisibility());
    }
    private void loadStars(int rating) throws LogicException {
        String imagePath = "gui/images/star.png";
        for (int i = 1; i <= rating; i++) {
            Image image = new Image(imagePath);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(40); 
            imageView.setFitHeight(40); 
            hBoxRating.getChildren().add(imageView);
        }
    }
    private void loadComboBox(String visibility) throws LogicException {
        cmbBoxVisibility.getItems().addAll("Visible", "Invisible");
        if(visibility.equals("Visible")) {
            cmbBoxVisibility.getSelectionModel().select(0);
        } else {
            cmbBoxVisibility.getSelectionModel().select(1);
        }
    }
    

    @FXML
    void previousMenu() {
        Stage stage = (Stage) btnDownloadEvidences.getScene().getWindow();
        stage.close();
    }

    @FXML
    void saveVisibility() {
        ConcludedCollaboration concludedCollaboration = new ConcludedCollaboration();
        String visibilitySelected = cmbBoxVisibility.getSelectionModel().getSelectedItem();
        concludedCollaboration.setVisibility(visibilitySelected);
        concludedCollaboration.setIdColaboration(CURRENT_CONCLUDED_COLLABORATION.getIdCollaboration());
        try {
            CONCLUDED_COLLABORATION_DAO.updateVisibility(concludedCollaboration);
            Alerts.showInformationAlert("Exito", "Se ha modificado la visibilidad.");
        } catch(LogicException logicException) {
            LOG.error(logicException);
            Alerts.displayAlertLogicException(logicException);
        }
    }

    @FXML
    void downloadEvidences() {
        EvidenceListInformation.getInstance().setAllFolders(true);
        EvidenceListInformation.getInstance().setIdCollaboration(CURRENT_CONCLUDED_COLLABORATION.getIdCollaboration());
        try{
            EvidenceListStage evidenceListStage = new EvidenceListStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }

}