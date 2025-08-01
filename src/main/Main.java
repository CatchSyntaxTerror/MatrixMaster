package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
/**
 * Entry point for the MatrixMaster JavaFX application.
 * Initializes and displays the main window. Future versions
 * will load the operation menu and launch the calculator GUI.
 *
 * @author Youssef Amin
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/layout.fxml"));

        // Get screen size
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = screenBounds.getWidth() * 0.66;
        double height = screenBounds.getHeight() * 0.66;

        // Set scene with half screen size
        Scene scene = new Scene(root, width, height);

        primaryStage.setTitle("MatrixMaster");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
