package gui.stages;

import gui.COILVICApplication;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DetailCollaborationStage extends Stage{
    public DetailCollaborationStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(COILVICApplication.class.getResource("fxml/DetailCollaboration.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(COILVICApplication.class.getResource("css/OfferProfessor.css").toExternalForm());
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Detalles");
        this.setScene(scene);
        this.showAndWait();
    }
}
