package edu.fiuba.algo3.gladiador.seniority;

import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.log.Log;
import edu.fiuba.algo3.log.Logeador;

public class SemiSenior implements Seniority {

    public static final int TURNO_PROMOCION_SEMI_SENIOR = 12;
    @Override
    public void obtenerPlusEnergia(Energia energia) {
        energia.incrementar(5);
    }

    @Override
    public Seniority incrementarSeniority(int turno, Log log) {
        if (turno == TURNO_PROMOCION_SEMI_SENIOR) {
            Logeador.agregarALog(log, "El gladiador ascendió a Senior.");
            return new Senior();
        }
        return this;
    }
}
