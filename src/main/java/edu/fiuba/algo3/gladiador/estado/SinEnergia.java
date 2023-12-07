package edu.fiuba.algo3.gladiador.estado;

import edu.fiuba.algo3.gladiador.Gladiador;

public class SinEnergia implements Estado {
    private static final int ENERGIA_MINIMA = 0;
    @Override
    public void accionar(Gladiador gladiador, int cantidadCeldas, int turno) {
        gladiador.incrementarEnergia(5);
        if (gladiador.obtenerEnergia() > ENERGIA_MINIMA ){
                gladiador.cambiarEstado(new Normal());
        }
    }
}
