package gui.controllers;

import gui.Alerts;
import gui.stages.CollaborationsInConclusionStage;
import gui.stages.CoordinatorMenuStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class CollaborationsCoordinatorMenuController implements Initializable {
    private static final Logger log = Logger.getLogger(CollaborationsCoordinatorMenuController.class);
    @FXML
    Button btnClose;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }
    
    @FXML
    private void previusMenu() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
        try{
            CoordinatorMenuStage coordinatorMenu = new CoordinatorMenuStage();
        } catch(IOException ioException) {
            log.warn(ioException);
            Alerts.displayAlertIOException();
        }
    }
    
    @FXML
    private void displayCollaborationsInConclusion() {
        Stage stage = (Stage) this.btnClose.getScene().getWindow();
        stage.close();
        try {
            CollaborationsInConclusionStage collaborationsInConclusionStage = new CollaborationsInConclusionStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            log.error(ioException);
        }
    }
    
}
