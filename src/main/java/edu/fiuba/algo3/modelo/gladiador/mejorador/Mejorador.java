package edu.fiuba.algo3.modelo.gladiador.mejorador;

import edu.fiuba.algo3.modelo.gladiador.equipamiento.*;

public class Mejorador {
    public Equipable obtenerSeguienteMejora(SinEquipamiento sinEquipamiento) {
        return new Casco();
    }
    public Equipable obtenerSeguienteMejora(Casco casco) {
        return new Armadura();
    }
    public Equipable obtenerSeguienteMejora(Armadura armadura) {
        return new EscudoEspada();
    }
    public Equipable obtenerSeguienteMejora(EscudoEspada escudoEspada) {
        return new Llave();
    }
    public Equipable obtenerSeguienteMejora(Llave llave) {
        return llave;
    }
}
