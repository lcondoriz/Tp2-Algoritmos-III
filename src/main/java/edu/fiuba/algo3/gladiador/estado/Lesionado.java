package edu.fiuba.algo3.gladiador.estado;

import edu.fiuba.algo3.gladiador.Gladiador;

public class Lesionado implements Estado {
    @Override
    public void accionar(Gladiador gladiador, int cantidadCeldas, int turno) {
        gladiador.cambiarEstado(new Normal());
    }
}