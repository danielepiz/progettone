package org.example;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.example.model.Libro;
import org.example.model.Scaffale;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class BibliotecaController {

    @FXML
    private GridPane scaffaleGrid;

    @FXML
    private HBox postaBox;

    @FXML
    private Label timerLabel;

    private int countdown = 0;

    private int index = 0;

    // =========================================================
    // INITIALIZE
    // =========================================================

    @FXML
    public void initialize() {

        // crea scaffali SOLO una volta
        if (AppState.biblioteca.scaffali.isEmpty()) {

            AppState.biblioteca.scaffali
                    .add(new Scaffale("SCAFFALE1", 6));

            AppState.biblioteca.scaffali
                    .add(new Scaffale("SCAFFALE2", 6));

            AppState.biblioteca.scaffali
                    .add(new Scaffale("SCAFFALE3", 6));

            AppState.biblioteca.scaffali
                    .add(new Scaffale("SCAFFALE4", 6));

            AppState.biblioteca.scaffali
                    .add(new Scaffale("SCAFFALE5", 6));

            AppState.biblioteca.scaffali
                    .add(new Scaffale("SCAFFALE6", 6));
        }

        aggiornaVista();
        aggiornaPostaView();

        // evita più timer
        if (!AppState.postaTimerAvviato) {

            startPostaTimer();

            AppState.postaTimerAvviato = true;
        }
    }

    //aggiorna scaffale

    private void aggiornaVista() {

        scaffaleGrid.getChildren().clear();

        Scaffale s =
                AppState.biblioteca.scaffali.get(index);

        int col = 0;
        int row = 0;

        for (Libro l : s.getLibri()) {

            ImageView img =
                    new ImageView(l.getCopertinaPath());

            stileLibro(img);

            // click popup
            img.setOnMouseClicked(e -> mostraPopup(l));

            // categoria sbagliata
            if (!l.getCategoria()
                    .equalsIgnoreCase(s.getCategoria())) {

                img.setStyle("-fx-border-color: red; -fx-border-width: 3;");
            }

            scaffaleGrid.add(img, col, row);

            col++;

            if (col == 3) {

                col = 0;
                row++;
            }
        }
    }

    //stile libri

    private void stileLibro(ImageView img) {

        // classe css
        img.getStyleClass().add("libro");

        // dimensioni
        img.setFitWidth(90);
        img.setFitHeight(140);

        // effetto hover
        img.setOnMouseEntered(e -> {

            img.setScaleX(1.08);
            img.setScaleY(1.08);

            // ombra più forte in hover
            img.setStyle(" -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.8), 18,0,4,4); ");
        });

        // ritorno normale
        img.setOnMouseExited(e -> {

            img.setScaleX(1);
            img.setScaleY(1);

            // stile normale
            img.setStyle(" -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10,0,2,2); ");
        });
    }


    //caricamento immagini

    private Image caricaImmagineLibro(String path) {

        InputStream stream =
                getClass().getResourceAsStream(path);

        if (stream == null) {

            System.out.println(
                    "IMMAGINE NON TROVATA: " + path
            );

            return new Image(
                    getClass()
                            .getResourceAsStream(
                                    "/img/placeholder.png"
                            )
            );
        }

        return new Image(stream);
    }

    //genera libro random

    private Libro generaLibroRandom() {

        String[] categorie = {

                "FANTASY",
                "HORROR",
                "ROMANCE",
                "T&M",
                "SCIFI"
        };

        Random random = new Random();

        String categoria =
                categorie[random.nextInt(categorie.length)];

        int numero = random.nextInt(6) + 1;

        String nomeFile =
                "/img/libro" + numero + categoria + ".png";

        System.out.println(nomeFile);

        Image copertina =
                caricaImmagineLibro(nomeFile);

        return new Libro(
                "Libro " + numero + " " + categoria,
                categoria,
                copertina
        );
    }

    //posta

    private void generaPosta() {

        int MAX_POSTA = 4;

        // posta piena
        if (AppState.biblioteca.posta.size() >= MAX_POSTA) {

            System.out.println("Posta piena");

            return;
        }

        Libro l = generaLibroRandom();

        AppState.biblioteca.posta.add(l);

        aggiornaPostaView();
    }

    private void aggiornaPostaView() {

        postaBox.getChildren().clear();

        for (Libro l : AppState.biblioteca.posta) {

            ImageView img =
                    new ImageView(l.getCopertinaPath());

            stileLibro(img);

            img.setFitWidth(60);
            img.setFitHeight(90);

            // click -> aggiungi scaffale
            img.setOnMouseClicked(
                    e -> aggiungiAScaffale(l)
            );

            postaBox.getChildren().add(img);
        }

        // bordo rosso se piena
        if (AppState.biblioteca.posta.size() >= 4) {

            postaBox.setStyle(" -fx-border-color: red; -fx-padding: 10; ");

        } else {

            postaBox.setStyle(" -fx-border-color: black; -fx-padding: 10; ");
        }
    }

    //aggiunge allo scaffale

    private void aggiungiAScaffale(Libro l) {

        Scaffale scaffaleCorrente =
                AppState.biblioteca.scaffali.get(index);

        // scaffale pieno
        if (scaffaleCorrente.getLibri().size() >= 6) {

            Alert alert =
                    new Alert(Alert.AlertType.WARNING);

            alert.setContentText("Scaffale pieno!");

            alert.show();

            return;
        }

        scaffaleCorrente.aggiungiLibro(l);

        AppState.biblioteca.posta.remove(l);

        aggiornaVista();
        aggiornaPostaView();
    }

    //timer per la posta


    private void startPostaTimer() {

        Timeline timeline = new Timeline(

                new KeyFrame(Duration.seconds(1), e -> {

                    countdown--;

                    timerLabel.setText(
                            "Nuovo libro tra: "
                                    + countdown + "s"
                    );

                    if(countdown <= 0){

                        generaPosta();

                        countdown = 2;
                    }
                })
        );

        timeline.setCycleCount(Animation.INDEFINITE);

        timeline.play();
    }


    //popup del libro

    private void mostraPopup(Libro libro) {

        Stage popup = new Stage();

        ImageView img =
                new ImageView(libro.getCopertinaPath());

        img.setFitWidth(200);
        img.setFitHeight(300);

        Button prestitoBtn = new Button();

        // aggiorna testo bottone
        Runnable aggiornaTesto = () -> {

            if (libro.isProntoPrestito()) {

                prestitoBtn.setText(
                        "Annulla prestito"
                );

            } else {

                prestitoBtn.setText(
                        "Prepara per il prestito"
                );
            }
        };

        aggiornaTesto.run();

        // toggle prestito
        prestitoBtn.setOnAction(e -> {

            libro.setProntoPrestito(
                    !libro.isProntoPrestito()
            );

            aggiornaTesto.run();

            aggiornaVista();
        });

        VBox layout =
                new VBox(15, img, prestitoBtn);

        layout.setAlignment(Pos.CENTER);

        Scene scene =
                new Scene(layout, 320, 420);

        popup.setScene(scene);

        popup.show();
    }

    //navigazione scaffali

    public void nextScaffale() {

        index =
                (index + 1)
                        % AppState.biblioteca.scaffali.size();

        aggiornaVista();
    }

    public void prevScaffale() {

        index--;

        if (index < 0) {

            index =
                    AppState.biblioteca.scaffali.size() - 1;
        }

        aggiornaVista();
    }

    //torna al menu

    public void goBack() throws IOException {

        App.setRoot("MainView");
    }
}
