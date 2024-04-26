/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui.controllers;

import gui.DataValidation;
import gui.stages.CoordinatorMenuStage;
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

/**
 * FXML Controller class
 *
 * @author chuch
 */
public class UniversityRegistrationController implements Initializable {
    @FXML
    private TextField txtFieldName;
    @FXML
    private TextField txtFieldCountry;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private void previusMenu() {
        Stage stage = (Stage) txtFieldName.getScene().getWindow();
        stage.close();
        try{
            UniversitiesStage universitiesStage = new UniversitiesStage();
        } catch(IOException ioException) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ERROR, intentalo mas tarde.");
            alert.showAndWait();
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
        
        UniversityDAO universityDAO = new UniversityDAO();
        try{
            result = universityDAO.insertUniversity(university);
        } catch(LogicException logicException) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(logicException.getMessage());
            alert.showAndWait();
        }
        
        if(result==1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Se ha registrado exitosamente la Universidad.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("No se ha podido guardar la informacion de la Universidad.");
            alert.showAndWait();
        }
        clearFields();
    }
    
    private boolean makeValidations() {
        String universityName = txtFieldName.getText();
        String country = txtFieldCountry.getText();
        boolean result = true;
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
        
        if(!DataValidation.validateName(universityName)) {
            result = false;
            alert.setContentText("Nombre con formato invalido");
        }
        if(!DataValidation.validateName(country)) {
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
