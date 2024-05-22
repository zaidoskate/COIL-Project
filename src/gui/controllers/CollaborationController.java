package gui.controllers;

import gui.Alerts;
import gui.stages.MyCollaborationsStage;
import gui.stages.ProfesorMenuStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class CollaborationController implements Initializable {
    
    @FXML
    private Button btnBack;

    private static final Logger LOG = Logger.getLogger(CollaborationController.class);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    public void displayMyCollaborations() {
        Stage stage = (Stage) this.btnBack.getScene().getWindow();
        stage.close();
        try {
            MyCollaborationsStage myCollaborationsStage = new MyCollaborationsStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
    
    @FXML
    public void previousMenu() {
        Stage stage = (Stage) this.btnBack.getScene().getWindow();
        stage.close();
        try {
            ProfesorMenuStage menuStage = new ProfesorMenuStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
}
