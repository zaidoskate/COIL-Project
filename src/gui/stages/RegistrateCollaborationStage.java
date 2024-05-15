package gui.stages;

import gui.COILVICApplication;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RegistrateCollaborationStage extends Stage {
    
    public RegistrateCollaborationStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(COILVICApplication.class.getResource("fxml/RegistrateCollaboration.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(COILVICApplication.class.getResource("css/OfferProfessor.css").toExternalForm());
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Registrar colaboracion1");
        this.setScene(scene);
        this.showAndWait();
    }
}
