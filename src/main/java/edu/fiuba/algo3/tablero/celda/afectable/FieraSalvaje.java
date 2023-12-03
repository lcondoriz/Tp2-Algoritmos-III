package edu.fiuba.algo3.tablero.celda.afectable;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.log.Log;

import java.io.IOException;

public class FieraSalvaje implements Afectable {
    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        Log log = gladiador.getLog();
        if (log != null) {
            try {
                log.addLine("El gladiador se encuentra con una Fiera Salvaje y entran en combate.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        gladiador.danioPorFieraSalvaje();
    }
}
