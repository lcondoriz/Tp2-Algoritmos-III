package edu.fiuba.algo3.tablero.celda;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.tablero.Coordenadas;
import edu.fiuba.algo3.tablero.celda.afectable.Afectable;

import java.util.List;

public class Salida extends Celda {
    public Salida(Coordenadas coordenadas, String tipo, int numeracion) {
        super(coordenadas, tipo, numeracion);
    }

    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        //gladiador.ganar();
    }
}
