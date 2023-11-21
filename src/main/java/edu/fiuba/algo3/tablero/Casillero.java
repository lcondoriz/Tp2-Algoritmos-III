package edu.fiuba.algo3.tablero;

import edu.fiuba.algo3.casillero.Afectable;
import edu.fiuba.algo3.gladiador.Gladiador;

public class Casillero {
    int posicion;
    private Afectable elementoCasillero; // Puede ser null si no hay elemento en el casillero

    public Casillero(int posicion, Afectable elementoCasillero) {
        this.posicion = posicion;
        this.elementoCasillero = elementoCasillero;
    }

    public int obtenerPosicion() {
        return this.posicion;
    }

    public Casillero avanzar(int cantidad) {
        this.posicion += cantidad;
        // logica de avanzar y devolver el nuevo casillero
        return this;
    }

    public Afectable obtenerElementoCasillero() {
        return this.elementoCasillero;
    }

    public void aplicarEfecto(Gladiador gladiador) {
        if (elementoCasillero != null) {
            elementoCasillero.aplicarEfecto(gladiador);
        }
    }
}
