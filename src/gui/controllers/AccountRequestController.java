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
    private static final UniversityDAO UNIVERSITY_DAO = new UniversityDAO();
    private static final DepartmentDAO DEPARTMENT_DAO = new DepartmentDAO();
    private static final UvAccountRequestDAO UV_ACCOUNT_REQUEST_DAO = new UvAccountRequestDAO();
    private static final ExternalAccountRequestDAO EXTERNAL_ACCOUNT_REQUEST_DAO = new ExternalAccountRequestDAO();
    private ArrayList<University> universities;
    private ArrayList<Department> departments;
    private static final Logger LOG = Logger.getLogger(AccountRequestController.class);
    
    @FXML
    private ComboBox<String> cmbBoxUniversities;
    @FXML
    private ComboBox<String> cmbBoxRegions;
    @FXML
    private ComboBox<String> cmbBoxDepartments;
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
        
        try{
            universities = UNIVERSITY_DAO.getUniversities();
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            LOG.error(logicException);
            Stage stage = (Stage) vBoxRegion.getScene().getWindow();
            stage.close();
        }
        
        for(University university:universities) {
            universitiesNames.add(university.getName());
        }
        cmbBoxUniversities.getItems().addAll(universitiesNames);
    }
    
    private void loadRegions() {
        ArrayList<String> regions = new ArrayList<>();
        try{
            regions = DEPARTMENT_DAO.getRegionsNames();
        } catch(LogicException logicException) {
            LOG.error(logicException);
        }
        cmbBoxRegions.getItems().addAll(regions);
    }
    
    private void loadDepartments(String region) {
        ArrayList<String> departmentsNames = new ArrayList<>();
        departments = new ArrayList<>();
        try{
            departments = DEPARTMENT_DAO.getDepartmentsByRegion(region);
        } catch(LogicException logicException) {
            LOG.error(logicException);
        }
        for(Department department:departments) {
            departmentsNames.add(department.getName());
        }
        cmbBoxDepartments.getItems().addAll(departmentsNames);
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
    private boolean validatePersonalNumber(String personalNumber) {
        if( !DataValidation.validateNotBlanks(personalNumber)){
            Alerts.showWarningAlert("El numero de personal es un campo obligatorio.");
            return false;
        }
        if(!DataValidation.validatePersonalNumberFormat(personalNumber)) {
            Alerts.showWarningAlert("Formato de numero de personal invalido.");
            return false;
        }
        if(!DataValidation.validatePersonalNumberExists(personalNumber)) {
            Alerts.showWarningAlert("Numero de personal existente.");
            return false;
        }
        return true;
    }
    
    private boolean makeValidations() {
        String name = DataValidation.trimUnnecesaryBlanks(txtFieldName.getText());
        String lastName = DataValidation.trimUnnecesaryBlanks(txtFieldLastName.getText());
        String email = DataValidation.trimUnnecesaryBlanks(txtFieldEmail.getText()); 
        String personalNumber = DataValidation.trimUnnecesaryBlanks(txtFieldPersonalNumber.getText()); 
        
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
        if(cmbBoxUniversities.getSelectionModel().getSelectedIndex() == 0) {
            if(cmbBoxDepartments.getSelectionModel().getSelectedIndex() == -1 || cmbBoxDepartments.getSelectionModel().getSelectedIndex() == -1) {
                Alerts.showWarningAlert("Selecciona region y facultad.");
                return false;
            }
            if(validatePersonalNumber(personalNumber) == false) {
                return false;
            }
        }
        return true;
    }
    
    
    @FXML
    private void universitySelected() {
        cmbBoxRegions.getItems().clear();
        if (cmbBoxUniversities.getSelectionModel().getSelectedIndex() == 0) {
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
        cmbBoxDepartments.getItems().clear();
        String regionSelected = cmbBoxRegions.getSelectionModel().getSelectedItem();
        loadDepartments(regionSelected);
    }
    
    @FXML
    private void saveAccountRequest() {
        String name = DataValidation.trimUnnecesaryBlanks(txtFieldName.getText());
        String lastName = DataValidation.trimUnnecesaryBlanks(txtFieldLastName.getText());
        String email = DataValidation.trimUnnecesaryBlanks(txtFieldEmail.getText()); 
        String personalNumber = DataValidation.trimUnnecesaryBlanks(txtFieldPersonalNumber.getText()); 
        int result = 0;
        
        if(!makeValidations()) {
            return;
        }
        
        if (cmbBoxUniversities.getSelectionModel().getSelectedIndex() == 0) {
            Department departmentSelected = departments.get(cmbBoxDepartments.getSelectionModel().getSelectedIndex());
            
            UvAccountRequest uvAccountRequest = new UvAccountRequest();
            uvAccountRequest.setEmail(email);
            uvAccountRequest.setIdDepartment(departmentSelected.getIdDepartment());
            uvAccountRequest.setLastName(lastName);
            uvAccountRequest.setName(name);
            uvAccountRequest.setPersonalNumber(personalNumber);
            
            try {
                result = UV_ACCOUNT_REQUEST_DAO.insertUvAccountRequest(uvAccountRequest);
            } catch(LogicException logicException) {
                LOG.error(logicException);
                Alerts.displayAlertLogicException(logicException);
            }
        } else {
            University universitySelected = universities.get(cmbBoxUniversities.getSelectionModel().getSelectedIndex() - 1);
            
            ExternalAccountRequest externalAccountRequest = new ExternalAccountRequest();
            externalAccountRequest.setEmail(email);
            externalAccountRequest.setLastName(lastName);
            externalAccountRequest.setName(name);
            externalAccountRequest.setIdUniversity(universitySelected.getUniversityId());
            
            try {
                result = EXTERNAL_ACCOUNT_REQUEST_DAO.insertExternalAccountRequest(externalAccountRequest);
            } catch(LogicException logicException) {
                LOG.error(logicException);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText(logicException.getMessage());
                alert.showAndWait();
            }
        }
        if(result == 1) {
            Alerts.showInformationAlert("Exito", "Se ha registrado exitosamente su solicitud. Debe esperar un correo con sus datos de acceso.");
            Stage stage = (Stage) vBoxRegion.getScene().getWindow();
            stage.close();
        }
    }
    @FXML
    private void cancelAccountRequest() {
        Alerts.showWarningAlert("No se ha enviado la solicitud de cuenta de acceso.");
        Stage stage = (Stage) vBoxRegion.getScene().getWindow();
        stage.close();
    }
}
