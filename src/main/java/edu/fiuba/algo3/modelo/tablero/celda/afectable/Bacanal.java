package edu.fiuba.algo3.modelo.tablero.celda.afectable;

import edu.fiuba.algo3.modelo.gladiador.Gladiador;
import edu.fiuba.algo3.modelo.juego.Dado;
import edu.fiuba.algo3.modelo.log.Logeador;

public class Bacanal implements Afectable {
    private static final int ENERGIA_PERDIDA_POR_TRAGO = 4;
    private static final String MENSAJE_BACANAL = "El gladiador se encuentra con un Bacanal y decide tomar vino.";
    private Dado dado;
    public Bacanal(Dado dado) {
        this.dado = dado;
    }
    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        Logeador.agregarALog(gladiador.getLog(),MENSAJE_BACANAL);
        int perdidaPorVino = calcularEnegiaPerdidaPorVino(); //boton para tirar dados(?
        gladiador.decrementarEnergia(perdidaPorVino);
    }
    private int calcularEnegiaPerdidaPorVino() {
        return dado.lanzar() * ENERGIA_PERDIDA_POR_TRAGO;
    }
}
