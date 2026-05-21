package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.model.Cliente;
import org.example.model.Libro;
import org.example.model.Prestito;
import org.example.model.Scaffale;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class PrestitiController {

    @FXML
    public ListView<Prestito> listaPrestiti;
    @FXML
    private ListView<Cliente> listaClienti;

    @FXML
    private ListView<Libro> listaLibri;

    public void initialize() {
        listaClienti.getItems().clear();

        for(Cliente c : AppState.biblioteca.clienti){
            if (c.isTesserato()){
                listaClienti.getItems().add(c);
            }
        }

        aggiornaPrestiti();
    }

    private void aggiornaPrestiti() {
        listaPrestiti.getItems().setAll(AppState.biblioteca.prestiti);
    }

    public void goBack() throws IOException {
        App.setRoot("MainView");
    }

    public void creaPrestito() {
        listaLibri.getItems().clear();

        for (Scaffale s : AppState.biblioteca.scaffali){
            for(Libro l : s.getLibri()){
                if(l.isProntoPrestito()){
                    listaLibri.getItems().add(l);
                }
            }
        }
    }

    @FXML
    public void confermaPrestito() {

        Cliente cliente = listaClienti.getSelectionModel().getSelectedItem();
        Libro libro = listaLibri.getSelectionModel().getSelectedItem();

        if (cliente == null || libro == null) {
            System.out.println("Seleziona cliente e libro");
            return;
        }

        mostraPopupDate(cliente, libro);
    }

    private void mostraPopupDate(Cliente cliente, Libro libro) {

        Stage popup = new Stage();

        DatePicker inizioPicker = new DatePicker();
        DatePicker finePicker = new DatePicker();

        Label errore = new Label("Durata non valida");
        errore.setStyle("-fx-text-fill: red;");
        errore.setVisible(false);

        Button inviaBtn = new Button("Invia prestito");

        inviaBtn.setOnAction(e -> {

            LocalDate inizio = inizioPicker.getValue();
            LocalDate fine = finePicker.getValue();

            if (!dateValide(inizio, fine)) {
                errore.setVisible(true);
                return;
            }

            // crea prestito
            Prestito p = new Prestito(cliente, libro, inizio, fine);
            AppState.biblioteca.prestiti.add(p);

            // rimuovi libro dallo scaffale
            for (Scaffale s : AppState.biblioteca.scaffali) {
                if (s.getLibri().contains(libro)) {
                    s.rimuoviLibro(libro);
                    break;
                }
            }

            listaLibri.getItems().remove(libro);
            aggiornaPrestiti();
            popup.close();
        });

        VBox layout = new VBox(10,
                new Label("Data inizio"),
                inizioPicker,
                new Label("Data fine"),
                finePicker,
                errore,
                inviaBtn
        );

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 300);
        popup.setScene(scene);
        popup.show();
        libro.setProntoPrestito(false);
    }

    private boolean dateValide(LocalDate inizio, LocalDate fine) {

        if (inizio == null || fine == null) return false;

        if (inizio.isBefore(LocalDate.now())) return false;

        if (fine.isBefore(inizio)) return false;

        long giorni = ChronoUnit.DAYS.between(inizio, fine);

        return giorni <= 30;
    }



    public void terminaPrestito() {
        Prestito p = listaPrestiti.getSelectionModel().getSelectedItem();

        if (p==null) {
            return;
        }

        AppState.biblioteca.prestiti.remove(p); //rimuove prestito
        AppState.biblioteca.posta.add(p.getLibro()); //rimette libro nella casella posta
        aggiornaPrestiti();
    }
}
