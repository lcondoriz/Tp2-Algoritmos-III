package edu.fiuba.algo3.tablero.celda;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.gladiador.equipamiento.Llave;
import edu.fiuba.algo3.tablero.celda.afectable.Afectable;

import java.util.List;

public class Llegada extends Celda  {
    public Llegada(int x, int y, String tipo, int numeracion) {
        super(x, y, tipo, numeracion);
    }

    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        // a. Si el jugador que llegó a Pompeya (clase Llegada) tiene la Llave es ganador.
        if (gladiador.obtenerEquipamiento() instanceof Llave) {
            // Gano, lanzar una excepción o algo.
            return;
        }

        // b. Sino, debe retroceder a la mitad del tablero.
        gladiador.retrocederMitadCamino();

    }
}
