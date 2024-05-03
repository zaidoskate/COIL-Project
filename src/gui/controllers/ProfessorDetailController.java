/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.controllers;

import gui.Alerts;
import gui.SessionManager;
import gui.stages.CandidatesStage;
import gui.stages.RegistrateCollaborationStage;
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

/**
 *
 * @author zaido
 */
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
    
    SessionManager currentSession = SessionManager.getInstance();
    OfferInformation currentOffer = OfferInformation.getOffer();
    CandidateInformation currentCandidate = CandidateInformation.getCandidateInformation();
    
    CollaborationOfferDAO collaborationOfferDAO = new CollaborationOfferDAO();
    CollaborationDAO collaborationDAO = new CollaborationDAO();//CREAR PRIMERO LA COLAB
    ProfessorBelongsToCollaborationDAO professorBelongsCollaborationDAO = new ProfessorBelongsToCollaborationDAO();//CREAR EL BELONGS
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.lblProfessorName.setText(currentCandidate.getProfessorName());
        this.lblProfessorEmail.setText(currentCandidate.getProfessorEmail());
        this.lblUniversityName.setText(currentCandidate.getUniversityName());
        this.lblUniversityLocation.setText(currentCandidate.getUniversityLocation());
    }
    
    @FXML
    private void previousMenu() {
        Stage stage = (Stage) this.btnBack.getScene().getWindow();
        stage.close();
        try {
            CandidatesStage candidatesStage = new CandidatesStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
        }
    }
    
    @FXML
    private void acceptCandidate() {
        try {
            RegistrateCollaborationStage registrateCollaborationStage = new RegistrateCollaborationStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
        }
    }
    
}
