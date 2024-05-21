/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author zaido
 */
public class DetailMyCollaborationController implements Initializable {

    @FXML
    private Label lblCollaborationName;
    
    @FXML
    private Label lblStartDate;
    
    @FXML
    private ImageView imageViewZipFile;
    
    @FXML
    private Label lblCertificatesAvailable;
    
    private static final ConcludedColaborationDAO concludedCollaborationDAO = new ConcludedColaborationDAO();
    
    private static final CollaborationInformation selectedCollaboration = CollaborationInformation.getCollaboration();
    
    private static final Logger log = Logger.getLogger(DetailMyCollaborationController.class);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setLabels();
    }
    
    private void setLabels() {
        this.lblCollaborationName.setText(selectedCollaboration.getCollaborationName());
        this.lblStartDate.setText(selectedCollaboration.getStartDate());
        try {
            if(!concludedCollaborationDAO.hasCertificatesUploaded(selectedCollaboration.getIdCollaboration())) {
                this.lblCertificatesAvailable.setText("Constancias no disponibles para descargar");
            }
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
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
            concludedCollaboration.setIdColaboration(selectedCollaboration.getIdCollaboration());
            try {
                if(concludedCollaborationDAO.obtainCertificates(concludedCollaboration, downloadPath.toString()) == 1) {
                    Alerts.showInformationAlert("Mensaje", "Constancias descargadas con éxito");
                }
            } catch(LogicException logicException) {
                Alerts.displayAlertLogicException(logicException);
                //log.error(logicException);
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
            log.error(ioException);
        }
    }
}
