package gui.stages;

import gui.COILVICApplication;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AccountRequestExternalListStage extends Stage {
    public AccountRequestExternalListStage() throws IOException {
        Parent root = FXMLLoader.load(COILVICApplication.class.getResource("fxml/AccountRequestExternalListFXML.fxml"));
        Scene scene = new Scene(root);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Solicitudes de cuenta");
        this.setScene(scene);  
        this.showAndWait();
    }
}
