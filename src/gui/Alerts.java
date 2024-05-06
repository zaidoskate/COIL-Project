/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javafx.scene.control.Alert;
import logic.LogicException;

/**
 *
 * @author chuch
 */
public class Alerts {
    
    public static void showInformationAlert(String headerText, String contentText) {
        showAlert(Alert.AlertType.INFORMATION, headerText, contentText);
    }

    public static void showWarningAlert(String contentText) {
        showAlert(Alert.AlertType.WARNING, "Advertencia", contentText);
    }
    
    public static void showErrorAlert(String headerText, String contentText) {
        showAlert(Alert.AlertType.ERROR, headerText, contentText);
    }

    private static void showAlert(Alert.AlertType alertType, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(headerText);
        alert.setTitle(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
        
    public static void displayAlertNotSelectedItem() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Advertencia");
        alert.setTitle("Advertencia");
        alert.setContentText("Seleccione una solicitud primero.");
        alert.showAndWait();
    }
    public static void displayAlertLogicException(LogicException logicException) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Advertencia");
        alert.setTitle("Advertencia");
        alert.setContentText(logicException.getMessage());
        alert.showAndWait();
    }
    public static void displayAlertIOException() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Advertencia");
        alert.setTitle("Advertencia");
        alert.setContentText("Ha ocurrido un error favor de intentarlo mas tarde.");
        alert.showAndWait();
    }
    public static void displayAccountSent() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Exito");
        alert.setTitle("Correo enviado");
        alert.setContentText("El correo se ha enviado a su destino con la clave de acceso");
        alert.showAndWait();
    }
    public static void displayAccountNoSent() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Error");
        alert.setTitle("Usuario no creado");
        alert.setContentText("No se ha podido registrar ni enviar el correo a su destino, intentelo mas tarde.");
        alert.showAndWait();
    }
}
