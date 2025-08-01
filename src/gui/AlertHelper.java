package gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * Utility class for displaying JavaFX alert dialogs.
 * Provides static methods to show error and informational messages.
 * Used throughout the GUI to report feedback to the user.
 *
 * @author Youssef Amin
 */

public class AlertHelper {

    public static void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showInfo(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
