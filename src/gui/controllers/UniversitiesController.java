package gui.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import logic.DAOs.UniversityDAO;
import logic.domain.University;
import logic.LogicException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chuch
 */
public class UniversitiesController implements Initializable {
    @FXML
    private TableView<University> tableView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUniversities();
    }
    
    private void loadUniversities() {
        UniversityDAO universityDAO = new UniversityDAO();
        ArrayList<University> universities = new ArrayList();
        try {
            universities = universityDAO.getUniversities();            
        } catch(LogicException logicException) {
            logicException.printStackTrace();
        }
        tableView.getItems().addAll(universities);
    }
    
}
