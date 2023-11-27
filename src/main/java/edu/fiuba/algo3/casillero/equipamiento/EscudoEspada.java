package edu.fiuba.algo3.casillero.equipamiento;

public class EscudoEspada implements Equipable {

    @Override
    public Equipable mejorarEquipamiento() {
        return new Llave();
    }

    @Override
    public int danoRecibidoPorFieraSalvaje() {
        return 10;
    }
}
