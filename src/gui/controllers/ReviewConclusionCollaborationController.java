package gui.controllers;

import gui.Alerts;
import gui.SessionManager;
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
import logic.DAOs.PendingMailDAO;
import logic.DAOs.ProfessorBelongsToCollaborationDAO;
import logic.DAOs.UserDAO;
import logic.LogicException;
import logic.domain.Evidence;
import logic.domain.FinalDocumentation;
import logic.domain.PendingMail;
import logic.domain.User;
import logic.model.CollaborationInformation;
import logic.model.EmailNotification;
import org.apache.log4j.Logger;

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
    private ComboBox cmbBoxFeedbackType;
    
    @FXML
    private TableView<Evidence> tblViewEvidence;
    
    @FXML
    private TableColumn<Evidence, String> clmEvidenceName;
    
    private ObservableList<Evidence> evidencesToDisplay = FXCollections.observableArrayList();
    
    private static final UserDAO USER_DAO = new UserDAO();
    private static final EvidenceDAO EVIDENCE_DAO = new EvidenceDAO();
    private static final FinalDocumentationDAO FINAL_DOCUMENTATION_DAO = new FinalDocumentationDAO();
    private static final PendingMailDAO PENDING_MAIL_DAO = new PendingMailDAO();
    private static final ProfessorBelongsToCollaborationDAO PROFESSOR_BELONGS_TO_COLLABORATION_DAO = new ProfessorBelongsToCollaborationDAO();
    
    private static final CollaborationInformation SELECTED_COLLABORATION = CollaborationInformation.getCollaboration();
    private static final SessionManager CURRENT_SESSION = SessionManager.getInstance();

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
            User professor = USER_DAO.getUserById(SELECTED_COLLABORATION.getIdUser());
            User mirrorProfessor = USER_DAO.getUserById(SELECTED_COLLABORATION.getIdMirrorUser());
            this.lblProfessorName.setText(professor.getName() + " " +professor.getLastName());
            this.lblProfessorEmail.setText(professor.getEmail());
            this.lblMirrorProfessorName.setText(mirrorProfessor.getName() + " " + mirrorProfessor.getLastName());
            this.lblMirrorProfessorEmail.setText(mirrorProfessor.getEmail());
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            log.error(logicException);
        }
    }
    
    private void setComboBoxFeedbacks() {
        this.cmbBoxFeedbackType.getItems().addAll("Feedback profesor", "Feedback estudiantado", "Feedback profesor espejo", "Feedback estudiantado espejo");
    }
    
    private void initializeTable() {
        this.clmEvidenceName.setCellValueFactory(new PropertyValueFactory("name"));
    }
    
    private void setEvidences() {
        try {
            ArrayList<Evidence> evidencesUploaded = EVIDENCE_DAO.getAllEvidencesByIdCollaboration(SELECTED_COLLABORATION.getIdCollaboration());
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
            log.error(logicException);
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
        if(this.cmbBoxFeedbackType.getSelectionModel().getSelectedItem() != null) {
            String feedbackType = cmbBoxFeedbackType.getSelectionModel().getSelectedItem().toString();
            File downloadPath = getDownloadPath("Guardar feedback", feedbackType);
            if(downloadPath != null) {
                FinalDocumentation finalDocumentation = new FinalDocumentation();
                finalDocumentation.setIdColaboration(SELECTED_COLLABORATION.getIdCollaboration());
                try {
                    switch (feedbackType) {
                        case "Feedback profesor":
                            if(FINAL_DOCUMENTATION_DAO.obtainProfessorFeedback(finalDocumentation, downloadPath.toString()) == 1) {
                                Alerts.showInformationAlert("Mensaje", "Feedback de profesor descargado");
                            }
                            break;
                        case "Feedback estudiantado":
                            if(FINAL_DOCUMENTATION_DAO.obtainStudentsFeedback(finalDocumentation, downloadPath.toString()) == 1) {
                                Alerts.showInformationAlert("Mensaje", "Feedback de estudiantes descargado");
                            }
                            break;
                        case "Feedback profesor espejo":
                            if(FINAL_DOCUMENTATION_DAO.obtainMirrorProfessorFeedback(finalDocumentation, downloadPath.toString()) == 1) {
                                Alerts.showInformationAlert("Mensaje", "Feedback de profesor espejo descargado");
                            }
                            break;
                        case "Feedback estudiantado espejo":
                            if(FINAL_DOCUMENTATION_DAO.obtainMirrorStudentsFeedback(finalDocumentation, downloadPath.toString()) == 1) {
                                Alerts.showInformationAlert("Mensaje", "Feedback de estudiantes espejo descargado");
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
        } else {
            Alerts.showWarningAlert("Seleccione un elemento para descargarlo");
        }
    }
    
    @FXML
    private void downloadEvidence() {
        if(this.tblViewEvidence.getSelectionModel().getSelectedItem() != null) {
            Evidence evidenceSelected = tblViewEvidence.getSelectionModel().getSelectedItem();
            File downloadPath = getDownloadPath("Guardar evidencia", evidenceSelected.getName());
            if(downloadPath != null) {
                try {
                    if (EVIDENCE_DAO.obtainEvidence(evidenceSelected, downloadPath.toString()) == 1) {
                        Alerts.showInformationAlert("Mensaje", "Evidencia descargada con éxito");
                    }
                } catch(LogicException logicException) {
                    Alerts.displayAlertLogicException(logicException);
                    log.error(logicException);
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
            log.error(ioException);
        }
    }
    
    @FXML
    private void declineConclusion() {
        try {
            if(PROFESSOR_BELONGS_TO_COLLABORATION_DAO.setStatusToCollaboration(SELECTED_COLLABORATION.getIdCollaboration(), "Iniciada") == 1) {
                User professorCollaboration = USER_DAO.getUserById(SELECTED_COLLABORATION.getIdUser());
                EmailNotification.getInstance().setEmail(professorCollaboration.getEmail());
                EmailNotification.getInstance().setMessageSuccess("Conclusión rechazada");
                try {
                    SendEmailStage sendEmailStage = new SendEmailStage();
                    if(EmailNotification.getInstance().getSentStatus()) {
                        Alerts.showInformationAlert("Mensaje", "Conclusión rechazada con éxito");
                    } else {
                        Alerts.showWarningAlert("Hubo un problema con el envío del correo");
                        PendingMail pendingMail = new PendingMail();
                        pendingMail.setSubject("Conclusión de colaboración");
                        pendingMail.setContent(EmailNotification.getInstance().getEmailBody());
                        pendingMail.setDestinationEmail(professorCollaboration.getEmail());
                        pendingMail.setIdUser(CURRENT_SESSION.getUserData().getIdUser());
                    }
                } catch(IOException ioException) {
                    Alerts.displayAlertIOException();
                    log.error(ioException);
                }
            }
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            log.error(logicException);
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
