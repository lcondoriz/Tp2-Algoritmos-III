package edu.fiuba.algo3.gladiador.seniority;

import edu.fiuba.algo3.gladiador.Energia;

public class Senior implements Seniority {
    @Override
    public int obtenerPlusEnergia() {
        return 10;
    }

    @Override
    public Seniority incrementarSeniority(Energia energia, int turno) {
        energia.incrementar(10);
        return this;
    }
}
