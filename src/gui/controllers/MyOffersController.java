package gui.controllers;

import gui.Alerts;
import gui.SessionManager;
import gui.stages.CandidatesStage;
import gui.stages.OfferProfessorStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.DAOs.CollaborationOfferDAO;
import logic.LogicException;
import logic.domain.CollaborationOffer;
import logic.model.OfferInformation;
import org.apache.log4j.Logger;

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
    
    private static final CollaborationOfferDAO COLLABORATION_OFFER_DAO = new CollaborationOfferDAO();
    
    private static final SessionManager CURRENT_SESSION = SessionManager.getInstance();
    private static final OfferInformation PROFESSOR_OFFER = OfferInformation.getOffer();
    
    private static final Logger LOG = Logger.getLogger(MyOffersController.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setProfessorOffer();
        if(PROFESSOR_OFFER.getIdOfferCollaboration() != 0) {
            showOffersAvailable();
        } else {
            showNoOffersAvailable();
        }
    }
    
    private void setProfessorOffer() {
        try {
            CollaborationOffer offer = COLLABORATION_OFFER_DAO.getProfessorApprovedOffer(CURRENT_SESSION.getUserData().getIdUser());
            PROFESSOR_OFFER.setIdOfferCollaboration(offer.getIdCollaboration());
            PROFESSOR_OFFER.setIdUser(offer.getIdUser());
            PROFESSOR_OFFER.setOfferPeriod(offer.getPeriod());
            PROFESSOR_OFFER.setOfferLanguage(offer.getLanguage());
            PROFESSOR_OFFER.setTopicsInterest(offer.getTopicsOfInterest());
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            LOG.error(logicException);
        }
        
    }
    
    private void showOffersAvailable() {
        this.lblPeriod.setText(PROFESSOR_OFFER.getOfferPeriod());
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
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
    
    @FXML
    private void showCandidates() {
        Stage stage = (Stage) this.candidates.getScene().getWindow();
        stage.close();
        try {
            CandidatesStage candidatesStage = new CandidatesStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
}
