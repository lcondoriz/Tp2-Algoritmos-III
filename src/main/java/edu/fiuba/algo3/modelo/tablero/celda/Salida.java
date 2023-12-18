package edu.fiuba.algo3.modelo.tablero.celda;

import edu.fiuba.algo3.modelo.gladiador.Gladiador;
import edu.fiuba.algo3.modelo.tablero.Coordenadas;

public class Salida extends Celda {
    public Salida(Coordenadas coordenadas, String tipo, int numeracion) {
        super(coordenadas, tipo, numeracion);
    }
    @Override
    public void aplicarEfecto(Gladiador gladiador) {
    }
}
