package edu.fiuba.algo3.tablero.celda.afectable;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.log.Log;
import edu.fiuba.algo3.log.Logeador;

import java.io.IOException;

public class FieraSalvaje implements Afectable {
    private static final String MENSAJE_FIERA = "El gladiador se encuentra con una Fiera Salvaje y entran en combate.";
    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        Logeador.agregarALog(gladiador.getLog(),MENSAJE_FIERA);
        gladiador.danioPorFieraSalvaje();
    }
}
