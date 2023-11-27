package edu.fiuba.algo3.modeloTest.juego;

import edu.fiuba.algo3.casillero.vacio.Vacio;
import edu.fiuba.algo3.exceptions.CantidadJugadoresException;
import edu.fiuba.algo3.exceptions.NoHayJugadoresException;
import edu.fiuba.algo3.exceptions.SinEnergiaException;
import edu.fiuba.algo3.juego.AlgoRoma;
import edu.fiuba.algo3.juego.Dado;
import edu.fiuba.algo3.juego.Jugador;
import edu.fiuba.algo3.tablero.Casillero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlgoRomaTest {

    @Test
    public void test01NoHayJugadoresException() {
        // Arrange
        Dado dado = new Dado(6);
        AlgoRoma algoRoma = new AlgoRoma(dado);

        Throwable exception = Assertions.assertThrows(NoHayJugadoresException.class, () -> {
            algoRoma.iniciarJuego();
        });

        assertEquals("No hay jugadores para iniciar el juego.", exception.getMessage());
    }
    @Test
    public void test02CantidadJugadoresInferior() {
        // Arrange
        Dado dado = new Dado(6);
        AlgoRoma algoRoma = new AlgoRoma(dado);

        /*
        Casillero casillero = new Casillero(0, new Vacio());            <----Logica de AlgoRoma
        Jugador jugador1 = new Jugador("Pepe", casillero);
        */
        algoRoma.agregarJugador("Pepe");

        Throwable exception = Assertions.assertThrows(CantidadJugadoresException.class, () -> {
            algoRoma.iniciarJuego();
        });

        assertEquals("La cantidad de jugadores debe ser entre 2 y 6.", exception.getMessage());
    }
}
