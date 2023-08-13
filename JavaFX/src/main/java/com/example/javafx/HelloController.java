package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Button boton_hola;

    @FXML
    private Label welcomeText;

    @FXML
    void onHelloButtonClick(ActionEvent event) {
        welcomeText.setText("Hello");
    }

    @FXML
    void onboton_hola(ActionEvent event) {
        welcomeText.setText("Hola");
    }

}
