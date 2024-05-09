/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.controllers;

import gui.Alerts;
import gui.SessionManager;
import gui.DataValidation;
import logic.LogicException;
import logic.domain.Collaboration;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.DAOs.CollaborationDAO;
import logic.DAOs.CollaborationOfferCandidateDAO;
import logic.DAOs.CollaborationOfferDAO;
import logic.DAOs.ProfessorBelongsToCollaborationDAO;
import logic.domain.ProfessorBelongsToCollaboration;
import logic.model.CandidateInformation;
import logic.model.OfferInformation;
import org.apache.log4j.Logger;

/**
 *
 * @author zaido
 */
public class RegistrateCollaborationController implements Initializable {
    
    @FXML
    private TextField txtFieldCollaborationName;
    
    @FXML
    private Button btnBack;
    
    @FXML
    private Button btnAccept;
    
    private static final CollaborationDAO collaborationDAO = new CollaborationDAO();
    private static final ProfessorBelongsToCollaborationDAO professorBelongsToCollaborationDAO = new ProfessorBelongsToCollaborationDAO();
    private static final CollaborationOfferDAO collaborationOfferDAO = new CollaborationOfferDAO();
    private static final CollaborationOfferCandidateDAO collaborationOfferCandidatesDAO = new CollaborationOfferCandidateDAO();
    
    private static final OfferInformation professorOffer = OfferInformation.getOffer();
    private static final SessionManager currentSession = SessionManager.getInstance();
    private static final CandidateInformation currentCandidate = CandidateInformation.getCandidateInformation();
    
    private static final Logger log = Logger.getLogger(RegistrateCollaborationController.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    private Collaboration createCollaboration() {
        Collaboration collaboration = new Collaboration();
        collaboration.setColaborationName(txtFieldCollaborationName.getText());
        collaboration.setLanguage(professorOffer.getOfferLanguage());
        collaboration.setInterestTopic(professorOffer.getTopicsInterest());
        return collaboration;
    }
    
    private ProfessorBelongsToCollaboration createProfessorBelongsToCollaboration(int idCollaboration) {
        ProfessorBelongsToCollaboration professorBelongsToCollaboration = new ProfessorBelongsToCollaboration();
        professorBelongsToCollaboration.setIdColaboration(idCollaboration);
        professorBelongsToCollaboration.setIdUser(currentSession.getUserData().getIdUser());
        professorBelongsToCollaboration.setIdUserMirrorClass(currentCandidate.getIdUser());
        professorBelongsToCollaboration.setColaborationStatus("Pendiente");
        return professorBelongsToCollaboration;
    }
    
    @FXML
    public void previousMenu() {
        Stage stage = (Stage) this.btnAccept.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void acceptCandidate() {
        try {
            if(DataValidation.validateNotBlanks(txtFieldCollaborationName.getText()) && DataValidation.validateWord(txtFieldCollaborationName.getText())) {
                Collaboration collaboration = createCollaboration();
                int idCollaborationInserted = collaborationDAO.addColaboration(collaboration);
                if(idCollaborationInserted > 0) {
                    ProfessorBelongsToCollaboration professorBelongsToCollaboration = createProfessorBelongsToCollaboration(idCollaborationInserted);
                    int added = professorBelongsToCollaborationDAO.addProfessorBelongsToCollaboration(professorBelongsToCollaboration);
                    if(added == 1) {
                        int candidatesDeleted = collaborationOfferCandidatesDAO.deleteCollaborationOffer(professorOffer.getIdOfferCollaboration());
                        if(candidatesDeleted > 0) {
                            int offerDeleted = collaborationOfferDAO.deleteCollaborationOffer(professorOffer.getIdOfferCollaboration());
                            if(offerDeleted == 1) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText("Mensaje");
                                alert.setTitle("Mensaje");
                                alert.setContentText("Has aceptado esta colaboración ponte en contacto con el profesor");
                                alert.showAndWait();
                                previousMenu();
                                return;
                            }
                        }
                    }
                }
                Alerts.showWarningAlert("Ha ocurrido un problema al aceptar al candidato");
            } else {
                Alerts.showWarningAlert("Proporcione un nombre válido para la colaboración");
            }
        } catch (LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            log.error(logicException);
        }
    }
}
