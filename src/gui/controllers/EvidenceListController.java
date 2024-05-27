package gui.controllers;

import gui.Alerts;
import gui.SessionManager;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.DAOs.EvidenceDAO;
import logic.LogicException;
import logic.domain.Evidence;
import logic.model.EvidenceListInformation;
import org.apache.log4j.Logger;

public class EvidenceListController implements Initializable {
    private static final Logger LOG = Logger.getLogger(UploadEvidencesController.class);
    private static final SessionManager CURRENT_SESSION = SessionManager.getInstance();
    private static final EvidenceListInformation EVIDENCE_LIST_INFORMATION = EvidenceListInformation.getInstance();
    private static final EvidenceDAO EVIDENCE_DAO = new EvidenceDAO();
    private ArrayList<Evidence> evidences = new ArrayList();
    
    @FXML
    private TableView<Evidence> tblViewEvidences;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnDownload;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            if(EVIDENCE_LIST_INFORMATION.getAllFolders()) {
                loadAllEvidences();
            } else {
                loadEvidencesByFolder();
            }
        } catch(LogicException logicException) {
            LOG.error(logicException);
            Alerts.displayAlertLogicException(logicException);
            Stage stage = (Stage) this.btnDelete.getScene().getWindow();
            stage.close();
        }
    }
    
    private void loadEvidencesByFolder() throws LogicException {
        btnDelete.setVisible(true);
        btnDownload.setVisible(false);
        evidences = EVIDENCE_DAO.getEvidencesByIdFolder(EVIDENCE_LIST_INFORMATION.getIdFolder());
        tblViewEvidences.getItems().addAll(evidences);
    }
    
    private void loadAllEvidences() throws LogicException {
        btnDelete.setVisible(false);
        btnDownload.setVisible(true);
        evidences = EVIDENCE_DAO.getAllEvidencesByIdCollaboration(EVIDENCE_LIST_INFORMATION.getIdCollaboration());
        tblViewEvidences.getItems().addAll(evidences);
    }
    
    private File getDownloadPath(String fileChooserTitle, String fileName) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(fileChooserTitle);
        fileChooser.setInitialFileName(fileName + ".pdf");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos PDF", "*.pdf"));
        
        return fileChooser.showSaveDialog(this.btnDownload.getScene().getWindow());
    }
    
    @FXML
    private void previusMenu() {
        Stage stage = (Stage) this.btnDelete.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void deleteEvidence() {
        Evidence evidenceSelected = this.tblViewEvidences.getSelectionModel().getSelectedItem();
        if(evidenceSelected != null) {
            try{
                int result = EVIDENCE_DAO.deleteEvidenceByName(evidenceSelected.getName());
                loadEvidencesByFolder();
                if(result == 1) {
                    Alerts.showInformationAlert("Exito", "Evidencia eliminada.");
                }
            } catch(LogicException logicException) {
                LOG.error(logicException);
                Alerts.displayAlertLogicException(logicException);
            }
        } else {
            Alerts.showWarningAlert("Seleccione una actividad primero");
        }
    }
    
    @FXML
    private void downloadEvidence() {
        if(this.tblViewEvidences.getSelectionModel().getSelectedItem() != null) {
            String evidenceName = tblViewEvidences.getSelectionModel().getSelectedItem().getName();
            File downloadPath = getDownloadPath("Guardar evidencia", evidenceName);
            if(downloadPath != null) {
                Evidence evidence = new Evidence();
                evidence.setName(evidenceName);
                try {
                    if (EVIDENCE_DAO.obtainEvidence(evidence, downloadPath.toString()) == 1) {
                        Alerts.showInformationAlert("Mensaje", "Evidencia descargada con exito");
                    }
                    else {
                        System.out.println("Hola");
                    }
                } catch(LogicException logicException) {
                    Alerts.displayAlertLogicException(logicException);
                    LOG.error(logicException);
                }
            }
        } else {
            Alerts.showWarningAlert("Seleccione una evidencia para descargarla");
        }
    }
}
