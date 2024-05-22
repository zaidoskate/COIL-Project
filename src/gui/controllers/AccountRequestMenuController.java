package gui.controllers;

import gui.Alerts;
import gui.stages.AccountRequestExternalListStage;
import gui.stages.AccountRequestUvListStage;
import gui.stages.CoordinatorMenuStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class AccountRequestMenuController implements Initializable {
    private static final Logger LOG = Logger.getLogger(AccountRequestMenuController.class);
    @FXML
    private Button btnCancel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    @FXML
    public void displayExternalAccountRequestList() {
        try{
            AccountRequestExternalListStage accountRequestExternalListStage = new AccountRequestExternalListStage();
        } catch(IOException ioException) {
            LOG.warn(ioException);
            Alerts.displayAlertIOException();
        }
    }
    @FXML
    public void displayUvAccountRequestList() {
        try{
            AccountRequestUvListStage accountRequestUvListStage = new AccountRequestUvListStage();
        } catch(IOException ioException) {
            LOG.warn(ioException);
            Alerts.displayAlertIOException();
        }
    }
    @FXML
    public void previusMenu() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
        try{
            CoordinatorMenuStage coordinatorMenu = new CoordinatorMenuStage();
        } catch(IOException ioException) {
            LOG.warn(ioException);
            Alerts.displayAlertIOException();
        }
    }

}
