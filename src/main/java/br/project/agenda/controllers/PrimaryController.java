package br.project.agenda.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.project.agenda.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class PrimaryController implements Initializable  {

    @FXML
    private VBox idvboxMain;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {       //Controlador que ao abrir a tela ele pode fazer algo
        String imageUrl = getClass().getResource("/img/background.jpg").toExternalForm();
        String fx = String.format("-fx-background-image: url('%s'); -fx-background-size: cover;", imageUrl);
        idvboxMain.setStyle(fx);

    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
        System.out.println("Entrei na segunda tela");
    }

    @FXML
    private void switchTotertiary() throws IOException {
        App.setRoot("tertiary");
    }
}
