package edu.fiuba.algo3.gladiador;

public class Energia {
    private int puntos;

    public Energia(int puntos) {
        this.puntos = puntos;
    }

    public int obtenerPuntos() {
        return puntos;
    }

    public void incrementar(int cantidad) {
        puntos += cantidad;
    }

    public void decrementar(int cantidad) {
        puntos -= cantidad;
    }
}