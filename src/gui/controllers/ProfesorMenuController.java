/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui.controllers;

import gui.SessionManager;
import gui.stages.OfferProfessorStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chuch
 */
public class ProfesorMenuController implements Initializable {

    @FXML
    private Text textName;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textName.setText(SessionManager.getInstance().getUserData().getName());
    }
    
    @FXML
    public void displayOfferProfessor() throws IOException {
        Stage menuStage = (Stage) textName.getScene().getWindow();
        menuStage.close();
        OfferProfessorStage offerProfessorStage = new OfferProfessorStage();
    }

}
