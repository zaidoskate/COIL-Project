package gui.controllers;

import gui.Alerts;
import gui.DataValidation;
import gui.stages.UniversitiesStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    
    private boolean validateUniversityName(String universityName) {
        if( !DataValidation.validateNotBlanks(universityName)){
            Alerts.showWarningAlert("La universidad es un campo obligatorio.");
            return false;
        }
        if(!DataValidation.validateName(universityName)){
            Alerts.showWarningAlert("El nombre de la Universidad es inválido, ingresa el nombre completo.");
            return false;
        }
        if(!DataValidation.validateLengthField(universityName, 45)) {
            Alerts.showWarningAlert("El nombre de la Universidad es demasiado largo.");
            return false;
        }
        return true;
    }
    
    private boolean validateCountryName(String countryName) {
        if( !DataValidation.validateNotBlanks(countryName)){
            Alerts.showWarningAlert("El país es un campo obligatorio.");
            return false;
        }
        if(!DataValidation.validateName(countryName)){
            Alerts.showWarningAlert("El nombre del país es inválido, ingresa el nombre completo.");
            return false;
        }
        if(!DataValidation.validateLengthField(countryName, 45)) {
            Alerts.showWarningAlert("El nombre del país es demasiado largo.");
            return false;
        }
        return true;
    }
    
    private boolean makeValidations() {
        String universityName = DataValidation.trimUnnecesaryBlanks(txtFieldName.getText());
        String country = DataValidation.trimUnnecesaryBlanks(txtFieldCountry.getText());
        if(!DataValidation.validateName(universityName) || !DataValidation.validateLengthField(universityName, 45)) {
        }
        if(!DataValidation.validateName(country) || !DataValidation.validateLengthField(country, 45)) {
        }
        return true;
    }
    public void clearFields() {
        txtFieldName.setText("");
        txtFieldCountry.setText("");
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
            return;
        }
        String universityName = DataValidation.trimUnnecesaryBlanks(txtFieldName.getText());
        String country = DataValidation.trimUnnecesaryBlanks(txtFieldCountry.getText());
        
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
            clearFields();
        } else {
            Alerts.showWarningAlert("No se ha podido guardar la informacion de la Universidad.");
        }
    }
}
