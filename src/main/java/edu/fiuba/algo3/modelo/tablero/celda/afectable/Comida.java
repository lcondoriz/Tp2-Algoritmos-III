package edu.fiuba.algo3.modelo.tablero.celda.afectable;

import edu.fiuba.algo3.modelo.gladiador.Gladiador;
import edu.fiuba.algo3.modelo.log.Logeador;

public class Comida implements Afectable {
    private static final int ENERGIA_POR_COMIDA = 15;
    private static final String MENSAJE_COMIDA = "El gladiador encuentra comida.";
    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        Logeador.agregarALog(gladiador.getLog(), MENSAJE_COMIDA);
        gladiador.incrementarEnergia(ENERGIA_POR_COMIDA);
    }
}
