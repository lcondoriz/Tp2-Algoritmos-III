package edu.fiuba.algo3.juego;

public class Dado {
    private int caras;

    public Dado(int caras) {
        this.caras = caras;
    }

    public int lanzar() {
        // Generar un número aleatorio entre 1 y el número de caras
        int resultado = (int) (Math.random() * caras) + 1;
        return resultado;
    }

}