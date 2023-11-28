package edu.fiuba.algo3.casillero.equipamiento;

import edu.fiuba.algo3.gladiador.Energia;

public interface Equipable {
    Equipable mejorarEquipamiento(Mejorador mejorador);

    void modificarEnergiaConFiera(Energia energia);
}
