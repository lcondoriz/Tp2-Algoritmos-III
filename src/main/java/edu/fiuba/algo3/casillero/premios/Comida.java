package edu.fiuba.algo3.casillero.premios;

import edu.fiuba.algo3.casillero.Afectable;
import edu.fiuba.algo3.gladiador.Gladiador;

public class Comida implements Afectable {
    private int incrementoEnergia;

    public Comida(int incrementoEnergia) {
        this.incrementoEnergia = incrementoEnergia;
    }

    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        gladiador.aumentarEnergia(incrementoEnergia);
    }
}