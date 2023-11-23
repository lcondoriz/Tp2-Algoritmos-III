package edu.fiuba.algo3.casillero.equipamiento;

public class Llave implements Equipable {

    @Override
    public Equipable mejorarEquipamiento() {
        return this;
    }

    @Override
    public int danoRecibidoPorFieraSalvaje() {
        return 0;
    }

}