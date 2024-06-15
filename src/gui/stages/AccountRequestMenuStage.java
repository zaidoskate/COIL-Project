package gui.stages;

import gui.COILVICApplication;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AccountRequestMenuStage extends Stage{
    public AccountRequestMenuStage() throws IOException {
        Parent root = FXMLLoader.load(COILVICApplication.class.getResource("fxml/AccountRequestMenuFXML.fxml"));
        Scene scene = new Scene(root);
        this.setTitle("Solicitudes de cuenta");
        this.setScene(scene);  
        this.show(); 
    }
}
