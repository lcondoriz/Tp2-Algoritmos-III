package edu.fiuba.algo3.modelo.juego;

public class Dado {
    private static final int CARAS_DADO = 6;
    private int caras;
    public Dado() {
        this.caras = CARAS_DADO;
    }
    public Dado(int caras) {
        this.caras = caras;
    }
    public int lanzar() {
        // Generar un número aleatorio entre 1 y el número de caras
        int resultado = (int) (Math.random() * caras) + 1;
        return resultado;
    }
}