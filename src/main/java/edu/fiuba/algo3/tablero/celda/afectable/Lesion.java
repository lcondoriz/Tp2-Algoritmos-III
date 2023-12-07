package edu.fiuba.algo3.tablero.celda.afectable;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.gladiador.estado.Lesionado;
import edu.fiuba.algo3.log.Log;
import edu.fiuba.algo3.log.Logeador;

import java.io.IOException;

public class Lesion implements Afectable {
    private static final int TURNOS_ESPERA = 1;
    private static final String MENSAJE_LESION = "El gladiador se lesiona.";
    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        Logeador.agregarALog(gladiador.getLog(),MENSAJE_LESION);
        Logeador.agregarALog(gladiador.getLog(),"Pierde "+Integer.valueOf(TURNOS_ESPERA).toString()+ " turno/s.");
        gladiador.cambiarEstado(new Lesionado());
    }

}
