package edu.fiuba.algo3.casillero.comestibles;

import edu.fiuba.algo3.casillero.EstrategiaCasillero;
import edu.fiuba.algo3.gladiador.Gladiador;

public class Comida implements EstrategiaCasillero {

    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        gladiador.incrementarEnergia(15);
    }

}