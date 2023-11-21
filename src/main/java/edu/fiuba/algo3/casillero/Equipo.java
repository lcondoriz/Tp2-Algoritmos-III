package edu.fiuba.algo3.casillero;

import edu.fiuba.algo3.gladiador.Gladiador;

public class Equipo implements Afectable {
    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        gladiador.incrementarEquipamiento();
    }
}
