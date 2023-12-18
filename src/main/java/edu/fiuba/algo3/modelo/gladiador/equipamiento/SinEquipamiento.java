package edu.fiuba.algo3.gladiador.equipamiento;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.gladiador.mejorador.Mejorador;

public class SinEquipamiento implements Equipable {
    private static final int DANO_FIERA_SIN_EQUIPAMIENTO = 20;
    @Override
    public Equipable mejorarEquipamiento(Mejorador mejorador) {
        return mejorador.obtenerSeguienteMejora(this);
    }
    @Override
    public void danioPorFieraSalvaje(Gladiador gladiador) {
        gladiador.decrementarEnergia(DANO_FIERA_SIN_EQUIPAMIENTO);
    }
}
