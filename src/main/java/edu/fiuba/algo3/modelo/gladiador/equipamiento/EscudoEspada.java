package edu.fiuba.algo3.modelo.gladiador.equipamiento;

import edu.fiuba.algo3.modelo.gladiador.Gladiador;
import edu.fiuba.algo3.modelo.gladiador.mejorador.Mejorador;

public class EscudoEspada implements Equipable {
    private static final int DANO_FIERA_ESCUDO_ESPADA = 2;
    @Override
    public Equipable mejorarEquipamiento(Mejorador mejorador) {
        return mejorador.obtenerSeguienteMejora(this);
    }
    @Override
    public void danioPorFieraSalvaje(Gladiador gladiador) {
        gladiador.decrementarEnergia(DANO_FIERA_ESCUDO_ESPADA);
    }
    @Override
    public String toString() {
        return "Escudo y Espada";
    }
}
