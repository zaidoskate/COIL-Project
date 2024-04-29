/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.controllers;

import gui.SessionManager;
import gui.stages.MyOffersStage;
import gui.stages.ProfessorDetailStage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logic.DAOs.CollaborationOfferCandidateDAO;
import logic.DAOs.ProfessorDAO;
import logic.DAOs.UserDAO;
import logic.LogicException;
import logic.domain.CollaborationOfferCandidate;
import logic.domain.User;
import logic.model.OfferInformation;

/**
 *
 * @author zaido
 */
public class CandidatesController implements Initializable {

    @FXML
    private TableView<OfferInformation> tblCandidates;
    
    @FXML
    private TableColumn<OfferInformation, String> clmProfessorName;
    
    @FXML
    private TableColumn<OfferInformation, String> clmUniversity;
    
    @FXML
    private Button btnDetail;
    
    @FXML
    private Button btnBack;
    
    OfferInformation professorOffer = OfferInformation.getOffer();
    SessionManager currentSession = SessionManager.getInstance();
    
    private ObservableList<OfferInformation> candidates;
    private CollaborationOfferCandidateDAO collaborationOfferCandidateDAO = new CollaborationOfferCandidateDAO();
    private UserDAO userDAO = new UserDAO();
    private ProfessorDAO professorDAO = new ProfessorDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeTable();
        setOfferCandidates();
        checkEmptyTable();
    }
    
    private void initializeTable() {
        candidates = FXCollections.observableArrayList();
        this.clmProfessorName.setCellValueFactory(new PropertyValueFactory("professorName"));
        this.clmUniversity.setCellValueFactory(new PropertyValueFactory("universityName"));
    }
    
    private void setOfferCandidates() {
        try {
            ArrayList<CollaborationOfferCandidate> candidatesObtained = collaborationOfferCandidateDAO.GetCollaborationOfferCandidateByIdCollaborationOffer(professorOffer.getIdOfferCollaboration());
            if(candidatesObtained != null) {
                for(CollaborationOfferCandidate candidate : candidatesObtained) {
                    User candidateUser = userDAO.getUserById(candidate.getIdUser());
                    String professorName = candidateUser.getName() + " " + candidateUser.getLastName();
                    String professorEmail = candidateUser.getEmail();
                    String universityName = professorDAO.getUniversityFromAProfessor(candidate.getIdUser()).getFirst();
                    String universityLocation = professorDAO.getUniversityFromAProfessor(candidate.getIdUser()).get(1);
                    
                    OfferInformation candidateRow = new OfferInformation();
                    candidateRow.setProfessorName(professorName);
                    candidateRow.setProfessorEmail(professorEmail);
                    candidateRow.setUniversityName(universityName);
                    candidateRow.setUniversityLocation(universityLocation);
                    this.candidates.add(candidateRow);
                    this.tblCandidates.setItems(candidates);
                }
            }
        } catch(LogicException logicException) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setTitle("Error");
            alert.setContentText(logicException.getMessage());
            alert.showAndWait();
        }
    }
    
    private void setSelectedCandidate() {
        professorOffer.setProfessorName(this.tblCandidates.getSelectionModel().getSelectedItem().getProfessorName());
        professorOffer.setProfessorEmail(this.tblCandidates.getSelectionModel().getSelectedItem().getProfessorEmail());
        professorOffer.setUniversityName(this.tblCandidates.getSelectionModel().getSelectedItem().getUniversityName());
        professorOffer.setUniversityLocation(this.tblCandidates.getSelectionModel().getSelectedItem().getUniversityLocation());
    }
    
    private void checkEmptyTable() {
        if(candidates.isEmpty()) {
            this.tblCandidates.setPlaceholder(new Label ("Aún no hay candidatos para su oferta"));
        }
    }
    
    @FXML
    public void previousMenu() {
        Stage stage = (Stage) this.btnBack.getScene().getWindow();
        stage.close();
        try {
            MyOffersStage myOffersStage = new MyOffersStage();
        } catch(IOException ioException) {
            
        }
    }
    
    @FXML
    public void showProfessorDetail() {
        if(this.tblCandidates.getSelectionModel().getSelectedItem() != null) {
            setSelectedCandidate();
            Stage stage = (Stage) this.btnDetail.getScene().getWindow();
            stage.close();
            try {
                ProfessorDetailStage professorDetailStage = new ProfessorDetailStage();
            } catch(IOException ioException) {
                ioException.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Advertencia");
            alert.setTitle("Advertencia");
            alert.setContentText("Seleccione un candidato para poder ver su detalle");
            alert.showAndWait();
        }
    }
    
}
