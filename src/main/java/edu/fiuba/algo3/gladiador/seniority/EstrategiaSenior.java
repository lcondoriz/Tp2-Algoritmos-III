package edu.fiuba.algo3.gladiador.seniority;

import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.EstrategiaSeniority;

public class EstrategiaSenior implements EstrategiaSeniority {

    @Override
    public void obtenerPlusEnergia(Energia energia) {
        energia.incrementar(10);
    }

    @Override
    public EstrategiaSeniority incrementarSeniority(int turno) {
        return this;
    }

}