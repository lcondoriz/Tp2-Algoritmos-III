package edu.fiuba.algo3.modeloTest;


import edu.fiuba.algo3.modelo.exceptions.CantidadJugadoresException;
import edu.fiuba.algo3.modelo.exceptions.NoHayJugadoresException;
import edu.fiuba.algo3.modelo.juego.AlgoRoma;
import edu.fiuba.algo3.modelo.juego.Dado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JuegoTest {
    private Dado dado;
    private AlgoRoma algoRoma;

    @BeforeEach
    public void setUp() {
        dado = mock(Dado.class);
        algoRoma = new AlgoRoma(dado);
    }

    @Test
    public void testInicializarJuegoSinJugadores() {
        // Assert
        assertThrows(NoHayJugadoresException.class, () -> algoRoma.inicializarJuego());
    }
    @Test
    public void testValidarMensajeInicializarJuegoSinJugadores() {
        // Act and Assert
        NoHayJugadoresException exception = assertThrows(NoHayJugadoresException.class, () -> algoRoma.inicializarJuego());
        assertEquals("No hay jugadores para iniciar el juego.", exception.getMessage());
    }
    @Test
    public void testInicializarJuegoConUnJugador() {
        // Arrange
        algoRoma.cargarTablero("files/mapa.json");
        algoRoma.agregarJugador("pepe");
        // Act and Assert
        CantidadJugadoresException exception = assertThrows(CantidadJugadoresException.class, () -> algoRoma.inicializarJuego());
        assertEquals("La cantidad de jugadores debe ser entre 2 y 6.", exception.getMessage());
    }

    @Test
    public void testJugar1Turno() {
        // Arrange
        algoRoma.cargarTablero("files/mapa.json");

        algoRoma.agregarJugador("Jugador 1");
        algoRoma.agregarJugador("Jugador 2");
        algoRoma.inicializarJuego();

        // Act & Assert
        assertDoesNotThrow(algoRoma::jugar1Turno);
    }
    @Test
    public void testJugarUnaRonda() {
        // Arrange
        algoRoma.cargarTablero("files/mapa.json");

        algoRoma.agregarJugador("Jugador 1");
        algoRoma.agregarJugador("Jugador 2");
        algoRoma.inicializarJuego();

        // Act & Assert
        assertDoesNotThrow(algoRoma::jugar1Ronda);
    }
}
