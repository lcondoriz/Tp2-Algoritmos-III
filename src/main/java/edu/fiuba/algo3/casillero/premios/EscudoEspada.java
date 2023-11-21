package edu.fiuba.algo3.casillero.premios;

import edu.fiuba.algo3.casillero.Equipamiento;

public class EscudoEspada implements Equipamiento {
    @Override
    public Equipamiento incrementarEquipo() {
        return new Llave();
    }
}
