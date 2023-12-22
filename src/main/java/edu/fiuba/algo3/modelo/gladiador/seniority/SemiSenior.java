package edu.fiuba.algo3.modelo.gladiador.seniority;

import edu.fiuba.algo3.modelo.gladiador.Energia;
import edu.fiuba.algo3.modelo.log.Log;
import edu.fiuba.algo3.modelo.log.Logeador;

public class SemiSenior implements Seniority {
    public static final int TURNO_PROMOCION_SEMI_SENIOR = 12;
    public static final int PLUS_ENERGIA_SEMI_SENIOR = 5;
    @Override
    public void obtenerPlusEnergia(Energia energia) {
        energia.incrementar(PLUS_ENERGIA_SEMI_SENIOR);
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
