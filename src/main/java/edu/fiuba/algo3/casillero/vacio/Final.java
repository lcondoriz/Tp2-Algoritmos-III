package edu.fiuba.algo3.casillero.vacio;

import edu.fiuba.algo3.casillero.EstrategiaCasillero;
import edu.fiuba.algo3.gladiador.Gladiador;

public class Final implements EstrategiaCasillero {

    private int cantidadDeCasilleros;

    public Final(int casilleros) {
        this.cantidadDeCasilleros = casilleros;
    }


    public void aplicarEfecto(Gladiador gladiador){
        gladiador.setCasillero((cantidadDeCasilleros/2));
    }
}
