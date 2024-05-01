package gui.controllers;

import gui.stages.AccountRequestUvListStage;
import gui.stages.CoordinatorMenuStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AccountRequestMenuController implements Initializable {
    @FXML
    private Button btnCancel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    @FXML
    public void displayExternalAccountRequestList() {
        
    }
    @FXML
    public void displayUvAccountRequestList() {
        try{
            AccountRequestUvListStage accountRequestUvListStage = new AccountRequestUvListStage();
        } catch(IOException ioException) {
            ioException.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ERROR, intentalo mas tarde.");
            alert.showAndWait();
        }
    }
    @FXML
    public void previusMenu() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
        try{
            CoordinatorMenuStage coordinatorMenu = new CoordinatorMenuStage();
        } catch(IOException ioException) {
            ioException.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ERROR, intentalo mas tarde.");
            alert.showAndWait();
        }
    }

}
