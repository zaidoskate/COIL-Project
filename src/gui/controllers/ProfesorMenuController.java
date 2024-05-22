package gui.controllers;

import gui.Alerts;
import gui.COILVICApplication;
import gui.SessionManager;
import gui.stages.CollaborationStage;
import gui.stages.OfferProfessorStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class ProfesorMenuController implements Initializable {
    private static final Logger LOG = Logger.getLogger(ProfesorMenuController.class);
    @FXML
    private Text textName;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textName.setText(SessionManager.getInstance().getUserData().getName());
    }
    
    @FXML
    public void displayOfferProfessor() {
        Stage menuStage = (Stage) textName.getScene().getWindow();
        menuStage.close();
        try {
            OfferProfessorStage offerProfessorStage = new OfferProfessorStage();
        } catch(IOException ioException) {
            LOG.warn(ioException);
        }
    }
    @FXML
    private void logout() {
        Stage stage = (Stage) textName.getScene().getWindow();
        stage.close();
        SessionManager.getInstance().logout();
        try {
            COILVICApplication application = new COILVICApplication();
            Stage loginStage = new Stage();
            application.start(loginStage);
        } catch(IOException ioException) {
            LOG.warn(ioException);
            Alerts.displayAlertIOException();
        }
    }

    @FXML
    public void displayCollaboration() {
        Stage menuStage = (Stage) textName.getScene().getWindow();
        menuStage.close();
        try {
            CollaborationStage collaborationStage = new CollaborationStage();
        } catch (IOException ioException) {
            LOG.warn(ioException);
        }
    }
}
