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
public class AccountRequestStage extends Stage {
    public AccountRequestStage() throws IOException {
        Parent root = FXMLLoader.load(COILVICApplication.class.getResource("fxml/AccountRequest.fxml"));
        Scene scene = new Scene(root);
        this.setTitle("Solicitud de cuenta");
        this.setScene(scene);  
        this.show(); 
    }
}
