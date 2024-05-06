/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.controllers;

import gui.Alerts;
import gui.DataValidation;
import gui.SessionManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import logic.DAOs.CollaborationOfferDAO;
import logic.DAOs.CoordinatorDAO;
import logic.DAOs.EvaluationDAO;
import logic.LogicException;
import logic.MailSender;
import logic.domain.Evaluation;
import logic.model.OfferInformation;

/**
 *
 * @author zaido
 */
public class DeclineOfferController implements Initializable {
    
    @FXML
    private Button btnDecline;
    
    @FXML
    private Button btnBack;
    
    @FXML
    private TextArea txtAreaReason;
    
    private final CollaborationOfferDAO collaborationOfferDAO = new CollaborationOfferDAO();
    private final EvaluationDAO evaluationDAO = new EvaluationDAO();
    private final CoordinatorDAO coordinatorDAO = new CoordinatorDAO();
    
    OfferInformation currentOffer = OfferInformation.getOffer();
    SessionManager currentSession = SessionManager.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(currentOffer.getProfessorEmail());
    }
    
    private Evaluation createEvaluation() throws LogicException {
        Evaluation evaluation = new Evaluation();
        evaluation.setIdCoordinator(coordinatorDAO.getIdCoordinatorByIdUser(currentSession.getUserData().getIdUser()));
        evaluation.setIdOfferCollaboration(currentOffer.getIdOfferCollaboration());
        evaluation.setReason(this.txtAreaReason.getText());
        return evaluation;
    }
    
    private boolean validateReason(String reason) {
        return DataValidation.validateNotBlanks(reason) && DataValidation.validateWord(reason);
    }
    
    private String createEmailBody(String reason) {
        return "Hemos decidido rechazar tu oferta, esto se debe a: " + reason;
    }
    
    @FXML
    private void closeDeclineCollaborationOffer() {
        Stage stage = (Stage) this.btnBack.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void declineCollaborationOffer() {
        try {
            Evaluation evaluation = createEvaluation();
            if(evaluation.getReason() != null && validateReason(evaluation.getReason())) {
                String emailBody = createEmailBody(evaluation.getReason());
                if(collaborationOfferDAO.evaluateCollaborationOffer(currentOffer.getIdOfferCollaboration(), "Rechazada") == 1){
                    if(evaluationDAO.insertEvaluationForDeclinedOffer(evaluation) == 1) {
                        if(MailSender.sendEmail(emailBody, currentOffer.getProfessorEmail())){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText("Hecho");
                            alert.setTitle("Hecho");
                            alert.setContentText("Has rechazado esta oferta");
                            alert.showAndWait();
                            closeDeclineCollaborationOffer();
                        }
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Error");
                alert.setTitle("Error");
                alert.setContentText("Debes proporcionar un motivo válido");
                alert.showAndWait();
            }
        } catch(LogicException logicException) {
            System.out.println(logicException.getCause().getMessage());
            Alerts.displayAlertLogicException(logicException);
        }
    }
    
}
