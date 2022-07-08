package edu.virginia.cs.hw5;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SDEWordleController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}