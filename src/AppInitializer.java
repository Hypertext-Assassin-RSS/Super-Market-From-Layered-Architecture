import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("view/main-window.fxml"));
        Scene mainScene = new Scene(load);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Super Market");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
