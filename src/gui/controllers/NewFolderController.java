package gui.controllers;

import gui.Alerts;
import gui.DataValidation;
import gui.SessionManager;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.DAOs.EvidenceFolderDAO;
import logic.LogicException;
import logic.domain.EvidenceFolder;
import logic.model.CollaborationInformation;
import org.apache.log4j.Logger;

public class NewFolderController implements Initializable{
    private static final Logger LOG = Logger.getLogger(NewFolderController.class);
    private static final CollaborationInformation CURRENT_COLLABORATION = CollaborationInformation.getCollaboration();
    private static final EvidenceFolderDAO EVIDENCE_FOLDER_DAO = new EvidenceFolderDAO();
    @FXML
    private TextField txtFieldName;
    @FXML
    private TextArea txtAreaDescription;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    private boolean makeValidations() {
        if(DataValidation.validateLengthField(txtFieldName.getText(), 45) == false) {
            return false;
        }
        if(DataValidation.validateLengthField(txtAreaDescription.getText(), 255) == false) {
            return false;
        }
        if(DataValidation.validateWord(txtAreaDescription.getText()) == false) {
            return false;
        }
        return true;
    }
    
    @FXML
    private void previusMenu() {
        Stage stage = (Stage) txtFieldName.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void saveFolder() {
        if(makeValidations()) {
            EvidenceFolder evidenceFolder = new EvidenceFolder();
            evidenceFolder.setDescription(txtAreaDescription.getText());
            evidenceFolder.setIdCollaboration(CURRENT_COLLABORATION.getIdCollaboration());
            evidenceFolder.setName(txtFieldName.getText());
            
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = currentDate.format(formatter);
            
            evidenceFolder.setCreationDate(formattedDate);
            try {
                EVIDENCE_FOLDER_DAO.insertEvidenceFolder(evidenceFolder);
                Alerts.showInformationAlert("Exito", "Se ha creado el folder con exito.");
                previusMenu();
            } catch(LogicException logicException) {
                LOG.error(logicException);
                Alerts.displayAlertLogicException(logicException);
            }
        } else {
            Alerts.showWarningAlert("No se ha podido crear el folder deseado, intentalo mas tarde.");
        }
    }
    
}