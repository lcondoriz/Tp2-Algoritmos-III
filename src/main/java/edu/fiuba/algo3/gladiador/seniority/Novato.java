package edu.fiuba.algo3.gladiador.seniority;

import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.log.Logeador;

public class Novato implements Seniority {
    public static final int TURNO_PROMOCION_NOVATO = 8;
    @Override
    public void obtenerPlusEnergia(Energia energia) {
        energia.incrementar(0);
    }

    @Override
    public Seniority incrementarSeniority(int turno) {
        if (turno == TURNO_PROMOCION_NOVATO){
            return new SemiSenior();
        }
        return this;
    }


}
