package edu.fiuba.algo3.gladiador.seniority;

import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.EstrategiaSeniority;

public class EstrategiaNovato implements EstrategiaSeniority {
    public static final int TURNO_PROMOCION_NOVATO = 8;
    @Override
    public void obtenerPlusEnergia(Energia energia) {
        energia.incrementar(0);
    }

    @Override
    public EstrategiaSeniority incrementarSeniority(int turno) {
        if (turno == TURNO_PROMOCION_NOVATO)
            return new EstrategiaSemiSenior();

        return this;
    }


}
