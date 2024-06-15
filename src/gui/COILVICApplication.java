package gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class COILVICApplication extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/LoginFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Inicio de Sesión");
        stage.setScene(scene);  
        stage.show();    
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}