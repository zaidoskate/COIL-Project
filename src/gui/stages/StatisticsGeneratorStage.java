package gui.stages;

import gui.COILVICApplication;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StatisticsGeneratorStage extends Stage {
    
    public StatisticsGeneratorStage() throws IOException {
        Parent root = FXMLLoader.load(COILVICApplication.class.getResource("fxml/StatisticsGeneratorFXML.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(COILVICApplication.class.getResource("css/OfferProfessor.css").toExternalForm());
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Descargar Numeralia");
        this.setScene(scene);  
        this.showAndWait(); 
    }
}
