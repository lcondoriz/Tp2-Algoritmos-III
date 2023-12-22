package edu.fiuba.algo3.modeloTest.juegoTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import edu.fiuba.algo3.modelo.juego.Dado;

public class DadoTest {
    private int cantidadDeTiradas;
    @BeforeEach
    public void setUp() {
        cantidadDeTiradas = 1000;
    }
    @Test
    public void testCrearDadoSinParametros() {
        Dado dado = new Dado();
        for (int i = 0; i < cantidadDeTiradas; i++) {
            int resultado = dado.lanzar();
            assertTrue(resultado >= 1 && resultado <= 6);
        }
    }
    @Test
    public void testCrearDadoConParametros() {
        Dado dado = new Dado(8);
        for (int i = 0; i < cantidadDeTiradas; i++) {
            int resultado = dado.lanzar();
            assertTrue(resultado >= 1 && resultado <= 8);
        }
    }
}