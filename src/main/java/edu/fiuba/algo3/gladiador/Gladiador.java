package edu.fiuba.algo3.gladiador;

import edu.fiuba.algo3.casillero.equipamiento.Equipable;
import edu.fiuba.algo3.casillero.equipamiento.SinEquipamiento;
import edu.fiuba.algo3.exceptions.SinEnergiaException;
import edu.fiuba.algo3.gladiador.seniority.EstrategiaNovato;
import edu.fiuba.algo3.tablero.Casillero;

public class Gladiador {
    private Energia energia;
    private EstrategiaSeniority estrategiaSeniority;
    private Casillero casillero;
    private Equipable equipamiento;

    public Gladiador(Energia energia, Casillero casillero) {
        this.energia = energia;
        this.casillero = casillero;
        this.equipamiento = new SinEquipamiento();
        this.estrategiaSeniority = new EstrategiaNovato();
    }

    public void mejorarEquipamiento() {
        this.equipamiento = equipamiento.mejorarEquipamiento();
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
    public int obtenerPosicionCasillero() {
        return this.casillero.obtenerPosicion();
    }

    // VER: No se si es conveniente tener que recibir el turno como parámetro.
    public void avanzar(int cantidadCasilleros, int turno) {
        if (!this.energia.tieneEnergiaSuficiente()) {
            throw new SinEnergiaException("El gladiador no tiene suficiente energía para avanzar");
        }
        // Falta toda la lógica de avanzar entre casilleros, cantidadCasilleros es el desplazamiento Ejemplo: si cantidadCasilleros = 5, se desplaza 5 casilleros desde el casillero actual.
        // Se supone cada casillero que recibe Gladiador tiene un atributo quatributo clase que Vacio, Obstaculom, Comida y Equipamiento. Luego se aplica el efecto de cada uno.

        // Cada casillero sabe como desplazarse entre sus vecinos
        // this.casillero = this.casillero.avanzar(cantidadCasilleros);

        // Cada casillero sabe como aplicar su efecto
        this.casillero.aplicarEfecto(this);

        // De acuerdo al turno, se incrementa el seniority
        this.estrategiaSeniority = this.estrategiaSeniority.incrementarSeniority(turno);

        // Cada vez que se avanza, es un turno nuevo. Y por cada turno nuevo y por el tipo de seniority se da un plus de energía.
        this.estrategiaSeniority.obtenerPlusEnergia(this.energia);
    }

    public void setearCasillero(Casillero casillero) {
        this.casillero = casillero;
    }
}
