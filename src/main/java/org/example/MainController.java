package org.example;

import javafx.event.ActionEvent;

import java.io.IOException;

public class MainController {

    public void stageBiblioteca(ActionEvent event) throws IOException {
        App.setRoot("BibliotecaView");
    }

    public void stageClienti() throws IOException {
        App.setRoot("ClientiView");
    }

    public void stagePrestiti() throws IOException {
        App.setRoot("PrestitiView");
    }
}
