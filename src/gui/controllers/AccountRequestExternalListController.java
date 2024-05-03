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
import logic.AccountCreator;
import logic.DAOs.ExternalAccountRequestDAO;
import logic.LogicException;
import logic.domain.ExternalAccountRequest;
import logic.domain.UvAccountRequest;
import logic.model.EmailAddress;
import logic.domain.ExternalAccountRequestData;

/**
 *
 * @author chuch
 */
public class AccountRequestExternalListController implements Initializable {
    @FXML
    private TableView<ExternalAccountRequestData> tableView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        loadUvAccountRequest();
    }
    
    private void loadUvAccountRequest() {
        tableView.getItems().clear();
        ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
        ArrayList<ExternalAccountRequestData> externalAccountRequestsData = new ArrayList();
        try{
            externalAccountRequestsData = externalAccountRequestDAO.getExternalAccountRequestsData();
        } catch(LogicException logicException) {
            Alerts.displayAlertLogicException(logicException);
        }
        tableView.getItems().addAll(externalAccountRequestsData);
    }
    
    private void deleteUvAccountRequest(ExternalAccountRequest externalAccountRequest) {
        ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
        try {
            externalAccountRequestDAO.deleteExternalAccountRequest(externalAccountRequest);
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
            int idAccountRequestSelected = tableView.getSelectionModel().getSelectedItem().getIdRequest();
            ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
            ExternalAccountRequest externalAccountRequest;
            boolean result = false;
            try{
                externalAccountRequest = externalAccountRequestDAO.getExternalAccountRequestById(idAccountRequestSelected);
                result = AccountCreator.createExternalAccount(externalAccountRequest); 
            } catch(LogicException logicException) {
                Alerts.displayAlertLogicException(logicException);
            }
            
            if(result == true) {
                Alerts.displayAccountSent();
                loadUvAccountRequest();
            } else {
                Alerts.displayAccountNoSent();
            }
        } else {
            Alerts.displayAlertNotSelectedItem();
        }
    }
    
    @FXML
    private void declineAccountRequest() {
        if(tableView.getSelectionModel().getSelectedItem() != null) {
            int idAccountRequestSelected = tableView.getSelectionModel().getSelectedItem().getIdRequest();
            ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
            ExternalAccountRequest externalAccountRequest;
            try{
                externalAccountRequest = externalAccountRequestDAO.getExternalAccountRequestById(idAccountRequestSelected);
            } catch(LogicException logicException) {
                Alerts.displayAlertLogicException(logicException);
                return;
            }
            EmailAddress.getInstance().setEmail(externalAccountRequest.getEmail());
            try {
                SendEmailStage sendEmailStage = new SendEmailStage();
            } catch(IOException ioexception) {
                Alerts.displayAlertIOException();
            }
            if(EmailAddress.getInstance().getSentStatus()) {
                deleteUvAccountRequest(externalAccountRequest);
                loadUvAccountRequest();
            }
        } else {
            Alerts.displayAlertNotSelectedItem();
        }
    }
}
