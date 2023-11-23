package edu.fiuba.algo3.casillero.equipamiento;

public class Casco implements Equipable {
        @Override
        public Equipable mejorarEquipamiento() {
            return new Armadura();
        }

        @Override
        public int danoRecibidoPorFieraSalvaje() {
            return 15;
        }

}
