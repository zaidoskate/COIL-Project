package gui.stages;

import gui.COILVICApplication;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UniversityRegistrationStage extends Stage {
    public UniversityRegistrationStage () throws IOException {
        Parent root = FXMLLoader.load(COILVICApplication.class.getResource("fxml/UniversityRegistrationFXML.fxml"));
        Scene scene = new Scene(root);
        this.setTitle("Registro de Universidad");
        this.setScene(scene);  
        this.show(); 
    }
}
