package edu.fiuba.algo3.juego;

import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.tablero.Casillero;

public class Jugador {
    private String nombre;
    private int turno;
    private Gladiador gladiador;

    public Jugador(String nombre, Casillero casillero) {    // Casillero de partida.
        this.nombre = nombre;
        this.turno = 0;
        this.gladiador = new Gladiador(new Energia(20), casillero); // El jugador es dueño de su gladiador, sin èl no puede jugar.
    }


    public void jugarTurno(Dado dado) {
        this.turno++;

        int cantidadDeMovimientos = dado.lanzar();
        gladiador.avanzar(cantidadDeMovimientos, turno);
    }
}
