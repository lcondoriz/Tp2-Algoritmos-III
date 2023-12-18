package edu.fiuba.algo3.gladiador.seniority;

import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.log.Log;

public interface Seniority {
    void obtenerPlusEnergia(Energia energia);
    Seniority incrementarSeniority(int turno, Log log);
}