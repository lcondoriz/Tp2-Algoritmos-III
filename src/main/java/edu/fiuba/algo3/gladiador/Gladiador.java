package edu.fiuba.algo3.gladiador;

import edu.fiuba.algo3.gladiador.equipamiento.Equipable;
import edu.fiuba.algo3.gladiador.estado.Estado;
import edu.fiuba.algo3.gladiador.estado.Normal;
import edu.fiuba.algo3.gladiador.estado.SinEnergia;
import edu.fiuba.algo3.gladiador.mejorador.Mejorador;
import edu.fiuba.algo3.gladiador.equipamiento.SinEquipamiento;
import edu.fiuba.algo3.gladiador.seniority.Novato;
import edu.fiuba.algo3.gladiador.seniority.Seniority;
import edu.fiuba.algo3.log.Log;
import edu.fiuba.algo3.tablero.celda.Celda;

import java.io.IOException;

public class Gladiador {
    private Log log;
    private Energia energia;
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
        if (log != null) {
            try {
                log.addLine("El gladiador se equipa un/a "+this.equipamiento.toString()+".");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void incrementarEnergia(int incremento) {
        if (log!=null) {
            try {
                log.addLine("Gana " + Integer.valueOf(incremento).toString() + " puntos de Energía.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.energia.incrementar(incremento);
    }
    public void decrementarEnergia(int decremento) {
        if (log != null) {
            try {
                log.addLine("Pierde " + Integer.valueOf(decremento).toString() + " puntos de Energía.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.energia.decrementar(decremento);
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
    public void mover(int cantidadCeldas, int turno) {
        if (!this.energia.tieneEnergiaSuficiente()) {
            this.cambiarEstado(new SinEnergia());
            return;
        }
        this.celda = this.celda.avanzar(cantidadCeldas);

        // Cada celda sabe como aplicar su efecto
        this.celda.aplicarEfecto(this);

        // De acuerdo al turno, se incrementa el seniority
        this.estrategiaSeniority = this.estrategiaSeniority.incrementarSeniority(turno);

        // Cada vez que se avanza, es un turno nuevo. Y por cada turno nuevo y por el tipo de seniority se da un plus de energía.
        this.estrategiaSeniority.obtenerPlusEnergia(this.energia);

    }
    public Celda obtenerCelda() {
        return this.celda;
    }
    public void retrocederMitadCamino() {
        this.celda = this.celda.retrocenderMitadCamino();
    }
    public Log getLog(){
     return log;
    }
    public void cambiarEstado(Estado estadoGladiador) {
        this.estadoGladiador = estadoGladiador;
    }
}
