package edu.fiuba.algo3.tablero.celda.afectable;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.log.Log;
import edu.fiuba.algo3.log.Logeador;

import java.io.IOException;

public class Comida implements Afectable {
    private static final int ENERGIA_POR_COMIDA = 15;
    private static final String MENSAJE_COMIDA = "El gladiador encuentra comida.";
    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        Logeador.agregarALog(gladiador.getLog(), MENSAJE_COMIDA);
        gladiador.incrementarEnergia(ENERGIA_POR_COMIDA);
    }
}
