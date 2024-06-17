package gui.controllers;

import gui.Alerts;
import gui.stages.CoordinatorMenuStage;
import gui.stages.UniversityRegistrationStage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import logic.DAOs.UniversityDAO;
import logic.domain.University;
import logic.LogicException;
import org.apache.log4j.Logger;

public class UniversitiesController implements Initializable {
    private static final UniversityDAO UNIVERSITY_DAO = new UniversityDAO();
    private static final Logger LOG = Logger.getLogger(UniversitiesController.class);
    @FXML
    private TableView<University> tblViewUniversities;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUniversities();
    }
    
    private void loadUniversities() {
        ArrayList<University> universities = new ArrayList();
        try {
            universities = UNIVERSITY_DAO.getUniversities();            
        } catch (LogicException logicException) {
            LOG.error(logicException);
        }
        tblViewUniversities.getItems().addAll(universities);
    }
    
    @FXML
    private void previusMenu() {
        Stage stage = (Stage) tblViewUniversities.getScene().getWindow();
        stage.close();
        try {
            CoordinatorMenuStage coordinatorMenuStage = new CoordinatorMenuStage();
        } catch (IOException ioException) {
            LOG.warn(ioException);
            Alerts.displayAlertIOException();
        }
    }
    
    @FXML
    private void newUniversity() {
        Stage stage = (Stage) tblViewUniversities.getScene().getWindow();
        stage.close();
        try {
            UniversityRegistrationStage universityRegistrationStage = new UniversityRegistrationStage();
        } catch (IOException ioException) {
            LOG.warn(ioException);
            Alerts.displayAlertIOException();
        }
    }
    
}
