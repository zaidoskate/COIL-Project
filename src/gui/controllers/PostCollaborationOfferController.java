/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.controllers;

import gui.DataValidation;
import gui.SessionManager;
import gui.stages.OfferProfessorStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.DAOs.CollaborationOfferDAO;
import logic.LogicException;
import logic.domain.CollaborationOffer;

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
    
    private SessionManager currentSession = SessionManager.getInstance();
    private CollaborationOfferDAO collaborationOfferDAO = new CollaborationOfferDAO();
    
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
    
    private void showInformationAlert(String headerText, String contentText) {
        showAlert(Alert.AlertType.INFORMATION, headerText, contentText);
    }
    
    private void showWarningAlert(String contentText) {
        showAlert(Alert.AlertType.WARNING, "Advertencia", contentText);
    }
    
    private void showErrorAlert(String contentText) {
        showAlert(Alert.AlertType.ERROR, "Error", contentText);
    }
    
    private void showAlert(Alert.AlertType alertType, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(headerText);
        alert.setTitle(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
    
    private boolean validateFields() {
        String objective = txtAreaObjective.getText();
        String topicsOfInterest = txtAreaTopicsOfInterest.getText();
        String period = txtFieldPeriod.getText();
        String language = txtAreaLanguage.getText();
        String additionalInformation = txtAreaAditionalInformation.getText();
        String profile = txtFieldProfile.getText();
        String numberStudents = txtFieldNumberStudents.getText();
        
        String[] fieldsObtained = {objective, topicsOfInterest, period, language, additionalInformation, profile, numberStudents};
        for(String field : fieldsObtained) {
            if(!DataValidation.validateWord(field)) {
                showErrorAlert("Datos incorrectos, inténtalo de nuevo");
                return false;
            }
        }
        if(!DataValidation.validateNotBlanks(numberStudents)) {
            showErrorAlert("Datos incorrectos, inténtalo de nuevo");
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
                    showInformationAlert("Hecho", "Su oferta será evaluada, espere un correo con el resultado");
                }
            } catch(LogicException logicException) {
                showErrorAlert(logicException.getMessage());
            }
        }
    }
    
    @FXML
    public void cancelPost() {
        showWarningAlert("¿Está seguro de que desea cancelar la oferta de colaboración?");
        Stage stage = (Stage) this.txtAreaObjective.getScene().getWindow();
        stage.close();
        try {
            OfferProfessorStage offerStage = new OfferProfessorStage();
        } catch (IOException ioException) {
            
        }
    }
    
}