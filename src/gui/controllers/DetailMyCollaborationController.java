package gui.controllers;

import gui.Alerts;
import gui.stages.CollaborationHistoryStage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.DAOs.ConcludedColaborationDAO;
import logic.LogicException;
import logic.domain.ConcludedCollaboration;
import logic.model.CollaborationInformation;
import org.apache.log4j.Logger;

public class DetailMyCollaborationController implements Initializable {

    @FXML
    private Label lblCollaborationName;
    
    @FXML
    private Label lblStartDate;
    
    @FXML
    private ImageView imageViewZipFile;
    
    @FXML
    private Label lblCertificatesAvailable;
    
    private static final ConcludedColaborationDAO CONCLUDED_COLLABORATION_DAO = new ConcludedColaborationDAO();
    
    private static final CollaborationInformation SELECTED_COLLABORATION = CollaborationInformation.getCollaboration();
    
    private static final Logger LOG = Logger.getLogger(DetailMyCollaborationController.class);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setLabels();
    }
    
    private void setLabels() {
        this.lblCollaborationName.setText(SELECTED_COLLABORATION.getCollaborationName());
        this.lblStartDate.setText(SELECTED_COLLABORATION.getStartDate());
        try {
            if(!CONCLUDED_COLLABORATION_DAO.hasCertificatesUploaded(SELECTED_COLLABORATION.getIdCollaboration())) {
                this.lblCertificatesAvailable.setText("Constancias no disponibles para descargar");
            }
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            LOG.error(logicException);
        }
    }
    
    private File getDownloadPath(String fileChooserTitle, String fileName) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(fileChooserTitle);
        fileChooser.setInitialFileName(fileName + ".zip");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos comprimidos zip", "*.zip"));
        return fileChooser.showSaveDialog(this.imageViewZipFile.getScene().getWindow());
    }
    
    @FXML
    private void downloadCertificates() {
        File downloadPath = getDownloadPath("Guardar constancias", "Constancias");
        if(downloadPath != null) {
            ConcludedCollaboration concludedCollaboration = new ConcludedCollaboration();
            concludedCollaboration.setIdColaboration(SELECTED_COLLABORATION.getIdCollaboration());
            try {
                if(CONCLUDED_COLLABORATION_DAO.obtainCertificates(concludedCollaboration, downloadPath.toString()) == 1) {
                    Alerts.showInformationAlert("Mensaje", "Constancias descargadas con éxito");
                }
            } catch(LogicException logicException) {
                Alerts.displayAlertLogicException(logicException);
                LOG.error(logicException);
            }
        }
    }
    
    @FXML
    private void previousMenu() {
        Stage stage = (Stage) this.lblCollaborationName.getScene().getWindow();
        stage.close();
        try {
            CollaborationHistoryStage historyStage = new CollaborationHistoryStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
}
