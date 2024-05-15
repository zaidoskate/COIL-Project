
package gui.controllers;

import gui.Alerts;
import gui.DataValidation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.DAOs.CoordinatorDAO;
import logic.DAOs.CredentialDAO;
import logic.DAOs.UserDAO;
import logic.LogicException;
import logic.MailSender;
import logic.domain.Coordinator;
import logic.domain.Credential;
import logic.domain.User;
import org.apache.log4j.Logger;

public class CreateCoordinatorAccountController implements Initializable {
    private static final Logger log = Logger.getLogger(CreateCoordinatorAccountController.class);
    @FXML
    TextField txtFieldName;
    @FXML
    TextField txtFieldLastname;
    @FXML
    TextField txtFieldEmail;
    @FXML
    TextField txtFieldPassword;
    @FXML
    TextField txtFieldUsername;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    private void clearFields() {
        txtFieldName.setText("");
        txtFieldLastname.setText("");
        txtFieldEmail.setText("");
        txtFieldPassword.setText("");
    }
    
    private boolean makeValidations() {
        String name = txtFieldName.getText();
        String lastName = txtFieldLastname.getText();
        String email = txtFieldEmail.getText(); 
        String password = txtFieldPassword.getText(); 
        String username = txtFieldUsername.getText(); 
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
        if(!DataValidation.validateWord(password)) {
            result = false;
            alert.setContentText("Formato de contraseña invalido.");
        }
        if(!DataValidation.validateWord(username)) {
            result = false;
            alert.setContentText("Formato de usuario invalido.");
        }
        
        if(!result) {
            alert.showAndWait();
        }
        
        return result;
    }
    
    private boolean sendEmail(User user, Credential credential) throws LogicException{
        String body;
        body = "Te damos la bienvenida al Sistema COIL-VIC de la Universidad Veracruzana."
                + "\nTus datos de acceso son los siguientes:"
                + "\nUsuario: "+credential.getUser()
                + "\nContrasena: "+credential.getPassword();
        
        boolean result = MailSender.sendEmail(body, user.getEmail());
    
        return result;
    }
    
    @FXML
    public void createAccount() {
        if(!makeValidations()) {
            clearFields();
            return;
        }
        String name = txtFieldName.getText();
        String lastName = txtFieldLastname.getText();
        String email = txtFieldEmail.getText(); 
        String password = txtFieldPassword.getText(); 
        String username = txtFieldUsername.getText(); 
        
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setLastName(lastName);
        
        int result = 0;
        int idUser = -1;
        
        try{
            UserDAO userDAO = new UserDAO();
            idUser = userDAO.addUser(user);
        } catch(LogicException logicException) {
            log.error(logicException);
            Alerts.displayAlertLogicException(logicException);
            clearFields();
        }
        if(idUser != -1) {
            Coordinator coordinator = new Coordinator();
            coordinator.setIdUser(idUser);
            Credential credential = new Credential();
            credential.setIdUser(idUser);
            credential.setPassword(password);
            credential.setUser(username);
            
            CoordinatorDAO coordinatorDAO = new CoordinatorDAO();
            CredentialDAO credentialDAO = new CredentialDAO();
            
            try {
                result = coordinatorDAO.insertCoordinator(coordinator);
                result = credentialDAO.insertCredential(credential);
            } catch(LogicException logicException) {
                clearFields();
                log.error(logicException);
                Alerts.displayAlertLogicException(logicException);
            }
            boolean emailSent = false;
            if(result > 0) {
                try {
                    emailSent = sendEmail(user,credential);
                } catch(LogicException logicException) {
                    clearFields();
                    log.error(logicException);
                    Alerts.displayAlertLogicException(logicException);
                }
            }
            if(emailSent == true) {
                clearFields();
                Alerts.showInformationAlert("Exito", "Se ha enviado el usuario y contraseña al correo.");
            }
        } 
    }
    
    @FXML
    public void cancel() {
        Alerts.showWarningAlert("No se ha creado la cuenta.");
        Stage stage = (Stage) txtFieldName.getScene().getWindow();
        stage.close();
    }

}
