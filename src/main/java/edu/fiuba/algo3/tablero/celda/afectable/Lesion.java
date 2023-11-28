package edu.fiuba.algo3.tablero.celda.afectable;

import edu.fiuba.algo3.gladiador.Gladiador;

public class Lesion implements Afectable {
    private static final int TURNOS_ESPERA = 1;

    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        gladiador.serLesionado(TURNOS_ESPERA);
    }
}
