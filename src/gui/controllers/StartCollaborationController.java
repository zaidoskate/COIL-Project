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
import logic.DAOs.ProfessorBelongsToCollaborationDAO;
import logic.DAOs.StartupDocumentationDAO;
import logic.LogicException;
import logic.domain.StartupDocumentation;
import logic.model.CollaborationInformation;
import org.apache.log4j.Logger;

public class StartCollaborationController implements Initializable {
    
    @FXML
    private Region regionFile;
    
    @FXML
    private ImageView imageViewFile;
    
    @FXML
    private ComboBox cmbBoxFileType;
    
    private static final StartupDocumentationDAO STARTUP_DOCUMENTATION_DAO = new StartupDocumentationDAO();
    private static final CollaborationDAO COLLABORATION_DAO = new CollaborationDAO();
    private static final ProfessorBelongsToCollaborationDAO PROFESSOR_BELONGS_TO_COLLABORATION_DAO = new ProfessorBelongsToCollaborationDAO();
    
    private static final CollaborationInformation COLLABORATION_INFORMATION = CollaborationInformation.getCollaboration();
    
    private static final Logger log = Logger.getLogger(StartCollaborationController.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setComboBox();
        checkCollaborationRegistrated();
    }
    
    private void checkCollaborationRegistrated() {
        try {
            if(!STARTUP_DOCUMENTATION_DAO.isCollaborationRegistrated(COLLABORATION_INFORMATION.getIdCollaboration())) {
                createStartupDocumentation();
            }
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            log.error(logicException);
        }
    }
    
    private void createStartupDocumentation() throws LogicException {
        StartupDocumentation startupDocumentation = new StartupDocumentation();
        startupDocumentation.setIdColaboration(COLLABORATION_INFORMATION.getIdCollaboration());
        STARTUP_DOCUMENTATION_DAO.addStartupDocumentation(startupDocumentation);
    }
    
    private void setComboBox() {
        cmbBoxFileType.getItems().addAll("Lista de estudiantes", "Lista de estudiantes espejo", "Syllabus");
    }
    
    private void uploadFile(String selectedFileType, File fileToUpload) throws LogicException {
        StartupDocumentation startupDocumentation = new StartupDocumentation();
        startupDocumentation.setIdColaboration(COLLABORATION_INFORMATION.getIdCollaboration());
        try {
            switch (selectedFileType) {
                case "Lista de estudiantes" :
                    startupDocumentation.setStudentsListPath(fileToUpload.getPath());
                    if(STARTUP_DOCUMENTATION_DAO.uploadStudentsList(startupDocumentation) == 1) {
                        Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido cargado con éxito");
                    }
                    break;
                case "Lista de estudiantes espejo" :
                    startupDocumentation.setMirrorClassStudentsListPath(fileToUpload.getPath());
                    if(STARTUP_DOCUMENTATION_DAO.uploadMirrorStudentsList(startupDocumentation) == 1) {
                        Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido cargado con éxito");
                    }
                    break;
                case "Syllabus" :
                    startupDocumentation.setSyllabusPath(fileToUpload.getPath());
                    if(STARTUP_DOCUMENTATION_DAO.uploadSyllabus(startupDocumentation) == 1) {
                        Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido cargado con éxito");
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
            if(STARTUP_DOCUMENTATION_DAO.hasFileUploaded("listaEstudiantado", COLLABORATION_INFORMATION.getIdCollaboration())) {
                if (STARTUP_DOCUMENTATION_DAO.hasFileUploaded("listaEstudiantadoEspejo", COLLABORATION_INFORMATION.getIdCollaboration())) {
                    if (STARTUP_DOCUMENTATION_DAO.hasFileUploaded("Syllabus", COLLABORATION_INFORMATION.getIdCollaboration())) {
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
        Stage stage = (Stage) this.regionFile.getScene().getWindow();
        stage.requestFocus();
        if(cmbBoxFileType.getSelectionModel().getSelectedItem() != null) {
            if(event.getGestureSource() != this.regionFile || event.getGestureSource() != imageViewFile) {
                if(event.getDragboard().hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                }
            }
        }
        event.consume();
    }
    
    @FXML
    public void onDragDropped(DragEvent event) {
        if(cmbBoxFileType.getSelectionModel().getSelectedItem() != null) {
            if(event.getGestureSource() != regionFile || event.getGestureSource() != imageViewFile){
                if(event.getDragboard().hasFiles()) {
                    File fileToUpload = event.getDragboard().getFiles().get(0);
                    String selectedFileType = (String) cmbBoxFileType.getSelectionModel().getSelectedItem().toString();
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
    public void deleteFile() {
        if(cmbBoxFileType.getSelectionModel().getSelectedItem() != null) {
            String selectedFileType = (String) cmbBoxFileType.getSelectionModel().getSelectedItem().toString();
            try {
                switch (selectedFileType) {
                    case "Lista de estudiantes" :
                        if(STARTUP_DOCUMENTATION_DAO.deleteUploadedFile("listaEstudiantado", COLLABORATION_INFORMATION.getIdCollaboration()) == 1) {
                            Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido eliminado con éxito");
                        }
                        break;
                    case "Lista de estudiantes espejo" :
                        if(STARTUP_DOCUMENTATION_DAO.deleteUploadedFile("listaEstudiantadoEspejo", COLLABORATION_INFORMATION.getIdCollaboration()) == 1) {
                            Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido eliminado con éxito");
                        }
                        break;
                    case "Syllabus" :
                        if(STARTUP_DOCUMENTATION_DAO.deleteUploadedFile("Syllabus", COLLABORATION_INFORMATION.getIdCollaboration()) == 1) {
                            Alerts.showInformationAlert("Mensaje", "El archivo para " + selectedFileType + " ha sido eliminado con éxito");
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
        if(cmbBoxFileType.getSelectionModel().getSelectedItem() != null) {
            String selectedFileType = (String) cmbBoxFileType.getSelectionModel().getSelectedItem().toString();
            try {
                switch (selectedFileType) {
                    case "Lista de estudiantes" :
                        if(STARTUP_DOCUMENTATION_DAO.hasFileUploaded("listaEstudiantado", COLLABORATION_INFORMATION.getIdCollaboration())) {
                            Alerts.showInformationAlert("Mensaje", selectedFileType + " tiene un archivo cargado");
                        }
                        break;
                    case "Lista de estudiantes espejo" :
                        if(STARTUP_DOCUMENTATION_DAO.hasFileUploaded("listaEstudiantadoEspejo", COLLABORATION_INFORMATION.getIdCollaboration())) {
                            Alerts.showInformationAlert("Mensaje", selectedFileType + " tiene un archivo cargado");
                        }
                        break;
                    case "Syllabus" :
                        if(STARTUP_DOCUMENTATION_DAO.hasFileUploaded("Syllabus", COLLABORATION_INFORMATION.getIdCollaboration())) {
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
    public void previousMenu() {
        Stage stage = (Stage) this.cmbBoxFileType.getScene().getWindow();
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
                if(PROFESSOR_BELONGS_TO_COLLABORATION_DAO.setStatusToCollaboration(COLLABORATION_INFORMATION.getIdCollaboration(), "Iniciada") == 1) {
                    if(COLLABORATION_DAO.startCollaboration(COLLABORATION_INFORMATION.getIdCollaboration()) == 1) {
                        Alerts.showInformationAlert("Hecho", "Ha iniciado esta colaboración");
                    }
                }
            } catch(LogicException logicException) {
                Alerts.displayAlertLogicException(logicException);
                log.error(logicException);
            }
            previousMenu();
        } else {
            Alerts.showWarningAlert("Tiene que cargar un documento para cada archivo solicitado antes de iniciar");
        }
    }
}
