/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author zaido
 */
public class ConcludeCollaborationController implements Initializable {

    @FXML
    private ComboBox comboBoxFileType;
    
    @FXML
    private Region regionFile;
    
    @FXML
    private ImageView imageViewFile;
    
    private static final FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
    private static final ProfessorBelongsToCollaborationDAO professorBelongsToCollaborationDAO = new ProfessorBelongsToCollaborationDAO();
    
    private static final CollaborationInformation collaborationInformation = CollaborationInformation.getCollaboration();
    private static final CollaborationDAO collaborationDAO = new CollaborationDAO();
    
    private static final Logger log = Logger.getLogger(ConcludeCollaborationController.class);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setComboBox();
        checkCollaborationRegistrated();
        
    }
    
    private void checkCollaborationRegistrated() {
        try {
            if(!finalDocumentationDAO.isCollaborationRegistrated(collaborationInformation.getIdCollaboration())) {
                createFinalDocumentation();
            }
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            log.error(logicException);
        }
    }
    
    private void createFinalDocumentation() throws LogicException {
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(collaborationInformation.getIdCollaboration());
        finalDocumentationDAO.addFinalDocumentation(finalDocumentation);
    }
    
    private void setComboBox() {
        this.comboBoxFileType.getItems().addAll("Feedback profesor", "Feedback estudiantado", "Feedback profesor espejo", "Feedback estudiantado espejo");
    }
    
    private void uploadFile(String selectedFileType, File fileToUpload) throws LogicException {
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(collaborationInformation.getIdCollaboration());
        switch (selectedFileType) {
            case "Feedback profesor":
                finalDocumentation.setProfessorFeedback(fileToUpload.getPath());
                if(finalDocumentationDAO.uploadProfessorFeedback(finalDocumentation) == 1) {
                    Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido cargado con éxito!");
                }
                break;
            case "Feedback estudiantado":
                finalDocumentation.setStudentsFeedback(fileToUpload.getPath());
                if(finalDocumentationDAO.uploadStudentsFeedback(finalDocumentation) == 1) {
                    Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido cargado con éxito!");
                }
                break;
            case "Feedback profesor espejo":
                finalDocumentation.setMirrorProfessorFeedback(fileToUpload.getPath());
                if(finalDocumentationDAO.uploadMirrorProfessorFeedback(finalDocumentation) == 1) {
                    Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido cargado con éxito!");
                }
                break;
            case "Feedback estudiantado espejo":
                finalDocumentation.setMirrorStudentsFeedback(fileToUpload.getPath());
                if(finalDocumentationDAO.uploadMirrorStudentsFeedback(finalDocumentation) == 1) {
                    Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido cargado con éxito!");
                }
                break;
            default:
                break;
        }
    }
    
    private boolean validateAllFilesUploaded() {
        boolean allFilesUploaded = false;
        try {
            if (finalDocumentationDAO.hasFileUploaded("feedbackProfesor", collaborationInformation.getIdCollaboration())) {
                if (finalDocumentationDAO.hasFileUploaded("feedbackEstudiantado", collaborationInformation.getIdCollaboration())) {
                    if (finalDocumentationDAO.hasFileUploaded("feedbackProfesorEspejo", collaborationInformation.getIdCollaboration())) {
                        if (finalDocumentationDAO.hasFileUploaded("feedbackEstudiantadoEspejo", collaborationInformation.getIdCollaboration())) {
                            allFilesUploaded = true;
                        }
                    }
                }
            }
        } catch (LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            log.error(logicException);
        }
        return allFilesUploaded;
    }

    
    @FXML
    private void onDragOver(DragEvent event) {
        Stage stage = (Stage) this.regionFile.getScene().getWindow();
        stage.requestFocus();
        if(comboBoxFileType.getSelectionModel().getSelectedItem() != null) {
            if(event.getGestureSource() != this.regionFile || event.getGestureSource() != imageViewFile) {
                if(event.getDragboard().hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                }
            }
        }
    }
    
    @FXML
    private void onDragDropped(DragEvent event) {
        if(comboBoxFileType.getSelectionModel().getSelectedItem() != null) {
            if(event.getGestureSource() != regionFile || event.getGestureSource() != imageViewFile) {
                if(event.getDragboard().hasFiles()) {
                    File fileToUpload = event.getDragboard().getFiles().get(0);
                    String selectedFileType = (String) comboBoxFileType.getSelectionModel().getSelectedItem().toString();
                    if(DataValidation.validateFileExtension(fileToUpload.getName(), "pdf")) {
                        try {
                            uploadFile(selectedFileType, fileToUpload);
                            event.setDropCompleted(true);
                        } catch(LogicException logicException) {
                            Alerts.displayAlertLogicException(logicException);
                            log.error(logicException);
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
        if(comboBoxFileType.getSelectionModel().getSelectedItem() != null) {
            String selectedFileType = (String) comboBoxFileType.getSelectionModel().getSelectedItem().toString();
            try {
                switch (selectedFileType) {
                    case "Feedback profesor" :
                        if(finalDocumentationDAO.hasFileUploaded("feedbackProfesor", collaborationInformation.getIdCollaboration())) {
                            Alerts.showInformationAlert("Mensaje", selectedFileType + " tiene un archivo cargado");
                        }
                        break;
                    case "Feedback estudiantado" :
                        if(finalDocumentationDAO.hasFileUploaded("feedbackEstudiantado", collaborationInformation.getIdCollaboration())) {
                            Alerts.showInformationAlert("Mensaje", selectedFileType + " tiene un archivo cargado");
                        }
                        break;
                    case "Feedback profesor espejo" :
                        if(finalDocumentationDAO.hasFileUploaded("feedbackProfesorEspejo", collaborationInformation.getIdCollaboration())) {
                            Alerts.showInformationAlert("Mensaje", selectedFileType + " tiene un archivo cargado");
                        }
                        break;
                    case "Feedback estudiantado espejo":
                        if(finalDocumentationDAO.hasFileUploaded("feedbackEstudiantadoEspejo", collaborationInformation.getIdCollaboration())) {
                            Alerts.showInformationAlert("Mensaje", selectedFileType + " tiene un archivo cargado");
                        }
                        break;
                    default:
                        break;
                }
            } catch(LogicException logicException) {
                Alerts.displayAlertLogicException(logicException);
                log.error(logicException);
            }
        }
    }
    
    @FXML
    private void concludeCollaboration() {
        if(validateAllFilesUploaded()) {
            try {
                if(professorBelongsToCollaborationDAO.setStatusToCollaboration(collaborationInformation.getIdCollaboration(), "Espera") == 1) {
                    if(collaborationDAO.concludeCollaboration(collaborationInformation.getIdCollaboration()) == 1) {
                        Alerts.showInformationAlert("Hecho", "Ha solicitado finalizar esta colaboración, el coordinador evaluará su petición");
                    }
                }
            } catch(LogicException logicException) {
                Alerts.displayAlertLogicException(logicException);
            }
            previousMenu();
        } else {
            Alerts.showWarningAlert("Tiene que cargar un documento para cada archivo solicitado antes de concluir");
        }
    }
    
    @FXML
    private void deleteFile() {
        if(comboBoxFileType.getSelectionModel().getSelectedItem() != null) {
            String selectedFileType = (String) comboBoxFileType.getSelectionModel().getSelectedItem().toString();
            try {
                switch (selectedFileType) {
                    case "Feedback profesor":
                        if(finalDocumentationDAO.deleteUploadedFile("feedbackProfesor", collaborationInformation.getIdCollaboration()) == 1) {
                            Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido eliminado con éxito!");
                        }
                        break;
                    case "Feedback estudiantado":
                        if(finalDocumentationDAO.deleteUploadedFile("feedbackEstudiantado", collaborationInformation.getIdCollaboration()) == 1) {
                            Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido eliminado con éxito!");
                        }
                        break;
                    case "Feedback profesor espejo":
                        if(finalDocumentationDAO.deleteUploadedFile("feedbackProfesorEspejo", collaborationInformation.getIdCollaboration()) == 1) {
                            Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido eliminado con éxito!");
                        }
                        break;
                    case "Feedback estudiantado espejo":
                        if(finalDocumentationDAO.deleteUploadedFile("feedbackEstudiantadoEspejo", collaborationInformation.getIdCollaboration()) == 1) {
                            Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido eliminado con éxito!");
                        }
                        break;
                    default:
                        break;
                }
            } catch(LogicException logicException) {
                Alerts.displayAlertLogicException(logicException);
                log.error(logicException);
            }
        }
    }
    
    @FXML
    private void previousMenu() {
        Stage stage = (Stage) this.comboBoxFileType.getScene().getWindow();
        stage.close();
        try {
            MyCollaborationsStage myCollaborationsStage = new MyCollaborationsStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            log.error(ioException);
        }
    }
}
