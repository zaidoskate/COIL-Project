/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui.controllers;

import dataaccess.DatabaseConnection;
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
import logic.DAOs.DepartmentDAO;
import logic.DAOs.ExternalAccountRequestDAO;
import logic.domain.University;
import logic.DAOs.UniversityDAO;
import logic.DAOs.UvAccountRequestDAO;
import logic.LogicException;
import logic.domain.Department;
import logic.domain.ExternalAccountRequest;
import logic.domain.UvAccountRequest;
import org.apache.log4j.Logger;


public class AccountRequestController implements Initializable {
    private ArrayList<University> universities;
    private ArrayList<Department> departments;
    @FXML
    private ComboBox<String> comboBoxUniversities;
    @FXML
    private ComboBox<String> comboBoxRegions;
    @FXML
    private ComboBox<String> comboBoxDepartments;
    @FXML
    private VBox vBoxRegion;
    @FXML
    private VBox vBoxDepartment;
    @FXML
    private VBox vBoxPersonalNumber;
    @FXML
    private TextField txtFieldName;
    @FXML
    private TextField txtFieldLastName;
    @FXML
    private TextField txtFieldEmail;
    @FXML
    private TextField txtFieldPersonalNumber;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUniversities();
    }

    private void loadUniversities() {
        ArrayList<String> universitiesNames = new ArrayList<>();
        universitiesNames.add("Universidad Veracruzana");
        universities = new ArrayList<>();
        UniversityDAO universityDAO = new UniversityDAO();
        
        try{
            universities = universityDAO.getUniversities();
        } catch(LogicException logicException) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(logicException.getMessage());
            alert.showAndWait();
            
            Stage stage = (Stage) vBoxRegion.getScene().getWindow();
            stage.close();
        }
        
        for(University university:universities) {
            universitiesNames.add(university.getName());
        }
        comboBoxUniversities.getItems().addAll(universitiesNames);
    }
    
    private void loadRegions() {
        ArrayList<String> regions = new ArrayList<>();
        DepartmentDAO departmentDAO = new DepartmentDAO();
        try{
            regions = departmentDAO.getRegionsNames();
        } catch(LogicException logicException) {
        }
        comboBoxRegions.getItems().addAll(regions);
    }
    
    private void loadDepartments(String region) {
        ArrayList<String> departmentsNames = new ArrayList<>();
        departments = new ArrayList<>();
        DepartmentDAO departmentDAO = new DepartmentDAO();
        try{
            departments = departmentDAO.getDepartmentsByRegion(region);
        } catch(LogicException logicException) {
            
        }
        for(Department department:departments) {
            departmentsNames.add(department.getName());
        }
        comboBoxDepartments.getItems().addAll(departmentsNames);
    }
    
    private boolean makeValidations() {
        String name = txtFieldName.getText();
        String lastName = txtFieldLastName.getText();
        String email = txtFieldEmail.getText(); 
        String personalNumber = txtFieldPersonalNumber.getText(); 
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
        if(comboBoxUniversities.getSelectionModel().getSelectedIndex() == 0) {
            if(comboBoxDepartments.getSelectionModel().getSelectedIndex() == -1 || comboBoxDepartments.getSelectionModel().getSelectedIndex() == -1) {
                result = false;
                alert.setContentText("Selecciona region y facultad.");
            }
            if(!DataValidation.validatePersonalNumberFormat(personalNumber)) {
                result = false;
                alert.setContentText("Formato de numero de personal invalido");
            }
            if(!DataValidation.validatePersonalNumberExists(personalNumber)) {
                result = false;
                alert.setContentText("Numero de personal existente");
            }
        }
        
        if(!result) {
            alert.showAndWait();
        }
        
        return result;
    }
    
    private void clearFields() {
        txtFieldName.setText("");
        txtFieldLastName.setText("");
        txtFieldEmail.setText("");
        txtFieldPersonalNumber.setText("");
        comboBoxUniversities.getSelectionModel().clearSelection();
        comboBoxDepartments.getSelectionModel().clearSelection();
        comboBoxRegions.getSelectionModel().clearSelection();
    }
    
    @FXML
    private void universitySelected() {
        comboBoxRegions.getItems().clear();
        if (comboBoxUniversities.getSelectionModel().getSelectedIndex() == 0) {
            loadRegions();
            vBoxRegion.setVisible(true);
            vBoxDepartment.setVisible(true);
            vBoxPersonalNumber.setVisible(true);
        } else {
            vBoxRegion.setVisible(false);
            vBoxDepartment.setVisible(false);
            vBoxPersonalNumber.setVisible(false);
        }
    }
    
    @FXML
    private void regionSelected() {
        comboBoxDepartments.getItems().clear();
        String regionSelected = comboBoxRegions.getSelectionModel().getSelectedItem();
        loadDepartments(regionSelected);
    }
    
    @FXML
    private void saveAccountRequest() {
        String name = txtFieldName.getText();
        String lastName = txtFieldLastName.getText();
        String email = txtFieldEmail.getText(); 
        String personalNumber = txtFieldPersonalNumber.getText(); 
        int result = 0;
        
        if(!makeValidations()) {
            clearFields();
            return;
        }
        
        if (comboBoxUniversities.getSelectionModel().getSelectedIndex() == 0) {
            Department departmentSelected = departments.get(comboBoxDepartments.getSelectionModel().getSelectedIndex());
            
            UvAccountRequest uvAccountRequest = new UvAccountRequest();
            uvAccountRequest.setEmail(email);
            uvAccountRequest.setIdDepartment(departmentSelected.getIdDepartment());
            uvAccountRequest.setLastName(lastName);
            uvAccountRequest.setName(name);
            uvAccountRequest.setPersonalNumber(personalNumber);
            
            UvAccountRequestDAO uvAccountRequestDAO = new UvAccountRequestDAO();
            try {
                result = uvAccountRequestDAO.insertUvAccountRequest(uvAccountRequest);
            } catch(LogicException logicException) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText(logicException.getMessage());
                alert.showAndWait();
            }
        } else {
            ExternalAccountRequest externalAccountRequest = new ExternalAccountRequest();
            externalAccountRequest.setEmail(email);
            externalAccountRequest.setLastName(lastName);
            externalAccountRequest.setName(name);
            externalAccountRequest.setIdUniversity(comboBoxUniversities.getSelectionModel().getSelectedIndex());
            
            ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
            try {
                result = externalAccountRequestDAO.insertExternalAccountRequest(externalAccountRequest);
            } catch(LogicException logicException) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText(logicException.getMessage());
                alert.showAndWait();
            }
        }
        if(result == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Se ha registrado exitosamente su solicitud. Debe esperar un correo con sus datos de acceso.");
            alert.showAndWait();
            Stage stage = (Stage) vBoxRegion.getScene().getWindow();
            stage.close();
        }
        clearFields();
    }
    @FXML
    private void cancelAccountRequest() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("No se ha enviado la solicitud de cuenta de acceso.");
        alert.showAndWait();
        Stage stage = (Stage) vBoxRegion.getScene().getWindow();
        stage.close();
    }
}
