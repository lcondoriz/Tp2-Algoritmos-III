package edu.fiuba.algo3.modelo.gladiador;

import edu.fiuba.algo3.modelo.exceptions.PartidaFinalizada;
import edu.fiuba.algo3.modelo.gladiador.equipamiento.Equipable;
import edu.fiuba.algo3.modelo.gladiador.equipamiento.Llave;
import edu.fiuba.algo3.modelo.gladiador.estado.Estado;
import edu.fiuba.algo3.modelo.gladiador.estado.Normal;
import edu.fiuba.algo3.modelo.gladiador.estado.SinEnergia;
import edu.fiuba.algo3.modelo.gladiador.mejorador.Mejorador;
import edu.fiuba.algo3.modelo.gladiador.equipamiento.SinEquipamiento;
import edu.fiuba.algo3.modelo.gladiador.seniority.Novato;
import edu.fiuba.algo3.modelo.gladiador.seniority.Seniority;
import edu.fiuba.algo3.modelo.log.Log;
import edu.fiuba.algo3.modelo.log.Logeador;
import edu.fiuba.algo3.modelo.tablero.celda.Celda;

public class Gladiador {
    private Log log;
    private Energia energia;
    private static final int  ENERGIA_MINIMA = 0;
    private Equipable equipamiento;
    private Seniority estrategiaSeniority;
    private Celda celda;
    private Estado estadoGladiador;
    public Gladiador(Energia energia, Celda celda) {
        this.energia = energia;
        this.equipamiento = new SinEquipamiento();
        this.estrategiaSeniority = new Novato();
        this.celda = celda;
        this.estadoGladiador = new Normal();
    }
    public Gladiador(Energia energia, Celda celda, Log log){
        this.energia = energia;
        this.equipamiento = new SinEquipamiento();
        this.estrategiaSeniority = new Novato();
        this.celda = celda;
        this.estadoGladiador = new Normal();
        this.log = log;
    }
    public void mejorarEquipamiento() {
        this.equipamiento = equipamiento.mejorarEquipamiento(new Mejorador());
        Logeador.agregarALog(this.log, "El gladiador se equipa un/a "+this.equipamiento.toString()+".");
    }
    public void incrementarEnergia(int incremento) {
        Logeador.agregarALog(this.log,"Gana " + Integer.valueOf(incremento).toString() + " puntos de Energía.");
        this.energia.incrementar(incremento);
        if (this.energia.obtenerEnergia() > ENERGIA_MINIMA) {
            this.estadoGladiador = new Normal();
        }
    }
    public void decrementarEnergia(int decremento) {
        Logeador.agregarALog(this.log, "Pierde " + Integer.valueOf(decremento).toString() + " puntos de Energía.");
        this.energia.decrementar(decremento);
        if (this.energia.obtenerEnergia() <= ENERGIA_MINIMA){
            this.estadoGladiador = new SinEnergia();
        }
    }
    public int obtenerEnergia() {
        return this.energia.obtenerEnergia();
    }
    public Equipable obtenerEquipamiento() {
        return this.equipamiento;
    }
    public void avanzar(int cantidadCeldas, int turno) {
        this.estadoGladiador.accionar(this, cantidadCeldas, turno);
    }
    public boolean tieneEquipamientoCompleto() {
        return this.equipamiento instanceof Llave;
    }
    public void mover(int cantidadCeldas, int turno) {
        this.celda = this.celda.avanzar(cantidadCeldas);

        // Cada celda sabe como aplicar su efecto
        this.celda.aplicarEfecto(this);

        // De acuerdo al turno, se incrementa el seniority
        this.estrategiaSeniority = this.estrategiaSeniority.incrementarSeniority(turno,this.log );

        // Cada vez que se avanza, es un turno nuevo. Y por cada turno nuevo y por el tipo de seniority se da un plus de energía.
        this.estrategiaSeniority.obtenerPlusEnergia(this.energia);
    }
    public Celda obtenerCelda() {
        return this.celda;
    }
    public void retrocederMitadCamino() {
        Logeador.agregarALog(this.log,"LLego a la LLegada pero debe retrocer a la mitad del tablero por equipamiento incompleto");
        this.celda = this.celda.retrocederMitadCamino();
    }
    public Log getLog(){
     return log;
    }
    public void cambiarEstado(Estado estadoGladiador) {
        this.estadoGladiador = estadoGladiador;
    }
    public void danioPorFieraSalvaje() {
        this.equipamiento.danioPorFieraSalvaje(this);
    }
    public void verificarSiEsGanador() {
        if (!this.tieneEquipamientoCompleto()) {
            this.retrocederMitadCamino();
            return ;
        }
        Logeador.agregarALog(this.log, "GANADOR DE LA PARTIDA");
        throw new PartidaFinalizada("Partida Finalizada");
    }
    public boolean EstasEnLaCelda(Celda otraCelda) {
        return this.celda.getPosicion() == otraCelda.getPosicion();
    }
}
