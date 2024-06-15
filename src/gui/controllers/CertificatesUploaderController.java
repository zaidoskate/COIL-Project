package gui.controllers;

import gui.Alerts;
import gui.DataValidation;
import gui.SessionManager;
import gui.stages.CollaborationsInConclusionStage;
import gui.stages.ReviewConclusionCollaborationStage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import logic.DAOs.CollaborationDAO;
import logic.DAOs.ConcludedColaborationDAO;
import logic.DAOs.ProfessorBelongsToCollaborationDAO;
import logic.LogicException;
import logic.domain.ConcludedCollaboration;
import logic.model.CollaborationInformation;
import org.apache.log4j.Logger;

public class CertificatesUploaderController implements Initializable {
    
    private ConcludedCollaboration collaborationConcluded = new ConcludedCollaboration();
    
    @FXML
    private Slider sliderGrade;
    
    @FXML
    private CheckBox checkBoxVisible;
    
    @FXML
    private Region regionCertificates;
    
    @FXML
    private ImageView imageViewCertificates;
    
    @FXML
    private TextArea txtAreaConclusion;
    
    private static final ConcludedColaborationDAO CONCLUDED_COLLABORATION_DAO = new ConcludedColaborationDAO();
    private static final CollaborationDAO COLLABORATION_DAO = new CollaborationDAO();
    private static final ProfessorBelongsToCollaborationDAO PROFESSOR_BELONGS_TO_COLLABORATION_DAO = new ProfessorBelongsToCollaborationDAO();
    private static final CollaborationInformation COLLABORATION_INFORMATION = CollaborationInformation.getCollaboration();
    private static final SessionManager CURRENT_SESSION = SessionManager.getInstance();
    
    private static final Logger LOG = Logger.getLogger(CertificatesUploaderController.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    private boolean validateDeviceDate() throws LogicException {
        String startDate = COLLABORATION_INFORMATION.getStartDate();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String deviceDate = LocalDate.now().format(dateFormatter);
        
        return DataValidation.validateDateRange(startDate, deviceDate);
    }
    
    private boolean validateFields() throws LogicException {
        boolean validFields = true;

        if (collaborationConcluded.getCertificatesFile() == null) {
            Alerts.showWarningAlert("Es necesario cargar un archivo ZIP para las constancias");
            validFields = false;
        }
        if (!DataValidation.validateLengthField(this.txtAreaConclusion.getText(), 255)) {
            Alerts.showWarningAlert("El motivo es muy largo, procure no extenderse más de 255 caracteres");
            validFields = false;
        }

        if (!DataValidation.validateNotBlanks(this.txtAreaConclusion.getText())) {
            Alerts.showWarningAlert("El motivo es un campo obligatorio");
            validFields = false;
        }

        if (!DataValidation.validateWord(this.txtAreaConclusion.getText())) {
            Alerts.showWarningAlert("Proporcione un motivo válido, evite el uso de caracteres especiales");
            validFields = false;
        }
        return validFields;
    }

    
    private void setBasicInformationConcludedCollaboration(double grade, String visible, String conclusion) {
        this.collaborationConcluded.setIdColaboration(COLLABORATION_INFORMATION.getIdCollaboration());
        this.collaborationConcluded.setIdUser(CURRENT_SESSION.getUserData().getIdUser());
        this.collaborationConcluded.setRating((int) grade);
        this.collaborationConcluded.setVisibility(visible);
        this.collaborationConcluded.setConclusion(conclusion);
    }
    
    private void trimUnnecesaryBlanks() {
        this.txtAreaConclusion.setText(DataValidation.trimUnnecesaryBlanks(this.txtAreaConclusion.getText()));
    }
    
    @FXML
    private void onDragOver(DragEvent event) {
        Stage stage = (Stage) this.regionCertificates.getScene().getWindow();
        stage.requestFocus();
        if(event.getGestureSource() != this.regionCertificates || event.getGestureSource() != this.imageViewCertificates) {
            if(event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
        }
    }
    
    @FXML
    private void onDragDropped(DragEvent event) {
        if(event.getGestureSource() != this.regionCertificates || event.getGestureSource() != this.imageViewCertificates) {
            if(event.getDragboard().hasFiles()) {
                File fileToUpload = event.getDragboard().getFiles().get(0);
                if(DataValidation.validateFileExtension(fileToUpload.getName(), "zip")) {
                    this.collaborationConcluded.setCertificatesFile(fileToUpload);
                    Alerts.showInformationAlert("Mensaje", "Archivo zip de constancias cargado, este se perderá si abandona la ventana");
                } else {
                    Alerts.showWarningAlert("Solo puede cargar un archivo comprimido ZIP");
                }
            }
        }
    }
    
    @FXML
    private void approveConclusion() {
        trimUnnecesaryBlanks();
        try {
            if(validateDeviceDate()) {
                if(validateFields()) {
                    double collaborationGrade = this.sliderGrade.getValue();
                    String collaborationVisible = "Invisible";
                    if(this.checkBoxVisible.isSelected()) {
                        collaborationVisible = "Visible";
                    }
                    String conclusion = this.txtAreaConclusion.getText();
                    setBasicInformationConcludedCollaboration(collaborationGrade, collaborationVisible, conclusion);
                    if(CONCLUDED_COLLABORATION_DAO.addConcludedCollaboration(collaborationConcluded) == 1) {
                        if(CONCLUDED_COLLABORATION_DAO.uploadCertificates(collaborationConcluded) == 1) {
                            if(PROFESSOR_BELONGS_TO_COLLABORATION_DAO.setStatusToCollaboration(COLLABORATION_INFORMATION.getIdCollaboration(), "Concluida") == 1) {
                                if(COLLABORATION_DAO.updateEndDateByIdCollaboration(COLLABORATION_INFORMATION.getIdCollaboration()) == 1) {
                                    Alerts.showInformationAlert("Mensaje", "Colaboracion concluida con éxito");
                                    Stage currentStage =(Stage) this.regionCertificates.getScene().getWindow();
                                    currentStage.close();
                                    CollaborationsInConclusionStage collaborationsInConclusionStage = new CollaborationsInConclusionStage();
                                }
                            }
                        }
                    }
                }
            } else {
                Alerts.showWarningAlert("No es posible concluir esta colaboración, la fecha programada en su dispositivo es anterior a la de inicio de la colaboración. Verifique su configuración e inténtelo nuevamente");
            }
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            LOG.error(logicException);
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
        
    }
    
    @FXML
    private void previousMenu() {
        Stage stage = (Stage) this.sliderGrade.getScene().getWindow();
        stage.close();
        try {
            ReviewConclusionCollaborationStage reviewStage = new ReviewConclusionCollaborationStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
}
