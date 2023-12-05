package edu.fiuba.algo3.entrega_3;

import com.google.gson.JsonObject;
import edu.fiuba.algo3.exceptions.CantidadTurnosException;
import edu.fiuba.algo3.exceptions.PartidaFinalizada;
import edu.fiuba.algo3.json.TableroConstructor;
import edu.fiuba.algo3.juego.AlgoRoma;
import edu.fiuba.algo3.juego.Dado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
public class CasosDeUso3 {
    @Test   // Caso de uso 19
    public void test19simularYVerificarQueHayUnGanador() {
        // Arrange
        Dado dadoMock = mock(Dado.class);
        when(dadoMock.lanzar()).thenReturn(2);
        AlgoRoma algoRoma = new AlgoRoma(dadoMock);

        algoRoma.cargarTablero("files/mapa.json");

        algoRoma.agregarJugador("pepe");
        algoRoma.agregarJugador("juan");
        algoRoma.agregarJugador("luis");
        algoRoma.agregarJugador("lucas");
        algoRoma.agregarJugador("alan");
        algoRoma.agregarJugador("nacho");

        algoRoma.inicializarJuego();

        try {
            algoRoma.jugar();
        } catch (PartidaFinalizada e) {
            String[] logLines = new String[0];
            try {
                logLines = algoRoma.getLog().getLines();;
            } catch (IOException er) {
                throw new RuntimeException(er);
            }
            assert(logLines[logLines.length - 1].contains("GANADOR DE LA PARTIDA"));
        }
    }
    @Test   // Caso de uso 20
    public void text20simularYVerificarQueNadieGane() {
        // Arrange
        Dado dadoMock = mock(Dado.class);
        when(dadoMock.lanzar()).thenReturn(0);
        AlgoRoma algoRoma = new AlgoRoma(dadoMock);

        algoRoma.cargarTablero("files/mapa.json");

        algoRoma.agregarJugador("pepe");
        algoRoma.agregarJugador("juan");
        algoRoma.agregarJugador("luis");
        algoRoma.agregarJugador("lucas");
        algoRoma.agregarJugador("alan");
        algoRoma.agregarJugador("nacho");

        algoRoma.inicializarJuego();

        try {
            algoRoma.jugar();
        } catch (CantidadTurnosException e) {
            String[] logLines = new String[0];
            try {
                logLines = algoRoma.getLog().getLines();;
            } catch (IOException er) {
                throw new RuntimeException(er);
            }
            assert(logLines[logLines.length - 1].contains("No hay ganador ya que superaron la maxima cantidad de rondas"));
        }
    }

}
