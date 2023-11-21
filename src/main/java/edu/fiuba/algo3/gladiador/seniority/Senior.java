package edu.fiuba.algo3.gladiador.seniority;

import edu.fiuba.algo3.casillero.Equipamiento;

public class Senior implements Seniority {
    @Override
    public int obtenerPlusEnergia() {
        return 10;
    }

    public Seniority incrementarSeniority() {
        return this;
    }
}
