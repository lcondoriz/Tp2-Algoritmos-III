package edu.fiuba.algo3.casillero.premios;


import edu.fiuba.algo3.casillero.Equipamiento;

public class Casco implements Equipamiento {
    @Override
    public Equipamiento incrementarEquipo() {
        return new Armadura();
    }
}