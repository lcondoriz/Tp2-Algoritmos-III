package edu.fiuba.algo3.gladiador.seniority;

import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.EstrategiaSeniority;

public class EstrategiaSemiSenior implements EstrategiaSeniority {

    @Override
    public void obtenerPlusEnergia(Energia energia) {
        energia.incrementar(5);
    }

    @Override
    public EstrategiaSeniority incrementarSeniority(int turno) {
        if (turno == 12)
            return new EstrategiaSenior();

        return this;
    }
}
