/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.controllers;

import gui.Alerts;
import gui.stages.CollaborationsInConclusionStage;
import gui.stages.SendEmailStage;
import gui.stages.UploadCertificatesStage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.DAOs.EvidenceDAO;
import logic.DAOs.FinalDocumentationDAO;
import logic.DAOs.ProfessorBelongsToCollaborationDAO;
import logic.DAOs.UserDAO;
import logic.LogicException;
import logic.domain.Evidence;
import logic.domain.FinalDocumentation;
import logic.domain.User;
import logic.model.CollaborationInformation;
import logic.model.EmailNotification;
import org.apache.log4j.Logger;

/**
 *
 * @author zaido
 */
public class ReviewConclusionCollaborationController implements Initializable {
    
    @FXML
    private Label lblProfessorName;
    
    @FXML
    private Label lblProfessorEmail;
    
    @FXML
    private Label lblMirrorProfessorName;
    
    @FXML
    private Label lblMirrorProfessorEmail;
    
    @FXML
    private ComboBox comboBoxFeedbackType;
    
    @FXML
    private TableView<Evidence> tblViewEvidence;
    
    @FXML
    private TableColumn<Evidence, String> clmEvidenceName;
    
    private ObservableList<Evidence> evidencesToDisplay = FXCollections.observableArrayList();
    
    private static final UserDAO userDAO = new UserDAO();
    private static final EvidenceDAO evidenceDAO = new EvidenceDAO();
    private static final FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
    private static final ProfessorBelongsToCollaborationDAO professorBelongsToCollaborationDAO = new ProfessorBelongsToCollaborationDAO();
    private static final CollaborationInformation selectedCollaboration = CollaborationInformation.getCollaboration();

    private static final Logger log = Logger.getLogger(ReviewConclusionCollaborationController.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setLabels();
        setComboBoxFeedbacks();
        initializeTable();
        setEvidences();
        checkEvidencesAvailable();
    }
    
    private void setLabels() {
        try {
            User professor = userDAO.getUserById(selectedCollaboration.getIdUser());
            User mirrorProfessor = userDAO.getUserById(selectedCollaboration.getIdMirrorUser());
            this.lblProfessorName.setText(professor.getName() + " " +professor.getLastName());
            this.lblProfessorEmail.setText(professor.getEmail());
            this.lblMirrorProfessorName.setText(mirrorProfessor.getName() + " " + mirrorProfessor.getLastName());
            this.lblMirrorProfessorEmail.setText(mirrorProfessor.getEmail());
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            //log.error(logicException);
        }
    }
    
    private void setComboBoxFeedbacks() {
        this.comboBoxFeedbackType.getItems().addAll("Feedback profesor", "Feedback estudiantado", "Feedback profesor espejo", "Feedback estudiantado espejo");
    }
    
    private void initializeTable() {
        this.clmEvidenceName.setCellValueFactory(new PropertyValueFactory("name"));
    }
    
    private void setEvidences() {
        try {
            ArrayList<Evidence> evidencesUploaded = evidenceDAO.getAllEvidencesByIdCollaboration(selectedCollaboration.getIdCollaboration());
            for(Evidence evidence : evidencesUploaded) {
                Evidence evidenceInformation = new Evidence();
                evidenceInformation.setName(evidence.getName());
                evidenceInformation.setAuthor(evidence.getAuthor());
                evidenceInformation.setDateOfCreation(evidence.getDateOfCreation());
                evidenceInformation.setIdFolderEvidence(evidence.getIdFolderEvidence());
                this.evidencesToDisplay.add(evidence);
                this.tblViewEvidence.setItems(evidencesToDisplay);
            }
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            //log.error(logicException);
        }
    }
    
    private void checkEvidencesAvailable() {
        if(this.evidencesToDisplay.isEmpty()) {
            this.tblViewEvidence.setPlaceholder(new Label("No hay evidencias para mostrar"));
        }
    }
    
    private File getDownloadPath(String fileChooserTitle, String fileName) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(fileChooserTitle);
        fileChooser.setInitialFileName(fileName + ".pdf");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos PDF", "*.pdf"));
        
        return fileChooser.showSaveDialog(this.lblProfessorName.getScene().getWindow());
    }
    
    @FXML
    private void downloadFeedback() {
        if(this.comboBoxFeedbackType.getSelectionModel().getSelectedItem() != null) {
            String feedbackType = comboBoxFeedbackType.getSelectionModel().getSelectedItem().toString();
            File downloadPath = getDownloadPath("Guardar feedback", feedbackType);
            if(downloadPath != null) {
                FinalDocumentation finalDocumentation = new FinalDocumentation();
                finalDocumentation.setIdColaboration(selectedCollaboration.getIdCollaboration());
                try {
                    switch (feedbackType) {
                        case "Feedback profesor":
                            if(finalDocumentationDAO.obtainProfessorFeedback(finalDocumentation, downloadPath.toString()) == 1) {
                                Alerts.showInformationAlert("Mensaje", "Feedback de profesor descargado");
                            }
                            break;
                        case "Feedback estudiantado":
                            if(finalDocumentationDAO.obtainStudentsFeedback(finalDocumentation, downloadPath.toString()) == 1) {
                                Alerts.showInformationAlert("Mensaje", "Feedback de estudiantes descargado");
                            }
                            break;
                        case "Feedback profesor espejo":
                            if(finalDocumentationDAO.obtainMirrorProfessorFeedback(finalDocumentation, downloadPath.toString()) == 1) {
                                Alerts.showInformationAlert("Mensaje", "Feedback de profesor espejo descargado");
                            }
                            break;
                        case "Feedback estudiantado espejo":
                            if(finalDocumentationDAO.obtainMirrorStudentsFeedback(finalDocumentation, downloadPath.toString()) == 1) {
                                Alerts.showInformationAlert("Mensaje", "Feedback de estudiantes espejo descargado");
                            }
                            break;
                        default:
                            break;
                    }
                } catch(LogicException logicException) {
                    Alerts.displayAlertLogicException(logicException);
                    //log.error(logicException);
                }
            }
        } else {
            Alerts.showWarningAlert("Seleccione un elemento para descargarlo");
        }
    }
    
    @FXML
    private void downloadEvidence() {
        if(this.tblViewEvidence.getSelectionModel().getSelectedItem() != null) {
            String evidenceName = tblViewEvidence.getSelectionModel().getSelectedItem().toString();
            File downloadPath = getDownloadPath("Guardar evidencia", evidenceName);
            if(downloadPath != null) {
                Evidence evidence = new Evidence();
                evidence.setName(evidenceName);
                try {
                    if (evidenceDAO.obtainEvidence(evidence, downloadPath.toString()) == 1) {
                        Alerts.showInformationAlert("Mensaje", "Evidencia descargada con exito");
                    }
                } catch(LogicException logicException) {
                    Alerts.displayAlertLogicException(logicException);
                    //log.error(logicException);
                }
            }
        } else {
            Alerts.showWarningAlert("Seleccione una evidencia para descargarla");
        }
    }
    
    @FXML
    private void approveConclusion() {
        Stage stage = (Stage) this.lblProfessorName.getScene().getWindow();
        stage.close();
        try {
            UploadCertificatesStage uploadCertificatesStage = new UploadCertificatesStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            //log.error(ioException);
        }
    }
    
    @FXML
    private void declineConclusion() {
        try {
            if(professorBelongsToCollaborationDAO.setStatusToCollaboration(selectedCollaboration.getIdCollaboration(), "Iniciada") == 1) {
                User professorCollaboration = userDAO.getUserById(selectedCollaboration.getIdUser());
                EmailNotification.getInstance().setEmail(professorCollaboration.getEmail());
                EmailNotification.getInstance().setMessageSuccess("Conclusión rechazada");
                EmailNotification.getInstance().setMessageCancel("Se cancelará el rechazo de la conclusión");
                try {
                    SendEmailStage sendEmailStage = new SendEmailStage();
                    if(EmailNotification.getInstance().getSentStatus()) {
                        Alerts.showInformationAlert("Mensaje", "Conclusión rechazada con éxito");
                    }
                } catch(IOException ioException) {
                    Alerts.displayAlertIOException();
                    //log.error(ioException);
                }
            }
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            //log.error(logicException);
        }
    }
    
    @FXML
    private void previousMenu() {
        Stage stage = (Stage) this.tblViewEvidence.getScene().getWindow();
        stage.close();
        try {
            CollaborationsInConclusionStage collaborationsInConclusionStage = new CollaborationsInConclusionStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            log.error(ioException);
        }
    }
}
