package edu.fiuba.algo3.casillero.obstaculos;

import edu.fiuba.algo3.casillero.EstrategiaCasillero;
import edu.fiuba.algo3.gladiador.Gladiador;

public class Obstaculo implements EstrategiaCasillero {
    private Obstaculizable obstaculizable;

    public Obstaculo(Obstaculizable obstaculizable) {   // Bacanal, FieraSalvaje, Lesion.
        this.obstaculizable = obstaculizable;
    }
    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        obstaculizable.aplicarEfecto(gladiador);
    }
}
