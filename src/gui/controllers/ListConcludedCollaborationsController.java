package gui.controllers;

import gui.Alerts;
import gui.stages.CollaborationsCoordinatorMenuStage;
import gui.stages.DetailCollaborationStage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import logic.DAOs.CollaborationDAO;
import logic.DAOs.ConcludedCollaborationDAO;
import logic.LogicException;
import logic.domain.Collaboration;
import logic.domain.ConcludedCollaboration;
import logic.model.ConcludedCollaborationInformation;
import org.apache.log4j.Logger;

public class ListConcludedCollaborationsController implements Initializable{
    @FXML
    private TableView<Collaboration> tblViewCollaboration;
    
    private static final ConcludedCollaborationDAO CONCLUDE_COLLABORATION_DAO = new ConcludedCollaborationDAO();
    private static final CollaborationDAO COLLABORATION_DAO = new CollaborationDAO();
    private static final Logger LOG = Logger.getLogger(CollaborationController.class);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadConcludeCollaborations();
    }
    
    private void loadConcludeCollaborations() {
        ArrayList<Collaboration> collaborations = new ArrayList();
        ArrayList<ConcludedCollaboration> concludedCollaborations = new ArrayList();
        try {
            concludedCollaborations = CONCLUDE_COLLABORATION_DAO.getConcludedCollaborationsByVisibility();
            for (ConcludedCollaboration concludedCollaboration : concludedCollaborations) {
                Collaboration collaboration = COLLABORATION_DAO.getColaborationById(concludedCollaboration.getIdColaboration());
                collaborations.add(collaboration);
            }
        } catch (LogicException logicException) {
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
        } catch (IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
    
    @FXML void detailCollaboration() {
        Collaboration collaborationSelected = tblViewCollaboration.getSelectionModel().getSelectedItem();
        if (collaborationSelected != null) {
            ConcludedCollaborationInformation.getInstance().setIdCollaboration(collaborationSelected.getIdColaboration());
            ConcludedCollaborationInformation.getInstance().setChangeVisibility(true);
            ConcludedCollaborationInformation.getInstance().setDownloadEvidences(false);
            try {
                DetailCollaborationStage detailCollaborationStage = new DetailCollaborationStage(); 
            } catch (IOException ioException) {
                LOG.warn(ioException);
                Alerts.displayAlertIOException();
            }
        } else {
            Alerts.showWarningAlert("No se ha seleccionado ninguna colaboración.");
        }
    }
}
