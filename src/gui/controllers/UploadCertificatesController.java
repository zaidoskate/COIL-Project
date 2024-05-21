/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.controllers;

import gui.Alerts;
import gui.DataValidation;
import gui.SessionManager;
import gui.stages.CollaborationsInConclusionStage;
import gui.stages.ReviewConclusionCollaborationStage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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

/**
 *
 * @author zaido
 */
public class UploadCertificatesController implements Initializable {
    
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
    
    private static final ConcludedColaborationDAO concludedCollaborationDAO = new ConcludedColaborationDAO();
    private static final CollaborationDAO collaborationDAO = new CollaborationDAO();
    private static final ProfessorBelongsToCollaborationDAO professorBelongsToCollaborationDAO = new ProfessorBelongsToCollaborationDAO();
    private static final CollaborationInformation collaborationInformation = CollaborationInformation.getCollaboration();
    private static final SessionManager currentSession = SessionManager.getInstance();
    
    private static final Logger log = Logger.getLogger(UploadCertificatesController.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    private boolean validateFields() throws LogicException {
        boolean validFields = false;
        if(collaborationConcluded.getCertificatesFile() != null) {
            if(DataValidation.validateLengthField(this.txtAreaConclusion.getText(), 255)) {
                if(DataValidation.validateNotBlanks(this.txtAreaConclusion.getText()) && DataValidation.validateWord(this.txtAreaConclusion.getText())) {
                    validFields = true;
                } else {
                    Alerts.showWarningAlert("Proporcione un motivo válido");
                } 
            } else {
                Alerts.showWarningAlert("El motivo es muy largo, procure no extenderse más de 255 caracteres");
            }
        } else {
            Alerts.showWarningAlert("Es necesario cargar un archivo ZIP para las constancias");
        }
        return validFields;
    }
    
    private void setBasicInformationConcludedCollaboration(double grade, String visible, String conclusion) {
        this.collaborationConcluded.setIdColaboration(collaborationInformation.getIdCollaboration());
        this.collaborationConcluded.setIdUser(currentSession.getUserData().getIdUser());
        this.collaborationConcluded.setRating((int) grade);
        this.collaborationConcluded.setVisibility(visible);
        this.collaborationConcluded.setConclusion(conclusion);
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
                    Alerts.showInformationAlert("Mensaje", "Archivo zip de constancias cargado, este se perdera si abandona la ventana");
                } else {
                    Alerts.showWarningAlert("Solo puede cargar un archivo comprimido ZIP");
                }
            }
        }
    }
    
    @FXML
    private void approveConclusion() {
        try {
            if(validateFields()) {
                double collaborationGrade = this.sliderGrade.getValue();
                String collaborationVisible = "Invisible";
                if(this.checkBoxVisible.isSelected()) {
                    collaborationVisible = "Visible";
                }
                String conclusion = this.txtAreaConclusion.getText();
                setBasicInformationConcludedCollaboration(collaborationGrade, collaborationVisible, conclusion);
                if(concludedCollaborationDAO.addConcludedCollaboration(collaborationConcluded) == 1) {
                    if(concludedCollaborationDAO.uploadCertificates(collaborationConcluded) == 1) {
                        if(professorBelongsToCollaborationDAO.setStatusToCollaboration(collaborationInformation.getIdCollaboration(), "Concluida") == 1) {
                            if(collaborationDAO.updateEndDateByIdCollaboration(collaborationInformation.getIdCollaboration()) == 1) {
                                Alerts.showInformationAlert("Mensaje", "Colaboracion concluida con éxito");
                                Stage currentStage =(Stage) this.regionCertificates.getScene().getWindow();
                                currentStage.close();
                                CollaborationsInConclusionStage collaborationsInConclusionStage = new CollaborationsInConclusionStage();
                            }
                        }
                    }
                }
            }
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
            logicException.printStackTrace();
            //log.error(logicException);
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            //log.error(ioException);
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
            log.error(ioException);
        }
    }
}
