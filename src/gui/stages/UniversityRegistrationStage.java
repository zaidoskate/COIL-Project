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

/**
 *
 * @author chuch
 */
public class UniversityRegistrationStage extends Stage {
    public UniversityRegistrationStage () throws IOException {
        Parent root = FXMLLoader.load(COILVICApplication.class.getResource("fxml/UniversityRegistrationFXML.fxml"));
        Scene scene = new Scene(root);
        this.setTitle("Registro de Universidad");
        this.setScene(scene);  
        this.show(); 
    }
}
