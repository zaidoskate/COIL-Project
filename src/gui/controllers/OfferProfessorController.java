package gui.controllers;

import gui.Alerts;
import gui.SessionManager;
import logic.model.OfferInformation;
import gui.stages.DetailOfferProfessorStage;
import gui.stages.MyOffersStage;
import gui.stages.PostCollaborationOfferStage;
import gui.stages.ProfesorMenuStage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logic.DAOs.CollaborationOfferDAO;
import logic.DAOs.UserDAO;
import logic.LogicException;

import logic.domain.CollaborationOffer;
import logic.domain.User;
import org.apache.log4j.Logger;

public class OfferProfessorController implements Initializable {

    @FXML
    private TableView<OfferInformation> tblViewOffersAvailable;
    
    @FXML
    private TableColumn<OfferInformation, String> clmProfessorName;
    
    @FXML
    private TableColumn<OfferInformation, String> clmProfessorEmail;
    
    @FXML
    private TableColumn<OfferInformation, String> clmOfferPeriod;
    
    @FXML
    private TableColumn<OfferInformation, String> clmOfferLanguage;
    
    private static final CollaborationOfferDAO COLLABORATION_OFFER_DAO = new CollaborationOfferDAO();
    private static final UserDAO USER_DAO = new UserDAO();
    private ObservableList<OfferInformation> displayableOffers;
    
    private static final OfferInformation SELECTED_OFFER = OfferInformation.getOffer();
    private static final SessionManager CURRENT_SESSION = SessionManager.getInstance();
    
    private static final Logger LOG = Logger.getLogger(OfferProfessorController.class);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeTable();
        setApprovedCollaborationOffer();
        checkEmptyTable();
    }
    
    private void checkEmptyTable() {
        if(this.displayableOffers.isEmpty()) {
            this.tblViewOffersAvailable.setPlaceholder(new Label("Aún no hay ofertas para colaborar"));
        }
    }
    
    private void initializeTable() {
        displayableOffers = FXCollections.observableArrayList();
        this.clmProfessorName.setCellValueFactory(new PropertyValueFactory("professorName"));
        this.clmProfessorEmail.setCellValueFactory(new PropertyValueFactory("professorEmail"));
        this.clmOfferPeriod.setCellValueFactory(new PropertyValueFactory("offerPeriod"));
        this.clmOfferLanguage.setCellValueFactory(new PropertyValueFactory("offerLanguage"));
    }
    
    private void setApprovedCollaborationOffer() {
        try {
            ArrayList<CollaborationOffer> displayableOffersToDisplay = COLLABORATION_OFFER_DAO.getApprovedCollaborationOffer();
            if (displayableOffersToDisplay != null) {
                for (CollaborationOffer offer : displayableOffersToDisplay) {
                    User user = USER_DAO.getUserById(offer.getIdUser());
                    OfferInformation offerRow = new OfferInformation();
                    offerRow.setProfessorName(user.getName() + " " + user.getLastName());
                    offerRow.setProfessorEmail(user.getEmail());
                    offerRow.setObjective(offer.getObjective());
                    offerRow.setTopicsInterest(offer.getTopicsOfInterest());
                    offerRow.setOfferPeriod(offer.getPeriod());
                    offerRow.setOfferLanguage(offer.getLanguage());
                    offerRow.setAditionalInformation(offer.getAditionalInfo());
                    offerRow.setIdOfferCollaboration(offer.getIdCollaboration());
                    offerRow.setIdUser(offer.getIdUser());
                    offerRow.setNumberStudents(offer.getNumberOfStudents());
                    offerRow.setStudentProfile(offer.getProfile());
                    this.displayableOffers.add(offerRow);
                }
                this.tblViewOffersAvailable.setItems(displayableOffers);
            }
        } catch (LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            LOG.error(logicException);
        }
    }

    
    private void setSelectedOffer() {
        SELECTED_OFFER.setProfessorName(tblViewOffersAvailable.getSelectionModel().getSelectedItem().getProfessorName());
        SELECTED_OFFER.setProfessorEmail(tblViewOffersAvailable.getSelectionModel().getSelectedItem().getProfessorEmail());
        SELECTED_OFFER.setObjective(tblViewOffersAvailable.getSelectionModel().getSelectedItem().getObjective());
        SELECTED_OFFER.setTopicsInterest(tblViewOffersAvailable.getSelectionModel().getSelectedItem().getTopicsInterest());
        SELECTED_OFFER.setOfferPeriod(tblViewOffersAvailable.getSelectionModel().getSelectedItem().getOfferPeriod());
        SELECTED_OFFER.setOfferLanguage(tblViewOffersAvailable.getSelectionModel().getSelectedItem().getOfferLanguage());
        SELECTED_OFFER.setAditionalInformation(tblViewOffersAvailable.getSelectionModel().getSelectedItem().getAditionalInformation());
        SELECTED_OFFER.setIdOfferCollaboration(tblViewOffersAvailable.getSelectionModel().getSelectedItem().getIdOfferCollaboration());
        SELECTED_OFFER.setIdUser(tblViewOffersAvailable.getSelectionModel().getSelectedItem().getIdUser());
        SELECTED_OFFER.setNumberStudents(tblViewOffersAvailable.getSelectionModel().getSelectedItem().getNumberStudents());
        SELECTED_OFFER.setStudentProfile(tblViewOffersAvailable.getSelectionModel().getSelectedItem().getStudentProfile());
    }
    
    @FXML
    public void showDetail(ActionEvent event) {
        if(tblViewOffersAvailable.getSelectionModel().getSelectedItem() != null) {
            setSelectedOffer();
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            try {
                DetailOfferProfessorStage detailStage = new DetailOfferProfessorStage();
            } catch(IOException ioException) {
                Alerts.displayAlertIOException();
                LOG.error(ioException);
            }
        } else {
            Alerts.showWarningAlert("Seleccione una oferta para poder ver su detalle");
        }
    }
    
    @FXML
    public void showPostOffer(ActionEvent event) {
        try {
            if(!COLLABORATION_OFFER_DAO.professorHasOffer(CURRENT_SESSION.getUserData().getIdUser())) {
                PostCollaborationOfferStage postStage = new PostCollaborationOfferStage();
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
            } else {
                Alerts.showInformationAlert("Mensaje", "Actualmente tiene una oferta publicada o en evaluación");
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
    public void previousMenu() {
        Stage stage = (Stage) tblViewOffersAvailable.getScene().getWindow();
        stage.close();
        try {
            ProfesorMenuStage menuStage = new ProfesorMenuStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
    
    @FXML
    public void showMyOffers() {
        Stage stage = (Stage) this.tblViewOffersAvailable.getScene().getWindow();
        stage.close();
        try {
            MyOffersStage myOffersStage = new MyOffersStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
}
