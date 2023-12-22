package edu.fiuba.algo3.modelo.tablero.celda;

import edu.fiuba.algo3.modelo.gladiador.Gladiador;
import edu.fiuba.algo3.modelo.tablero.Coordenadas;

public class Llegada extends Celda  {
    public Llegada(Coordenadas coordenadas, String tipo, int numeracion) {
        super(coordenadas, tipo, numeracion);
    }
    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        gladiador.verificarSiEsGanador();
    }
}
