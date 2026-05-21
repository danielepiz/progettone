package org.example;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.example.model.Cliente;

import java.io.IOException;


public class ClientiController {

    @FXML
    private ListView<Cliente> listaClienti;

    @FXML
    public void initialize() {
        listaClienti.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        aggiornaLista();
    }

    public void toggleTessera() {
        ObservableList<Cliente> selezionati = listaClienti.getSelectionModel().getSelectedItems();
        for (Cliente c : selezionati){
            c.setTesserato(!c.isTesserato());
        }
        aggiornaLista();
    }

    public void banCliente() {
        ObservableList<Cliente> selezionati = listaClienti.getSelectionModel().getSelectedItems();
        for (Cliente c : selezionati){
            AppState.biblioteca.clienti.remove(c);
        }
        aggiornaLista();

//        Cliente c = listaClienti.getSelectionModel().getSelectedItem();
//        if (c != null) {
//            AppState.biblioteca.clienti.remove(c);
//            aggiornaLista();
//        }
    }

    private void aggiornaLista(){
        listaClienti.getItems().setAll(AppState.biblioteca.clienti);
    }

    public void goBack() throws IOException {
        App.setRoot("MainView");
    }
}
