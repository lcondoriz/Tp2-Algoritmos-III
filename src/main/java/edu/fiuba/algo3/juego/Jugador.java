package edu.fiuba.algo3.juego;

import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.log.Log;
import edu.fiuba.algo3.tablero.celda.Celda;

import java.io.IOException;

public class Jugador {
    private String nombre;
    private int turno;
    private Gladiador gladiador;

    private Log log;

    private static final int ENERGIA_INICIAL = 20;

    public Jugador(String nombre, Celda celda, Log log) {    // Casillero de partida.
        this.nombre = nombre;
        this.turno = 0; // El turno empieza en 0.
        this.log = log;
        this.gladiador = new Gladiador(new Energia(ENERGIA_INICIAL), celda, log); // El jugador es dueño de su gladiador, sin èl no puede jugar.

    }
    public void jugarTurno(Dado dado) {
        this.turno++;

        Integer cantidadAAvanzar = Integer.valueOf(dado.lanzar());
        try {
            log.addLine("'"+this.getNombre()+"'"+" tira dados y avanza "+ cantidadAAvanzar.toString() +" casilleros.");
        } catch (IOException e) {
            throw new RuntimeException(e); //especializar exception(?
        }
        gladiador.avanzar(cantidadAAvanzar, this.turno);
    }
    public String getNombre(){
        return nombre;
    }
}
