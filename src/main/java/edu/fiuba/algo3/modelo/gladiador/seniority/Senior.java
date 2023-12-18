package edu.fiuba.algo3.modelo.gladiador.seniority;

import edu.fiuba.algo3.modelo.gladiador.Energia;
import edu.fiuba.algo3.modelo.log.Log;

public class Senior implements Seniority {
    public static final int PLUS_ENERGIA_SENIOR = 10;
    @Override
    public void obtenerPlusEnergia(Energia energia) {
        energia.incrementar(PLUS_ENERGIA_SENIOR);
    }
    @Override
    public Seniority incrementarSeniority(int turno, Log log) {
        return this;
    }

}