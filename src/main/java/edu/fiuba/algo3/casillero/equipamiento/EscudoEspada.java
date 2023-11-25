package edu.fiuba.algo3.casillero.equipamiento;

public class EscudoEspada implements Equipable {

    @Override
    public Equipable mejorarEquipamiento(Mejorador mejorador) {
        return mejorador.obtenerSeguienteMejora(this);
    }

    @Override
    public int danoRecibidoPorFieraSalvaje() {
        return 10;
    }
}
