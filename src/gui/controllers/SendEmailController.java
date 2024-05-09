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
    private static final Logger log = Logger.getLogger(SendEmailController.class);
    @FXML
    private TextArea txtAreaMessage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void cancelEmail() {
        Alerts.showWarningAlert(EmailNotification.getInstance().getMessageCancel());
        Stage stage = (Stage) txtAreaMessage.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void sendEmail() {
        String email = EmailNotification.getInstance().getEmail();
        String body = txtAreaMessage.getText();
        boolean result = false;
        if(DataValidation.validateWord(body)){
            try{
                result = MailSender.sendEmail(body, email);
            } catch(LogicException logicException) {
                log.error(logicException);
                Alerts.displayAlertLogicException(logicException);
            }
        }
        if(result == true) {
            EmailNotification.getInstance().emailSent();
            EmailNotification.getInstance().setEmailBody(this.txtAreaMessage.getText());
            Alerts.showInformationAlert("Exito", EmailNotification.getInstance().getMessageSuccess());
            Stage stage = (Stage) txtAreaMessage.getScene().getWindow();
            stage.close();
        } else {
            Alerts.showWarningAlert("Ha ocurrido un error, Intentalo de nuevo mas tarde.");
        }
    }
}
