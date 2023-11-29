package edu.fiuba.algo3.juego;

import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.tablero.celda.Celda;

public class Jugador {
    private String nombre;
    private int turno;
    private Gladiador gladiador;
    private static final int ENERGIA_INICIAL = 20;

    public Jugador(String nombre, Celda celda) {    // Casillero de partida.
        this.nombre = nombre;
        this.turno = 0; // El turno empieza en 0.
        this.gladiador = new Gladiador(new Energia(ENERGIA_INICIAL), celda); // El jugador es dueño de su gladiador, sin èl no puede jugar.
    }
    public void jugarTurno(Dado dado) {
        this.turno++;

        gladiador.avanzar(dado.lanzar(), this.turno);
    }
}
