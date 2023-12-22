package edu.fiuba.algo3.modelo.gladiador;

public class Energia {
    public static final int ENERGIA_SUFICIENTE = 0;
    private int puntos;
    public Energia(int puntos) {
        this.puntos = puntos;
    }
    public int obtenerEnergia() {
        return puntos;
    }
    public void incrementar(int cantidad) {
        puntos += cantidad;
    }
    public void decrementar(int cantidad) {
        puntos -= cantidad;
    }
    public boolean tieneEnergiaSuficiente() {
        return puntos > ENERGIA_SUFICIENTE;
    }
}