package edu.fiuba.algo3.tablero;

import java.util.LinkedList;

public class Tablero {

    private LinkedList<Casillero> casillas;
    private int ancho;
    private int largo;

    public Tablero(int ancho , int largo) {
        this.casillas = new LinkedList<>();
        this.ancho = ancho;
        this.largo = largo;
    }
    
    public void setDimensiones(int ancho, int largo) {
        this.ancho = ancho;
        this.largo = largo;
    }

    public void agregarCasillero(Casillero casillero) {
        casillas.add(casillero);
    }

    public Casillero obtenerCasillero(int indice) {
        if (indice >= 0 && indice < casillas.size()) {
            return casillas.get(indice);
        } else {
            throw new IndexOutOfBoundsException("Índice fuera de límites");
        }
    }

    public int cantidadCasilleros() {
        return casillas.size();
    }

    public int obtenerAncho() {
        return ancho;
    }

    public int obtenerLargo() {
        return largo;
    }
}