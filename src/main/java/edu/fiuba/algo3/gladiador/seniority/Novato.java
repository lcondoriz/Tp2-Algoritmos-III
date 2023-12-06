package edu.fiuba.algo3.gladiador.seniority;

import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.log.Log;
import edu.fiuba.algo3.log.Logeador;

public class Novato implements Seniority {
    public static final int TURNO_PROMOCION_NOVATO = 8;
    @Override
    public void obtenerPlusEnergia(Energia energia) {
        energia.incrementar(0);
    }

    @Override
    public Seniority incrementarSeniority(int turno, Log log) {
        if (turno == TURNO_PROMOCION_NOVATO){
            Logeador.agregarALog(log,"El gladiador ascendió a SemiSenior.");
            return new SemiSenior();
        }
        return this;
    }


}
