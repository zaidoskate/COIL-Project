package gui.stages;

import gui.COILVICApplication;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminMenuStage extends Stage {
    public AdminMenuStage() throws IOException {
        Parent root = FXMLLoader.load(COILVICApplication.class.getResource("fxml/AdminMenu.fxml"));
        Scene scene = new Scene(root);
        this.setTitle("Menu Principal");
        this.setScene(scene);  
        this.show(); 
    }
}
