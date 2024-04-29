/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.controllers;

import gui.SessionManager;
import gui.stages.OfferProfessorStage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import logic.DAOs.CollaborationOfferCandidateDAO;
import logic.DAOs.ProfessorDAO;
import logic.LogicException;
import logic.domain.CollaborationOfferCandidate;
import logic.model.OfferInformation;

/**
 *
 * @author zaido
 */
public class DetailOfferProfessorController implements Initializable {
    
    @FXML
    private Label professorName;
    
    @FXML
    private Label professorEmail;
    
    @FXML
    private Label universityName;
    
    @FXML
    private Label universityCountry;
    
    @FXML
    private TextArea objective;
    
    @FXML
    private TextArea topicsOfInterest;
    
    @FXML
    private TextField period;
    
    @FXML
    private TextArea language;
    
    @FXML
    private TextArea aditionalInformation;
    
    @FXML
    private TextField profile;
    
    @FXML
    private Label numberStudents;
    
    private ProfessorDAO professorDAO = new ProfessorDAO();
    private CollaborationOfferCandidateDAO candidateDAO = new CollaborationOfferCandidateDAO();
    
    OfferInformation selectedOffer = OfferInformation.getOffer();
    SessionManager currentSession = SessionManager.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.professorName.setText(selectedOffer.getProfessorName());
        this.professorEmail.setText(selectedOffer.getProfessorEmail());
        this.objective.setText(selectedOffer.getObjective());
        this.topicsOfInterest.setText(selectedOffer.getTopicsInterest());
        this.period.setText(selectedOffer.getOfferPeriod());
        this.language.setText(selectedOffer.getOfferLanguage());
        this.aditionalInformation.setText(selectedOffer.getAditionalInformation());
        this.numberStudents.setText(String.valueOf(selectedOffer.getNumberStudents()));
        this.profile.setText(selectedOffer.getStudentProfile());
        try {
            ArrayList<String> universityInfo = professorDAO.getUniversityFromAProfessor(selectedOffer.getIdUser());
            this.universityName.setText(universityInfo.get(0));
            this.universityCountry.setText(universityInfo.get(1));
        } catch(LogicException logicException) {
            showErrorAlert(logicException.getMessage());
        }
    }
    
    @FXML
    public void previousMenu() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("ATENCION");
        alert.setTitle("ATENCION");
        alert.setContentText("Regresará al menú anterior");
        alert.showAndWait();
        Stage stage = (Stage) this.objective.getScene().getWindow();
        stage.close();
        try{
            OfferProfessorStage offerStage = new OfferProfessorStage();
        } catch(IOException ioException) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setHeaderText("Error");
            alertError.setTitle("Error");
            alertError.setContentText("Error al regresar al menu ofertas, vuelve a intentarlo");
            alertError.showAndWait();
        }
    }
    
    @FXML
    public void applyOffer() {
        if (selectedOffer.getIdUser() == currentSession.getUserData().getIdUser()) {
            showWarningAlert("No puedes postularte a tu propia oferta");
            return;
        }

        try {
            if (candidateDAO.professorHasAppliedForOffer(currentSession.getUserData().getIdUser(), selectedOffer.getIdOfferCollaboration())) {
                showWarningAlert("Ya te has postulado a esta oferta");
            } else {
                applyForOffer();
            }
        } catch (LogicException logicException) {
            showErrorAlert(logicException.getMessage());
        }
    }

    private void applyForOffer() throws LogicException {
        CollaborationOfferCandidate candidate = new CollaborationOfferCandidate();
        candidate.setIdCollaboration(selectedOffer.getIdOfferCollaboration());
        candidate.setIdUser(currentSession.getUserData().getIdUser());
        candidateDAO.InsertCollaborationOfferCandidate(candidate);

        showInformationAlert("Te has postulado", "Se ha postulado a esta oferta, espere la correspondencia del profesor");
    }

    private void showInformationAlert(String headerText, String contentText) {
        showAlert(Alert.AlertType.INFORMATION, headerText, contentText);
    }

    private void showWarningAlert(String contentText) {
        showAlert(Alert.AlertType.WARNING, "Advertencia", contentText);
    }

    private void showErrorAlert(String contentText) {
        showAlert(Alert.AlertType.ERROR, "Error", contentText);
    }

    private void showAlert(Alert.AlertType alertType, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(headerText);
        alert.setTitle(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

}
