package edu.fiuba.algo3.juego;

import edu.fiuba.algo3.exceptions.CantidadJugadoresException;
import edu.fiuba.algo3.exceptions.NoHayJugadoresException;
import edu.fiuba.algo3.json.TableroConstructor;
import edu.fiuba.algo3.log.Log;
import edu.fiuba.algo3.tablero.Tablero;
import edu.fiuba.algo3.javafx.Interfaz;
import edu.fiuba.algo3.javafx.TableroVisual;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgoRoma{
    private static final int MIN_JUGADORES = 2;
    private static final int MAX_JUGADORES = 6;
    private static final int JUGADORES_ESPECIALES = 4;
    private static final int[] ORDEN_TURNOS = {3, 4, 1, 2};
    private static final int TURNO_INICIAL = 0;
    private static final int MAX_TURNOS = 30;
    private static final int MAX_CANTIDAD_RONDAS = 30;
    private List<Jugador> jugadores;
    private Dado dado;
    private Tablero tablero;
    private int turno;

    private Log log;
    private Interfaz interfaz;
    
    {
        try {
            log = new Log("./src/main/java/edu/fiuba/algo3/log/log.txt");
            log.resetLog(); // sirve para resetear el txt despues vemos si combiene o no hacerlo
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public AlgoRoma(Dado dado) {
        this.turno = TURNO_INICIAL;
        this.jugadores = new ArrayList<>();
        this.dado = dado;
        this.interfaz = new Interfaz();
    }

    public void cargarTablero(String path) {
        this.tablero = new TableroConstructor().construirTableroDesdeJSON(path);
    }

    public void agregarJugador(String nombre) {
        Jugador jugador = new Jugador(nombre, this.tablero.obtenerCeldaDeSalida(), log);
        this.jugadores.add(jugador);
        try {
            log.addLine("Se agregó al jugador: '" + nombre + "'.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*mandarle a la interfaz la referencia del gladiador

        */
    }

    public void inicializarJuego() {
        if (jugadores.isEmpty()) {
            throw new NoHayJugadoresException("No hay jugadores para iniciar el juego.");
        }

        this.validarCantidadJugadores();

        try {
            log.addLine("Se inicio el Juego.");
        } catch (IOException e) {
            throw new RuntimeException(e);  //hace falta especializar exception?
        }

        // Si hay 4 jugadores y empieza 3, sigue 4,1,2,
        if (jugadores.size() == JUGADORES_ESPECIALES) {
            moverJugadores(jugadores, ORDEN_TURNOS);
        } else { // Mezclar los jugadores de forma aleatoria.
            Collections.shuffle(jugadores);
        }
    }

    public void jugar() {
        for (int i = 0; i < MAX_CANTIDAD_RONDAS; i++) {
            for (Jugador jugador : jugadores) {
                try {
                    log.addLine("Es el turno de: '" + jugador.obtenerNombre() + "'.");
                } catch (IOException e) {
                    throw new RuntimeException(e); //especializar exception(?
                }
                jugador.jugarTurno(dado);
            }
        }
    }

    public void jugar1Ronda() {
        for (Jugador jugador : jugadores) {
            try {
                log.addLine("Es el turno de: '" + jugador.obtenerNombre() + "'.");
            } catch (IOException e) {
                throw new RuntimeException(e); //especializar exception(?
            }
            jugador.jugarTurno(dado);
        }
    }
    

    private void moverJugadores(List<Jugador> listaJugadores, int[] ordenTurnos) {
        Jugador jugadorActual = listaJugadores.remove(ordenTurnos[0] - 1);
        listaJugadores.add(0, jugadorActual);

        jugadorActual = listaJugadores.remove(ordenTurnos[1] - 1);
        listaJugadores.add(1, jugadorActual);
    }

    public Log getLog() {
        return log;
    }
    private void validarCantidadJugadores() {
        if (jugadores.size() < MIN_JUGADORES || jugadores.size() > MAX_JUGADORES) {
            throw new CantidadJugadoresException("La cantidad de jugadores debe ser entre 2 y 6.");
        }
    }
    public List<Jugador> obtenerJugadores(){
        return jugadores;
    }
    public int obtenerTurno(){
        return turno;
    }
}
