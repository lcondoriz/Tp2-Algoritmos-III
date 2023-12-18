package edu.fiuba.algo3.tablero.celda.fabrica;

import edu.fiuba.algo3.tablero.celda.afectable.Afectable;

public interface FabricaDeAfectables {
    Afectable crearAfectable(String tipo);
}
