package edu.fiuba.algo3.tablero;

import edu.fiuba.algo3.casillero.EstrategiaCasillero;
import edu.fiuba.algo3.gladiador.Gladiador;

public class Casillero {
    private int posicion;
    private EstrategiaCasillero estrategiaCasillero;

    public Casillero(int posicion, EstrategiaCasillero  estrategiaCasillero) {
        this.posicion = posicion;
        this.estrategiaCasillero = estrategiaCasillero;
    }

    public void aplicarEfecto(Gladiador gladiador) {
        estrategiaCasillero.aplicarEfecto(gladiador);
    }

    public int obtenerPosicion() {
        return posicion;
    }
}
