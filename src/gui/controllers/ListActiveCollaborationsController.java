package gui.controllers;

import gui.Alerts;
import gui.stages.CollaborationsCoordinatorMenuStage;
import gui.stages.DetailActiveColaborationStage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import logic.DAOs.CollaborationDAO;
import logic.DAOs.ConcludedColaborationDAO;
import logic.LogicException;
import logic.domain.Collaboration;
import logic.model.CollaborationInformation;
import org.apache.log4j.Logger;

public class ListActiveCollaborationsController implements Initializable{
    @FXML
    private TableView<Collaboration> tblViewCollaboration;
    
    private static final ConcludedColaborationDAO CONCLUDE_COLLABORATION_DAO = new ConcludedColaborationDAO();
    private static final CollaborationDAO COLLABORATION_DAO = new CollaborationDAO();
    private static final Logger LOG = Logger.getLogger(CollaborationController.class);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadActiveCollaborations();
    }
    
    private void loadActiveCollaborations() {
        ArrayList<Collaboration> collaborations = new ArrayList();
        try {
            collaborations = COLLABORATION_DAO.getActiveCollaborations();
        } catch(LogicException logicException) {
            LOG.error(logicException);
            Alerts.displayAlertLogicException(logicException);
            previousMenu();
        }
        tblViewCollaboration.getItems().addAll(collaborations);
    }
    
    @FXML
    public void previousMenu() {
        Stage stage = (Stage) this.tblViewCollaboration.getScene().getWindow();
        stage.close();
        try {
            CollaborationsCoordinatorMenuStage coordinaorMenuStage = new CollaborationsCoordinatorMenuStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
    
    @FXML void detailCollaboration() {
        Collaboration collaborationSelected = tblViewCollaboration.getSelectionModel().getSelectedItem();
        if(collaborationSelected != null) {
            CollaborationInformation.getCollaboration().setIdCollaboration(collaborationSelected.getIdColaboration());
            try {
                DetailActiveColaborationStage detailActiveColaborationStage = new DetailActiveColaborationStage(); 
            } catch(IOException ioException) {
                LOG.warn(ioException);
                Alerts.displayAlertIOException();
            }
        } else {
            Alerts.showWarningAlert("No se ha seleccionado ninguna colaboracion.");
        }
    }
}
