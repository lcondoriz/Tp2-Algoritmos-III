package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.tablero.celda.Celda;
import java.util.LinkedList;

public class Tablero {
    private int ancho;
    private int largo;
    private LinkedList<Celda> celdas;
    public Tablero(int ancho, int largo, LinkedList<Celda> celdas) {
        this.ancho = ancho;
        this.largo = largo;
        this.celdas = celdas;
    }
    public int obtenerAncho() {
        return ancho;
    }
    public int obtenerLargo() {
        return largo;
    }
    public Celda obtenerCeldaDeSalida() {
        return celdas.getFirst();
    }
    public LinkedList<Celda> obtenerCeldas() {
        return celdas;
    }
}
