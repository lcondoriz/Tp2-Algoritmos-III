package edu.fiuba.algo3.gladiador.seniority;

import edu.fiuba.algo3.gladiador.Energia;

public class SemiSenior implements Seniority {

    @Override
    public int obtenerPlusEnergia() {
        return 10;
    }

    @Override
    public Seniority incrementarSeniority(Energia energia, int turno) {
        if (turno >= 12) {
            energia.incrementar(5);
            return new Senior();
        }
        energia.incrementar(5);
        return this;
    }
}