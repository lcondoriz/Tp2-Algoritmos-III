package edu.fiuba.algo3.modelo.tablero.celda.afectable;

import edu.fiuba.algo3.modelo.gladiador.Gladiador;
import edu.fiuba.algo3.modelo.log.Logeador;

public class Equipo implements Afectable {
    private static final String MENSAJE_EQUIPO = "El gladiador se encuentra una mejora de Equipamiento.";
    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        Logeador.agregarALog(gladiador.getLog(),MENSAJE_EQUIPO);
        gladiador.mejorarEquipamiento();
    }
}
