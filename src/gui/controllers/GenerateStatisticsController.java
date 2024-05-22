package gui.controllers;

import gui.Alerts;
import gui.stages.CoordinatorMenuStage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.DAOs.UvProfessorDAO;
import logic.FileDownloader;
import logic.LogicException;
import org.apache.log4j.Logger;

public class GenerateStatisticsController implements Initializable {

    @FXML
    private Button btnContinue;
    
    @FXML
    private Button btnDownloadStatistics;
    
    @FXML
    private Label lblGenerated;
    
    private int[] regionCollaborationCounts = new int[5];
    private int[] academicAreaCollaborationCounts = new int[6];
    
    private static final UvProfessorDAO UV_PROFESSOR_DAO = new UvProfessorDAO();
    
    private static final Logger LOG = Logger.getLogger(GenerateStatisticsController.class);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            if(checkCollaborationsAvailable()) {
                this.btnDownloadStatistics.setVisible(true);
            } else {
                this.lblGenerated.setText("No se pudo generar la numeralia");
            }
        } catch(LogicException logicException) {
            this.btnDownloadStatistics.setVisible(false);
            this.lblGenerated.setText("No se pudo generar la numeralia");
            Alerts.displayAlertLogicException(logicException);
            LOG.error(logicException);
        }
    }
    
    private boolean checkCollaborationsAvailable() throws LogicException {
        boolean available = false;
        String[] regions = {"Xalapa", "Veracruz", "Coatzacoalcos", "Orizaba", "Tuxpan"};
        for(int i = 0; i < regions.length; i++) {
            regionCollaborationCounts[i] = UV_PROFESSOR_DAO.getCollaborationCountByProfessorRegion(regions[i]);
            if (regionCollaborationCounts[i] != 0) {
                available = true;
                break;
            }
        }
        for(int i = 1; i < 7; i++) {
            academicAreaCollaborationCounts[i-1] = UV_PROFESSOR_DAO.getCollaborationCountByProfessorAcademicArea(i);
            if (academicAreaCollaborationCounts[i-1] != 0) {
                available = true;
                break;
            }
        }
        return available;
    }
    
    @FXML
    private void closeDownload() {
        Stage stage = (Stage) this.btnContinue.getScene().getWindow();
        stage.close();
        try {
            CoordinatorMenuStage coordinatorMenuStage = new CoordinatorMenuStage();
        } catch(IOException ioException) {
            Alerts.displayAlertIOException();
            LOG.error(ioException);
        }
    }
    
    @FXML
    private void downloadStatistics() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar numeralia");
        fileChooser.setInitialFileName("Numeralia.xlsx");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de Excel", "*.xlsx"));
        
        File selectedPath = fileChooser.showSaveDialog(btnContinue.getScene().getWindow());
        
        if(selectedPath != null) {
            try {
                FileDownloader.exportToExcel(selectedPath.getAbsolutePath(), regionCollaborationCounts, academicAreaCollaborationCounts);
            } catch (IOException ioException) {
                Alerts.displayAlertIOException();
                LOG.error(ioException);
            }
            Alerts.showInformationAlert("Mensaje", "Se ha descargado la numeralia con éxito");
            closeDownload();
        }
    }
}
