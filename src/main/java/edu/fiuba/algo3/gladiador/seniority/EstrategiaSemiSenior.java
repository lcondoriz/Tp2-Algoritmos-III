package edu.fiuba.algo3.gladiador.seniority;

import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.EstrategiaSeniority;

public class EstrategiaSemiSenior implements EstrategiaSeniority {

    public static final int TURNO_PROMOCION_SEMI_SENIOR = 12;
    @Override
    public void obtenerPlusEnergia(Energia energia) {
        energia.incrementar(5);
    }

    @Override
    public EstrategiaSeniority incrementarSeniority(int turno) {
        if (turno == TURNO_PROMOCION_SEMI_SENIOR)
            return new EstrategiaSenior();

        return this;
    }
}
