package edu.fiuba.algo3.tablero.celda.afectable;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.log.Log;

import java.io.IOException;

public class Comida implements Afectable {
    private static final int ENERGIA_POR_COMIDA = 15;
    @Override
    public void aplicarEfecto(Gladiador gladiador) {

        Log log = gladiador.getLog();
        if (log != null) {
            try {
                log.addLine("El gladiador encuentra comida.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        gladiador.incrementarEnergia(ENERGIA_POR_COMIDA);
    }
}
