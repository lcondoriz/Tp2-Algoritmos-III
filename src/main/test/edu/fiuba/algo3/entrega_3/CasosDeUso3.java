package edu.fiuba.algo3.entrega_3;

import com.google.gson.JsonObject;
import edu.fiuba.algo3.json.TableroConstructor;
import edu.fiuba.algo3.juego.AlgoRoma;
import edu.fiuba.algo3.juego.Dado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CasosDeUso3 {
    @Test   // Caso de uso 15
    public void test15verificarQueUnJugadorGanaLaPartida() {
        // Arrange
        Dado dadoMock = mock(Dado.class);
        when(dadoMock.lanzar()).thenReturn(2);

        AlgoRoma algoRoma = new AlgoRoma(dadoMock);
        algoRoma.agregarJugador("Pepe");
        algoRoma.agregarJugador("Jorge");
        algoRoma.inicializarJuego();


        // Act
        algoRoma.jugar();
        // Assert

        //agregar la linea donde deberia decir que jugador gana.
        assert (lineas[].contains("El jugador Pepe se consagra ganador del juego"));
    }

    @Test   // Caso de uso 16
    public void test16verificarQueUnJugadorGanaLaPartida() {
        // Arrange
        Dado dadoMock = mock(Dado.class);
        when(dadoMock.lanzar()).thenReturn(0);

        AlgoRoma algoRoma = new AlgoRoma(dadoMock);
        algoRoma.agregarJugador("Pepe");
        algoRoma.agregarJugador("Jorge");
        algoRoma.inicializarJuego();

        // Act
        algoRoma.jugar();

        // Assert
        //agregar la linea donde deberia decir que jugador gana.
        assert (lineas[].contains("Ningun jugador ha logrado alzarse con la gloria. No hay ganadores."));

    }

}

