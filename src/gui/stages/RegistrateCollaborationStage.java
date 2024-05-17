package gui.stages;

import gui.COILVICApplication;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RegistrateCollaborationStage extends Stage {
    
    private Stage professorDetailStage;
    
    public RegistrateCollaborationStage(Stage professorDetailStage) throws IOException {
        this.professorDetailStage = professorDetailStage;
        FXMLLoader loader = new FXMLLoader(COILVICApplication.class.getResource("fxml/RegistrateCollaboration.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(COILVICApplication.class.getResource("css/OfferProfessor.css").toExternalForm());
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Registrar colaboracion");
        this.setScene(scene);
        this.showAndWait();
    }
    
    public Stage getProfessorDetailStage() {
        return this.professorDetailStage;
    }
}
