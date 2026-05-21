package org.example.model;

import javafx.scene.image.Image;

public class Libro {
    private String titolo;
    private String categoria;
    private Image copertinaPath;
    private boolean prontoPrestito;

    public Libro(String titolo, String categoria, Image copertinaPath) {
        this.titolo = titolo;
        this.categoria = categoria;
        this.copertinaPath = copertinaPath;
        this.prontoPrestito = false;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean isProntoPrestito() {
        return prontoPrestito;
    }

    public void setProntoPrestito(boolean v) {
        prontoPrestito = v;
    }

    @Override
    public String toString() {
        return titolo + " (" + categoria + ")";
    }

    public Image getCopertinaPath() {
        return copertinaPath;
    }
}
