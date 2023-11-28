package edu.fiuba.algo3.casillero.obstaculos;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.juego.Dado;

public class Bacanal implements Obstaculizable {

    private int PUNTOS_POR_TRAGO = 4;
    private Dado dado;

    public Bacanal(Dado dado) {
        this.dado = dado;
    }
    public void aplicarEfecto(Gladiador gladiador) {
       gladiador.decrementarEnergia(this.danioPorBacanal());
    }
    private int danioPorBacanal(){
        return dado.lanzar() * PUNTOS_POR_TRAGO;
    }
}
