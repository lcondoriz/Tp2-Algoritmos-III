package edu.fiuba.algo3.gladiador.equipamiento;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.gladiador.mejorador.Mejorador;
import edu.fiuba.algo3.tablero.celda.afectable.FieraSalvaje;

public class Armadura implements Equipable {

    @Override
    public Equipable mejorarEquipamiento(Mejorador mejorador) {
        return mejorador.obtenerSeguienteMejora(this);
    }

    @Override
    public void luchaFieraSalvaje(FieraSalvaje fiera, Gladiador gladiador) {
        fiera.afectarConEquipamiento(this, gladiador);
    }
    @Override
    public String toString() {
        return "Armadura";
    }

}
