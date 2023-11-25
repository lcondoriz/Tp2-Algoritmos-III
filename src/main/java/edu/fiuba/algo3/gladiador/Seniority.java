package edu.fiuba.algo3.gladiador;

public interface Seniority {
    void obtenerPlusEnergia(Energia energia);
    Seniority incrementarSeniority(int turno);
}