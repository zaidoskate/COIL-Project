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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logic.DAOs.CollaborationOfferDAO;
import logic.DAOs.UserDAO;
import logic.LogicException;

import logic.domain.CollaborationOffer;
import logic.domain.User;

/**
 * FXML Controller class
 *
 * @author zaido
 */
public class OfferProfessorController implements Initializable {

    @FXML
    private TableView<OfferInformation> tableView;
    
    @FXML
    private TableColumn<OfferInformation, String> professorColumn;
    
    @FXML
    private TableColumn<OfferInformation, String> emailColumn;
    
    @FXML
    private TableColumn<OfferInformation, String> periodColumn;
    
    @FXML
    private TableColumn<OfferInformation, String> languageColumn;
    
    private CollaborationOfferDAO collaborationOfferDAO;
    private UserDAO userDAO;
    private ObservableList<OfferInformation> offers;
    
    OfferInformation selectedOffer = OfferInformation.getOffer();
    SessionManager currentSession = SessionManager.getInstance();
    
    public OfferProfessorController() {
        collaborationOfferDAO = new CollaborationOfferDAO();
        userDAO = new UserDAO();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeTable();
        setApprovedCollaborationOffer();
    }
    
    private void initializeTable() {
        offers = FXCollections.observableArrayList();
        this.professorColumn.setCellValueFactory(new PropertyValueFactory("professorName"));
        this.emailColumn.setCellValueFactory(new PropertyValueFactory("professorEmail"));
        this.periodColumn.setCellValueFactory(new PropertyValueFactory("offerPeriod"));
        this.languageColumn.setCellValueFactory(new PropertyValueFactory("offerLanguage"));
    }
    
    private void setApprovedCollaborationOffer(){
        try{
            ArrayList<CollaborationOffer> offersToDisplay = collaborationOfferDAO.getApprovedCollaborationOffer();
            if(offersToDisplay != null){
                for (CollaborationOffer offer : offersToDisplay) {
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
                    this.offers.add(offerRow);
                    this.tableView.setItems(offers);
                }
            }
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
        }
    }
    
    private void setSelectedOffer() {
        selectedOffer.setProfessorName(tableView.getSelectionModel().getSelectedItem().getProfessorName());
        selectedOffer.setProfessorEmail(tableView.getSelectionModel().getSelectedItem().getProfessorEmail());
        selectedOffer.setObjective(tableView.getSelectionModel().getSelectedItem().getObjective());
        selectedOffer.setTopicsInterest(tableView.getSelectionModel().getSelectedItem().getTopicsInterest());
        selectedOffer.setOfferPeriod(tableView.getSelectionModel().getSelectedItem().getOfferPeriod());
        selectedOffer.setOfferLanguage(tableView.getSelectionModel().getSelectedItem().getOfferLanguage());
        selectedOffer.setAditionalInformation(tableView.getSelectionModel().getSelectedItem().getAditionalInformation());
        selectedOffer.setIdOfferCollaboration(tableView.getSelectionModel().getSelectedItem().getIdOfferCollaboration());
        selectedOffer.setIdUser(tableView.getSelectionModel().getSelectedItem().getIdUser());
        selectedOffer.setNumberStudents(tableView.getSelectionModel().getSelectedItem().getNumberStudents());
        selectedOffer.setStudentProfile(tableView.getSelectionModel().getSelectedItem().getStudentProfile());
    }
    
    @FXML
    public void showDetail(ActionEvent event) {
        if(tableView.getSelectionModel().getSelectedItem() != null) {
        setSelectedOffer();
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            try {
                DetailOfferProfessorStage detailStage = new DetailOfferProfessorStage();
            } catch(IOException ioException) {
                Alerts.displayAlertIOException();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Advertencia");
            alert.setTitle("Advertencia");
            alert.setContentText("Seleccione una oferta para poder ver su detalle");
            alert.showAndWait();
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Mensaje");
                alert.setTitle("Mensaje");
                alert.setContentText("Actualmente tiene una oferta publicada o en aprobacion");
                alert.showAndWait();
            }
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
        } catch (IOException ioException) {
            Alerts.displayAlertIOException();
        }
    }
    
    @FXML
    public void previousMenu() {
        Stage stage = (Stage) tableView.getScene().getWindow();
        stage.close();
        try {
            ProfesorMenuStage menuStage = new ProfesorMenuStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
        }
    }
    
    @FXML
    public void showMyOffers() {
        Stage stage = (Stage) this.tableView.getScene().getWindow();
        stage.close();
        try {
            MyOffersStage myOffersStage = new MyOffersStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
        }
    }
}
