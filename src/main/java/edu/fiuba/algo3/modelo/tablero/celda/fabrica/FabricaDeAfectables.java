package edu.fiuba.algo3.modelo.tablero.celda.fabrica;

import edu.fiuba.algo3.modelo.tablero.celda.afectable.Afectable;

public interface FabricaDeAfectables {
    Afectable crearAfectable(String tipo);
}
