package edu.fiuba.algo3.gladiador.seniority;

import edu.fiuba.algo3.gladiador.Energia;

public class Senior implements Seniority {

    @Override
    public void obtenerPlusEnergia(Energia energia) {
        energia.incrementar(10);
    }

    @Override
    public Seniority incrementarSeniority(int turno) {
        return this;
    }

}