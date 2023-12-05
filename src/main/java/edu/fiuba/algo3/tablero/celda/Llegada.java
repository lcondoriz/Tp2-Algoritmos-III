package edu.fiuba.algo3.tablero.celda;

import edu.fiuba.algo3.gladiador.Gladiador;

public class Llegada extends Celda  {
    public Llegada(int x, int y, String tipo, int numeracion) {
        super(x, y, tipo, numeracion);
    }

    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        gladiador.verificarSiEsGanador();

    }
}
