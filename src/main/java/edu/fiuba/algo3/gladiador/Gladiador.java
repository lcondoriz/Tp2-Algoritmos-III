package edu.fiuba.algo3.gladiador;

import edu.fiuba.algo3.gladiador.equipamiento.Equipable;
import edu.fiuba.algo3.gladiador.mejorador.Mejorador;
import edu.fiuba.algo3.gladiador.equipamiento.SinEquipamiento;
import edu.fiuba.algo3.gladiador.seniority.Novato;
import edu.fiuba.algo3.gladiador.seniority.Seniority;
import edu.fiuba.algo3.tablero.celda.Celda;

public class Gladiador {
    private Energia energia;
    private Equipable equipamiento;
    private Seniority estrategiaSeniority;
    private Celda celda;
    private int turnosEsperaLesion;

    public Gladiador(Energia energia, Celda celda) {
        this.energia = energia;
        this.equipamiento = new SinEquipamiento();
        this.estrategiaSeniority = new Novato();
        this.celda = celda;
        this.turnosEsperaLesion = 0;
    }

    public void mejorarEquipamiento() {
        this.equipamiento = equipamiento.mejorarEquipamiento(new Mejorador());
    }
    public void incrementarEnergia(int incremento) {
        this.energia.incrementar(incremento);
    }
    public void decrementarEnergia(int decremento) {
        this.energia.decrementar(decremento);
    }
    public int obtenerEnergia() {
        return this.energia.obtenerEnergia();
    }
    public Equipable obtenerEquipamiento() {
        return this.equipamiento;
    }
//    public int obtenerPosicioncelda() {
//        return this.celda.obtenerPosicion();
//    }

    // VER: No se si es conveniente tener que recibir el turno como parámetro.
    public void avanzar(int cantidadCeldas, int turno) {
        if (!this.energia.tieneEnergiaSuficiente()) {
            this.energia.incrementar(5);
            return;
        }

        if (turnosEsperaLesion > 0) {
            turnosEsperaLesion--;
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
    public void serLesionado(int turnosEspera) {
        this.turnosEsperaLesion = turnosEspera;
    }
    public Celda obtenerCelda() {
        return this.celda;
    }
    public void retrocederMitadCamino() {
        this.celda = this.celda.retrocenderMitadCamino();
    }

//    public void setCelda(int posicion){
//        celda.setPosicion(posicion);
//    }
//    public void setearcelda(celda celda) {
//        this.celda = celda;
//    }

}
