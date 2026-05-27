package org.example;

import org.example.model.Biblioteca;

public class AppState { //unica istanza globale della biblioteca
    public static Biblioteca biblioteca = new Biblioteca();
    public static boolean postaTimerAvviato = false;
    public static boolean temaChiaro = true;
    public static boolean darkMode = false;
}
