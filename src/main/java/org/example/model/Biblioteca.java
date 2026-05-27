package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    public List<Scaffale> scaffali = new ArrayList<>();
    public List<Cliente> clienti = new ArrayList<>();
    public List<Prestito > prestiti = new ArrayList<>();
    public List<Libro> posta = new ArrayList<>();

    public void inizializzaClienti() {
        clienti.add(new Cliente("Mario Rossi" , false));
        clienti.add(new Cliente("Luigi Verdi" , false));
        clienti.add(new Cliente("Anna Bianchi" , false));
        clienti.add(new Cliente("Giulia Neri" , false));
        clienti.add(new Cliente("Marco Gialli" , false));
        clienti.add(new Cliente("Giovanni Esposito", false));
        clienti.add(new Cliente("Francesco Romano", false));
        clienti.add(new Cliente("Sofia Lombardi", false));
        clienti.add(new Cliente("Antonio Ferrari", false));
        clienti.add(new Cliente("Chiara Conti", false));
        clienti.add(new Cliente("Roberto Ricci", false));
        clienti.add(new Cliente("Martina Moretti", false));
        clienti.add(new Cliente("Paolo Greco", false));
        clienti.add(new Cliente("Giulia De Luca", false));
        clienti.add(new Cliente("Luca Bianchi", false));
        clienti.add(new Cliente("Sara Martinelli", false));
        clienti.add(new Cliente("Davide Costa", false));
        clienti.add(new Cliente("Elena Rizzo", false));
        clienti.add(new Cliente("Matteo Bruno", false));
        clienti.add(new Cliente("Valentina Gallo", false));
        clienti.add(new Cliente("Alessandro Fontana", false));
        clienti.add(new Cliente("Beatrice Orlando", false));
        clienti.add(new Cliente("Nicola Serra", false));
        clienti.add(new Cliente("Federica Leone", false));
        clienti.add(new Cliente("Simone Piazza", false));
        clienti.add(new Cliente("Irene Vitale", false));
        clienti.add(new Cliente("Tommaso Rinaldi", false));
        clienti.add(new Cliente("Camilla Sartori", false));
        clienti.add(new Cliente("Emanuele Barbieri", false));
    }


}
