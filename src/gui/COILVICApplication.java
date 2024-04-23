package gui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author zaido
 */
import java.io.IOException;
import javafx.scene.Parent;
public class COILVICApplication extends Application {
    
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);  
        } catch(IOException e) {
            e.printStackTrace();
        }
        stage.show();    
    }
    public static void main(String[] args) {
        launch();
    }
    
}
