package edu.fiuba.algo3.gladiador;

public interface EstrategiaSeniority {
    void obtenerPlusEnergia(Energia energia);
    EstrategiaSeniority incrementarSeniority(int turno);
}