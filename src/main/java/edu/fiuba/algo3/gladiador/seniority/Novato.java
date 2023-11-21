package edu.fiuba.algo3.gladiador.seniority;

import edu.fiuba.algo3.gladiador.Energia;

public class Novato implements Seniority {

    @Override
    public int obtenerPlusEnergia() {
        return 0;
    }

    @Override
    public Seniority incrementarSeniority(Energia energia, int turno) {

        if (turno == 8) {
            energia.incrementar(5);
            return new SemiSenior();

        }
        energia.incrementar(0);
        return this;
    }
}
