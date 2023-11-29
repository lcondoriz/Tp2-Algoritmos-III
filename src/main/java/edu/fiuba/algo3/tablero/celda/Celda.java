package edu.fiuba.algo3.tablero.celda;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.tablero.celda.afectable.Afectable;

import java.util.LinkedList;
import java.util.List;

public abstract class Celda {
    private int numeracion;
    private int x;
    private int y;
    private String tipo;
    private List<Afectable> afectable;
    private Celda celdaAnterior;
    private Celda siguienteCelda;

    public Celda(int x, int y, String tipo, int numeracion) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
        this.numeracion = numeracion;
        this.afectable = new LinkedList<>();

        this.celdaAnterior = null;
        this.siguienteCelda = null;
    }
    public abstract void aplicarEfecto(Gladiador gladiador);
    public void aplicarAfectables(Gladiador gladiador) {
        for (Afectable afectable : this.afectable) {
            afectable.aplicarEfecto(gladiador);
        }
    }
    public void agregarCeldaAnterior(Celda celda) {
        this.celdaAnterior = celda;
    }
    public void agregarSiguienteCelda(Celda celda) {
        this.siguienteCelda = celda;
    }
    public void agregarAfectable(Afectable afectable) {
        this.afectable.add(afectable);
    }
    public Celda avanzar(int pasos) {
        if (pasos == 0 || this.siguienteCelda == null) {
            return this;
        }
        return this.siguienteCelda.avanzar(pasos - 1);
    }
    public Celda retroceder(int pasos) {
        if (pasos == 0 || this.celdaAnterior == null) {
            return this;
        }
        return this.celdaAnterior.retroceder(pasos - 1);
    }
    public Celda retrocenderMitadCamino() {
        return this.retroceder(this.numeracion / 2);
    }
}
