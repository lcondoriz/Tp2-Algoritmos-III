package edu.fiuba.algo3.casillero.obstaculos;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.juego.Dado;

public class Bacanal implements Obstaculizable {

    private Dado dado;

    public Bacanal(Dado dado) {
        this.dado = dado;
    }
    public void aplicarEfecto(Gladiador gladiador) {
       gladiador.decrementarEnergia(dado.lanzar() * 4);
    }
}
