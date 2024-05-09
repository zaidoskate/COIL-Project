/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.controllers;

import gui.Alerts;
import gui.stages.MyCollaborationsStage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import logic.DAOs.CollaborationDAO;
import logic.DAOs.ProfessorBelongsToCollaborationDAO;
import logic.DAOs.StartupDocumentationDAO;
import logic.LogicException;
import logic.domain.StartupDocumentation;
import logic.model.CollaborationInformation;
import org.apache.log4j.Logger;

/**
 *
 * @author zaido
 */
public class StartCollaborationController implements Initializable {
    
    @FXML
    private Region region;
    
    @FXML
    private ImageView imageViewFile;
    
    @FXML
    private ComboBox comboBoxFileType;
    
    private static final StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO();
    private static final CollaborationDAO collaborationDAO = new CollaborationDAO();
    private static final ProfessorBelongsToCollaborationDAO professorBelongsToCollaborationDAO = new ProfessorBelongsToCollaborationDAO();
    
    private static final CollaborationInformation collaborationInformation = CollaborationInformation.getCollaboration();
    
    private static final Logger log = Logger.getLogger(StartCollaborationController.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setComboBox();
        checkCollaborationRegistrated();
    }
    
    private void checkCollaborationRegistrated() {
        try {
            if(!startupDocumentationDAO.isCollaborationRegistrated(collaborationInformation.getIdCollaboration())) {
                createStartupDocumentation();
            }
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            log.error(logicException);
        }
    }
    
    private void createStartupDocumentation() throws LogicException {
        StartupDocumentation startupDocumentation = new StartupDocumentation();
        startupDocumentation.setIdColaboration(collaborationInformation.getIdCollaboration());
        startupDocumentationDAO.addStartupDocumentation(startupDocumentation);
    }
    
    private void setComboBox() {
        comboBoxFileType.getItems().addAll("Lista de estudiantes", "Lista de estudiantes espejo", "Syllabus");
    }
    
    private void uploadFile(String selectedFileType, File fileToUpload) throws LogicException {
        StartupDocumentation startupDocumentation = new StartupDocumentation();
        startupDocumentation.setIdColaboration(collaborationInformation.getIdCollaboration());
        try {
            switch (selectedFileType) {
                case "Lista de estudiantes" :
                    startupDocumentation.setStudentsListPath(fileToUpload.getPath());
                    if(startupDocumentationDAO.uploadStudentsList(startupDocumentation) == 1) {
                        Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido cargado con éxito!");
                    }
                    break;
                case "Lista de estudiantes espejo" :
                    startupDocumentation.setMirrorClassStudentsListPath(fileToUpload.getPath());
                    if(startupDocumentationDAO.uploadMirrorStudentsList(startupDocumentation) == 1) {
                        Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido cargado con éxito!");
                    }
                    break;
                case "Syllabus" :
                    startupDocumentation.setSyllabusPath(fileToUpload.getPath());
                    if(startupDocumentationDAO.uploadSyllabus(startupDocumentation) == 1) {
                        Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido cargado con éxito!");
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
    
    private boolean validateAllFilesUploaded() {
        boolean allFilesUploaded = false;
        try {
            if(startupDocumentationDAO.hasFileUploaded("listaEstudiantado", collaborationInformation.getIdCollaboration())) {
                if (startupDocumentationDAO.hasFileUploaded("listaEstudiantadoEspejo", collaborationInformation.getIdCollaboration())) {
                    if (startupDocumentationDAO.hasFileUploaded("Syllabus", collaborationInformation.getIdCollaboration())) {
                        allFilesUploaded = true;
                    }
                }
            }
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            log.error(logicException);
        }
        return allFilesUploaded;
    }
    
    @FXML
    public void onDragOver(DragEvent event) {
        Stage stage = (Stage) this.region.getScene().getWindow();
        stage.requestFocus();
        if(comboBoxFileType.getSelectionModel().getSelectedItem() != null) {
            if(event.getGestureSource() != region || event.getGestureSource() != imageViewFile){
                if(event.getDragboard().hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                }
            }
        }
        event.consume();
    }
    
    @FXML
    public void onDragDropped(DragEvent event) {
        if(comboBoxFileType.getSelectionModel().getSelectedItem() != null) {
            if(event.getGestureSource() != region || event.getGestureSource() != imageViewFile){
                if(event.getDragboard().hasFiles()) {
                    File fileToUpload = event.getDragboard().getFiles().get(0);
                    String selectedFileType = (String) comboBoxFileType.getSelectionModel().getSelectedItem().toString();
                    try {
                        uploadFile(selectedFileType, fileToUpload);
                        event.setDropCompleted(true);
                    } catch(LogicException logicException) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Error");
                        alert.setTitle("Error");
                        alert.setContentText(logicException.getMessage());
                    }
                    event.consume();
                }
            }
        }
    }
    
    @FXML
    public void deleteFile() {
        if(comboBoxFileType.getSelectionModel().getSelectedItem() != null) {
            String selectedFileType = (String) comboBoxFileType.getSelectionModel().getSelectedItem().toString();
            try {
                switch (selectedFileType) {
                    case "Lista de estudiantes" :
                        if(startupDocumentationDAO.deleteUploadedFile("listaEstudiantado", collaborationInformation.getIdCollaboration()) == 1) {
                            Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido Eliminado con éxito!");
                        }
                        break;
                    case "Lista de estudiantes espejo" :
                        if(startupDocumentationDAO.deleteUploadedFile("listaEstudiantadoEspejo", collaborationInformation.getIdCollaboration()) == 1) {
                            Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido eliminado con éxito!");
                        }
                        break;
                    case "Syllabus" :
                        if(startupDocumentationDAO.deleteUploadedFile("Syllabus", collaborationInformation.getIdCollaboration()) == 1) {
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
    public void showHasUploadedFile() {
        if(comboBoxFileType.getSelectionModel().getSelectedItem() != null) {
            String selectedFileType = (String) comboBoxFileType.getSelectionModel().getSelectedItem().toString();
            try {
                switch (selectedFileType) {
                    case "Lista de estudiantes" :
                        if(startupDocumentationDAO.hasFileUploaded("listaEstudiantado", collaborationInformation.getIdCollaboration())) {
                            Alerts.showInformationAlert("Mensaje", selectedFileType + "tiene un archivo cargado");
                        }
                        break;
                    case "Lista de estudiantes espejo" :
                        if(startupDocumentationDAO.hasFileUploaded("listaEstudiantadoEspejo", collaborationInformation.getIdCollaboration())) {
                            Alerts.showInformationAlert("Mensaje", selectedFileType + "tiene un archivo cargado");
                        }
                        break;
                    case "Syllabus" :
                        if(startupDocumentationDAO.hasFileUploaded("Syllabus", collaborationInformation.getIdCollaboration())) {
                            Alerts.showInformationAlert("Mensaje", selectedFileType + "tiene un archivo cargado");
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
    public void previousMenu() {
        Stage stage = (Stage) this.comboBoxFileType.getScene().getWindow();
        stage.close();
        try {
            MyCollaborationsStage myCollaborationsStage = new MyCollaborationsStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            log.error(ioException);
        }
    }
    
    @FXML
    public void startCollaboration() {
        if(validateAllFilesUploaded()) {
            try {
                if(professorBelongsToCollaborationDAO.setStartedStatusToCollaboration(collaborationInformation.getIdCollaboration()) == 1) {
                    if(collaborationDAO.startCollaboration(collaborationInformation.getIdCollaboration()) == 1) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("Hecho");
                        alert.setTitle("Hecho");
                        alert.setContentText("Ha iniciado esta colaboracion");
                        alert.showAndWait();
                    }
                }
            } catch(LogicException logicException) {
                Alerts.displayAlertLogicException(logicException);
            }
            previousMenu();
        } else {
            Alerts.showWarningAlert("Tiene que cargar un documento para cada archivo solicitado antes de iniciar");
        }
    }
}
