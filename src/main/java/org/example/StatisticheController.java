package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class StatisticheController {

    @FXML
    private Label libriLabel;

    @FXML
    private Label clientiLabel;

    @FXML
    private Label prestitiLabel;

    @FXML
    private Label postaLabel;

    @FXML
    private Label scaffaleLabel;

    @FXML
    public void initialize() {

        int totaleLibri = 0;

        int max = 0;
        String nomeScaffale = "";

        for(var s : AppState.biblioteca.scaffali){

            totaleLibri += s.getLibri().size();

            if(s.getLibri().size() > max){

                max = s.getLibri().size();
                nomeScaffale = s.getCategoria();
            }
        }

        long tesserati =
                AppState.biblioteca.clienti
                        .stream()
                        .filter(c -> c.isTesserato())
                        .count();

        libriLabel.setText(
                "Libri negli scaffali: " + totaleLibri
        );

        clientiLabel.setText(
                "Clienti tesserati: " + tesserati
        );

        prestitiLabel.setText(
                "Prestiti attivi: "
                        + AppState.biblioteca.prestiti.size()
        );

        postaLabel.setText(
                "Libri nella posta: "
                        + AppState.biblioteca.posta.size()
        );

        scaffaleLabel.setText(
                "Scaffale più pieno: "
                        + nomeScaffale
        );
    }

    public void goBack() throws IOException {

        App.setRoot("MainView");
    }
}
