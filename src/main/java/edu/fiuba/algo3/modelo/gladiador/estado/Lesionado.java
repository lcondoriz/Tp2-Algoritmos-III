package edu.fiuba.algo3.modelo.gladiador.estado;

import edu.fiuba.algo3.modelo.gladiador.Gladiador;
import edu.fiuba.algo3.modelo.log.Logeador;

public class Lesionado implements Estado {
    @Override
    public void accionar(Gladiador gladiador, int cantidadCeldas, int turno) {
        Logeador.agregarALog(gladiador.getLog(),"El gladiador perdió el turno. Se recupera el próximo.");
        gladiador.cambiarEstado(new Normal());
    }
}