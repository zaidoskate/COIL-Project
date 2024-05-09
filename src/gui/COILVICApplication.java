package gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;


public class COILVICApplication extends Application {
    private static final Logger log = Logger.getLogger(COILVICApplication.class);
    
    @Override
    public void start(Stage stage) throws IOException {
        
        
        Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Inicio de Sesion");
        stage.setScene(scene);  
        stage.show();    
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}