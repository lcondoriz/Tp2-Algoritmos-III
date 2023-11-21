package edu.fiuba.algo3.gladiador;

import edu.fiuba.algo3.casillero.Equipamiento;
import edu.fiuba.algo3.casillero.premios.Casco;
import edu.fiuba.algo3.casillero.premios.SinEquipamiento;
import edu.fiuba.algo3.exception.SinEnergiaException;
import edu.fiuba.algo3.gladiador.seniority.*;
import edu.fiuba.algo3.tablero.*;

public class Gladiador {
    private Energia energia;
    private Seniority seniority;
    private Casillero casillero;
    private Equipamiento equipamiento;

    public Gladiador(Energia energia, Seniority seniority, Casillero casillero) {
        this.seniority = seniority;
        this.energia = energia;
        this.casillero = casillero;
        this.equipamiento = null;
    }

    public int obtenerEnergia() { // cambiar
        return energia.obtenerPuntos();
    }

    public int obtenerPosicion() {
        return casillero.obtenerPosicion();
    }

    public void avanzar(int cantidad) { // El gladiador avanza la cantidad de casilleros indicada, y se guarda el nuevo
                                        // casillero en el atributo casillero y se aplica el efecto del elemento del
                                        // casillero
        if (energia.obtenerPuntos() <= 0) {
            throw new SinEnergiaException("El jugador no tiene suficiente energía para jugar el turno.");
        }
        casillero = casillero.avanzar(cantidad);

        casillero.aplicarEfecto(this);
    }

    public void aumentarEnergia(int cantidad) {
        energia.incrementar(cantidad);
    }

    public void disminuirEnergia(int cantidad) {
        energia.decrementar(cantidad);
    }

    public void incrementarEquipamiento() {
        if (equipamiento == null) {
            equipamiento = new SinEquipamiento();
        }
        this.equipamiento = equipamiento.incrementarEquipo();
    }

    public Equipamiento getEquipamiento() {
        return equipamiento;
    }

    public Seniority getSeniority() {
        return seniority;
    }

    public void incrementarSeniority() {
        this.seniority = seniority.incrementarSeniority();
    }
}
