package edu.fiuba.algo3.tablero.celda.afectable;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.juego.Dado;
import edu.fiuba.algo3.log.Log;

import java.io.IOException;

public class Bacanal implements Afectable {
    private static final int ENERGIA_PERDIDA_POR_VINO = 4;
    private Dado dado;

    public Bacanal(Dado dado) {
        this.dado = dado;
    }

    @Override
    public void aplicarEfecto(Gladiador gladiador) {

        Log log = gladiador.getLog();
        try {
            log.addLine("El gladiador se encuentra con un Bacanal y decide tomar vino.");
            int perdidaPorVino = calcularEnegiaPerdidaPorVino(); //boton para tirar dados(?
            gladiador.decrementarEnergia(perdidaPorVino);
            ;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private int calcularEnegiaPerdidaPorVino() {
        return dado.lanzar() * ENERGIA_PERDIDA_POR_VINO;
    }
}
