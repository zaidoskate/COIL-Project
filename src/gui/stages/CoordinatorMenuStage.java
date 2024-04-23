/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.stages;

import gui.COILVICApplication;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CoordinatorMenuStage extends Stage{
    public CoordinatorMenuStage() throws IOException {
        Parent root = FXMLLoader.load(COILVICApplication.class.getResource("fxml/CoordinatorMenu.fxml"));
        Scene scene = new Scene(root);
        this.setTitle("Menu Principal");
        this.setScene(scene);  
        this.show(); 
    }
}
