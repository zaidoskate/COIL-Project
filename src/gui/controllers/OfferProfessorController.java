/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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

/**
 * FXML Controller class
 *
 * @author zaido
 */
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
    
    private static final CollaborationOfferDAO collaborationOfferDAO = new CollaborationOfferDAO();
    private static final UserDAO userDAO = new UserDAO();
    private ObservableList<OfferInformation> displayableOffers;
    
    private static final OfferInformation selectedOffer = OfferInformation.getOffer();
    private static final SessionManager currentSession = SessionManager.getInstance();
    
    private static final Logger log = Logger.getLogger(OfferProfessorController.class);
    
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
    
    private void setApprovedCollaborationOffer(){
        try{
            ArrayList<CollaborationOffer> displayableOffersToDisplay = collaborationOfferDAO.getApprovedCollaborationOffer();
            if(displayableOffersToDisplay != null){
                for (CollaborationOffer offer : displayableOffersToDisplay) {
                    User user = userDAO.getUserById(offer.getIdUser());
                    String professorName = user.getName() + " " + user.getLastName();
                    String professorEmail = user.getEmail();
                    String objective = offer.getObjective();
                    String topics = offer.getTopicsOfInterest();
                    String period = offer.getPeriod();
                    String language = offer.getLanguage();
                    String aditionalInformation = offer.getAditionalInfo();
                    String profile = offer.getProfile();
                    int offerId = offer.getIdCollaboration();
                    int userId = offer.getIdUser();
                    int numberStudents = offer.getNumberOfStudents();
                    
                    OfferInformation offerRow = new OfferInformation();
                    offerRow.setProfessorName(professorName);
                    offerRow.setProfessorEmail(professorEmail);
                    offerRow.setObjective(objective);
                    offerRow.setTopicsInterest(topics);
                    offerRow.setOfferPeriod(period);
                    offerRow.setOfferLanguage(language);
                    offerRow.setAditionalInformation(aditionalInformation);
                    offerRow.setIdOfferCollaboration(offerId);
                    offerRow.setIdUser(userId);
                    offerRow.setNumberStudents(numberStudents);
                    offerRow.setStudentProfile(profile);
                    this.displayableOffers.add(offerRow);
                    this.tblViewOffersAvailable.setItems(displayableOffers);
                }
            }
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            log.error(logicException);
        }
    }
    
    private void setSelectedOffer() {
        selectedOffer.setProfessorName(tblViewOffersAvailable.getSelectionModel().getSelectedItem().getProfessorName());
        selectedOffer.setProfessorEmail(tblViewOffersAvailable.getSelectionModel().getSelectedItem().getProfessorEmail());
        selectedOffer.setObjective(tblViewOffersAvailable.getSelectionModel().getSelectedItem().getObjective());
        selectedOffer.setTopicsInterest(tblViewOffersAvailable.getSelectionModel().getSelectedItem().getTopicsInterest());
        selectedOffer.setOfferPeriod(tblViewOffersAvailable.getSelectionModel().getSelectedItem().getOfferPeriod());
        selectedOffer.setOfferLanguage(tblViewOffersAvailable.getSelectionModel().getSelectedItem().getOfferLanguage());
        selectedOffer.setAditionalInformation(tblViewOffersAvailable.getSelectionModel().getSelectedItem().getAditionalInformation());
        selectedOffer.setIdOfferCollaboration(tblViewOffersAvailable.getSelectionModel().getSelectedItem().getIdOfferCollaboration());
        selectedOffer.setIdUser(tblViewOffersAvailable.getSelectionModel().getSelectedItem().getIdUser());
        selectedOffer.setNumberStudents(tblViewOffersAvailable.getSelectionModel().getSelectedItem().getNumberStudents());
        selectedOffer.setStudentProfile(tblViewOffersAvailable.getSelectionModel().getSelectedItem().getStudentProfile());
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
                log.error(ioException);
            }
        } else {
            Alerts.showWarningAlert("Seleccione una oferta para poder ver su detalle");
        }
    }
    
    @FXML
    public void showPostOffer(ActionEvent event) {
        try {
            if(!collaborationOfferDAO.professorHasOffer(currentSession.getUserData().getIdUser())) {
                PostCollaborationOfferStage postStage = new PostCollaborationOfferStage();
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
            } else {
                Alerts.showInformationAlert("Mensaje", "Actualmente tiene una oferta publicada o en aprobacion");
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
    public void previousMenu() {
        Stage stage = (Stage) tblViewOffersAvailable.getScene().getWindow();
        stage.close();
        try {
            ProfesorMenuStage menuStage = new ProfesorMenuStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
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
            log.error(ioException);
        }
    }
}
