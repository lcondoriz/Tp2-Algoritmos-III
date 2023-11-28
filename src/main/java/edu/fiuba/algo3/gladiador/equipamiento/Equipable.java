package edu.fiuba.algo3.gladiador.equipamiento;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.gladiador.mejorador.Mejorador;
import edu.fiuba.algo3.tablero.celda.afectable.FieraSalvaje;

public interface Equipable {
    Equipable mejorarEquipamiento(Mejorador mejorador);

    // Se usa el patrón Visitor
    void luchaFieraSalvaje(FieraSalvaje fiera, Gladiador gladiador);
}
