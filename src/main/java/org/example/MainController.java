package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;

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

    public void stageStatistiche() throws IOException {
        App.setRoot("StatisticheView");
    }

    @FXML
    private ToggleButton temaBtn;

    @FXML
    public void initialize(){
        if(AppState.darkMode){
            temaBtn.setText("☀ Tema Chiaro");
        } else {
            temaBtn.setText("🌙 Tema Scuro");
        }
    }

    @FXML
    private void toggleTema() {

        Scene scene = temaBtn.getScene();

        scene.getStylesheets().clear();

        if(AppState.temaChiaro){

            scene.getStylesheets().add(
                    getClass()
                            .getResource("/css/style.css")
                            .toExternalForm()
            );

            temaBtn.setText("🌙 Tema Scuro");

        } else {

            scene.getStylesheets().add(
                    getClass()
                            .getResource("/css/light.css")
                            .toExternalForm()
            );

            temaBtn.setText("☀ Tema Chiaro");
        }

        AppState.temaChiaro = !AppState.temaChiaro;
    }


}
