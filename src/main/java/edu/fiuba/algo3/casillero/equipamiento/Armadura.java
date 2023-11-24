package edu.fiuba.algo3.casillero.equipamiento;

public class Armadura implements Equipable {

    @Override
    public Equipable mejorarEquipamiento() {
        return new EscudoEspada();
    }

    @Override
    public int danoRecibidoPorFieraSalvaje() {
        return 15;
    }

}
