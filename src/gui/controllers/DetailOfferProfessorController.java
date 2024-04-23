/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.controllers;

import gui.stages.OfferProfessorStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import logic.DAOs.ProfessorDAO;
import logic.domain.CollaborationOffer;
import logic.model.OfferInformation;

/**
 *
 * @author zaido
 */
public class DetailOfferProfessorController implements Initializable {
    
    private int offerId;
    
    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }
    
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
    
    private ProfessorDAO professorDAO;
    
    OfferInformation selectedOffer = OfferInformation.getOffer();

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
    }
    
    @FXML
    public void previousMenu() {
        Stage stage = (Stage) this.objective.getScene().getWindow();
        stage.close();
        try{
            OfferProfessorStage offerStage = new OfferProfessorStage();
        } catch(IOException ioException) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setTitle("Error");
            alert.setContentText("Error al regresar al menu ofertas, vuelve a intentarlo");
            alert.showAndWait();
        }
    }
    
}
