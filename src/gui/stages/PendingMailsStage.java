package gui.stages;

import gui.COILVICApplication;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PendingMailsStage extends Stage{
    public PendingMailsStage() throws IOException {
        Parent root = FXMLLoader.load(COILVICApplication.class.getResource("fxml/PendingMails.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(COILVICApplication.class.getResource("css/styles.css").toExternalForm());
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Correos pendientes");
        this.setScene(scene);  
        this.showAndWait(); 
    }
}
