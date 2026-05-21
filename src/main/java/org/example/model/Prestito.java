package org.example.model;

import java.time.LocalDate;

public class Prestito {
    private Cliente cliente;
    private Libro libro;
    private LocalDate inizio;
    private LocalDate fine;

    public Prestito(Cliente c, Libro l, LocalDate i, LocalDate f) {
        cliente = c;
        libro = l;
        inizio = i;
        fine = f;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Libro getLibro() {
        return libro;
    }

    @Override
    public String toString() {
        return "Prestito: " +
                "Cliente: " + cliente +
                ", Libro: " + libro +
                ", Fine prestito: " + fine +
                ", Inizio prestito: " + inizio
                ;
    }
}

