package gui.stages;

import gui.COILVICApplication;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewFolderStage extends Stage {
    public NewFolderStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(COILVICApplication.class.getResource("fxml/NewFolderFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(COILVICApplication.class.getResource("css/styles.css").toExternalForm());
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Nuevo folder");
        this.setScene(scene);
        this.showAndWait();
    }
}
