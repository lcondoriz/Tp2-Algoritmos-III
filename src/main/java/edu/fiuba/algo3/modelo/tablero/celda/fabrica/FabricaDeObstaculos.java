package edu.fiuba.algo3.modelo.tablero.celda.fabrica;

import edu.fiuba.algo3.modelo.juego.Dado;
import edu.fiuba.algo3.modelo.tablero.celda.afectable.Afectable;
import edu.fiuba.algo3.modelo.tablero.celda.afectable.Bacanal;
import edu.fiuba.algo3.modelo.tablero.celda.afectable.FieraSalvaje;
import edu.fiuba.algo3.modelo.tablero.celda.afectable.Lesion;

public class FabricaDeObstaculos implements FabricaDeAfectables {
    @Override
    public Afectable crearAfectable(String tipo) {
        switch (tipo) {
            case "Bacanal":
                return new Bacanal(new Dado());
            case "Fiera":
                return new FieraSalvaje();
            case "Lesion":
                return new Lesion();
            default:
                return null;
        }
    }
}
