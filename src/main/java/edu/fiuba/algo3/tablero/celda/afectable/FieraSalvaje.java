package edu.fiuba.algo3.tablero.celda.afectable;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.gladiador.equipamiento.*;

public class FieraSalvaje implements Afectable {

    private static final int DANO_FIERA_SIN_EQUIPAMIENTO = 20;
    private static final int DANO_FIERA_CASCO = 15;
    private static final int DANO_FIERA_ARMADURA = 10;
    private static final int DANO_FIERA_ESCUDO_ESPADA = 2;
    private static final int DANO_FIERA_LLAVE = 0;
    @Override
    public void aplicarEfecto(Gladiador gladiador) {
        gladiador.obtenerEquipamiento().luchaFieraSalvaje(this, gladiador);
    }
    public void afectarConEquipamiento(SinEquipamiento equipamiento, Gladiador gladiador) {
        gladiador.decrementarEnergia(DANO_FIERA_SIN_EQUIPAMIENTO);
    }
    public void afectarConEquipamiento(Casco equipamiento, Gladiador gladiador) {
        gladiador.decrementarEnergia(DANO_FIERA_CASCO);
    }
    public void afectarConEquipamiento(Armadura equipamiento, Gladiador gladiador) {
        gladiador.decrementarEnergia(DANO_FIERA_ARMADURA);
    }
    public void afectarConEquipamiento(EscudoEspada equipamiento, Gladiador gladiador) {
        gladiador.decrementarEnergia(DANO_FIERA_ESCUDO_ESPADA);
    }
    public void afectarConEquipamiento(Llave equipamiento, Gladiador gladiador) {
        gladiador.decrementarEnergia(DANO_FIERA_LLAVE);
    }
}
