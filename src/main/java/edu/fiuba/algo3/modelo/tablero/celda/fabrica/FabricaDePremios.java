package edu.fiuba.algo3.tablero.celda.fabrica;

import edu.fiuba.algo3.tablero.celda.afectable.Afectable;
import edu.fiuba.algo3.tablero.celda.afectable.Comida;
import edu.fiuba.algo3.tablero.celda.afectable.Equipo;

public class FabricaDePremios implements FabricaDeAfectables {
    @Override
    public Afectable crearAfectable(String tipo) {
        switch (tipo) {
            case "Comida":
                return new Comida();
            case "Equipamiento":
                return new Equipo() ;
            default:
                return null;
        }
    }
}
