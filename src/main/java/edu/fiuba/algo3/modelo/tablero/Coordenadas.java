package edu.fiuba.algo3.modelo.tablero;

public class Coordenadas {
    private int x;
    private int y;
    public Coordenadas (int x, int y){
        this.x= x;
        this.y =y;
    }
    public int obtenerCoordenadaX(){
        return x;
    }
    public int obtenerCoordenadaY(){
        return y;
    }
}
