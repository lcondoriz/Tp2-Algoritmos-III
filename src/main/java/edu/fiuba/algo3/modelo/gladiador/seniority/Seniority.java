package edu.fiuba.algo3.modelo.gladiador.seniority;

import edu.fiuba.algo3.modelo.gladiador.Energia;
import edu.fiuba.algo3.modelo.log.Log;

public interface Seniority {
    void obtenerPlusEnergia(Energia energia);
    Seniority incrementarSeniority(int turno, Log log);
}