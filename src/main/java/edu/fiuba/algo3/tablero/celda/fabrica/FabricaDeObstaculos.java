package edu.fiuba.algo3.tablero.celda.fabrica;

import edu.fiuba.algo3.juego.Dado;
import edu.fiuba.algo3.tablero.celda.afectable.Afectable;
import edu.fiuba.algo3.tablero.celda.afectable.Bacanal;
import edu.fiuba.algo3.tablero.celda.afectable.FieraSalvaje;
import edu.fiuba.algo3.tablero.celda.afectable.Lesion;

public class FabricaDeObstaculos implements FabricaDeAfectables {
    private static final int CARAS_DADO = 6;
    @Override
    public Afectable crearAfectable(String tipo) {
        switch (tipo) {
            case "Bacanal":
                return new Bacanal(new Dado(CARAS_DADO));
            case "Fiera":
                return new FieraSalvaje();
            case "Lesion":
                return new Lesion();
            default:
                return null;
        }
    }
}
