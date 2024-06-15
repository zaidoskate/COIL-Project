package gui.controllers;

import gui.Alerts;
import gui.SessionManager;
import gui.DataValidation;
import gui.stages.OfferProfessorStage;
import gui.stages.CollaborationRegistrationStage;
import java.io.IOException;
import logic.LogicException;
import logic.domain.Collaboration;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.DAOs.CollaborationDAO;
import logic.DAOs.CollaborationOfferCandidateDAO;
import logic.DAOs.CollaborationOfferDAO;
import logic.DAOs.EvaluationDAO;
import logic.DAOs.ProfessorBelongsToCollaborationDAO;
import logic.domain.ProfessorBelongsToCollaboration;
import logic.model.CandidateInformation;
import logic.model.OfferInformation;
import org.apache.log4j.Logger;

public class CollaborationRegistrationController implements Initializable {
    
    @FXML
    private TextField txtFieldCollaborationName;
    
    @FXML
    private Button btnBack;
    
    @FXML
    private Button btnAccept;
    
    private static final CollaborationDAO COLLABORATION_DAO = new CollaborationDAO();
    private static final ProfessorBelongsToCollaborationDAO PROFESSOR_BELONGS_TO_COLLABORATION_DAO = new ProfessorBelongsToCollaborationDAO();
    private static final CollaborationOfferDAO COLLABORATION_OFFER_DAO = new CollaborationOfferDAO();
    private static final CollaborationOfferCandidateDAO COLLABORATION_OFFER_CANDIDATE_DAO = new CollaborationOfferCandidateDAO();
    private static final EvaluationDAO EVALUATION_DAO = new EvaluationDAO();
    
    private static final OfferInformation PROFESSOR_OFFER = OfferInformation.getOffer();
    private static final SessionManager CURRENT_SESSION = SessionManager.getInstance();
    private static final CandidateInformation CURRENT_CANDIDATE = CandidateInformation.getCandidateInformation();
    
    private static final Logger LOG = Logger.getLogger(CollaborationRegistrationController.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    private Collaboration createCollaboration() {
        Collaboration collaboration = new Collaboration();
        collaboration.setColaborationName(txtFieldCollaborationName.getText());
        collaboration.setLanguage(PROFESSOR_OFFER.getOfferLanguage());
        collaboration.setInterestTopic(PROFESSOR_OFFER.getTopicsInterest());
        return collaboration;
    }
    
    private ProfessorBelongsToCollaboration createProfessorBelongsToCollaboration(int idCollaboration) {
        ProfessorBelongsToCollaboration professorBelongsToCollaboration = new ProfessorBelongsToCollaboration();
        professorBelongsToCollaboration.setIdColaboration(idCollaboration);
        professorBelongsToCollaboration.setIdUser(CURRENT_SESSION.getUserData().getIdUser());
        professorBelongsToCollaboration.setIdUserMirrorClass(CURRENT_CANDIDATE.getIdUser());
        professorBelongsToCollaboration.setColaborationStatus("Pendiente");
        return professorBelongsToCollaboration;
    }
    
    private boolean validateCollaborationName() {
        boolean validName = true;
        if(!DataValidation.validateNotBlanks(txtFieldCollaborationName.getText())){
            validName = false;
            Alerts.showWarningAlert("El nombre de la colaboración no puede estar vacío");
        }
        if(!DataValidation.validateName(txtFieldCollaborationName.getText())) {
            validName = false;
            Alerts.showWarningAlert("El nombre de la colaboración deben ser palabras válidas, evite el uso de caracteres especiales y el uso de solo números");
        }
        if(!DataValidation.validateLengthField(txtFieldCollaborationName.getText(), 45)) {
            validName = false;
            Alerts.showWarningAlert("El nombre de la colaboración excede la longitud máxima permitida, procure no extenderse más de 45 caracteres");
        }
        return validName;
    }
    
    @FXML
    public void previousMenu() {
        Stage stage = (Stage) this.btnAccept.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void acceptCandidate() {
        this.txtFieldCollaborationName.setText(DataValidation.trimUnnecesaryBlanks(this.txtFieldCollaborationName.getText()));
        try {
            if(validateCollaborationName()) {
                Collaboration collaboration = createCollaboration();
                int idCollaborationInserted = COLLABORATION_DAO.addColaboration(collaboration);
                if(idCollaborationInserted > 0) {
                    ProfessorBelongsToCollaboration professorBelongsToCollaboration = createProfessorBelongsToCollaboration(idCollaborationInserted);
                    int added = PROFESSOR_BELONGS_TO_COLLABORATION_DAO.addProfessorBelongsToCollaboration(professorBelongsToCollaboration);
                    if(added == 1) {
                        int candidatesDeleted = COLLABORATION_OFFER_CANDIDATE_DAO.deleteCollaborationOffer(PROFESSOR_OFFER.getIdOfferCollaboration());
                        int evaluationDeleted = EVALUATION_DAO.deleteEvaluation(PROFESSOR_OFFER.getIdOfferCollaboration());
                        if(candidatesDeleted > 0 && evaluationDeleted == 1) {
                            int offerDeleted = COLLABORATION_OFFER_DAO.deleteCollaborationOffer(PROFESSOR_OFFER.getIdOfferCollaboration());
                            if(offerDeleted == 1) {
                                Alerts.showInformationAlert("Mensaje", "Has aceptado esta colaboración ponte en contacto con el profesor");
                                Stage professorDetailStage = ((CollaborationRegistrationStage)this.btnAccept.getScene().getWindow()).getProfessorDetailStage();
                                professorDetailStage.close();
                                previousMenu();
                                try {
                                    OfferProfessorStage offerStage = new OfferProfessorStage();
                                } catch(IOException ioException) {
                                    Alerts.displayAlertIOException();
                                    LOG.error(ioException);
                                }
                                return;
                            }
                        }
                    }
                }
                Alerts.showWarningAlert("Ha ocurrido un problema al aceptar al candidato");
            }
        } catch (LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            LOG.error(logicException);
        }
    }
}
