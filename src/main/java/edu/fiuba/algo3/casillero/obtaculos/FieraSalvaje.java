package edu.fiuba.algo3.casillero.obtaculos;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.casillero.Afectable;

public class FieraSalvaje implements Afectable {
    private int penalizacion;

    public FieraSalvaje(int penalizacion) {
        this.penalizacion = penalizacion;
    }

    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        gladiador.disminuirEnergia(penalizacion);
    }
}