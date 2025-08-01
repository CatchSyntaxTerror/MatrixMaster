package gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for the main menu interface in MatrixMaster.
 * Handles user interaction for selecting operations and navigating
 * to the appropriate input screen.
 *
 * @author Youssef Amin
 */

public class MainMenuController {

    @FXML
    private Button exitBtn;

    @FXML
    private void initialize() {
        exitBtn.setOnAction(event -> {
            System.exit(0);
        });
    }
}
