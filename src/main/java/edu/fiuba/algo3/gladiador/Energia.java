package edu.fiuba.algo3.gladiador;

public class Energia {
    private int puntos;

    public Energia(int puntos) {
        this.puntos = puntos;
    }

    public int obtenerEnergia() {
        return puntos;
    }

    public void incrementar(int cantidad) {
        this.puntos += cantidad;
    }

    public void decrementar(int cantidad) {
        this.puntos -= cantidad;
    }

    public void actualizar(int cantidad){
        this.puntos = cantidad;
    }
    public boolean tieneEnergiaSuficiente() {
        return puntos > 0;
    }

}