package edu.fiuba.algo3.casillero.equipamiento;

import edu.fiuba.algo3.gladiador.Energia;

public class Armadura implements Equipable {

    private static final int DANIO_POR_FIERA = 10;
    @Override
    public Equipable mejorarEquipamiento(Mejorador mejorador) {
        return mejorador.obtenerSeguienteMejora(this);
    }

    @Override
    public void modificarEnergiaConFiera(Energia energia) {
        energia.decrementar( DANIO_POR_FIERA);
    }


}
