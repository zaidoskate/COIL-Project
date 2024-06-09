
package gui.controllers;

import gui.Alerts;
import gui.DataValidation;
import gui.SessionManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.DAOs.CoordinatorDAO;
import logic.DAOs.CredentialDAO;
import logic.DAOs.PendingMailDAO;
import logic.DAOs.UserDAO;
import logic.LogicException;
import logic.MailSender;
import logic.domain.Coordinator;
import logic.domain.Credential;
import logic.domain.PendingMail;
import logic.domain.User;
import org.apache.log4j.Logger;

public class CreateCoordinatorAccountController implements Initializable {
    private static final Logger LOG = Logger.getLogger(CreateCoordinatorAccountController.class);
    private final SessionManager currentSession = SessionManager.getInstance();
    private static final PendingMailDAO PENDING_MAIL_DAO = new PendingMailDAO();
    private static final CoordinatorDAO COORDINATOR_DAO = new CoordinatorDAO();
    private static final CredentialDAO CREDENTIAL_DAO = new CredentialDAO();
    private static final UserDAO USER_DAO = new UserDAO();
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
    private static boolean validateNameField(String name) {
        if( !DataValidation.validateNotBlanks(name)){
            Alerts.showWarningAlert("El nombre es un campo obligatorio.");
            return false;
        }
        if(!DataValidation.validateLengthField(name, 45) ) {
            Alerts.showWarningAlert("Nombre demasiado largo.");
            return false;
        }
        if (!DataValidation.validateName(name)) {
            Alerts.showWarningAlert("Nombre(s) invalido.");
            return false;
        }
        return true;
    }

    private static boolean validateLastNameField(String lastName) {
        if( !DataValidation.validateNotBlanks(lastName)){
            Alerts.showWarningAlert("El apellido es un campo obligatorio.");
            return false;
        }
        if(!DataValidation.validateLengthField(lastName, 45) ) {
            Alerts.showWarningAlert("Apellido demasiado largo.");
            return false;
        }
        if (!DataValidation.validateName(lastName)) {
            Alerts.showWarningAlert("Apellido invalido.");
            return false;
        }
        return true;
    }

    private static boolean validateEmailField(String email) {
        if( !DataValidation.validateNotBlanks(email)){
            Alerts.showWarningAlert("El correo es un campo obligatorio.");
            return false;
        }
        if(!DataValidation.validateLengthField(email, 45) ) {
            Alerts.showWarningAlert("El correo demasiado largo.");
            return false;
        }
        if (!DataValidation.validateEmail(email)) {
            Alerts.showWarningAlert("Formato de correo invalido.");
            return false;
        }
        return true;
    }

    private static boolean validatePasswordField(String password) {
        if( !DataValidation.validateNotBlanks(password)){
            Alerts.showWarningAlert("La contraseña es un campo obligatorio.");
            return false;
        }
        if(!DataValidation.validateLengthField(password, 45) ) {
            Alerts.showWarningAlert("La contraseña es demasiado larga.");
            return false;
        }
        if (!DataValidation.validateWord(password)) {
            Alerts.showWarningAlert("Formato de contraseña inválido. Utilice solo letras.");
            return false;
        }
        return true;
    }

    private static boolean validateUsernameField(String username) {
        if( !DataValidation.validateNotBlanks(username)){
            Alerts.showWarningAlert("El usuario es un campo obligatorio.");
            return false;
        }
        if(!DataValidation.validateLengthField(username, 45) ) {
            Alerts.showWarningAlert("El usuario es demasiado largo.");
            return false;
        }
        if (!DataValidation.validateWord(username)) {
            Alerts.showWarningAlert("Formato de usuario inválido. No utilice caracteres especiales");
            return false;
        }
        return true;
    }
    
    private boolean makeValidations() {
        String name = DataValidation.trimUnnecesaryBlanks(txtFieldName.getText());
        String lastName = DataValidation.trimUnnecesaryBlanks(txtFieldLastname.getText());
        String email = DataValidation.trimUnnecesaryBlanks(txtFieldEmail.getText()); 
        String password = DataValidation.trimUnnecesaryBlanks(txtFieldPassword.getText()); 
        String username = DataValidation.trimUnnecesaryBlanks(txtFieldUsername.getText()); 
        if(!validateNameField(name)) {
            return true;
        }
        if(!validateLastNameField(lastName)) {
            return true;
        }
        if(!validateEmailField(email)) {
            return true;
        }
        if(!validatePasswordField(password)) {
            return true;
        }
        if(!validateUsernameField(username)) {
            return true;
        }
        return true;
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
    
    private void registerPendingMail(User user, Credential credential) {
        String body = "Te damos la bienvenida al Sistema COIL-VIC de la Universidad Veracruzana."
                + "\nTus datos de acceso son los siguientes:"
                + "\nUsuario: "+credential.getUser()
                + "\nContrasena: "+credential.getPassword();
        int currentIdUser = currentSession.getUserData().getIdUser();
        
        PendingMail pendingMail = new PendingMail();
        pendingMail.setIdUser(currentIdUser);
        pendingMail.setContent(body);
        pendingMail.setSubject("Cuenta de acceso coordinador");
        pendingMail.setDestinationEmail(user.getEmail());
        
        try {
            PENDING_MAIL_DAO.insertPendingMail(pendingMail);
        } catch (LogicException logicException) {
            LOG.error(logicException);
        }
    }
    
    @FXML
    public void createAccount() {
        if(!makeValidations()) {
            return;
        }
        String name = DataValidation.trimUnnecesaryBlanks(txtFieldName.getText());
        String lastName = DataValidation.trimUnnecesaryBlanks(txtFieldLastname.getText());
        String email = DataValidation.trimUnnecesaryBlanks(txtFieldEmail.getText()); 
        String password = DataValidation.trimUnnecesaryBlanks(txtFieldPassword.getText()); 
        String username = DataValidation.trimUnnecesaryBlanks(txtFieldUsername.getText()); 
        
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setLastName(lastName);
        
        int result = 0;
        int idUser = -1;
        
        try{
            idUser = USER_DAO.addUser(user);
        } catch(LogicException logicException) {
            LOG.error(logicException);
            Alerts.displayAlertLogicException(logicException);
        }
        if(idUser != -1) {
            Coordinator coordinator = new Coordinator();
            coordinator.setIdUser(idUser);
            Credential credential = new Credential();
            credential.setIdUser(idUser);
            credential.setPassword(password);
            credential.setUser(username);
            
            try {
                result = COORDINATOR_DAO.insertCoordinator(coordinator);
                result = CREDENTIAL_DAO.insertCredential(credential);
            } catch(LogicException logicException) {
                LOG.error(logicException);
                Alerts.displayAlertLogicException(logicException);
            }
            boolean emailSent = false;
            if(result > 0) {
                try {
                    emailSent = sendEmail(user,credential);
                } catch(LogicException logicException) {
                    LOG.error(logicException);
                    Alerts.displayAlertLogicException(logicException);
                }
            }
            if(emailSent == true) {
                Alerts.showInformationAlert("Éxito", "Se ha enviado el usuario y contraseña al correo.");
            } else if(result > 0) {
                registerPendingMail(user, credential);
                Alerts.showInformationAlert("Correo pendiente", "El correo queda pendiente por enviarse a su destino.");
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
