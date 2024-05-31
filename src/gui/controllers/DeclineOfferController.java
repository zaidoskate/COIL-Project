package gui.controllers;

import gui.Alerts;
import gui.DataValidation;
import gui.SessionManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import logic.DAOs.CollaborationOfferDAO;
import logic.DAOs.CoordinatorDAO;
import logic.DAOs.EvaluationDAO;
import logic.LogicException;
import logic.MailSender;
import logic.domain.Evaluation;
import logic.model.EmailNotification;
import logic.model.OfferInformation;
import org.apache.log4j.Logger;

public class DeclineOfferController implements Initializable {
    
    private static final OfferInformation SELECTED_OFFER = OfferInformation.getOffer();
    private static final SessionManager CURRENT_SESSION = SessionManager.getInstance();
    private static final CollaborationOfferDAO COLLABORATION_OFFER_DAO = new CollaborationOfferDAO();
    private static final CoordinatorDAO COORDINATOR_DAO = new CoordinatorDAO();
    private static final EvaluationDAO EVALUATION_DAO = new EvaluationDAO();
    
    private static final Logger LOG = Logger.getLogger(DeclineOfferController.class);
            
    @FXML
    private TextArea txtAreaReason;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    private Evaluation createEvaluationDeclined() throws LogicException {
        Evaluation evaluation = new Evaluation();
        evaluation.setIdCoordinator(COORDINATOR_DAO.getIdCoordinatorByIdUser(CURRENT_SESSION.getUserData().getIdUser()));
        evaluation.setIdOfferCollaboration(SELECTED_OFFER.getIdOfferCollaboration());
        evaluation.setReason(this.txtAreaReason.getText());
        return evaluation;
    }
    
    private boolean sendDeclinedOfferEmail() throws LogicException {
        String email = EmailNotification.getInstance().getEmail();
        String body = "Hemos decidido rechazar tu oferta: " + this.txtAreaReason.getText();
        boolean emailSent = false;
        emailSent = MailSender.sendEmail(body, email);
        return emailSent;
    }
    
    private boolean validateReason(String reason) {
        boolean validReason = true;
        if (!DataValidation.validateNotBlanks(reason)) {
            validReason = false;
            Alerts.showWarningAlert("El motivo no puede estar vacío");
        }
        if (!DataValidation.validateWord(reason)) {
            validReason = false;
            Alerts.showWarningAlert("El motivo tienen que ser palabras válidas, evite el uso de caracteres especiales");
        }
        if(!DataValidation.validateLengthField(reason, 255)) {
            validReason = false;
            Alerts.showWarningAlert("Proporcione un motivo de no más de 255 caracteres");
        }
        return validReason;
    }
    
    @FXML
    private void declineOffer() {
        this.txtAreaReason.setText(DataValidation.trimUnnecesaryBlanks(this.txtAreaReason.getText()));
        if(validateReason(this.txtAreaReason.getText())) {
            try {
                Evaluation evaluation = createEvaluationDeclined();
                    if(COLLABORATION_OFFER_DAO.evaluateCollaborationOffer(SELECTED_OFFER.getIdOfferCollaboration(), "Rechazada") == 1) {
                        if(EVALUATION_DAO.insertEvaluationForDeclinedOffer(evaluation) == 1) {
                            if (sendDeclinedOfferEmail()) {
                                EmailNotification.getInstance().emailSent();
                                EmailNotification.getInstance().setEmailBody(this.txtAreaReason.getText());
                                Alerts.showInformationAlert("Éxito", "Correo enviado al profesor");
                                previousMenu();
                            } else {
                                Alerts.showWarningAlert("Ha ocurrido un error, inténtelo de nuevo más tarde");
                            }
                        }
                    }
            } catch(LogicException logicException) {
                Alerts.displayAlertLogicException(logicException);
                LOG.error(logicException);
            }
        }
    }
    
    @FXML
    private void previousMenu() {
        Stage stage = (Stage) this.txtAreaReason.getScene().getWindow();
        stage.close();
    }
    
}
