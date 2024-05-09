package gui;

import javafx.scene.control.Alert;
import logic.LogicException;

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
