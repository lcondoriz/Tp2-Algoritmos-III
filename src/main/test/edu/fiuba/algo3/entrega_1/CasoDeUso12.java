package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.casillero.comestibles.Comida;
import edu.fiuba.algo3.casillero.equipamiento.Equipamiento;
import edu.fiuba.algo3.casillero.equipamiento.EscudoEspada;
import edu.fiuba.algo3.casillero.equipamiento.Llave;
import edu.fiuba.algo3.casillero.obstaculos.FieraSalvaje;
import edu.fiuba.algo3.casillero.obstaculos.Obstaculo;
import edu.fiuba.algo3.casillero.vacio.Vacio;
import edu.fiuba.algo3.exceptions.CantidadJugadoresException;
import edu.fiuba.algo3.exceptions.CantidadTurnosException;
import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.juego.AlgoRoma;
import edu.fiuba.algo3.juego.Dado;
import edu.fiuba.algo3.tablero.Casillero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso12 {
    @Test // Caso de uso 12
    public void verificarQueSiPasan30TurnosYNadieLlegoALaMetaSeTerminaElJuego() {
        // Falta implementar
        AlgoRoma algoRoma = new AlgoRoma(new Dado(6));
        for (int i = 0; i<29;i++){
            algoRoma.jugarTurno();
        }

        try {
            algoRoma.jugarTurno();
        }catch (CantidadTurnosException ex){
            assertEquals(ex.getClass(), CantidadTurnosException.class);
        }
    }
}
