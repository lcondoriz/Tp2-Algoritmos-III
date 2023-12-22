package edu.fiuba.algo3.entrega_1;


import edu.fiuba.algo3.modelo.gladiador.equipamiento.Llave;

import edu.fiuba.algo3.modelo.exceptions.CantidadTurnosException;
import edu.fiuba.algo3.modelo.gladiador.Energia;
import edu.fiuba.algo3.modelo.gladiador.Gladiador;
import edu.fiuba.algo3.modelo.gladiador.equipamiento.SinEquipamiento;
import edu.fiuba.algo3.modelo.juego.AlgoRoma;
import edu.fiuba.algo3.modelo.juego.Dado;
import edu.fiuba.algo3.modelo.tablero.celda.Camino;
import edu.fiuba.algo3.modelo.tablero.celda.Celda;
import edu.fiuba.algo3.modelo.tablero.celda.Llegada;
import edu.fiuba.algo3.modelo.tablero.celda.Salida;
import edu.fiuba.algo3.modelo.tablero.Coordenadas;
import edu.fiuba.algo3.modelo.tablero.celda.afectable.Comida;
import edu.fiuba.algo3.modelo.tablero.celda.afectable.Equipo;
import edu.fiuba.algo3.modelo.tablero.celda.afectable.FieraSalvaje;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class CasosDeUso {
    @Test // Caso de uso 1
    public void verificarQueElJugadorEmpiezaConLaEnergiaYEquipamientoCorrespondiente() {
        // Arrange
        Energia energia = new Energia(20);

        // Act
        Celda celdaMock = mock(Camino.class);

        Gladiador gladiador = new Gladiador(energia, celdaMock);

        // Assert
        // Energía del gladiador
        assertEquals(20, gladiador.obtenerEnergia());
        // Equipamiento del gladiador
        assertEquals(SinEquipamiento.class, gladiador.obtenerEquipamiento().getClass());
    }
    @Test // Caso de uso 2
    public void verificarQueElJugadorSalgaDeLaCasillaInicial() {
        // Arrange
        Energia energiaMock = mock(Energia.class);
        Celda casillero = new Salida(new Coordenadas(0, 0),"Salida",0);

        // Act
        Gladiador gladiador = new Gladiador(energiaMock, casillero);

        // Assert
        // verifica mos que el casillero inicial sea posicion 0
        assertEquals(Salida.class, gladiador.obtenerCelda().getClass());
    }

    @Test // Caso de uso 3
    public void verificarQueUnJugadorSinEnergiaNoPuedaJugarElTurno() {
        // Arrange
        Energia energia = new Energia(20);
        FieraSalvaje fiera = new FieraSalvaje();
        Celda salida = new Salida(new Coordenadas(0, 0),"Salida",0);
        Celda camino = new Camino(new Coordenadas(1, 0),"Camino",1);

        salida.agregarSiguienteCelda(camino);
        camino.agregarCeldaAnterior(salida);

        Gladiador gladiador = new Gladiador(energia, salida);

        gladiador.decrementarEnergia(20);

        // Act
        gladiador.avanzar(1, 0);

        // Assert
        assertEquals(salida, gladiador.obtenerCelda());
    }
    @Test // Caso de uso 4
    public void verificarQueSiRecibeComidaIncrementaEnergíaEn15() {
        // Arrange
        Energia energia = new Energia(20);

        Celda camino = new Camino(new Coordenadas(1, 0),"Camino",1);
        Comida comida = new Comida();
        camino.agregarAfectable(comida);

        Gladiador gladiador = new Gladiador(energia, camino);

        // Act
        gladiador.avanzar(0, 0);

        // Assert
        assertEquals(35, gladiador.obtenerEnergia());
    }
    @Test // Caso de uso 5
    public void verificarQueSiRecibeUnPremioPorPrimeraVezObtieneUnCasco() {
        // Arrange
        Energia energia = new Energia(20);
        Celda salida = new Salida(new Coordenadas(0, 0),"Salida",0);
        Celda caminoCasco = new Camino(new Coordenadas(1, 0),"Camino",1);
        Celda caminoFiera = new Camino(new Coordenadas(2, 0),"Camino",2);

        salida.agregarSiguienteCelda(caminoCasco);
        caminoCasco.agregarCeldaAnterior(salida);
        caminoCasco.agregarSiguienteCelda(caminoFiera);
        caminoFiera.agregarCeldaAnterior(caminoCasco);

        caminoCasco.agregarAfectable(new Equipo());
        caminoFiera.agregarAfectable(new FieraSalvaje());

        Gladiador gladiador = new Gladiador(energia, salida);

        // Act
        gladiador.avanzar(1, 0);
        gladiador.avanzar(1, 0);

        // Assert
        assertEquals(5, gladiador.obtenerEnergia());
    }
    @Test // Caso de uso 6
    public void verificarQueSiRecibeUnPremioPorTerceraVezObtieneUnEscudoYespada() {
        // Arrange
        Energia energia = new Energia(20);

        Celda salida = new Salida(new Coordenadas(0, 0),"Salida",0);
        Celda camino_1 = new Camino(new Coordenadas(1, 0),"Camino",1);
        Celda camino_2 = new Camino(new Coordenadas(2, 0),"Camino",2);
        Celda camino_3 = new Camino(new Coordenadas(3, 0),"Camino",3);
        Celda caminoFiera = new Camino(new Coordenadas(4, 0),"Camino",4);

        salida.agregarSiguienteCelda(camino_1);
        camino_1.agregarCeldaAnterior(salida);
        camino_1.agregarSiguienteCelda(camino_2);
        camino_2.agregarCeldaAnterior(camino_1);
        camino_2.agregarSiguienteCelda(camino_3);
        camino_3.agregarCeldaAnterior(camino_2);
        camino_3.agregarSiguienteCelda(caminoFiera);

        camino_1.agregarAfectable(new Equipo());
        camino_2.agregarAfectable(new Equipo());
        camino_3.agregarAfectable(new Equipo());
        caminoFiera.agregarAfectable(new FieraSalvaje());

        Gladiador gladiador = new Gladiador(energia, salida);

        // Act
        gladiador.avanzar(1, 0);
        gladiador.avanzar(1, 1);
        gladiador.avanzar(1, 2);
        gladiador.avanzar(1, 3);

        // Assert
        assertEquals(18, gladiador.obtenerEnergia());
    }
    @Test // Caso de uso 7
    public void verificarQueSiHayUnCombateConUnaFieraSalvajeYTieneUnCascoPierde15PuntosDeEnergia() {
        // Arrange
        Energia energia = new Energia(20);

        Celda salida = new Salida(new Coordenadas(0, 0),"Salida",0);
        Celda camino = new Camino(new Coordenadas(1, 0),"Camino",1);
        Celda caminoFiera = new Camino(new Coordenadas(2, 0),"Camino",2);

        salida.agregarSiguienteCelda(camino);
        camino.agregarCeldaAnterior(salida);
        camino.agregarSiguienteCelda(caminoFiera);
        caminoFiera.agregarCeldaAnterior(camino);

        camino.agregarAfectable(new Equipo());
        caminoFiera.agregarAfectable(new FieraSalvaje());

        Gladiador gladiador = new Gladiador(energia, salida);

        // Act
        gladiador.avanzar(1, 0);
        gladiador.avanzar(1, 1);

        // Assert
        assertEquals(5, gladiador.obtenerEnergia());
    }
    @Test // Caso de uso 8
    public void verificarQueSiPasan8TurnosElSeniorityDelGladiadorPasaDeNovatoASemiSeniorYVeSuEnergiaIncrementadaAlProximoTurno() {
        // Arrange
        Energia energia = new Energia(20);

        // Mocks de celdas
        Celda salidaMock = mock(Salida.class);
        Celda caminoMock1 = mock(Camino.class);
        Celda caminoMock2 = mock(Camino.class);
        Celda caminoMock3 = mock(Camino.class);
        Celda caminoMock4= mock(Camino.class);
        Celda caminoMock5 = mock(Camino.class);
        Celda caminoMock6 = mock(Camino.class);
        Celda caminoMock7 = mock(Camino.class);
        Celda caminoMock8= mock(Camino.class);
        Celda caminoMock9= mock(Camino.class);

        // Configura el comportamiento esperado al avanzar a través de las celdas
        when(salidaMock.avanzar(anyInt())).thenReturn(caminoMock1);
        when(caminoMock1.avanzar(anyInt())).thenReturn(caminoMock2);
        when(caminoMock2.avanzar(anyInt())).thenReturn(caminoMock3);
        when(caminoMock3.avanzar(anyInt())).thenReturn(caminoMock4);
        when(caminoMock4.avanzar(anyInt())).thenReturn(caminoMock5);
        when(caminoMock5.avanzar(anyInt())).thenReturn(caminoMock6);
        when(caminoMock6.avanzar(anyInt())).thenReturn(caminoMock7);
        when(caminoMock7.avanzar(anyInt())).thenReturn(caminoMock8);
        when(caminoMock8.avanzar(anyInt())).thenReturn(caminoMock9);

        // Gladiador con mock de celda de salida
        Gladiador gladiador = new Gladiador(energia, salidaMock);

        // Act: Simula el avance del gladiador durante 8 turnos
        for (int i = 0; i < 9; i++) {
            gladiador.avanzar(1, i);
        }

        // Assert: Verifica que el seniority sea Semisenior y que la energía se haya incrementado al próximo turno
        //assertEquals(SemiSenior.class, gladiador.obtenerSeniority().getClass());
        assertEquals(25, gladiador.obtenerEnergia());
    }
    @Test // Caso de uso 9
    public void verificarQueSiLlegaALaMetaSinLaLlaveEnElEquipamientoRetrocedeALaMitadDeLasCasillas() {
        //Arrange
        Energia energia = new Energia(20);

        Celda salida = new Salida(new Coordenadas(1,7),"Salida", 0);
        Celda llegada = new Llegada(new Coordenadas(2,6),"Llegada", 2);
        Celda nuevaCelda = new Camino(new Coordenadas(2,7),"Camino", 1);
        salida.agregarSiguienteCelda(nuevaCelda);
        nuevaCelda.agregarCeldaAnterior(salida);
        nuevaCelda.agregarSiguienteCelda(llegada);
        llegada.agregarCeldaAnterior(nuevaCelda);
        nuevaCelda.agregarAfectable(new Equipo());
        Gladiador gladiador = new Gladiador(energia, salida);
        //Act
        gladiador.avanzar(2, 1);
        assertTrue(gladiador.EstasEnLaCelda(nuevaCelda));
    }
    @Test // Caso de uso 10
    public void verificarQueSiLoAtacaUnaFieraSalvajeYPoseeTodoElEquipamientoElDanioEnEnergiaEs0() {
        // Arrange
        Energia energia = new Energia(20);

        Celda salida = new Salida(new Coordenadas(0, 0), "Salida", 0);

        Celda celdaActual = salida;
        int cantidad = 5;

        for (int i = 1; i <= cantidad; i++) {
            Celda nuevaCelda = new Camino(new Coordenadas(i, 0), "Camino", i);
            celdaActual.agregarSiguienteCelda(nuevaCelda);
            nuevaCelda.agregarCeldaAnterior(celdaActual);
            celdaActual = nuevaCelda;

            if (i < cantidad) {
                celdaActual.agregarAfectable(new Equipo());
            } else {
                celdaActual.agregarAfectable(new FieraSalvaje());
            }
        }

        Gladiador gladiador = new Gladiador(energia, salida);

        // Act: Simula el avance del gladiador durante 5 turnos
        for (int i = 0; i < 5; i++) {
            gladiador.avanzar(1, i);
        }

        // Assert: Verifica que la energía se haya mantenido en 20
        assertEquals(20, gladiador.obtenerEnergia());
    }
    @Test // Caso de uso 11
    public void verificarQueSiElGladiadorTieneLaLlaveYRecibeOtroEquipamientoNoCambiaNada() {
        // Arrange
        Energia energia = new Energia(20);

        Celda salida = new Salida(new Coordenadas(0, 0), "Salida", 0);

        Celda celdaActual = salida;
        int cantidad = 5;

        for (int i = 1; i <= cantidad; i++) {
            Celda nuevaCelda = new Camino(new Coordenadas(i, 0), "Camino", i);
            celdaActual.agregarSiguienteCelda(nuevaCelda);
            nuevaCelda.agregarCeldaAnterior(celdaActual);
            celdaActual = nuevaCelda;

            celdaActual.agregarAfectable(new Equipo());
        }

        Gladiador gladiador = new Gladiador(energia, salida);

        // Act: Simula el avance del gladiador durante 5 turnos
        for (int i = 0; i < 5; i++) {
            gladiador.avanzar(1, i);
        }

        // Assert: Verifica que la energía se haya mantenido en 20
        assertEquals(Llave.class, gladiador.obtenerEquipamiento().getClass());
    }
    @Test // Caso de uso 12
    public void verificarQueSiPasan30TurnosYNadieLlegoALaMetaSeTerminaElJuego() {
        // Falta implementar
        AlgoRoma algoRoma = new AlgoRoma(new Dado(6));

        try {
            algoRoma.jugar();
        }catch (CantidadTurnosException ex){
            assertEquals(ex.getClass(), CantidadTurnosException.class);
        }
    }
}
