package edu.fiuba.algo3.modelo.gladiador.estado;

import edu.fiuba.algo3.modelo.gladiador.Gladiador;

public interface Estado {
    void accionar(Gladiador gladiador, int cantidadCeldas, int turno);
}
