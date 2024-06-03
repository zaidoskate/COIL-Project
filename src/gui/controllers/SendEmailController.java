package gui.controllers;

import gui.Alerts;
import gui.DataValidation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import logic.LogicException;
import logic.MailSender;
import logic.model.EmailNotification;
import org.apache.log4j.Logger;

public class SendEmailController implements Initializable {
    private static final Logger LOG = Logger.getLogger(SendEmailController.class);
    @FXML
    private TextArea txtAreaMessage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    private boolean validateEmailBody(String body) {
        if( !DataValidation.validateNotBlanks(body)){
            Alerts.showWarningAlert("El cuerpo del correo es un campo obligatorio.");
            return false;
        }
        if(DataValidation.validateWord(body)) {
            Alerts.showWarningAlert("El cuerpo del correo debe contener únicamente palabras y números.");
            return false;
        }
        if(DataValidation.validateLengthField(body, 100)) {
            Alerts.showWarningAlert("El cuerpo del correo no debe pasar los 100 caracteres.");
            return false;
        }
        return true;
    }
    @FXML
    private void sendEmail() {
        String email = EmailNotification.getInstance().getEmail();
        String body = DataValidation.trimUnnecesaryBlanks(txtAreaMessage.getText());
        boolean result = false;
        if(DataValidation.validateWord(body)){
            try{
                result = MailSender.sendEmail(body, email);
            } catch(LogicException logicException) {
                LOG.error(logicException);
            }
        }
        if(result == true) {
            EmailNotification.getInstance().emailSent();
            EmailNotification.getInstance().setEmailBody(this.txtAreaMessage.getText());
            Alerts.showInformationAlert("Exito", EmailNotification.getInstance().getMessageSuccess());
        } else {
            Alerts.showWarningAlert("Ha ocurrido un error al enviar el correo, Intentalo de nuevo mas tarde.");
        }
        Stage stage = (Stage) txtAreaMessage.getScene().getWindow();
        stage.close();
    }
}
