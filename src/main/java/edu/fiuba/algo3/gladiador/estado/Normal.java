package edu.fiuba.algo3.gladiador.estado;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.log.Logeador;

public class Normal implements Estado {
    @Override
    public void accionar(Gladiador gladiador, int cantidadCeldas, int turno) {
        Logeador.agregarALog(gladiador.getLog(), "El gladiador tira dados y avanza "+ Integer.valueOf(cantidadCeldas).toString() +" casilleros.");
        gladiador.mover(cantidadCeldas, turno);
    }
}