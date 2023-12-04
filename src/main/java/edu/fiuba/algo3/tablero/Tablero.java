package edu.fiuba.algo3.tablero;

import edu.fiuba.algo3.tablero.celda.Celda;

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

    // Obtener celda de Salida.
    public Celda obtenerCeldaDeSalida() {
        return celdas.getFirst();
    }
}
