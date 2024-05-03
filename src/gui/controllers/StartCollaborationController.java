/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.controllers;

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
    
    private final StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO();
    private final CollaborationDAO collaborationDAO = new CollaborationDAO();
    private final ProfessorBelongsToCollaborationDAO professorBelongsToCollaborationDAO = new ProfessorBelongsToCollaborationDAO();
    
    private final CollaborationInformation collaborationInformation = CollaborationInformation.getCollaboration();

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
    
    private void showAlertUploaded(String fileType) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Mensaje");
        alert.setTitle("Mensaje");
        alert.setContentText("El archivo para " + fileType + " ha sido cargado con exito!");
        alert.showAndWait();
    }
    
    public void showAlertDeleted(String fileType) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Mensaje");
        alert.setTitle("Mensaje");
        alert.setContentText("El archivo para " + fileType + " ha sido eliminado con exito!");
        alert.showAndWait();
    }
    
    public void showAlertHasFile(String fileType) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Mensaje");
        alert.setTitle("Mensaje");
        alert.setContentText(fileType + " tiene un archivo cargado");
        alert.showAndWait();
    }
    
    private void uploadFile(String selectedFileType, File fileToUpload) throws LogicException {
        StartupDocumentation startupDocumentation = new StartupDocumentation();
        startupDocumentation.setIdColaboration(collaborationInformation.getIdCollaboration());
        try {
            switch (selectedFileType) {
                case "Lista de estudiantes" :
                    startupDocumentation.setStudentsListPath(fileToUpload.getPath());
                    if(startupDocumentationDAO.uploadStudentsList(startupDocumentation) == 1) {
                        showAlertUploaded(selectedFileType);
                    }
                    break;
                case "Lista de estudiantes espejo" :
                    startupDocumentation.setMirrorClassStudentsListPath(fileToUpload.getPath());
                    if(startupDocumentationDAO.uploadMirrorStudentsList(startupDocumentation) == 1) {
                        showAlertUploaded(selectedFileType);
                    }
                    break;
                case "Syllabus" :
                    startupDocumentation.setSyllabusPath(fileToUpload.getPath());
                    if(startupDocumentationDAO.uploadSyllabus(startupDocumentation) == 1) {
                        showAlertUploaded(selectedFileType);
                    }
                    break;
                default:
                    break;
            }
        } catch(LogicException logicException) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(logicException.getMessage());
            alert.showAndWait();
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
            
        }
        return allFilesUploaded;
    }
    
    @FXML
    public void onDragOver(DragEvent event) {
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
                            showAlertDeleted(selectedFileType);
                        }
                        break;
                    case "Lista de estudiantes espejo" :
                        if(startupDocumentationDAO.deleteUploadedFile("listaEstudiantadoEspejo", collaborationInformation.getIdCollaboration()) == 1) {
                            showAlertDeleted(selectedFileType);
                        }
                        break;
                    case "Syllabus" :
                        if(startupDocumentationDAO.deleteUploadedFile("Syllabus", collaborationInformation.getIdCollaboration()) == 1) {
                            showAlertDeleted(selectedFileType);
                        }
                        break;
                    default:
                        break;
                }
            } catch(LogicException logicException) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(logicException.getMessage());
                alert.showAndWait();
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
                            showAlertHasFile(selectedFileType);
                        }
                        break;
                    case "Lista de estudiantes espejo" :
                        if(startupDocumentationDAO.hasFileUploaded("listaEstudiantadoEspejo", collaborationInformation.getIdCollaboration())) {
                            showAlertHasFile(selectedFileType);
                        }
                        break;
                    case "Syllabus" :
                        if(startupDocumentationDAO.hasFileUploaded("Syllabus", collaborationInformation.getIdCollaboration())) {
                            showAlertHasFile(selectedFileType);
                        }
                        break;
                    default:
                        break;
                }
            } catch(LogicException logicException) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(logicException.getMessage());
                alert.showAndWait();
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

            }
            previousMenu();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Cuidado");
            alert.setTitle("Cuidado");
            alert.setContentText("Tienes que cargar un documento para cada archivo solicitado antes de iniciar");
            alert.showAndWait();
        }
    }
}
