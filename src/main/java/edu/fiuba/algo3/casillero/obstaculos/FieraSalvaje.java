package edu.fiuba.algo3.casillero.obstaculos;

import edu.fiuba.algo3.gladiador.Gladiador;

public class FieraSalvaje implements Obstaculizable {

    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        gladiador.decrementarEnergia(gladiador.obtenerEquipamiento().danoRecibidoPorFieraSalvaje());
    }
}
