package edu.fiuba.algo3.gladiador.equipamiento;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.gladiador.mejorador.Mejorador;

public interface Equipable {
    Equipable mejorarEquipamiento(Mejorador mejorador);
    void danioPorFieraSalvaje(Gladiador gladiador);
}
