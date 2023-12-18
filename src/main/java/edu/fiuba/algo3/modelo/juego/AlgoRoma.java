package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.exceptions.CantidadJugadoresException;
import edu.fiuba.algo3.modelo.exceptions.CantidadTurnosException;
import edu.fiuba.algo3.modelo.exceptions.NoHayJugadoresException;
import edu.fiuba.algo3.modelo.exceptions.PartidaFinalizada;
import edu.fiuba.algo3.modelo.parser.TableroConstructor;
import edu.fiuba.algo3.modelo.log.Log;
import edu.fiuba.algo3.modelo.log.Logeador;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlgoRoma {
    private static final int MIN_JUGADORES = 2;
    private static final int MAX_JUGADORES = 6;
    private static final int TURNO_INICIAL = 0;
    private static final int MAX_CANTIDAD_RONDAS = 30;
    private List<Jugador> jugadores;
    private Dado dado;
    private Tablero tablero;
    private int turno;
    private boolean hayGanador;
    private Jugador primerJugador;
    private Log log;
    {
        try {
            log = new Log("./src/main/java/edu/fiuba/algo3/modelo/log/log.txt");
            log.resetLog(); // sirve para resetear el txt despues vemos si combiene o no hacerlo
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public AlgoRoma(Dado dado) {
        this.turno = TURNO_INICIAL;
        this.jugadores = new ArrayList<>();
        this.dado = dado;
    }
    public void cargarTablero(String path) {
        this.tablero = new TableroConstructor().construirTableroDesdeJSON(path);
    }
    public void agregarJugador(String nombre) {
        Jugador jugador = new Jugador(nombre, this.tablero.obtenerCeldaDeSalida(), log);
        this.jugadores.add(jugador);
        Logeador.agregarALog(this.log, "Se agregó al jugador: '" + nombre + "'.");
    }
    public void inicializarJuego() {
        if (jugadores.isEmpty()) {
            throw new NoHayJugadoresException("No hay jugadores para iniciar el juego.");
        }

        this.validarCantidadJugadores();

        Logeador.agregarALog(this.log, "Se inicio el Juego.");
        this.moverOrdenDeJugadoresAJugar();
    }
    private void moverOrdenDeJugadoresAJugar() {
        Random random = new Random();
        int indiceJugadorAleatorio = random.nextInt(this.jugadores.size());
        for (int i = 0; i < indiceJugadorAleatorio; i++) {
            this.jugadores.add(this.jugadores.remove(0));
        }
        this.primerJugador = this.jugadores.get(0);
    }
    public void jugar() {
        for (int i = 0; i < MAX_CANTIDAD_RONDAS; i++) {
            if (hayGanador){return;};
            Logeador.agregarALog(this.log,"TURNO:"+Integer.valueOf(this.turno +1).toString());
            for (Jugador jugador : jugadores) {
                Logeador.agregarALog(this.log,"Es el turno de: '" + jugador.obtenerNombre() + "'.");
                try {
                    jugador.jugarTurno(dado);
                    this.turno++;
                } catch (PartidaFinalizada e) {
                    this.hayGanador = true;
                    throw new PartidaFinalizada("GANADOR DE LA PARTIDA: "  + jugador.obtenerNombre()); //especializar exception(?
                }
            }
        }
        Logeador.agregarALog(this.log,"No hay ganador ya que superaron la maxima cantidad de rondas.");
        throw new CantidadTurnosException("No hay ganador ya que superaron la maxima cantidad de rondas.");
    }
    public Log getLog() {
        return this.log;
    }
    private void validarCantidadJugadores() {
        if (jugadores.size() < MIN_JUGADORES || jugadores.size() > MAX_JUGADORES) {
            throw new CantidadJugadoresException("La cantidad de jugadores debe ser entre 2 y 6.");
        }
    }
    public void jugar1Turno(){
        if (hayGanador){return;}
        Jugador jugador = this.jugadores.remove(0);
        if (primerJugador==jugador){this.turno++;}
        Logeador.agregarALog(this.log,"TURNO:"+Integer.valueOf(this.turno).toString());
        try {
            Logeador.agregarALog(this.log,"Es el turno de: '" + jugador.obtenerNombre() + "'.");
            jugador.jugarTurno(dado);
        } catch (PartidaFinalizada e) {
            //mostrar en interfaz cartel con "GANADOR DE LA PARTIDA: "+ jugador.obtenerNombre() con boton de volver a jugar y otro de cerrar(?
            //volver a jugar: reiniciar tablero, mover a todos
            this.hayGanador = true;
            throw new PartidaFinalizada("GANADOR DE LA PARTIDA: "  + jugador.obtenerNombre()); //especializar exception(?
        }
        this.jugadores.add(jugador);

    }
    public void jugar1Ronda() { //testear interfaz
        if (hayGanador){return;};
        this.turno++;
        Logeador.agregarALog(this.log,"TURNO:"+Integer.valueOf(this.turno).toString());
        for (Jugador jugador : jugadores) {
            Logeador.agregarALog(this.log,"Es el turno de: '" + jugador.obtenerNombre() + "'.");
            try {
                jugador.jugarTurno(dado);
            } catch (PartidaFinalizada e) {
                //mostrar en interfaz cartel con "GANADOR DE LA PARTIDA: "+ jugador.obtenerNombre() con boton de volver a jugar y otro de cerrar(?
                //volver a jugar: reiniciar tablero, mover a todos
                this.hayGanador = true;
                throw new PartidaFinalizada("GANADOR DE LA PARTIDA: "  + jugador.obtenerNombre()); //especializar exception(?
            }
        }
    }
    public List<Jugador> obtenerJugadores(){
        return jugadores;
    }
}
