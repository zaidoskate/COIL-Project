package gui.controllers;

import gui.Alerts;
import gui.SessionManager;
import gui.stages.OfferDeclinationStage;
import gui.stages.OfferCoordinatorStage;
import gui.stages.OfferProfessorStage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import logic.DAOs.CollaborationOfferCandidateDAO;
import logic.DAOs.CollaborationOfferDAO;
import logic.DAOs.CoordinatorDAO;
import logic.DAOs.EvaluationDAO;
import logic.DAOs.ProfessorDAO;
import logic.DAOs.UserDAO;
import logic.LogicException;
import logic.domain.CollaborationOfferCandidate;
import logic.domain.Evaluation;
import logic.domain.PendingMail;
import logic.model.EmailNotification;
import logic.model.OfferInformation;
import org.apache.log4j.Logger;

public class DetailOfferProfessorController implements Initializable {
    
    @FXML
    private Label professorName;
    
    @FXML
    private Label professorEmail;
    
    @FXML
    private Label universityName;
    
    @FXML
    private Label universityCountry;
    
    @FXML
    private TextArea objective;
    
    @FXML
    private TextArea topicsOfInterest;
    
    @FXML
    private TextField period;
    
    @FXML
    private TextArea language;
    
    @FXML
    private TextArea aditionalInformation;
    
    @FXML
    private TextField profile;
    
    @FXML
    private Label numberStudents;
    
    @FXML
    private Button btnCorrespond;
    
    @FXML
    private Button btnBack;
    
    @FXML
    private Button btnApprove;
    
    @FXML
    private Button btnDecline;
    
    private static final ProfessorDAO PROFESSOR_DAO = new ProfessorDAO();
    private static final CollaborationOfferCandidateDAO CANDIDATE_DAO = new CollaborationOfferCandidateDAO();
    private static final UserDAO USER_DAO = new UserDAO();
    private static final CollaborationOfferDAO COLLABORATION_OFFER_DAO = new CollaborationOfferDAO();
    private static final EvaluationDAO EVALUATION_DAO = new EvaluationDAO();
    private static final CoordinatorDAO coordinatorDAO = new CoordinatorDAO();
    
    private static final OfferInformation SELECTED_OFFER = OfferInformation.getOffer();
    private static final SessionManager CURRENT_SESSION = SessionManager.getInstance();
    
    private static final Logger LOG = Logger.getLogger(DetailOfferProfessorController.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.professorName.setText(SELECTED_OFFER.getProfessorName());
        this.professorEmail.setText(SELECTED_OFFER.getProfessorEmail());
        this.objective.setText(SELECTED_OFFER.getObjective());
        this.topicsOfInterest.setText(SELECTED_OFFER.getTopicsInterest());
        this.period.setText(SELECTED_OFFER.getOfferPeriod());
        this.language.setText(SELECTED_OFFER.getOfferLanguage());
        this.aditionalInformation.setText(SELECTED_OFFER.getAditionalInformation());
        this.numberStudents.setText(String.valueOf(SELECTED_OFFER.getNumberStudents()));
        this.profile.setText(SELECTED_OFFER.getStudentProfile());
        try {
            checkVisibleButtons();
            ArrayList<String> universityInfo = PROFESSOR_DAO.getUniversityFromAProfessor(SELECTED_OFFER.getIdUser());
            this.universityName.setText(universityInfo.get(0));
            this.universityCountry.setText(universityInfo.get(1));
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            LOG.error(logicException);
        }
    }
    
    private void checkVisibleButtons() throws LogicException {
        if(getTypeUser().equals("Coordinador")) {
            this.btnApprove.setVisible(true);
            this.btnDecline.setVisible(true);
        } else {
            this.btnCorrespond.setVisible(true);
        }
    }
    
    private String getTypeUser() throws LogicException {
        return USER_DAO.getUserTypeById(CURRENT_SESSION.getUserData().getIdUser());
    }

    private void applyForOffer() throws LogicException {
        CollaborationOfferCandidate candidate = new CollaborationOfferCandidate();
        candidate.setIdCollaboration(SELECTED_OFFER.getIdOfferCollaboration());
        candidate.setIdUser(CURRENT_SESSION.getUserData().getIdUser());
        int appliedSuccess = CANDIDATE_DAO.InsertCollaborationOfferCandidate(candidate);
        if(appliedSuccess == 1) {
            Alerts.showInformationAlert("Se ha postulado", "Se ha postulado a esta oferta, espere la correspondencia del profesor");
        }
    }

    
    private Evaluation createEvaluationApproved() throws LogicException {
        Evaluation evaluation = new Evaluation();
        evaluation.setIdCoordinator(coordinatorDAO.getIdCoordinatorByIdUser(CURRENT_SESSION.getUserData().getIdUser()));
        evaluation.setIdOfferCollaboration(SELECTED_OFFER.getIdOfferCollaboration());
        return evaluation;
    }
    
    private Evaluation createEvaluationDeclined() throws LogicException {
        Evaluation evaluation = new Evaluation();
        evaluation.setIdCoordinator(coordinatorDAO.getIdCoordinatorByIdUser(CURRENT_SESSION.getUserData().getIdUser()));
        evaluation.setIdOfferCollaboration(SELECTED_OFFER.getIdOfferCollaboration());
        evaluation.setReason(EmailNotification.getInstance().getEmailBody());
        return evaluation;
    }

    @FXML
    public void previousMenu() {
        Stage stage = (Stage) this.objective.getScene().getWindow();
        stage.close();
        try{
            if(getTypeUser().equals("Coordinador")) {
                OfferCoordinatorStage offerCoordinatorStage =  new OfferCoordinatorStage();
            } else {
                OfferProfessorStage offerProfessorStage = new OfferProfessorStage();
            }
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            LOG.error(logicException);
        } catch (IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
    
    @FXML
    public void applyOffer() {
        if (SELECTED_OFFER.getIdUser() == CURRENT_SESSION.getUserData().getIdUser()) {
            Alerts.showWarningAlert("No puede postularse a su propia oferta");
            return;
        }

        try {
            if (CANDIDATE_DAO.professorHasAppliedForOffer(CURRENT_SESSION.getUserData().getIdUser(), SELECTED_OFFER.getIdOfferCollaboration())) {
                Alerts.showWarningAlert("Ya se ha postulado a esta oferta");
            } else {
                applyForOffer();
            }
        } catch (LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            LOG.error(logicException);
        }
    }
    
    @FXML
    private void approveOffer() {
        try {
            if(COLLABORATION_OFFER_DAO.evaluateCollaborationOffer(SELECTED_OFFER.getIdOfferCollaboration(), "Aprobada") == 1) {
                Evaluation evaluation = createEvaluationApproved();
                if(EVALUATION_DAO.insertEvaluationForApprovedOffer(evaluation) == 1) {
                    Alerts.showInformationAlert("Mensaje", "Ha aprobado esta oferta de colaboración");
                    previousMenu();
                }
            }
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            LOG.error(logicException);
        }
    }
    
    @FXML
    private void displayDeclineOffer() {
        EmailNotification.getInstance().setEmail(SELECTED_OFFER.getProfessorEmail());
        EmailNotification.getInstance().setMessageSuccess("Oferta rechazada");
        try {
            OfferDeclinationStage declineOfferStage = new OfferDeclinationStage();
            if(EmailNotification.getInstance().getSentStatus()) {
                Alerts.showInformationAlert("Mensaje", "Oferta rechazada con éxito");
            } else {
                PendingMail pendingMail = new PendingMail();
                pendingMail.setSubject("Conclusión de colaboración");
                pendingMail.setContent(EmailNotification.getInstance().getEmailBody());
                pendingMail.setDestinationEmail(SELECTED_OFFER.getProfessorEmail());
                pendingMail.setIdUser(CURRENT_SESSION.getUserData().getIdUser());
            }
            previousMenu();
        } catch (IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
}
