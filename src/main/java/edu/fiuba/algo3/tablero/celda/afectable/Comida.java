package edu.fiuba.algo3.tablero.celda.afectable;

import edu.fiuba.algo3.gladiador.Gladiador;

public class Comida implements Afectable {
    private static final int VIDA = 15;
    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        gladiador.incrementarEnergia(VIDA);
    }
}
