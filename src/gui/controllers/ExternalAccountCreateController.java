/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.AccountCreator;
import logic.DAOs.ExternalAccountRequestDAO;
import logic.DAOs.UniversityDAO;
import logic.LogicException;
import logic.domain.ExternalAccountRequest;
import logic.domain.University;

/**
 *
 * @author chuch
 */
public class ExternalAccountCreateController implements Initializable {

    private ArrayList<University> universities;
    @FXML
    private ComboBox<String> comboBoxUniversities;
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(logicException.getMessage());
            alert.showAndWait();
            
            Stage stage = (Stage) comboBoxUniversities.getScene().getWindow();
            stage.close();
        }
        
        for(University university:universities) {
            universitiesNames.add(university.getName());
        }
        comboBoxUniversities.getItems().addAll(universitiesNames);
    }
    
    private void clearFields() {
        txtFieldName.setText("");
        txtFieldLastName.setText("");
        txtFieldEmail.setText("");
        comboBoxUniversities.getSelectionModel().clearSelection();
    }
    
    private boolean makeValidations() {
        String name = txtFieldName.getText();
        String lastName = txtFieldLastName.getText();
        String email = txtFieldEmail.getText(); 
        boolean result = true;
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
        
        if(!DataValidation.validateName(name) || !DataValidation.validateName(lastName)) {
            result = false;
            alert.setContentText("Nombre(s) o Apellido(s) invalido.");
        }
        if(!DataValidation.validateEmail(email)) {
            result = false;
            alert.setContentText("Formato de correo invalido.");
        }
        if(comboBoxUniversities.getSelectionModel().getSelectedIndex() == -1) {
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
        
        ExternalAccountRequest externalAccountRequest = new ExternalAccountRequest();
        externalAccountRequest.setEmail(email);
        externalAccountRequest.setLastName(lastName);
        externalAccountRequest.setName(name);
        externalAccountRequest.setIdUniversity(comboBoxUniversities.getSelectionModel().getSelectedIndex());
        
        boolean result = false;
        
        try {
            result = AccountCreator.createExternalAccount(externalAccountRequest);
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
        }
        if(result == true) {
            Alerts.displayAccountSent();
            Stage stage = (Stage) txtFieldName.getScene().getWindow();
            stage.close();
        }
        clearFields();
    }
    
    @FXML
    private void cancelExternalAccount() {
        Alerts.displayAccountNoSent();
        Stage stage = (Stage) txtFieldName.getScene().getWindow();
        stage.close();
    }
}
