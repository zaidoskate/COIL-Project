package gui.stages;

import gui.COILVICApplication;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UploadEvidencesStage extends Stage {
    public UploadEvidencesStage() throws IOException {
        Parent root = FXMLLoader.load(COILVICApplication.class.getResource("fxml/UploadEvidences.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(COILVICApplication.class.getResource("css/OfferProfessor.css").toExternalForm());
        scene.getStylesheets().add(COILVICApplication.class.getResource("css/styles.css").toExternalForm());
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Subir evidencias");
        this.setScene(scene);  
        this.showAndWait();
    }
}
