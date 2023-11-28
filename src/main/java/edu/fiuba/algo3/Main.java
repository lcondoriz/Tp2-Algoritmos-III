package edu.fiuba.algo3;

import edu.fiuba.algo3.lector.LectorJSON;
import edu.fiuba.algo3.tablero.Tablero;

public class Main {
    public static void main(String[] args) {
        App.main(args);
        LectorJSON lector = new LectorJSON();
        Tablero tablero = new Tablero(1, 1);
        lector.leerArchivo("src\\main\\java\\edu\\fiuba\\algo3\\mapa.json", tablero);
    }
}
