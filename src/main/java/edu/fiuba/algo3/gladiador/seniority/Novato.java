package edu.fiuba.algo3.gladiador.seniority;

import edu.fiuba.algo3.casillero.Equipamiento;
import edu.fiuba.algo3.casillero.premios.Armadura;
import edu.fiuba.algo3.casillero.premios.EscudoEspada;

public class Novato implements Seniority {
    @Override
    public int obtenerPlusEnergia() {
        return 0;
    }

    public Seniority incrementarSeniority() {
        return new SemiSenior();
    }
}
