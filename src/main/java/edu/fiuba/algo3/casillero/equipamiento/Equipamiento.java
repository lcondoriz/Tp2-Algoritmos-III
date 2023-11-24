package edu.fiuba.algo3.casillero.equipamiento;

import edu.fiuba.algo3.casillero.EstrategiaCasillero;
import edu.fiuba.algo3.gladiador.Gladiador;

public class Equipamiento implements EstrategiaCasillero {
    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        gladiador.mejorarEquipamiento();
    }

}
