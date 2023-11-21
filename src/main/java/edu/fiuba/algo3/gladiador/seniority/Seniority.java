package edu.fiuba.algo3.gladiador.seniority;

import edu.fiuba.algo3.casillero.Equipamiento;
import edu.fiuba.algo3.gladiador.Energia;

public interface Seniority {
    int obtenerPlusEnergia();

    Seniority incrementarSeniority(Energia energia, int turno);
}