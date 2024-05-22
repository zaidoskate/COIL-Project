package gui.controllers;

import gui.Alerts;
import gui.DataValidation;
import gui.SessionManager;
import gui.stages.OfferProfessorStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.DAOs.CollaborationOfferDAO;
import logic.LogicException;
import logic.domain.CollaborationOffer;
import org.apache.log4j.Logger;

public class PostCollaborationOfferController implements Initializable {

    @FXML
    private TextArea txtAreaObjective;
    
    @FXML
    private TextArea txtAreaTopicsOfInterest;
    
    @FXML
    private TextArea txtAreaLanguage;
    
    @FXML
    private TextArea txtAreaAditionalInformation;
    
    @FXML
    private TextField txtFieldYear;
    
    @FXML
    private TextField txtFieldNumberStudents;
    
    @FXML
    private TextField txtFieldProfile;
    
    @FXML
    private ComboBox<String> cmbBoxPeriod;
    
    @FXML
    private Button btnPost;
    
    @FXML
    private Button btnCancel;
    
    private static final SessionManager CURRENT_SESSION = SessionManager.getInstance();
    private static final CollaborationOfferDAO COLLABORATION_OFFER_DAO = new CollaborationOfferDAO();
    
    private static final Logger LOG = Logger.getLogger(PostCollaborationOfferController.class);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeComboBox();
    }
    
    private void initializeComboBox() {
        this.cmbBoxPeriod.getItems().addAll("Febrero - Julio", "Agosto - Enero");
    }
    
    private CollaborationOffer createOffer() {
        String objective = this.txtAreaObjective.getText();
        String topicsOfInterest = this.txtAreaTopicsOfInterest.getText();
        String period = this.cmbBoxPeriod.getSelectionModel().getSelectedItem() + " " +  this.txtFieldYear.getText();
        String language = this.txtAreaLanguage.getText();
        String aditionalInformation = this.txtAreaAditionalInformation.getText();
        String profile = this.txtFieldProfile.getText();
        int numberStudents = Integer.parseInt(this.txtFieldNumberStudents.getText());
        
        CollaborationOffer currentOffer = new CollaborationOffer();
        currentOffer.setObjective(objective);
        currentOffer.setTopicsOfInterest(topicsOfInterest);
        currentOffer.setPeriod(period);
        currentOffer.setLanguage(language);
        currentOffer.setAditionalInfo(aditionalInformation);
        currentOffer.setProfile(profile);
        currentOffer.setNumberOfStudents(numberStudents);
        currentOffer.setIdUser(CURRENT_SESSION.getUserData().getIdUser());
        return currentOffer;
    }

    private boolean validateWords() {
        String[] fields = {
            txtAreaObjective.getText(),
            txtAreaTopicsOfInterest.getText(),
            txtFieldYear.getText(),
            txtAreaLanguage.getText(),
            txtAreaAditionalInformation.getText(),
            txtFieldProfile.getText(),
            txtFieldNumberStudents.getText()
        };
        String[] fieldNames = {"Objetivo", "Temas de interés", "Año", "Idioma", "Información adicional", "Perfil", "Número de estudiantes"};

        for (int i = 0; i < fields.length; i++) {
            if (!DataValidation.validateWord(fields[i])) {
                Alerts.showWarningAlert(fieldNames[i] + " debe constar de palabras válidas, evite el uso de caracteres especiales");
                return false;
            }
        }
        return true;
    }
    
    private boolean validateNotEmptyFields() {
        String[] fields = {
            txtAreaObjective.getText(),
            txtAreaTopicsOfInterest.getText(),
            txtFieldYear.getText(),
            txtAreaLanguage.getText(),
            txtAreaAditionalInformation.getText(),
            txtFieldProfile.getText(),
            txtFieldNumberStudents.getText()
        };
        String[] fieldNames = {"Objetivo", "Temas de interés", "Año", "Idioma", "Información adicional", "Perfil", "Número de estudiantes"};

        for (int i = 0; i < fields.length; i++) {
            if (!DataValidation.validateNotBlanks(fields[i])) {
                Alerts.showWarningAlert(fieldNames[i] + " no puede estar vacío");
                return false;
            }
        }
        return true;
    }


    private boolean validateFieldLengths() {
        String[] fieldsToCheckLength = {
            txtAreaObjective.getText(),
            txtAreaTopicsOfInterest.getText(),
            txtFieldYear.getText(),
            txtAreaLanguage.getText(),
            txtAreaAditionalInformation.getText(),
            txtFieldProfile.getText()
        };

        int[] maxLengths = {150, 150, 4, 20, 150, 40};
        String[] fieldNames = {"Objetivo", "Temas de interés", "Año", "Idioma", "Información adicional", "Perfil"};

        for (int i = 0; i < fieldsToCheckLength.length; i++) {
            if (!DataValidation.validateLengthField(fieldsToCheckLength[i], maxLengths[i])) {
                Alerts.showWarningAlert(fieldNames[i] + " excede la longitud máxima permitida");
                return false;
            }
        }
        return true;
    }

    private boolean validateNumberStudentsField() {
        if (!DataValidation.validateNumberStudents(txtFieldNumberStudents.getText())) {
            Alerts.showWarningAlert("Introduzca un número de estudiantes válido");
            return false;
        }
        return true;
    }

    private boolean validateYearField() {
        if (!DataValidation.validateYear(this.txtFieldYear.getText())) {
            Alerts.showWarningAlert("Año no válido, considere el año actual en adelante");
            return false;
        }
        return true;
    }

    private boolean validatePeriodField() {
        if (!DataValidation.validatePeriodValidity(this.cmbBoxPeriod.getSelectionModel().getSelectedItem(), this.txtFieldYear.getText())) {
            Alerts.showWarningAlert("Seleccione un periodo próximo a empezar");
            return false;
        }
        return true;
    }

    private boolean validateFields() {
        return validateNotEmptyFields() && validateFieldLengths() && validateWords() && validateNumberStudentsField() && validateYearField() && validatePeriodField();
    }
    
    @FXML
    public void postCollaborationOffer() {
        if (validateFields()) {
            CollaborationOffer currentOffer = createOffer();
            try {
                int offerInsertedSuccess = COLLABORATION_OFFER_DAO.insertColaborationOffer(currentOffer);
                if (offerInsertedSuccess == 1) {
                    Alerts.showInformationAlert("Hecho", "Su oferta será evaluada, espere un correo con el resultado");
                    Stage stage = (Stage) this.btnPost.getScene().getWindow();
                    stage.close();
                    OfferProfessorStage offerProfessorStage = new OfferProfessorStage();
                }
            } catch (LogicException logicException) {
                Alerts.displayAlertLogicException(logicException);
                LOG.error(logicException);
            } catch (IOException ioException) {
                Alerts.displayAlertIOException();
                LOG.error(ioException);
            }
        }
    }
    
    @FXML
    public void cancelPost() {
        Stage stage = (Stage) this.txtAreaObjective.getScene().getWindow();
        stage.close();
        try {
            OfferProfessorStage offerProfessorStage = new OfferProfessorStage();
        } catch (IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
}
