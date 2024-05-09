/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.DAOs.CollaborationOfferDAO;
import logic.LogicException;
import logic.domain.CollaborationOffer;
import org.apache.log4j.Logger;

/**
 *
 * @author zaido
 */
public class PostCollaborationOfferController implements Initializable {

    @FXML
    private TextArea txtAreaObjective;
    
    @FXML
    private TextArea txtAreaTopicsOfInterest;
    
    @FXML
    private TextField txtFieldPeriod;
    
    @FXML
    private TextArea txtAreaLanguage;
    
    @FXML
    private TextArea txtAreaAditionalInformation;
    
    @FXML
    private TextField txtFieldNumberStudents;
    
    @FXML
    private TextField txtFieldProfile;
    
    @FXML
    private Button btnPost;
    
    @FXML
    private Button btnCancel;
    
    private static final SessionManager currentSession = SessionManager.getInstance();
    private static final CollaborationOfferDAO collaborationOfferDAO = new CollaborationOfferDAO();
    
    private static final Logger log = Logger.getLogger(PostCollaborationOfferController.class);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    private CollaborationOffer createOffer() {
        String objective = this.txtAreaObjective.getText();
        String topicsOfInterest = this.txtAreaTopicsOfInterest.getText();
        String period = this.txtFieldPeriod.getText();
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
        currentOffer.setIdUser(currentSession.getUserData().getIdUser());
        return currentOffer;
    }
    
    private boolean validateFields() {
        String[] fields = {
            txtAreaObjective.getText(),
            txtAreaTopicsOfInterest.getText(),
            txtFieldPeriod.getText(),
            txtAreaLanguage.getText(),
            txtAreaAditionalInformation.getText(),
            txtFieldProfile.getText(),
            txtFieldNumberStudents.getText()
        };

        for (String field : fields) {
            if (!DataValidation.validateWord(field)) {
                Alerts.showWarningAlert("Datos incorrectos, inténtalo de nuevo");
                return false;
            }
        }

        String[] fieldsToCheckLength = {
            txtAreaObjective.getText(),
            txtAreaTopicsOfInterest.getText(),
            txtFieldPeriod.getText(),
            txtAreaLanguage.getText(),
            txtAreaAditionalInformation.getText(),
            txtFieldProfile.getText()
        };

        int[] maxLengths = {150, 150, 50, 20, 150, 40};
        String[] fieldNames = {"Objetivo", "Temas de interés", "Periodo", "Idioma", "Información adicional", "Perfil"};

        for (int i = 0; i < fieldsToCheckLength.length; i++) {
            if (!DataValidation.validateLengthField(fieldsToCheckLength[i], maxLengths[i])) {
                Alerts.showWarningAlert(fieldNames[i] + " excede la longitud máxima permitida");
                return false;
            }
        }

        if (!DataValidation.validateNotBlanks(txtFieldNumberStudents.getText())) {
            Alerts.showWarningAlert("Número de estudiantes no puede estar en blanco");
            return false;
        }

        return true;
    }


    
    @FXML
    public void postCollaborationOffer() {
        if(validateFields()){
            CollaborationOffer currentOffer = createOffer();
            try {
                int offerInsertedSuccess = collaborationOfferDAO.insertColaborationOffer(currentOffer);
                if(offerInsertedSuccess == 1) {
                    Alerts.showInformationAlert("Hecho", "Su oferta será evaluada, espere un correo con el resultado");
                    Stage stage = (Stage) this.btnPost.getScene().getWindow();
                    stage.close();
                }
            } catch(LogicException logicException) {
                Alerts.displayAlertLogicException(logicException);
                log.error(logicException);
            }
        }
    }
    
    @FXML
    public void cancelPost() {
        Alerts.showWarningAlert("¿Está seguro de que desea cancelar la oferta de colaboración?");
        Stage stage = (Stage) this.txtAreaObjective.getScene().getWindow();
        stage.close();
        try {
            OfferProfessorStage offerStage = new OfferProfessorStage();
        } catch (IOException ioException) {
            Alerts.displayAlertIOException();
            log.error(ioException);
        }
    }
    
}
