package edu.fiuba.algo3.tablero.celda;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.gladiador.equipamiento.Llave;
import edu.fiuba.algo3.tablero.celda.afectable.Afectable;
import edu.fiuba.algo3.tablero.Coordenadas;

import java.util.List;

public class Llegada extends Celda  {
    public Llegada(Coordenadas coordenadas, String tipo, int numeracion) {
        super(coordenadas, tipo, numeracion);
    }

    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        gladiador.verificarSiEsGanador();

    }
}
