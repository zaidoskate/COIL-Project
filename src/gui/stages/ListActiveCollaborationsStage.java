package gui.stages;

import gui.COILVICApplication;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ListActiveCollaborationsStage extends Stage {
    public ListActiveCollaborationsStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(COILVICApplication.class.getResource("fxml/ListActiveCollaborations.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(COILVICApplication.class.getResource("css/OfferProfessor.css").toExternalForm());
        this.setTitle("Colaboraciones activas");
        this.setScene(scene);
        this.show();
    }
}
