package gui.controllers;

import gui.Alerts;
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
import org.apache.log4j.Logger;

public class LoginController implements Initializable {
    private static final Logger log = Logger.getLogger(LoginController.class);
    @FXML
    private TextField txtFieldUser;
    
    @FXML
    private PasswordField psswdFieldPassword;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void displayAccountRequest() {
        try{
            AccountRequestStage accountRequestStage = new AccountRequestStage();
            
        } catch(IOException ioException) {            
            log.warn(ioException);
            Alerts.displayAlertIOException();
        }
        
    }
    
    @FXML
    private void logIn() {
        String user = txtFieldUser.getText();
        String password = psswdFieldPassword.getText();
        Credential credential = new Credential();
        credential.setUser(user);
        credential.setPassword(password);
        CredentialDAO credentialDAO = new CredentialDAO();
        try {
            int idUser = credentialDAO.getIdUserByCredential(credential);
            if(idUser != -1) {
                createSession(idUser);
            } else {
                Alerts.showWarningAlert("Usuario y Contraseña incorrectos");
            }
        } catch(LogicException logicException) {
            log.error(logicException);
            Alerts.displayAlertLogicException(logicException);
        } finally {
            txtFieldUser.clear();
            psswdFieldPassword.clear();
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
                log.warn(ioException);
                Alerts.displayAlertIOException();
            }
        } else {
            try{
                ProfesorMenuStage profesorMenuStage = new ProfesorMenuStage();
            } catch(IOException ioException) {
                log.warn(ioException);
                Alerts.displayAlertIOException();
            }
        }
        Stage stage = (Stage) txtFieldUser.getScene().getWindow();
        stage.close();
    }
}
