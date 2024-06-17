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
import gui.stages.AdminMenuStage;
import gui.stages.ProfesorMenuStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.apache.log4j.Logger;

public class LoginController implements Initializable {
    private static final Logger LOG = Logger.getLogger(LoginController.class);
    private static final UserDAO USER_DAO = new UserDAO();
    private static final CredentialDAO CREDENTIAL_DAO = new CredentialDAO();
    @FXML
    private TextField txtFieldUser;
    @FXML
    private PasswordField psswdFieldPassword;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    @FXML
    private void displayAccountRequest() {
        try {
            AccountRequestStage accountRequestStage = new AccountRequestStage();
            
        } catch (IOException ioException) {            
            LOG.warn(ioException);
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
        try {
            int idUser = CREDENTIAL_DAO.getIdUserByCredential(credential);
            if (idUser != -1) {
                createSession(idUser);
            } else {
                Alerts.showWarningAlert("Usuario y Contraseña incorrectos");
            }
        } catch (LogicException logicException) {
            LOG.error(logicException);
            Alerts.displayAlertLogicException(logicException);
        } finally {
            txtFieldUser.clear();
            psswdFieldPassword.clear();
        }
            
    }
    
    private void createSession(int idUser) throws LogicException{
        String typeUser = USER_DAO.getUserTypeById(idUser);
        User user = USER_DAO.getUserById(idUser);
        UserData userData = new UserData();
        userData.setIdUser(idUser);
        userData.setName(user.getName());
        userData.setTypeUser(typeUser);
        SessionManager.getInstance().login(userData);
        displayMenu(typeUser);
    }
    
    private void displayMenu(String typeUser) {
        if (typeUser.equals("Coordinador")) {
            try{
                CoordinatorMenuStage coordinatorMenuStage = new CoordinatorMenuStage();
            } catch(IOException ioException) {
                LOG.warn(ioException);
                Alerts.displayAlertIOException();
            }
        } else if (typeUser.equals("Administrador")) {
            try {
                AdminMenuStage adminMenuStage = new AdminMenuStage();
            } catch (IOException ioException) {
                LOG.warn(ioException);
                Alerts.displayAlertIOException();
            }
        } else {
            try {
                ProfesorMenuStage profesorMenuStage = new ProfesorMenuStage();
            } catch (IOException ioException) {
                LOG.warn(ioException);
                Alerts.displayAlertIOException();
            }
        }
        Stage stage = (Stage) txtFieldUser.getScene().getWindow();
        stage.close();
    }
}
