package edu.fiuba.algo3.tablero.celda.afectable;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.log.Log;
import edu.fiuba.algo3.log.Logeador;

import java.io.IOException;

public class Equipo implements Afectable {
    private static final String MENSAJE_EQUIPO = "El gladiador se encuentra una mejora de Equipamiento.";
    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        Logeador.agregarALog(gladiador.getLog(),MENSAJE_EQUIPO);
        gladiador.mejorarEquipamiento();
    }
}
