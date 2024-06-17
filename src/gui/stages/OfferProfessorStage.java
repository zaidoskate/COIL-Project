package gui.stages;

import gui.COILVICApplication;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OfferProfessorStage extends Stage {
    
    public OfferProfessorStage() throws IOException {
        Parent root = FXMLLoader.load(COILVICApplication.class.getResource("fxml/OfferProfessorFXML.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(COILVICApplication.class.getResource("css/OfferProfessor.css").toExternalForm());
        this.setTitle("Ofertas");
        this.setScene(scene);
        this.show();
    }
}
