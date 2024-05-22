package gui.controllers;

import gui.Alerts;
import gui.DataValidation;
import gui.stages.UniversitiesStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.DAOs.UniversityDAO;
import logic.LogicException;
import logic.domain.University;
import org.apache.log4j.Logger;

public class UniversityRegistrationController implements Initializable {
    private static final UniversityDAO UNIVERSITY_DAO = new UniversityDAO();
    private static final Logger LOG = Logger.getLogger(UniversityRegistrationController.class);
    @FXML
    private TextField txtFieldName;
    @FXML
    private TextField txtFieldCountry;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    @FXML
    private void previusMenu() {
        Stage stage = (Stage) txtFieldName.getScene().getWindow();
        stage.close();
        try{
            UniversitiesStage universitiesStage = new UniversitiesStage();
        } catch(IOException ioException) {
            LOG.warn(ioException);
            Alerts.displayAlertIOException();
        }
    }
    
    @FXML
    private void saveUniversity() {        
        if(!makeValidations()) {
            clearFields();
            return;
        }
        String universityName = txtFieldName.getText();
        String country = txtFieldCountry.getText();
        
        University university = new University();
        university.setName(universityName);
        university.setCountry(country);
        int result = 0;
        
        try{
            result = UNIVERSITY_DAO.insertUniversity(university);
        } catch(LogicException logicException) {
            LOG.error(logicException);
            Alerts.displayAlertLogicException(logicException);
        }
        
        if(result==1) {
            Alerts.showInformationAlert("Exito", "Se ha registrado exitosamente la Universidad.");
        } else {
            Alerts.showWarningAlert("No se ha podido guardar la informacion de la Universidad.");
        }
        clearFields();
    }
    
    private boolean makeValidations() {
        String universityName = txtFieldName.getText();
        String country = txtFieldCountry.getText();
        boolean result = true;
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
        
        if(!DataValidation.validateName(universityName) || !DataValidation.validateLengthField(universityName, 45)) {
            result = false;
            alert.setContentText("Nombre con formato invalido");
        }
        if(!DataValidation.validateName(country) || !DataValidation.validateLengthField(country, 45)) {
            result = false;
            alert.setContentText("Pais con formato invalido");
        }
        if(!result) {
            alert.showAndWait();
        }
        return result;
    }
    
    private void clearFields() {
        txtFieldName.setText("");
        txtFieldCountry.setText("");
    }
    
}
