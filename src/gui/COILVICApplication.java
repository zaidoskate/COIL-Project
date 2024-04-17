
package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class COILVICApplication extends Application{
    public static void main() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(COILVICApplication.class.getResource("fxml/login"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sistema COIL-VIC");
        stage.setScene(scene);
        stage.show();
    }
}
