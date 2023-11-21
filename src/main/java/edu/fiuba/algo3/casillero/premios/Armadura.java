package edu.fiuba.algo3.casillero.premios;

import edu.fiuba.algo3.casillero.Equipamiento;

public class Armadura implements Equipamiento {
    @Override
    public Equipamiento incrementarEquipo() {
        return new EscudoEspada();
    }
    @Override
    public int perdidaEnergiaPelea() {
        return 10;
    }
}
