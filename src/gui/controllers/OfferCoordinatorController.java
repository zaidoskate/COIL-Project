package gui.controllers;

import gui.Alerts;
import gui.SessionManager;
import gui.stages.CoordinatorMenuStage;
import gui.stages.DetailOfferProfessorStage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
import logic.model.OfferInformation;
import org.apache.log4j.Logger;

public class OfferCoordinatorController implements Initializable {

    @FXML
    private TableView<OfferInformation> tblViewPendingOffers;
    
    @FXML
    private TableColumn<OfferInformation, String> clmProfessorName;
    
    @FXML
    private TableColumn<OfferInformation, String> clmOfferPeriod;
    
    @FXML
    private Button btnEvaluate;
    
    @FXML
    private Button btnBack;
    
    private static final CollaborationOfferDAO COLLABORATION_OFFER_DAO = new CollaborationOfferDAO();
    private static final UserDAO USER_DAO = new UserDAO();
    
    private ObservableList<OfferInformation> displayableOffers;
    
    private static final OfferInformation SELECTED_OFFER = OfferInformation.getOffer();
    private static final SessionManager CURRENT_SESSION = SessionManager.getInstance();
    
    private static final Logger LOG = Logger.getLogger(OfferCoordinatorController.class);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeTable();
        setUnapprovedCollaborationOffer();
        checkEmptyTable();
    }
    
    private void initializeTable() {
        displayableOffers = FXCollections.observableArrayList();
        this.clmProfessorName.setCellValueFactory(new PropertyValueFactory("professorName"));
        this.clmOfferPeriod.setCellValueFactory(new PropertyValueFactory("offerPeriod"));
    }
    
    private void setUnapprovedCollaborationOffer() {
        try {
            ArrayList<CollaborationOffer> offersToDisplay = COLLABORATION_OFFER_DAO.getUnapprovedCollaborationOffer();
            if (offersToDisplay != null){
                for (CollaborationOffer offer : offersToDisplay) {
                    User user = USER_DAO.getUserById(offer.getIdUser());
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
                    this.tblViewPendingOffers.setItems(displayableOffers);
                }
            }
        } catch (LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            LOG.error(logicException);
        }
    }
    
    private void setSelectedOffer() {
        SELECTED_OFFER.setProfessorName(tblViewPendingOffers.getSelectionModel().getSelectedItem().getProfessorName());
        SELECTED_OFFER.setProfessorEmail(tblViewPendingOffers.getSelectionModel().getSelectedItem().getProfessorEmail());
        SELECTED_OFFER.setObjective(tblViewPendingOffers.getSelectionModel().getSelectedItem().getObjective());
        SELECTED_OFFER.setTopicsInterest(tblViewPendingOffers.getSelectionModel().getSelectedItem().getTopicsInterest());
        SELECTED_OFFER.setOfferPeriod(tblViewPendingOffers.getSelectionModel().getSelectedItem().getOfferPeriod());
        SELECTED_OFFER.setOfferLanguage(tblViewPendingOffers.getSelectionModel().getSelectedItem().getOfferLanguage());
        SELECTED_OFFER.setAditionalInformation(tblViewPendingOffers.getSelectionModel().getSelectedItem().getAditionalInformation());
        SELECTED_OFFER.setIdOfferCollaboration(tblViewPendingOffers.getSelectionModel().getSelectedItem().getIdOfferCollaboration());
        SELECTED_OFFER.setIdUser(tblViewPendingOffers.getSelectionModel().getSelectedItem().getIdUser());
        SELECTED_OFFER.setNumberStudents(tblViewPendingOffers.getSelectionModel().getSelectedItem().getNumberStudents());
        SELECTED_OFFER.setStudentProfile(tblViewPendingOffers.getSelectionModel().getSelectedItem().getStudentProfile());
    }
    
    private void checkEmptyTable() {
        if (this.displayableOffers.isEmpty()) {
            this.tblViewPendingOffers.setPlaceholder(new Label ("No hay ofertas por evaluar"));
        }
    }
    
    @FXML
    private void showDetail() {
        if (this.tblViewPendingOffers.getSelectionModel().getSelectedItem() != null) {
            setSelectedOffer();
            Stage stage = (Stage) this.btnEvaluate.getScene().getWindow();
            stage.close();
            try {
                DetailOfferProfessorStage detailOfferStage = new DetailOfferProfessorStage();
            } catch (IOException ioException) {
                Alerts.displayAlertIOException();
                LOG.error(ioException);
            }
        } else {
            Alerts.showWarningAlert("Seleccione una oferta para poder ver su detalle");
        }
    }
    
    @FXML
    private void previousMenu() {
        Stage stage = (Stage) this.btnBack.getScene().getWindow();
        stage.close();
        try {
            CoordinatorMenuStage coordinatorMenuStage = new CoordinatorMenuStage();
        } catch (IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
    
}
