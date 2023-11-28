package edu.fiuba.algo3.tablero.celda;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.tablero.celda.afectable.Afectable;

import java.util.List;

public class Camino extends Celda {
    public Camino(int x, int y, String tipo, int numeracion) {
        super(x, y, tipo, numeracion);
    }

    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        this.aplicarAfectables(gladiador);
    }
}
