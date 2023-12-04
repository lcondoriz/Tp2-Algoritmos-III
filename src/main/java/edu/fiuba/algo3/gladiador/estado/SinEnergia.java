package edu.fiuba.algo3.gladiador.estado;

import edu.fiuba.algo3.gladiador.Gladiador;

public class SinEnergia implements Estado {
    @Override
    public void accionar(Gladiador gladiador, int cantidadCeldas, int turno) {
        gladiador.incrementarEnergia(5);
        gladiador.cambiarEstado(new Normal());
    }
}
