/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.controllers;

import gui.Alerts;
import gui.SessionManager;
import gui.stages.OfferCoordinatorStage;
import gui.stages.OfferProfessorStage;
import gui.stages.SendEmailStage;
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
import logic.model.EmailNotification;
import logic.model.OfferInformation;
import org.apache.log4j.Logger;

/**
 *
 * @author zaido
 */
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
    
    private static final ProfessorDAO professorDAO = new ProfessorDAO();
    private static final CollaborationOfferCandidateDAO candidateDAO = new CollaborationOfferCandidateDAO();
    private static final UserDAO userDAO = new UserDAO();
    private static final CollaborationOfferDAO collaborationOfferDAO = new CollaborationOfferDAO();
    private static final EvaluationDAO evaluationDAO = new EvaluationDAO();
    private static final CoordinatorDAO coordinatorDAO = new CoordinatorDAO();
    
    private static final OfferInformation selectedOffer = OfferInformation.getOffer();
    private static final SessionManager currentSession = SessionManager.getInstance();
    
    private static final Logger log = Logger.getLogger(DetailOfferProfessorController.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.professorName.setText(selectedOffer.getProfessorName());
        this.professorEmail.setText(selectedOffer.getProfessorEmail());
        this.objective.setText(selectedOffer.getObjective());
        this.topicsOfInterest.setText(selectedOffer.getTopicsInterest());
        this.period.setText(selectedOffer.getOfferPeriod());
        this.language.setText(selectedOffer.getOfferLanguage());
        this.aditionalInformation.setText(selectedOffer.getAditionalInformation());
        this.numberStudents.setText(String.valueOf(selectedOffer.getNumberStudents()));
        this.profile.setText(selectedOffer.getStudentProfile());
        try {
            checkVisibleButtons();
            ArrayList<String> universityInfo = professorDAO.getUniversityFromAProfessor(selectedOffer.getIdUser());
            this.universityName.setText(universityInfo.get(0));
            this.universityCountry.setText(universityInfo.get(1));
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            log.error(logicException);
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
        return userDAO.getUserTypeById(currentSession.getUserData().getIdUser());
    }

    private void applyForOffer() throws LogicException {
        CollaborationOfferCandidate candidate = new CollaborationOfferCandidate();
        candidate.setIdCollaboration(selectedOffer.getIdOfferCollaboration());
        candidate.setIdUser(currentSession.getUserData().getIdUser());
        int appliedSuccess = candidateDAO.InsertCollaborationOfferCandidate(candidate);
        if(appliedSuccess == 1) {
            Alerts.showInformationAlert("Te has postulado", "Se ha postulado a esta oferta, espere la correspondencia del profesor");
        }
    }

    
    private Evaluation createEvaluationApproved() throws LogicException {
        Evaluation evaluation = new Evaluation();
        evaluation.setIdCoordinator(coordinatorDAO.getIdCoordinatorByIdUser(currentSession.getUserData().getIdUser()));
        evaluation.setIdOfferCollaboration(selectedOffer.getIdOfferCollaboration());
        return evaluation;
    }
    
    private Evaluation createEvaluationDeclined() throws LogicException {
        Evaluation evaluation = new Evaluation();
        evaluation.setIdCoordinator(coordinatorDAO.getIdCoordinatorByIdUser(currentSession.getUserData().getIdUser()));
        evaluation.setIdOfferCollaboration(selectedOffer.getIdOfferCollaboration());
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
            log.error(logicException);
        } catch (IOException ioException) {
            Alerts.displayAlertIOException();
            log.error(ioException);
        }
    }
    
    @FXML
    public void applyOffer() {
        if (selectedOffer.getIdUser() == currentSession.getUserData().getIdUser()) {
            Alerts.showWarningAlert("No puede postularse a su propia oferta");
            return;
        }

        try {
            if (candidateDAO.professorHasAppliedForOffer(currentSession.getUserData().getIdUser(), selectedOffer.getIdOfferCollaboration())) {
                Alerts.showWarningAlert("Ya se ha postulado a esta oferta");
            } else {
                applyForOffer();
            }
        } catch (LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            log.error(logicException);
        }
    }
    
    @FXML
    private void approveOffer() {
        try {
            if(collaborationOfferDAO.evaluateCollaborationOffer(selectedOffer.getIdOfferCollaboration(), "Aprobada") == 1) {
                Evaluation evaluation = createEvaluationApproved();
                if(evaluationDAO.insertEvaluationForApprovedOffer(evaluation) == 1) {
                    Alerts.showInformationAlert("Mensaje", "Ha aprobado esta oferta de colaboración");
                    previousMenu();
                }
            }
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            log.error(logicException);
        }
    }
    
    @FXML
    private void displayDeclineOffer() {
        EmailNotification.getInstance().setEmail(selectedOffer.getProfessorEmail());
        EmailNotification.getInstance().setMessageSuccess("Oferta rechazada");
        EmailNotification.getInstance().setMessageCancel("Se cancelará el rechazo de la oferta");
        try {
            SendEmailStage sendEmailStage = new SendEmailStage();
            if(EmailNotification.getInstance().getSentStatus()) {
                Evaluation evaluation = createEvaluationDeclined();
                if(collaborationOfferDAO.evaluateCollaborationOffer(selectedOffer.getIdOfferCollaboration(), "Rechazada") == 1) {
                    if(evaluationDAO.insertEvaluationForDeclinedOffer(evaluation) == 1) {
                        if(EmailNotification.getInstance().getSentStatus()) {
                            previousMenu();
                        }
                    }
                }
            }
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            log.error(logicException);
        } catch (IOException ioException) {
            Alerts.displayAlertIOException();
            log.error(ioException);
        }
    }
}
