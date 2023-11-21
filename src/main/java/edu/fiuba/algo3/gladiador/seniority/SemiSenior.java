package edu.fiuba.algo3.gladiador.seniority;

public class SemiSenior implements Seniority {
    @Override
    public int obtenerPlusEnergia() {
        return 5;
    }

    public Seniority incrementarSeniority() {
        return new Senior();
    }
}