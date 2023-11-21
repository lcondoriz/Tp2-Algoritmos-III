package edu.fiuba.algo3.casillero.premios;

import edu.fiuba.algo3.casillero.Equipamiento;

public class SinEquipamiento implements Equipamiento {
    @Override
    public Equipamiento incrementarEquipo() {
        return new Casco();
    }
    @Override
    public int perdidaEnergiaPelea() {
        return 20;
    }
}
