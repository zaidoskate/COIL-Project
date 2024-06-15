package gui.controllers;

import gui.Alerts;
import gui.SessionManager;
import gui.stages.CandidatesStage;
import gui.stages.CollaborationRegistrationStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import logic.DAOs.CollaborationDAO;
import logic.DAOs.CollaborationOfferDAO;
import logic.DAOs.ProfessorBelongsToCollaborationDAO;
import logic.model.CandidateInformation;
import logic.model.OfferInformation;
import org.apache.log4j.Logger;

public class ProfessorDetailController implements Initializable {

    @FXML
    private Label lblProfessorName;
    
    @FXML
    private Label lblProfessorEmail;
    
    @FXML
    private Label lblUniversityName;
    
    @FXML
    private Label lblUniversityLocation;
    
    @FXML
    private Button btnAccept;
    
    @FXML
    private Button btnBack;
    
    private static final SessionManager CURRENT_SESSION = SessionManager.getInstance();
    private static final OfferInformation CURRENT_OFFER = OfferInformation.getOffer();
    private static final CandidateInformation CURRENT_CANDIDATE = CandidateInformation.getCandidateInformation();
    
    private static final CollaborationOfferDAO COLLABORATION_OFFER_DAO = new CollaborationOfferDAO();
    private static final CollaborationDAO COLLABORATION_DAO = new CollaborationDAO();
    private static final ProfessorBelongsToCollaborationDAO PROFESSOR_BELONGS_TO_COLLABORATION_DAO = new ProfessorBelongsToCollaborationDAO();
    
    private static final Logger LOG = Logger.getLogger(ProfessorDetailController.class);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.lblProfessorName.setText(CURRENT_CANDIDATE.getProfessorName());
        this.lblProfessorEmail.setText(CURRENT_CANDIDATE.getProfessorEmail());
        this.lblUniversityName.setText(CURRENT_CANDIDATE.getUniversityName());
        this.lblUniversityLocation.setText(CURRENT_CANDIDATE.getUniversityLocation());
    }
    
    @FXML
    private void previousMenu() {
        Stage stage = (Stage) this.btnBack.getScene().getWindow();
        stage.close();
        try {
            CandidatesStage candidatesStage = new CandidatesStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
    
    @FXML
    private void acceptCandidate() {
        try {
            Stage currentStage = (Stage) this.btnAccept.getScene().getWindow();
            CollaborationRegistrationStage registrateCollaborationStage = new CollaborationRegistrationStage(currentStage);
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
    
}
