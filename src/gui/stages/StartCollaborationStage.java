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
 * @author zaido
 */
public class StartCollaborationStage extends Stage {
    
    public StartCollaborationStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(COILVICApplication.class.getResource("fxml/StartCollaboration.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(COILVICApplication.class.getResource("css/OfferProfessor.css").toExternalForm());
        this.setTitle("Iniciar colaboracion");
        this.setScene(scene);
        this.show();
    }
}
