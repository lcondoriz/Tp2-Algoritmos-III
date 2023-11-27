package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.casillero.comestibles.Comida;
import edu.fiuba.algo3.casillero.equipamiento.Equipamiento;
import edu.fiuba.algo3.casillero.equipamiento.EscudoEspada;
import edu.fiuba.algo3.casillero.obstaculos.FieraSalvaje;
import edu.fiuba.algo3.casillero.obstaculos.Obstaculo;
import edu.fiuba.algo3.casillero.vacio.Final;
import edu.fiuba.algo3.casillero.vacio.Vacio;
import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.juego.AlgoRoma;
import edu.fiuba.algo3.juego.Dado;
import edu.fiuba.algo3.tablero.Casillero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasoDeUso9 {
    @Test // Caso de uso 9
    public void verificarQueSiLlegaALaMetaSinLaLlaveEnElEquipamientoRetrocedeALaMitadDeLasCasillas() {
        // Falta implementar.
        //Dado dado = new Dado(6);
        //AlgoRoma algoRoma = new AlgoRoma(dado);//quiza iria el JSON

        Casillero casilleroInicial = new Casillero(0, new Vacio());
        Casillero casilleroMitad = new Casillero(1, new Vacio());
        Casillero casilleroFinal = new Casillero(2, new Final(3));

        Gladiador gladiador = new Gladiador(new Energia(20), casilleroInicial);

       /* Tablero tablero = new Tablero({"Bacabal","Vacio","Equipamiento"
                casilleroInicial,casilleroMitad,casilleroFinal
        });
        */
        gladiador.avanzar(2,1);

        gladiador.setearCasillero(casilleroFinal);
        casilleroFinal.aplicarEfecto(gladiador);

        assertEquals(gladiador.obtenerPosicionCasillero(), casilleroMitad.obtenerPosicion());

    }
}
