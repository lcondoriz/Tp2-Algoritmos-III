package edu.fiuba.algo3.gladiador.estado;

import edu.fiuba.algo3.gladiador.Gladiador;

public interface Estado {
    void accionar(Gladiador gladiador, int cantidadCeldas, int turno);
}
