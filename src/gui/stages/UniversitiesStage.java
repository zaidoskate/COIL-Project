package gui.stages;

import gui.COILVICApplication;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UniversitiesStage extends Stage{
    public UniversitiesStage () throws IOException {
        Parent root = FXMLLoader.load(COILVICApplication.class.getResource("fxml/UniversitiesFXML.fxml"));
        Scene scene = new Scene(root);
        this.setTitle("Universidades");
        this.setScene(scene);  
        this.show(); 
    }
}
