package org.example.model;

public class Cliente {
    private String nome;
    private boolean tesserato;

    public Cliente(String nome, boolean tesserato) {
        this.nome = nome;
        this.tesserato = false;
    }

    public String getNome() {
        return nome;
    }

    public boolean isTesserato() {
        return tesserato;
    }

    public void setTesserato(boolean t) {
        this.tesserato = t;
    }

    @Override
    public String toString() {
        return nome + (tesserato ? " (Tesserato)" : "");
    }

}
