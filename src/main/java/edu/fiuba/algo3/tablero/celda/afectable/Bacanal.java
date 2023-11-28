package edu.fiuba.algo3.tablero.celda.afectable;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.juego.Dado;

public class Bacanal implements Afectable {
    private static final int ENERGIA_PERDIDA_POR_VINO = 4;
    private Dado dado;

    public Bacanal(Dado dado) {
        this.dado = dado;
    }

    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        gladiador.decrementarEnergia(calcularEnegiaPerdidaPorVino());
    }
    private int calcularEnegiaPerdidaPorVino() {
        return dado.lanzar() * ENERGIA_PERDIDA_POR_VINO;
    }
}
