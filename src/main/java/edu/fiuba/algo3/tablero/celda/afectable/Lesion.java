package edu.fiuba.algo3.tablero.celda.afectable;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.gladiador.estado.Lesionado;
import edu.fiuba.algo3.log.Log;

import java.io.IOException;

public class Lesion implements Afectable {
    private static final int TURNOS_ESPERA = 1;

    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        Log log = gladiador.getLog();
        if (log != null) {
            try {
                log.addLine("El gladiador se lesiona.");
                log.addLine("Pierde "+Integer.valueOf(TURNOS_ESPERA).toString()+ " turno/s.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        gladiador.cambiarEstado(new Lesionado());
    }
}
