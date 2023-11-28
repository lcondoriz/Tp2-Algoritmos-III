package edu.fiuba.algo3.tablero;

import java.util.List;

import edu.fiuba.algo3.casillero.EstrategiaCasillero;
import edu.fiuba.algo3.gladiador.Gladiador;

public class Casillero {
    private int posicion;
    private List<EstrategiaCasillero> estrategiasCasillero;

    public Casillero(int posicion, List<EstrategiaCasillero> estrategiasCasillero) {
        this.posicion = posicion;
        this.estrategiasCasillero = estrategiasCasillero;
    }

    public void setPosicion(int posicion){
        this.posicion = posicion;
    }
    
    public void aplicarEfecto(Gladiador gladiador) {
        for (EstrategiaCasillero estrategia : estrategiasCasillero) {
            estrategia.aplicarEfecto(gladiador);
        }
    }
    
    public int obtenerPosicion() {
        return posicion;
    }
}
