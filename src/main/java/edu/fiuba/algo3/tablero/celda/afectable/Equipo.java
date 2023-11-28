package edu.fiuba.algo3.tablero.celda.afectable;

import edu.fiuba.algo3.gladiador.Gladiador;

public class Equipo implements Afectable {
    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        gladiador.mejorarEquipamiento();
    }
}