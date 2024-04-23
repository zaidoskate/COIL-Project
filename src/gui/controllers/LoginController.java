/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui.controllers;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import logic.DAOs.CredentialDAO;
import logic.DAOs.UserDAO;
import logic.LogicException;
import logic.domain.Credential;
import gui.SessionManager;
import gui.stages.CoordinatorMenuStage;
import javafx.stage.Stage;
import logic.domain.User;
import logic.model.UserData;
import gui.stages.AccountRequestStage;
import gui.stages.ProfesorMenuStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

public class LoginController implements Initializable {
    @FXML
    private TextField textFieldUser;
    
    @FXML
    private PasswordField passwordField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void displayAccountRequest() {
        try{
            AccountRequestStage accountRequestStage = new AccountRequestStage();
            
        } catch(IOException ioException) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("ERROR, intentalo mas tarde.");
                alert.showAndWait();
        }
        
    }
    
    @FXML
    private void logIn() {
        String user = textFieldUser.getText();
        String password = passwordField.getText();
        Credential credential = new Credential();
        credential.setUser(user);
        credential.setPassword(password);
        CredentialDAO credentialDAO = new CredentialDAO();
        try {
            int idUser = credentialDAO.getIdUserByCredential(credential);
            if(idUser != -1) {
                createSession(idUser);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Usuario y contraseña incorrectos.");
                alert.showAndWait();
            }
        } catch(LogicException logicException) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText(logicException.getMessage());
                alert.showAndWait();
        } finally {
            textFieldUser.clear();
            passwordField.clear();
        }
            
    }
    
    private void createSession(int idUser) throws LogicException{
        UserDAO userDAO = new UserDAO();
        String typeUser = userDAO.getUserTypeById(idUser);
        User user = userDAO.getUserById(idUser);
        UserData userData = new UserData();
        userData.setIdUser(idUser);
        userData.setName(user.getName());
        userData.setTypeUser(typeUser);
        SessionManager.getInstance().login(userData);
        displayMenu(typeUser);
    }
    
    private void displayMenu(String typeUser) {
        if(typeUser.equals("Coordinador")) {
            try{
                CoordinatorMenuStage coordinatorMenuStage = new CoordinatorMenuStage();
            } catch(IOException ioException) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("ERROR, intentalo mas tarde.");
                alert.showAndWait();
            }
        } else {
            try{
                ProfesorMenuStage profesorMenuStage = new ProfesorMenuStage();
            } catch(IOException ioException) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("ERROR, intentalo mas tarde.");
                alert.showAndWait();
            }
        }
        Stage stage = (Stage) textFieldUser.getScene().getWindow();
        stage.close();
    }
}
