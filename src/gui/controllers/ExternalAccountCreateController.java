package gui.controllers;

import gui.Alerts;
import gui.DataValidation;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.AccountCreator;
import logic.DAOs.UniversityDAO;
import logic.LogicException;
import logic.domain.ExternalAccountRequest;
import logic.domain.University;
import org.apache.log4j.Logger;

public class ExternalAccountCreateController implements Initializable {
    private static final Logger LOG = Logger.getLogger(ExternalAccountCreateController.class);

    private ArrayList<University> universities;
    @FXML
    private ComboBox<String> cmbBoxUniversities;
    @FXML
    private TextField txtFieldName;
    @FXML
    private TextField txtFieldLastName;
    @FXML
    private TextField txtFieldEmail;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUniversities();
    }
    private void loadUniversities() {
        ArrayList<String> universitiesNames = new ArrayList<>();
        universities = new ArrayList<>();
        UniversityDAO universityDAO = new UniversityDAO();
        
        try{
            universities = universityDAO.getUniversities();
        } catch(LogicException logicException) {
            LOG.error(logicException);
            Alerts.displayAlertLogicException(logicException);
            
            Stage stage = (Stage) cmbBoxUniversities.getScene().getWindow();
            stage.close();
        }
        
        for(University university:universities) {
            universitiesNames.add(university.getName());
        }
        cmbBoxUniversities.getItems().addAll(universitiesNames);
    }
    
    private boolean validateName(String name) {
        if( !DataValidation.validateNotBlanks(name)){
            Alerts.showWarningAlert("El nombre es un campo obligatorio.");
            return false;
        }
        if(!DataValidation.validateLengthField(name, 45)) {
            Alerts.showWarningAlert("El nombre excede el tamaño de 45 caracteres.");
            return false;
        }
        if(!DataValidation.validateName(name)) {
            Alerts.showWarningAlert("El nombre tiene formato inválido, escribe con mayúscula cada palabra.");
            return false;
        }
        return true;
    }
    private boolean validateLastName(String lastName) {
        if( !DataValidation.validateNotBlanks(lastName)){
            Alerts.showWarningAlert("El apellido es un campo obligatorio.");
            return false;
        }
        if(!DataValidation.validateLengthField(lastName, 45)) {
            Alerts.showWarningAlert("El apellido excede el tamaño de 45 caracteres.");
            return false;
        }
        if(!DataValidation.validateName(lastName)) {
            Alerts.showWarningAlert("El apellido tiene formato inválido, escribe con mayúscula cada palabra.");
            return false;
        }
        return true;
    }
    private boolean validateEmail(String email) {
        if( !DataValidation.validateNotBlanks(email)){
            Alerts.showWarningAlert("El correo es un campo obligatorio.");
            return false;
        }
        if(!DataValidation.validateLengthField(email, 45)) {
            Alerts.showWarningAlert("El nombre excede el tamaño de 45 caracteres.");
            return false;
        }
        if(!DataValidation.validateEmail(email)) {
            Alerts.showWarningAlert("El correo tiene formato inválido.");
            return false;
        }
        return true;
    }
    
    private boolean makeValidations() {
        String name = DataValidation.trimUnnecesaryBlanks(txtFieldName.getText());
        String lastName = DataValidation.trimUnnecesaryBlanks(txtFieldLastName.getText());
        String email = DataValidation.trimUnnecesaryBlanks(txtFieldEmail.getText()); 
        
        if(validateName(name) == false) {
            return false;
        }
        if(validateLastName(lastName) == false) {
            return false;
        }
        if(validateEmail(email) == false) {
            return false;
        }
        if(cmbBoxUniversities.getSelectionModel().getSelectedIndex() == -1) {
            Alerts.showWarningAlert("Universidad no seleccionada.");
            return false;
        }
        return true;
    }
    public void clearFields() {
        cmbBoxUniversities.getSelectionModel().clearSelection();
        txtFieldName.setText("");
        txtFieldLastName.setText("");
        txtFieldEmail.setText("");
    }
    
    @FXML
    private void createExternalAccount() {
        if(!makeValidations()) {
            return;
        }
        String name = DataValidation.trimUnnecesaryBlanks(txtFieldName.getText());
        String lastName = DataValidation.trimUnnecesaryBlanks(txtFieldLastName.getText());
        String email = DataValidation.trimUnnecesaryBlanks(txtFieldEmail.getText()); 
        University universitySelected = universities.get(cmbBoxUniversities.getSelectionModel().getSelectedIndex());
        
        ExternalAccountRequest externalAccountRequest = new ExternalAccountRequest();
        externalAccountRequest.setEmail(email);
        externalAccountRequest.setLastName(lastName);
        externalAccountRequest.setName(name);
        externalAccountRequest.setIdUniversity(universitySelected.getUniversityId());
        
        boolean result = false;
        
        try {
            result = AccountCreator.createExternalAccount(externalAccountRequest);
        } catch(LogicException logicException) {
            LOG.error(logicException);
            Alerts.displayAlertLogicException(logicException);
        }
        if(result == true) {
            Alerts.showInformationAlert("Éxito", "El correo se ha enviado a su destino con la clave de acceso");
        }
        clearFields();
    }
    
    @FXML
    private void cancelExternalAccount() {
        Stage stage = (Stage) txtFieldName.getScene().getWindow();
        stage.close();
    }
}
