/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.controllers;

import gui.Alerts;
import gui.stages.SendEmailStage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import logic.domain.UvAccountRequest;
import logic.DAOs.UvAccountRequestDAO;
import logic.LogicException;
import logic.AccountCreator;
import logic.model.EmailAddress;

/**
 *
 * @author chuch
 */
public class AccountRequestUvListController implements Initializable{
    @FXML
    private TableView<UvAccountRequest> tableView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUvAccountRequest();
    }
    
    private void loadUvAccountRequest() {
        tableView.getItems().clear();
        UvAccountRequestDAO uvAccountRequestDAO = new UvAccountRequestDAO();
        ArrayList<UvAccountRequest> uvAccountRequests = new ArrayList();
        try{
            uvAccountRequests = uvAccountRequestDAO.getUvAccountRequests();
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
        }
        tableView.getItems().addAll(uvAccountRequests);
    }

    private void deleteUvAccountRequest(UvAccountRequest uvAccountRequest) {
        UvAccountRequestDAO uvAccountRequestDAO = new UvAccountRequestDAO();
        try {
            uvAccountRequestDAO.deleteUvAccountRequest(uvAccountRequest);
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
        }
    }
    
    @FXML
    private void previusMenu() {
        Stage stage = (Stage) tableView.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void acceptAccountRequest() {
        if(tableView.getSelectionModel().getSelectedItem() != null) {
            UvAccountRequest uvaccountRequest = tableView.getSelectionModel().getSelectedItem();
            boolean result = false;
            try{
                result = AccountCreator.createUVAccount(uvaccountRequest);   
            } catch(LogicException logicException) {
                Alerts.displayAlertLogicException(logicException);
            }
            
            if(result == true) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Exito");
                alert.setTitle("Correo enviado");
                alert.setContentText("El correo se ha enviado a su destino con la clave de acceso");
                alert.showAndWait();
                loadUvAccountRequest();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Error");
                alert.setTitle("Usuario no creado");
                alert.setContentText("No se ha podido registrar ni enviar el correo a su destino, intentelo mas tarde.");
                alert.showAndWait();
            }
        } else {
            Alerts.displayAlertNotSelectedItem();
        }
    }
    
    @FXML
    private void declineAccountRequest() {
        if(tableView.getSelectionModel().getSelectedItem() != null) {
            UvAccountRequest uvaccountRequest = tableView.getSelectionModel().getSelectedItem();
            EmailAddress.getInstance().setEmail(uvaccountRequest.getEmail());
            try {
                SendEmailStage sendEmailStage = new SendEmailStage();
            } catch(IOException ioexception) {
                Alerts.displayAlertIOException();
            }
            if(EmailAddress.getInstance().getSentStatus()) {
                deleteUvAccountRequest(uvaccountRequest);
                loadUvAccountRequest();
            }
        } else {
            Alerts.displayAlertNotSelectedItem();
        }
    }

}
