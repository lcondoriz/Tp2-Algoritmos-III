package edu.fiuba.algo3.modelo.gladiador.seniority;

import edu.fiuba.algo3.modelo.gladiador.Energia;
import edu.fiuba.algo3.modelo.log.Log;
import edu.fiuba.algo3.modelo.log.Logeador;

public class Novato implements Seniority {
    public static final int TURNO_PROMOCION_NOVATO = 8;
    public static final int PLUS_ENERGIA_NOVATO = 0;
    @Override
    public void obtenerPlusEnergia(Energia energia) {
        energia.incrementar(PLUS_ENERGIA_NOVATO);
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
