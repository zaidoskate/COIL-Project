/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.controllers;

import gui.SessionManager;
import gui.stages.CandidatesStage;
import gui.stages.OfferProfessorStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.DAOs.CollaborationOfferDAO;
import logic.LogicException;
import logic.domain.CollaborationOffer;
import logic.model.OfferInformation;

/**
 *
 * @author zaido
 */
public class MyOffersController implements Initializable {
    
    @FXML
    private AnchorPane offerAvailablePane;
    
    @FXML
    private AnchorPane noOfferAvailablePane;
    
    @FXML
    private Button back;
    
    @FXML
    private Button candidates;
    
    @FXML
    private Label lblPeriod;
    
    private final CollaborationOfferDAO collaborationOfferDAO = new CollaborationOfferDAO();
    
    private final SessionManager currentSession = SessionManager.getInstance();
    private final OfferInformation professorOffer = OfferInformation.getOffer();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setProfessorOffer();
        if(professorOffer.getIdOfferCollaboration() != 0) {
            showOffersAvailable();
        } else {
            showNoOffersAvailable();
        }
    }
    
    private void setProfessorOffer() {
        try {
            CollaborationOffer offer = collaborationOfferDAO.getProfessorApprovedOffer(currentSession.getUserData().getIdUser());
            professorOffer.setIdOfferCollaboration(offer.getIdCollaboration());
            professorOffer.setIdUser(offer.getIdUser());
            professorOffer.setOfferPeriod(offer.getPeriod());
            professorOffer.setOfferLanguage(offer.getLanguage());
            professorOffer.setTopicsInterest(offer.getTopicsOfInterest());
        } catch(LogicException logicException) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setTitle("Error");
            alert.setContentText(logicException.getMessage());
            alert.showAndWait();
        }
        
    }
    
    private void showOffersAvailable() {
        this.lblPeriod.setText(professorOffer.getOfferPeriod());
        this.offerAvailablePane.setVisible(true);
        
    }
    
    private void showNoOffersAvailable() {
        this.noOfferAvailablePane.setVisible(true);
    }
    
    @FXML
    private void previousMenu() {
        Stage stage = (Stage) this.back.getScene().getWindow();
        stage.close();
        try {
            OfferProfessorStage offerStage = new OfferProfessorStage();
        } catch(IOException ioException) {
            
        }
    }
    
    @FXML
    private void showCandidates() {
        Stage stage = (Stage) this.candidates.getScene().getWindow();
        stage.close();
        try {
            CandidatesStage candidatesStage = new CandidatesStage();
        } catch(IOException ioException) {
            
        }
    }
}
