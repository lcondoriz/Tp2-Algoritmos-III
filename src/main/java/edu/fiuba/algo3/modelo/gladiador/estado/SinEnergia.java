package edu.fiuba.algo3.modelo.gladiador.estado;

import edu.fiuba.algo3.modelo.gladiador.Gladiador;

public class SinEnergia implements Estado {
    private static final int ENERGIA_MINIMA = 0;
    private static final int ENERGIA_INCREMENTO = 5;
    @Override
    public void accionar(Gladiador gladiador, int cantidadCeldas, int turno) {
        gladiador.incrementarEnergia(ENERGIA_INCREMENTO);
        if (gladiador.obtenerEnergia() > ENERGIA_MINIMA ){
                gladiador.cambiarEstado(new Normal());
        }
    }
}
