package gui.controllers;

import gui.Alerts;
import gui.DataValidation;
import gui.stages.MyCollaborationsStage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import logic.DAOs.CollaborationDAO;
import logic.DAOs.FinalDocumentationDAO;
import logic.DAOs.ProfessorBelongsToCollaborationDAO;
import logic.LogicException;
import logic.domain.FinalDocumentation;
import logic.model.CollaborationInformation;
import org.apache.log4j.Logger;

public class ConcludeCollaborationController implements Initializable {

    @FXML
    private ComboBox cmbBoxFileType;
    
    @FXML
    private Region regionFile;
    
    @FXML
    private ImageView imageViewFile;
    
    private static final FinalDocumentationDAO FINAL_DOCUMENTATION_DAO = new FinalDocumentationDAO();
    private static final ProfessorBelongsToCollaborationDAO PROFESSOR_BELONGS_TO_COLLABORATION_DAO = new ProfessorBelongsToCollaborationDAO();
    
    private static final CollaborationInformation COLLABORATION_INFORMATION = CollaborationInformation.getCollaboration();
    private static final CollaborationDAO COLLABORATION_DAO = new CollaborationDAO();
    
    private static final Logger LOG = Logger.getLogger(ConcludeCollaborationController.class);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setComboBox();
        checkCollaborationRegistrated();
        
    }
    
    private void checkCollaborationRegistrated() {
        try {
            if(!FINAL_DOCUMENTATION_DAO.isCollaborationRegistrated(COLLABORATION_INFORMATION.getIdCollaboration())) {
                createFinalDocumentation();
            }
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            LOG.error(logicException);
        }
    }
    
    private void createFinalDocumentation() throws LogicException {
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(COLLABORATION_INFORMATION.getIdCollaboration());
        FINAL_DOCUMENTATION_DAO.addFinalDocumentation(finalDocumentation);
    }
    
    private void setComboBox() {
        this.cmbBoxFileType.getItems().addAll("Feedback profesor", "Feedback estudiantado", "Feedback profesor espejo", "Feedback estudiantado espejo");
    }
    
    private void uploadFile(String selectedFileType, File fileToUpload) throws LogicException {
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(COLLABORATION_INFORMATION.getIdCollaboration());
        switch (selectedFileType) {
            case "Feedback profesor":
                finalDocumentation.setProfessorFeedback(fileToUpload.getPath());
                if(FINAL_DOCUMENTATION_DAO.uploadProfessorFeedback(finalDocumentation) == 1) {
                    Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido cargado con éxito");
                }
                break;
            case "Feedback estudiantado":
                finalDocumentation.setStudentsFeedback(fileToUpload.getPath());
                if(FINAL_DOCUMENTATION_DAO.uploadStudentsFeedback(finalDocumentation) == 1) {
                    Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido cargado con éxito");
                }
                break;
            case "Feedback profesor espejo":
                finalDocumentation.setMirrorProfessorFeedback(fileToUpload.getPath());
                if(FINAL_DOCUMENTATION_DAO.uploadMirrorProfessorFeedback(finalDocumentation) == 1) {
                    Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido cargado con éxito");
                }
                break;
            case "Feedback estudiantado espejo":
                finalDocumentation.setMirrorStudentsFeedback(fileToUpload.getPath());
                if(FINAL_DOCUMENTATION_DAO.uploadMirrorStudentsFeedback(finalDocumentation) == 1) {
                    Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido cargado con éxito");
                }
                break;
            default:
                break;
        }
    }
    
    private boolean validateAllFilesUploaded() {
        boolean allFilesUploaded = false;
        try {
            if (FINAL_DOCUMENTATION_DAO.hasFileUploaded("feedbackProfesor", COLLABORATION_INFORMATION.getIdCollaboration())) {
                if (FINAL_DOCUMENTATION_DAO.hasFileUploaded("feedbackEstudiantado", COLLABORATION_INFORMATION.getIdCollaboration())) {
                    if (FINAL_DOCUMENTATION_DAO.hasFileUploaded("feedbackProfesorEspejo", COLLABORATION_INFORMATION.getIdCollaboration())) {
                        if (FINAL_DOCUMENTATION_DAO.hasFileUploaded("feedbackEstudiantadoEspejo", COLLABORATION_INFORMATION.getIdCollaboration())) {
                            allFilesUploaded = true;
                        }
                    }
                }
            }
        } catch (LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            LOG.error(logicException);
        }
        return allFilesUploaded;
    }

    
    @FXML
    private void onDragOver(DragEvent event) {
        Stage stage = (Stage) this.regionFile.getScene().getWindow();
        stage.requestFocus();
        if(cmbBoxFileType.getSelectionModel().getSelectedItem() != null) {
            if(event.getGestureSource() != this.regionFile || event.getGestureSource() != imageViewFile) {
                if(event.getDragboard().hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                }
            }
        }
    }
    
    @FXML
    private void onDragDropped(DragEvent event) {
        if(cmbBoxFileType.getSelectionModel().getSelectedItem() != null) {
            if(event.getGestureSource() != regionFile || event.getGestureSource() != imageViewFile) {
                if(event.getDragboard().hasFiles()) {
                    File fileToUpload = event.getDragboard().getFiles().get(0);
                    String selectedFileType = (String) cmbBoxFileType.getSelectionModel().getSelectedItem().toString();
                    if(DataValidation.validateFileExtension(fileToUpload.getName(), "pdf")) {
                        try {
                            uploadFile(selectedFileType, fileToUpload);
                            event.setDropCompleted(true);
                        } catch(LogicException logicException) {
                            Alerts.displayAlertLogicException(logicException);
                            LOG.error(logicException);
                        }
                    } else {
                        Alerts.showWarningAlert("Solo se permiten archivos con extensión PDF");
                    }
                    event.consume();
                }
            }
        }
    }
    
    @FXML
    private void showHasUploadedFile() {
        if(cmbBoxFileType.getSelectionModel().getSelectedItem() != null) {
            String selectedFileType = (String) cmbBoxFileType.getSelectionModel().getSelectedItem().toString();
            try {
                switch (selectedFileType) {
                    case "Feedback profesor" :
                        if(FINAL_DOCUMENTATION_DAO.hasFileUploaded("feedbackProfesor", COLLABORATION_INFORMATION.getIdCollaboration())) {
                            Alerts.showInformationAlert("Mensaje", selectedFileType + " tiene un archivo cargado");
                        }
                        break;
                    case "Feedback estudiantado" :
                        if(FINAL_DOCUMENTATION_DAO.hasFileUploaded("feedbackEstudiantado", COLLABORATION_INFORMATION.getIdCollaboration())) {
                            Alerts.showInformationAlert("Mensaje", selectedFileType + " tiene un archivo cargado");
                        }
                        break;
                    case "Feedback profesor espejo" :
                        if(FINAL_DOCUMENTATION_DAO.hasFileUploaded("feedbackProfesorEspejo", COLLABORATION_INFORMATION.getIdCollaboration())) {
                            Alerts.showInformationAlert("Mensaje", selectedFileType + " tiene un archivo cargado");
                        }
                        break;
                    case "Feedback estudiantado espejo":
                        if(FINAL_DOCUMENTATION_DAO.hasFileUploaded("feedbackEstudiantadoEspejo", COLLABORATION_INFORMATION.getIdCollaboration())) {
                            Alerts.showInformationAlert("Mensaje", selectedFileType + " tiene un archivo cargado");
                        }
                        break;
                    default:
                        break;
                }
            } catch(LogicException logicException) {
                Alerts.displayAlertLogicException(logicException);
                LOG.error(logicException);
            }
        }
    }
    
    @FXML
    private void concludeCollaboration() {
        if(validateAllFilesUploaded()) {
            try {
                if(PROFESSOR_BELONGS_TO_COLLABORATION_DAO.setStatusToCollaboration(COLLABORATION_INFORMATION.getIdCollaboration(), "Espera") == 1) {
                    if(COLLABORATION_DAO.concludeCollaboration(COLLABORATION_INFORMATION.getIdCollaboration()) == 1) {
                        Alerts.showInformationAlert("Hecho", "Ha solicitado finalizar esta colaboración, el coordinador evaluará su petición");
                    }
                }
            } catch(LogicException logicException) {
                Alerts.displayAlertLogicException(logicException);
                LOG.error(logicException);
            }
            previousMenu();
        } else {
            Alerts.showWarningAlert("Tiene que cargar un documento para cada archivo solicitado antes de concluir");
        }
    }
    
    @FXML
    private void deleteFile() {
        if(cmbBoxFileType.getSelectionModel().getSelectedItem() != null) {
            String selectedFileType = (String) cmbBoxFileType.getSelectionModel().getSelectedItem().toString();
            try {
                switch (selectedFileType) {
                    case "Feedback profesor":
                        if(FINAL_DOCUMENTATION_DAO.deleteUploadedFile("feedbackProfesor", COLLABORATION_INFORMATION.getIdCollaboration()) == 1) {
                            Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido eliminado con éxito");
                        }
                        break;
                    case "Feedback estudiantado":
                        if(FINAL_DOCUMENTATION_DAO.deleteUploadedFile("feedbackEstudiantado", COLLABORATION_INFORMATION.getIdCollaboration()) == 1) {
                            Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido eliminado con éxito");
                        }
                        break;
                    case "Feedback profesor espejo":
                        if(FINAL_DOCUMENTATION_DAO.deleteUploadedFile("feedbackProfesorEspejo", COLLABORATION_INFORMATION.getIdCollaboration()) == 1) {
                            Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido eliminado con éxito");
                        }
                        break;
                    case "Feedback estudiantado espejo":
                        if(FINAL_DOCUMENTATION_DAO.deleteUploadedFile("feedbackEstudiantadoEspejo", COLLABORATION_INFORMATION.getIdCollaboration()) == 1) {
                            Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido eliminado con éxito");
                        }
                        break;
                    default:
                        break;
                }
            } catch(LogicException logicException) {
                Alerts.displayAlertLogicException(logicException);
                LOG.error(logicException);
            }
        }
    }
    
    @FXML
    private void previousMenu() {
        Stage stage = (Stage) this.cmbBoxFileType.getScene().getWindow();
        stage.close();
        try {
            MyCollaborationsStage myCollaborationsStage = new MyCollaborationsStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
}
