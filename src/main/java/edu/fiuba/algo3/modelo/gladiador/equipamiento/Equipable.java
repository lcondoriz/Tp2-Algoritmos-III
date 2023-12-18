package edu.fiuba.algo3.modelo.gladiador.equipamiento;

import edu.fiuba.algo3.modelo.gladiador.Gladiador;
import edu.fiuba.algo3.modelo.gladiador.mejorador.Mejorador;

public interface Equipable {
    Equipable mejorarEquipamiento(Mejorador mejorador);
    void danioPorFieraSalvaje(Gladiador gladiador);
}
