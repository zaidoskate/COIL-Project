package gui.controllers;

import gui.Alerts;
import gui.DataValidation;
import gui.SessionManager;
import gui.stages.EvidenceListStage;
import gui.stages.NewFolderStage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.DAOs.EvidenceFolderDAO;
import logic.DAOs.EvidenceDAO;
import logic.LogicException;
import logic.domain.EvidenceFolder;
import logic.domain.Evidence;
import logic.model.CollaborationInformation;
import logic.model.EvidenceListInformation;
import org.apache.log4j.Logger;

public class EvidenceUploaderController implements Initializable {
    private static final Logger LOG = Logger.getLogger(EvidenceUploaderController.class);
    private static final SessionManager CURRENT_SESSION = SessionManager.getInstance();
    private static final CollaborationInformation CURRENT_COLLABORATION = CollaborationInformation.getCollaboration();
    private static final EvidenceListInformation EVIDENCE_LIST_INFORMATION = EvidenceListInformation.getInstance();
    private static final EvidenceFolderDAO EVIDENCE_FOLDER_DAO = new EvidenceFolderDAO();
    private static final EvidenceDAO EVIDENCE_DAO = new EvidenceDAO();
    private ArrayList<EvidenceFolder> evidenceFolders = new ArrayList();
    
    @FXML
    private ComboBox cmbBoxFolders;
    @FXML
    private AnchorPane anchorPaneFile;
    @FXML
    private ImageView imageFile;
    @FXML
    private Text fileNameUploaded;
    @FXML
    private TextField txtFieldEvidenceName;
    
    private static File uploadedFile = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadFolders();
    }
    
    private void loadFolders() {
        try {
            evidenceFolders = EVIDENCE_FOLDER_DAO.getEvidenceFoldersByIdCollaboration(CURRENT_COLLABORATION.getIdCollaboration());
        } catch (LogicException logicException) {
            LOG.error(logicException);
            Alerts.displayAlertLogicException(logicException);
            Stage stage = (Stage) this.anchorPaneFile.getScene().getWindow();
            stage.close();
        }
        ArrayList<String> folderNames = new ArrayList<>();
        for (EvidenceFolder evidenceFolder : evidenceFolders) {
            folderNames.add(evidenceFolder.getName()); 
        }
        cmbBoxFolders.getItems().clear();
        cmbBoxFolders.getItems().addAll(folderNames);
    }
    
    private void uploadFile() throws LogicException {
        int folderId = evidenceFolders.get(cmbBoxFolders.getSelectionModel().getSelectedIndex()).getIdEvidenceFolder();
        String evidenceName = DataValidation.trimUnnecesaryBlanks(txtFieldEvidenceName.getText());
        String evidenceAutor = CURRENT_SESSION.getUserData().getName();
        
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        
        Evidence evidence = new Evidence();
        evidence.setAuthor(evidenceAutor);
        evidence.setIdFolderEvidence(folderId);
        evidence.setName(evidenceName);
        evidence.setDateOfCreation(formattedDate);
        evidence.setFile(uploadedFile.getPath());
        
        EVIDENCE_DAO.uploadEvidence(evidence);
    }
    
    private boolean makeValidations() {
        String evidenceName = DataValidation.trimUnnecesaryBlanks(txtFieldEvidenceName.getText());
        if ( !DataValidation.validateNotBlanks(evidenceName)){
            Alerts.showWarningAlert("El correo es un campo obligatorio.");
            return false;
        }
        if ( !DataValidation.validateName(evidenceName)){
            Alerts.showWarningAlert("Nombre de la evidencia inválido.");
            return false;
        }
        if (!DataValidation.validateLengthField(evidenceName, 45)) {
            Alerts.showWarningAlert("El nombre de la evidencia es demasiado largo.");
            return false;
        }
        if (cmbBoxFolders.getSelectionModel().getSelectedIndex() < 0) {
            Alerts.showWarningAlert("Se debe seleccionar un folder.");
            return false;
        }
        if (uploadedFile == null) {
            Alerts.showWarningAlert("No se ha cargado ningun archivo PDF.");
            return false;
        }
        return true;
    }
    
    public void clearFields() {
        uploadedFile = null;
        cmbBoxFolders.getSelectionModel().clearSelection();
        fileNameUploaded.setVisible(false);
        txtFieldEvidenceName.setText("");
    }
    
    @FXML
    private void newFolder() {
        try { 
            NewFolderStage newFolderStage = new NewFolderStage();
        } catch (IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
        loadFolders();
    }
    @FXML
    private void viewEvidences() {
        int indexFolderSelected = cmbBoxFolders.getSelectionModel().getSelectedIndex();
        if (indexFolderSelected >= 0) {
            try { 
                EVIDENCE_LIST_INFORMATION.setAllFolders(false);
                EVIDENCE_LIST_INFORMATION.setIdCollaboration(CURRENT_COLLABORATION.getIdCollaboration());
                EVIDENCE_LIST_INFORMATION.setIdFolder(evidenceFolders.get(indexFolderSelected).getIdEvidenceFolder());
                EvidenceListStage evidenceListStage = new EvidenceListStage();
            } catch (IOException ioException) {
                Alerts.displayAlertIOException();
                LOG.error(ioException);
            }
        } else {
            Alerts.showWarningAlert("No se ha seleccionado un folder.");
        }
    }
    @FXML
    private void deleteUploadedFile() {
        if (uploadedFile != null) {
            uploadedFile = null;
            fileNameUploaded.setVisible(false);
            Alerts.showWarningAlert("Se ha eliminado el archivo cargado");
        } else {
            Alerts.showWarningAlert("No hay ningún archivo cargado");
        }
    }
    @FXML
    private void uploadEvidence() {
        if (makeValidations()) {
            try {
                uploadFile();
                clearFields();
                Alerts.showInformationAlert("Éxito", "Se ha cargado con éxito la evidencia.");
            } catch (LogicException logicException) {
                LOG.error(logicException);
                Alerts.displayAlertLogicException(logicException);
            }
        }
    }
    @FXML
    private void previusMenu() {
        Stage stage = (Stage) this.anchorPaneFile.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void onDragOver(DragEvent event) {
        Stage stage = (Stage) this.anchorPaneFile.getScene().getWindow();
        stage.requestFocus();
        if (event.getGestureSource() != this.anchorPaneFile || event.getGestureSource() != imageFile) {
            if (event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
        }
        
    }
    
    @FXML
    private void onDragDropped(DragEvent event) {
            if (event.getGestureSource() != anchorPaneFile || event.getGestureSource() != imageFile) {
                if (event.getDragboard().hasFiles()) {
                    File fileToUpload = event.getDragboard().getFiles().get(0);
                    if (DataValidation.validateFileExtension(fileToUpload.getName(), "pdf")) {
                        uploadedFile = fileToUpload;
                        fileNameUploaded.setVisible(true);
                        event.setDropCompleted(true);
                    } else {
                        Alerts.showWarningAlert("Solo se permiten archivos con extensión PDF");
                    }
                    event.consume();
                }
            }
        
    }
}
