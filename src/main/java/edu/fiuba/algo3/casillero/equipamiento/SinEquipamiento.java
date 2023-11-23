package edu.fiuba.algo3.casillero.equipamiento;

public class SinEquipamiento implements Equipable {

        @Override
        public Equipable mejorarEquipamiento() {
            return new Casco();
        }

        @Override
        public int danoRecibidoPorFieraSalvaje() {
            return 20;
        }

}
