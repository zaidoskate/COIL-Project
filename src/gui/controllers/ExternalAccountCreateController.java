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
    private static final Logger log = Logger.getLogger(ExternalAccountCreateController.class);

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
            log.error(logicException);
            Alerts.displayAlertLogicException(logicException);
            
            Stage stage = (Stage) cmbBoxUniversities.getScene().getWindow();
            stage.close();
        }
        
        for(University university:universities) {
            universitiesNames.add(university.getName());
        }
        cmbBoxUniversities.getItems().addAll(universitiesNames);
    }
    
    private void clearFields() {
        txtFieldName.setText("");
        txtFieldLastName.setText("");
        txtFieldEmail.setText("");
        cmbBoxUniversities.getSelectionModel().clearSelection();
    }
    
    private boolean makeValidations() {
        String name = txtFieldName.getText();
        String lastName = txtFieldLastName.getText();
        String email = txtFieldEmail.getText(); 
        boolean result = true;
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
        
        if(!DataValidation.validateLengthField(name, 50) || !DataValidation.validateLengthField(lastName, 25)) {
            result = false;
            alert.setContentText("Nombre(s) o Apellido(s) invalido.");
        }
        if(!DataValidation.validateName(name) || !DataValidation.validateName(lastName)) {
            result = false;
            alert.setContentText("Nombre(s) o Apellido(s) invalido.");
        }
        if(!DataValidation.validateEmail(email)) {
            result = false;
            alert.setContentText("Formato de correo invalido.");
        }
        if(cmbBoxUniversities.getSelectionModel().getSelectedIndex() == -1) {
            result = false;
            alert.setContentText("Universidad no seleccionada.");
        }
        
        if(!result) {
            alert.showAndWait();
        }
        
        return result;
    }
    
    @FXML
    private void createExternalAccount() {
        if(!makeValidations()) {
            clearFields();
            return;
        }
        String name = txtFieldName.getText();
        String lastName = txtFieldLastName.getText();
        String email = txtFieldEmail.getText(); 
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
            log.error(logicException);
            Alerts.displayAlertLogicException(logicException);
        }
        if(result == true) {
            Alerts.showInformationAlert("Exito", "El correo se ha enviado a su destino con la clave de acceso");
            Stage stage = (Stage) txtFieldName.getScene().getWindow();
            stage.close();
        }
        clearFields();
    }
    
    @FXML
    private void cancelExternalAccount() {
        Alerts.showWarningAlert("No se ha podido registrar ni enviar el correo a su destino, intentelo mas tarde.");
        Stage stage = (Stage) txtFieldName.getScene().getWindow();
        stage.close();
    }
}
