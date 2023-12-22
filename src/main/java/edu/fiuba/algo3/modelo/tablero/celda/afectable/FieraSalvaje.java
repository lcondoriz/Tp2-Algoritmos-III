package edu.fiuba.algo3.modelo.tablero.celda.afectable;

import edu.fiuba.algo3.modelo.gladiador.Gladiador;
import edu.fiuba.algo3.modelo.log.Logeador;

public class FieraSalvaje implements Afectable {
    private static final String MENSAJE_FIERA = "El gladiador se encuentra con una Fiera Salvaje y entran en combate.";
    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        Logeador.agregarALog(gladiador.getLog(),MENSAJE_FIERA);
        gladiador.danioPorFieraSalvaje();
    }
}
