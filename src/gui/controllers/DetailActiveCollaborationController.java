package gui.controllers;

import gui.Alerts;
import gui.SessionManager;
import gui.stages.EvidenceListStage;
import gui.stages.SendEmailStage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import logic.DAOs.CollaborationDAO;
import logic.DAOs.PendingMailDAO;
import logic.DAOs.ProfessorBelongsToCollaborationDAO;
import logic.DAOs.ProfessorDAO;
import logic.LogicException;
import logic.domain.Collaboration;
import logic.domain.PendingMail;
import logic.domain.User;
import logic.model.CollaborationInformation;
import logic.model.EmailNotification;
import logic.model.EvidenceListInformation;
import org.apache.log4j.Logger;

public class DetailActiveCollaborationController implements Initializable{
    @FXML
    private Button btnDownloadEvidences;
    @FXML
    private Label professorName;
    @FXML
    private Label universityProfessor;
    @FXML
    private TextArea topicsOfInterest;
    @FXML
    private TextArea language;
    @FXML
    private TextArea startDate;
    @FXML
    private TextArea collaborationStatus;
    @FXML
    private Label mirrorProfessorName;
    @FXML
    private Label mirrorUniversity;

    private static final Logger LOG = Logger.getLogger(CollaborationController.class);
    private CollaborationInformation CURRENT_COLLABORATION = CollaborationInformation.getCollaboration();
    private final SessionManager CURRENT_SESSION = SessionManager.getInstance();
    private static final ProfessorDAO PROFESSOR_DAO = new ProfessorDAO();
    private static final ProfessorBelongsToCollaborationDAO PROFESSOR_BELONGS_DAO = new ProfessorBelongsToCollaborationDAO();
    private static final CollaborationDAO COLLABORATION_DAO = new CollaborationDAO();
    private static final PendingMailDAO PENDING_MAIL_DAO = new PendingMailDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }
    private void loadData() {
        int idCollaboration = CURRENT_COLLABORATION.getIdCollaboration();
        try {
            loadProfessorsData(idCollaboration);
            loadCollaborationData(idCollaboration);
        } catch (LogicException logicException) {
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
        String statusCollaboration = PROFESSOR_BELONGS_DAO.getStatusByIdCollaboration(idCollaboration);
        topicsOfInterest.setText(collaboration.getInterestTopic());
        language.setText(collaboration.getLanguage());
        startDate.setText(collaboration.getStartDate());
        collaborationStatus.setText(statusCollaboration);
    }
    
    private void savePendingMail(String professorEmail) throws LogicException {
        PendingMail pendingMail = new PendingMail();
        pendingMail.setContent(EmailNotification.getInstance().getEmailBody());
        pendingMail.setDestinationEmail(professorEmail);
        pendingMail.setSubject("Alerta de colaboracion activa.");
        pendingMail.setIdUser(CURRENT_SESSION.getUserData().getIdUser());

        PENDING_MAIL_DAO.insertPendingMail(pendingMail);
    }
    
    @FXML
    private void previousMenu() {
        Stage stage = (Stage) btnDownloadEvidences.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void downloadEvidences() {
        EvidenceListInformation.getInstance().setAllFolders(true);
        EvidenceListInformation.getInstance().setIdCollaboration(CURRENT_COLLABORATION.getIdCollaboration());
        try {
            EvidenceListStage evidenceListStage = new EvidenceListStage();
        } catch (IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }

    @FXML
    private void sendAlert() {
        String professorEmail;
        try {
            professorEmail = PROFESSOR_BELONGS_DAO.getEmailProfessorByIdCollaboration(CURRENT_COLLABORATION.getIdCollaboration());
        } catch (LogicException logicException) {
            LOG.error(logicException);
            Alerts.displayAlertLogicException(logicException);
            return;
        }
        EmailNotification.getInstance().setEmail(professorEmail);
        EmailNotification.getInstance().setMessageSuccess("Se ha enviado con éxito el correo de alerta.");
        try {
            SendEmailStage sendEmailStage = new SendEmailStage();
        } catch (IOException ioexception) {
            LOG.warn(ioexception);
            Alerts.displayAlertIOException();
        }
        if (EmailNotification.getInstance().getSentStatus() == false) {
            try {
                savePendingMail(professorEmail);
            } catch (LogicException logicException) {
                LOG.error(logicException);
                Alerts.displayAlertLogicException(logicException);
            }
        }
    }

}
