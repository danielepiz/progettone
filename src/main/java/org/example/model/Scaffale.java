package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Scaffale {
    private String categoria;
    private List<Libro> libri;
    private int capacita;

    public Scaffale(String categoria, int capacita) {
        this.categoria = categoria;
        this.capacita = capacita;
        this.libri = new ArrayList<>();
    }

    public boolean aggiungiLibro(Libro libro) {
        if (libri.size() < capacita) {
            libri.add(libro);
            return true;
        }
        return false;
    }

    public void rimuoviLibro(Libro libro) {
        libri.remove(libro);
    }

    public List<Libro> getLibri() {
        return libri;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getCapacita() {
        return capacita;
    }
}

