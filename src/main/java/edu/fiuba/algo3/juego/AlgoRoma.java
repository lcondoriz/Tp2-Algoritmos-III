package edu.fiuba.algo3.juego;

import edu.fiuba.algo3.exceptions.CantidadJugadoresException;
import edu.fiuba.algo3.exceptions.NoHayJugadoresException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgoRoma {
    private static final int MIN_JUGADORES = 2;
    private static final int MAX_JUGADORES = 6;
    private static final int JUGADORES_ESPECIALES = 4;
    private static final int[] ORDEN_TURNOS = {3, 4, 1, 2};

    private List<Jugador> jugadores;
    private Dado dado;

    public AlgoRoma(Dado dado) {
        this.jugadores = new ArrayList<>();
        this.dado = dado;
    }
    public void agregarJugador(Jugador jugador) {
        this.jugadores.add(jugador);
    }
    public void iniciarJuego() {
        if (jugadores.isEmpty()) {
            throw new NoHayJugadoresException("No hay jugadores para iniciar el juego.");
        }
        // La cantidad de jugadores debe ser entre 2 y 6.
        if (jugadores.size() < MIN_JUGADORES  || jugadores.size() > MAX_JUGADORES) {
            throw new CantidadJugadoresException("La cantidad de jugadores debe ser entre 2 y 6.");
        }

        // Si hay 4 jugadores y empieza 3, sigue 4,1,2,
        if (jugadores.size() == JUGADORES_ESPECIALES) {
            moverJugadores(jugadores, ORDEN_TURNOS);
        } else { // Mezclar los jugadores de forma aleatoria.
            Collections.shuffle(jugadores);
        }

        // Cargamos el tablero (JSON).
    }

    public void jugarTurno() {
        for (Jugador jugador : jugadores) {
            jugador.jugarTurno(dado);
            // Se puede crear un metodo:
            // turnosTotales += jugador.ObtenerTurno(); que devuelva un turno.
            // y lanzar una excepcion si turnosTotales > 30


        }
    }

    private void moverJugadores(List<Jugador> listaJugadores, int[] ordenTurnos) {
        Jugador jugadorActual = listaJugadores.remove(ordenTurnos[0] - 1);
        listaJugadores.add(0, jugadorActual);

        jugadorActual = listaJugadores.remove(ordenTurnos[1] - 1);
        listaJugadores.add(1, jugadorActual);
    }
}