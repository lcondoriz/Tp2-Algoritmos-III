package edu.fiuba.algo3.modeloTest.log;

import edu.fiuba.algo3.juego.AlgoRoma;
import edu.fiuba.algo3.juego.Dado;
import edu.fiuba.algo3.juego.Jugador;
import edu.fiuba.algo3.log.Log;
import edu.fiuba.algo3.tablero.celda.Camino;
import edu.fiuba.algo3.tablero.celda.Celda;
import edu.fiuba.algo3.tablero.celda.Salida;
import edu.fiuba.algo3.tablero.celda.afectable.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class LogTest {
    private String pepe = "Pepe";
    private String pablo = "Pablo";
    private AlgoRoma algoRoma;

    @BeforeEach
    public void setUp() {


        algoRoma = new AlgoRoma(new Dado(6));
        algoRoma.cargarTablero("files/mapa.json");
        algoRoma.agregarJugador(pepe);
        algoRoma.agregarJugador(pablo);
        algoRoma.inicializarJuego();

    }

    @Test
    public void logRegistraAgregarJugadoresEIniciarElJuego(){

        Log log = algoRoma.getLog();

        String[] lineas = new String[0];
        try {
            lineas = log.getLines();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String linea : lineas){        //esto es para verlo por consola y no tener que abrir el txt, no testea nada en si
            System.out.println(linea);
        }


        assert(lineas[0].contains("Se agregó al jugador: '"+pepe+"'."));
        assert(lineas[1].contains("Se agregó al jugador: '"+pablo+"'."));
        assert(lineas[2].contains("Se inicio el Juego."));

    }
    @Test
    public void logRegistraCuandoLosJugadoresAvanzan() {

        Dado dadoMock = mock(Dado.class);
        when(dadoMock.lanzar()).thenReturn(2);

        Celda salida = new Salida(0, 0,"Salida",0);
        Celda camino_1 = new Camino(1, 0,"Camino",1);
        Celda camino_2 = new Camino(2, 0,"Camino",2);


        salida.agregarSiguienteCelda(camino_1);
        camino_1.agregarCeldaAnterior(salida);
        camino_1.agregarSiguienteCelda(camino_2);
        camino_2.agregarCeldaAnterior(camino_1);


        Log log = null;
        try {
            log = new Log("./src/main/test/edu/fiuba/algo3/modeloTest/log/log.txt");
            log.resetLog();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        Jugador jugador = new Jugador(pepe,salida,log);

        jugador.jugarTurno(dadoMock);


        String[] lineas = new String[0];
        try {
            lineas = log.getLines();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*for (String linea : lineas){
            System.out.println(linea);
        }*/
        assert(lineas[0].contains("'"+pepe+"' tira dados y avanza 2 casilleros."));

    }

    @Test
    public void logRegistraCuandoLosGladiadoresSonAfectadosPorUnBacanal() {

        Dado dadoMock = mock(Dado.class);
        when(dadoMock.lanzar()).thenReturn(2);

        Celda salida = new Salida(0, 0, "Salida", 0);
        Celda camino_1 = new Camino(1, 0, "Camino", 1);
        Celda camino_2 = new Camino(2, 0, "Camino", 2);


        salida.agregarSiguienteCelda(camino_1);
        camino_1.agregarCeldaAnterior(salida);
        camino_1.agregarSiguienteCelda(camino_2);
        camino_2.agregarCeldaAnterior(camino_1);

        camino_2.agregarAfectable(new Bacanal(dadoMock));

        Log log = null;
        try {
            log = new Log("./src/main/test/edu/fiuba/algo3/modeloTest/log/log.txt");
            log.resetLog();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Jugador jugador = new Jugador(pepe, salida, log);
        jugador.jugarTurno(dadoMock);


        String[] lineas = new String[0];
        try {
            lineas = log.getLines();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assert (lineas[1].contains("El gladiador se encuentra con un Bacanal y decide tomar vino."));
        assert (lineas[2].contains("Pierde 8 puntos de Energía."));
    }

    @Test
    public void logRegistraCuandoLosGladiadoresSonAfectadosPorUnaFiera() {

        Dado dadoMock = mock(Dado.class);
        when(dadoMock.lanzar()).thenReturn(2);

        Celda salida = new Salida(0, 0, "Salida", 0);
        Celda camino_1 = new Camino(1, 0, "Camino", 1);
        Celda camino_2 = new Camino(2, 0, "Camino", 2);


        salida.agregarSiguienteCelda(camino_1);
        camino_1.agregarCeldaAnterior(salida);
        camino_1.agregarSiguienteCelda(camino_2);
        camino_2.agregarCeldaAnterior(camino_1);

        camino_2.agregarAfectable(new FieraSalvaje());

        Log log = null;
        try {
            log = new Log("./src/main/test/edu/fiuba/algo3/modeloTest/log/log.txt");
            log.resetLog();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Jugador jugador = new Jugador(pepe, salida, log);
        jugador.jugarTurno(dadoMock);


        String[] lineas = new String[0];
        try {
            lineas = log.getLines();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assert (lineas[1].contains("El gladiador se encuentra con una Fiera Salvaje y entran en combate."));
        assert (lineas[2].contains("Pierde 20 puntos de Energía."));
    }
    @Test
    public void logRegistraCuandoLosGladiadoresSonAfectadosPorLesion() {

        Dado dadoMock = mock(Dado.class);
        when(dadoMock.lanzar()).thenReturn(2);

        Celda salida = new Salida(0, 0, "Salida", 0);
        Celda camino_1 = new Camino(1, 0, "Camino", 1);
        Celda camino_2 = new Camino(2, 0, "Camino", 2);


        salida.agregarSiguienteCelda(camino_1);
        camino_1.agregarCeldaAnterior(salida);
        camino_1.agregarSiguienteCelda(camino_2);
        camino_2.agregarCeldaAnterior(camino_1);

        camino_2.agregarAfectable(new Lesion());

        Log log = null;
        try {
            log = new Log("./src/main/test/edu/fiuba/algo3/modeloTest/log/log.txt");
            log.resetLog();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Jugador jugador = new Jugador(pepe, salida, log);
        jugador.jugarTurno(dadoMock);


        String[] lineas = new String[0];
        try {
            lineas = log.getLines();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String linea : lineas){
            System.out.println(linea);
        }

        assert (lineas[1].contains("El gladiador se lesiona."));
        assert (lineas[2].contains("Pierde 1 turno/s."));
    }

    @Test
    public void logRegistraCuandoLosGladiadoresSonAfectadosPorComida() {

        Dado dadoMock = mock(Dado.class);
        when(dadoMock.lanzar()).thenReturn(2);

        Celda salida = new Salida(0, 0, "Salida", 0);
        Celda camino_1 = new Camino(1, 0, "Camino", 1);
        Celda camino_2 = new Camino(2, 0, "Camino", 2);


        salida.agregarSiguienteCelda(camino_1);
        camino_1.agregarCeldaAnterior(salida);
        camino_1.agregarSiguienteCelda(camino_2);
        camino_2.agregarCeldaAnterior(camino_1);

        camino_2.agregarAfectable(new Comida());

        Log log = null;
        try {
            log = new Log("./src/main/test/edu/fiuba/algo3/modeloTest/log/log.txt");
            log.resetLog();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Jugador jugador = new Jugador(pepe, salida, log);
        jugador.jugarTurno(dadoMock);


        String[] lineas = new String[0];
        try {
            lineas = log.getLines();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*for (String linea : lineas){
            System.out.println(linea);
        }*/

        assert (lineas[1].contains("El gladiador encuentra comida."));
        assert (lineas[2].contains("Gana 15 puntos de Energía."));
    }
    @Test
    public void logRegistraCuandoLosGladiadoresSonAfectadosPorEquipamiento() {

        Dado dadoMock = mock(Dado.class);
        when(dadoMock.lanzar()).thenReturn(2);

        Celda salida = new Salida(0, 0, "Salida", 0);
        Celda camino_1 = new Camino(1, 0, "Camino", 1);
        Celda camino_2 = new Camino(2, 0, "Camino", 2);


        salida.agregarSiguienteCelda(camino_1);
        camino_1.agregarCeldaAnterior(salida);
        camino_1.agregarSiguienteCelda(camino_2);
        camino_2.agregarCeldaAnterior(camino_1);

        camino_2.agregarAfectable(new Equipo());

        Log log = null;
        try {
            log = new Log("./src/main/test/edu/fiuba/algo3/modeloTest/log/log.txt");
            log.resetLog();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Jugador jugador = new Jugador(pepe, salida, log);
        jugador.jugarTurno(dadoMock);


        String[] lineas = new String[0];
        try {
            lineas = log.getLines();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*for (String linea : lineas){
            System.out.println(linea);
        }*/

        assert (lineas[1].contains("El gladiador se encuentra una mejora de Equipamiento."));
        assert (lineas[2].contains("El gladiador se equipa un/a Casco."));
    }
    /*@Test
    public void logRegistraCuandoLosGladiadoresAsciendenSuSeniority() {

        Dado dadoMock = mock(Dado.class);
        when(dadoMock.lanzar()).thenReturn(2);

        Celda salida = new Salida(0, 0, "Salida", 0);
        Celda camino_1 = new Camino(1, 0, "Camino", 1);
        Celda camino_2 = new Camino(2, 0, "Camino", 2);


        salida.agregarSiguienteCelda(camino_1);
        camino_1.agregarCeldaAnterior(salida);
        camino_1.agregarSiguienteCelda(camino_2);
        camino_2.agregarCeldaAnterior(camino_1);

        camino_2.agregarAfectable(new Equipo());

        Log log = null;
        try {
            log = new Log("./src/main/test/edu/fiuba/algo3/modeloTest/log/log.txt");
            log.resetLog();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Jugador jugador = new Jugador(pepe, salida, log);
        jugador.jugarTurno(dadoMock);


        String[] lineas = new String[0];
        try {
            lineas = log.getLines();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String linea : lineas){
            System.out.println(linea);
        }

        assert (lineas[1].contains("El gladiador se encuentra una mejora de Equipamiento."));
        assert (lineas[2].contains("El gladiador se equipa un/a Casco."));
    }*/
  /*  @Test
    public void logRegistraUnaSimulacionDelJuegoCorrectamente(){

    String jugador1 = "Se agregó al jugador: 'Pepe'.";
    String jugador2 = "Se agregó al jugador: 'Pablo'.";
    String jugador3 = "Se agregó al jugador: 'Pato'.";
    String jugador4 = "Se agregó al jugador: 'Paola'.";
    String inicio = "Inicio el Juego.";
    String turnoPepe1_1 = "Es el turno de 'Pepe'.";
    String turnoPepe1_2 = "'Pepe' tira dados y avanza 5 casilleros.";
    String turnoPepe1_3 = "'Pepe' se equipa un Casco.";
    String turnoPablo1_1 = "Es el turno de 'Pablo'.";
    String turnoPablo1_2 = "'Pablo' tira dados y avanza 3 casilleros.";
    String turnoPablo1_3 = "'Pablo' se equipa un Casco.";
    String turno1Finalizado = "Siguiente ronda.";



    try {
        Log log = new Log("./src/main/test/edu/fiuba/algo3/modeloTest/log/log.txt");
        log.resetLog();
        log.addLine(jugador1);
        log.addLine(jugador2);
        log.addLine(inicio);
        log.addLine(turnoPepe1_1);
        log.addLine(turnoPepe1_2);
        log.addLine(turnoPepe1_3);
        log.addLine(turnoPablo1_1);
        log.addLine(turnoPablo1_2);
        log.addLine(turnoPablo1_3);
        log.addLine(turno1Finalizado);

        String[] lineas = log.getLines();

        for (String linea : lineas) {
            System.out.println(linea);
        }


        assert(lineas[0].contains(jugador1));
        assert(lineas[1].contains(jugador2));
        assert(lineas[2].contains(inicio));
        assert(lineas[3].contains(turnoPepe1_1));
        assert(lineas[4].contains(turnoPepe1_2));
        assert(lineas[5].contains(turnoPepe1_3));
        assert(lineas[6].contains(turnoPablo1_1));
        assert(lineas[7].contains(turnoPablo1_2));
        assert(lineas[8].contains(turnoPablo1_3));
        assert(lineas[9].contains(turno1Finalizado));



    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    }
    */
}
