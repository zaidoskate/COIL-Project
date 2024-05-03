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
}
