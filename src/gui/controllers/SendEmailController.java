/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.controllers;

import gui.Alerts;
import gui.DataValidation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import logic.LogicException;
import logic.MailSender;
import logic.model.EmailAddress;

/**
 *
 * @author chuch
 */
public class SendEmailController implements Initializable {
    @FXML
    private TextArea txtAreaMessage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void cancelDecline() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Advertencia");
        alert.setTitle("Correo no enviado.");
        alert.setContentText("No se ha rechazado la solicitud de cuenta.");
        alert.showAndWait();
        Stage stage = (Stage) txtAreaMessage.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void declineRequest() {
        String email = EmailAddress.getInstance().getEmail();
        String body = txtAreaMessage.getText();
        boolean result = false;
        if(DataValidation.validateWord(body)){
            try{
                result = MailSender.sendEmail(body, email);
            } catch(LogicException logicException) {
                Alerts.displayAlertLogicException(logicException);
            }
        }
        if(result == true) {
            EmailAddress.getInstance().emailSent();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Exito");
            alert.setTitle("Solicitud rechazada");
            alert.setContentText("El correo se ha enviado a su destino informando el rechazo de su cuenta.");
            alert.showAndWait();

            Stage stage = (Stage) txtAreaMessage.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Error");
            alert.setTitle("Ha ocurrido un error");
            alert.setContentText("Intentalo de nuevo mas tarde.");
            alert.showAndWait();
        }
    }
}
