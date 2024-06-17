package gui.controllers;

import gui.Alerts;
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
import logic.model.CandidateInformation;
import logic.model.OfferInformation;
import org.apache.log4j.Logger;


public class CandidatesController implements Initializable {

    @FXML
    private TableView<OfferInformation> tblViewCandidates;
    
    @FXML
    private TableColumn<OfferInformation, String> clmProfessorName;
    
    @FXML
    private TableColumn<OfferInformation, String> clmUniversity;
    
    @FXML
    private Button btnDetail;
    
    @FXML
    private Button btnBack;
    
    private ObservableList<OfferInformation> candidates;
    
    private static final OfferInformation PROFESSOR_OFFER = OfferInformation.getOffer();
    private static final CandidateInformation CANDIDATE_INFORMATION = CandidateInformation.getCandidateInformation();
    private static final SessionManager CURRENT_SESSION = SessionManager.getInstance();
    
    private static final CollaborationOfferCandidateDAO COLLABORATION_OFFER_CANDIDATE_DAO = new CollaborationOfferCandidateDAO();
    private static final UserDAO USER_DAO = new UserDAO();
    private static final ProfessorDAO PROFESSOR_DAO = new ProfessorDAO();
    
    private static final Logger LOG = Logger.getLogger(CandidatesController.class);
    
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
            ArrayList<CollaborationOfferCandidate> candidatesObtained = COLLABORATION_OFFER_CANDIDATE_DAO.GetCollaborationOfferCandidateByIdCollaborationOffer(PROFESSOR_OFFER.getIdOfferCollaboration());
            if (candidatesObtained != null) {
                for (CollaborationOfferCandidate candidate : candidatesObtained) {
                    User candidateUser = USER_DAO.getUserById(candidate.getIdUser());
                    String professorName = candidateUser.getName() + " " + candidateUser.getLastName();
                    String professorEmail = candidateUser.getEmail();
                    String universityName = PROFESSOR_DAO.getUniversityFromAProfessor(candidate.getIdUser()).getFirst();
                    String universityLocation = PROFESSOR_DAO.getUniversityFromAProfessor(candidate.getIdUser()).get(1);
                    int idUser = candidateUser.getIdUser();
                    
                    OfferInformation candidateRow = new OfferInformation();
                    candidateRow.setProfessorName(professorName);
                    candidateRow.setProfessorEmail(professorEmail);
                    candidateRow.setUniversityName(universityName);
                    candidateRow.setUniversityLocation(universityLocation);
                    candidateRow.setIdUser(idUser);
                    this.candidates.add(candidateRow);
                    this.tblViewCandidates.setItems(candidates);
                }
            }
        } catch (LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            LOG.error(logicException);
        }
    }
    
    private void setSelectedCandidate() {
        CANDIDATE_INFORMATION.setProfessorName(this.tblViewCandidates.getSelectionModel().getSelectedItem().getProfessorName());
        CANDIDATE_INFORMATION.setProfessorEmail(this.tblViewCandidates.getSelectionModel().getSelectedItem().getProfessorEmail());
        CANDIDATE_INFORMATION.setUniversityName(this.tblViewCandidates.getSelectionModel().getSelectedItem().getUniversityName());
        CANDIDATE_INFORMATION.setUniversityLocation(this.tblViewCandidates.getSelectionModel().getSelectedItem().getUniversityLocation());
        CANDIDATE_INFORMATION.setIdUser(this.tblViewCandidates.getSelectionModel().getSelectedItem().getIdUser());
    }
    
    private void checkEmptyTable() {
        if (candidates.isEmpty()) {
            this.tblViewCandidates.setPlaceholder(new Label ("Aún no hay candidatos para su oferta"));
        }
    }
    
    @FXML
    public void previousMenu() {
        Stage stage = (Stage) this.btnBack.getScene().getWindow();
        stage.close();
        try {
            MyOffersStage myOffersStage = new MyOffersStage();
        } catch (IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
    
    @FXML
    public void showProfessorDetail() {
        if (this.tblViewCandidates.getSelectionModel().getSelectedItem() != null) {
            setSelectedCandidate();
            Stage stage = (Stage) this.btnDetail.getScene().getWindow();
            stage.close();
            try {
                ProfessorDetailStage professorDetailStage = new ProfessorDetailStage();
            } catch (IOException ioException) {
                Alerts.displayAlertIOException();
                LOG.error(ioException);
            }
        } else {
            Alerts.showWarningAlert("Seleccione un candidato para poder ver su detalle");
        }
    }
}
