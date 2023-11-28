package edu.fiuba.algo3.modeloTest.gladiador;

import edu.fiuba.algo3.casillero.equipamiento.Equipable;
import edu.fiuba.algo3.casillero.equipamiento.Equipamiento;
import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.tablero.Casillero;
import edu.fiuba.algo3.tablero.Coordenadas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

public class GladiadorTest {
    private Gladiador gladiador;
    private Casillero casillero;

    @BeforeEach
    public void setUp() {
        Energia energia = new Energia(20);
        casillero = new Casillero(0, new ArrayList<>() {{
            add(new Equipamiento());}}, new Coordenadas(0, 0));
        gladiador = new Gladiador(energia, casillero);
    }

    @Test
    public void testMejorarEquipamiento() {
        Equipable equipamientoInicial = gladiador.obtenerEquipamiento();
        gladiador.mejorarEquipamiento();
        Equipable equipamientoMejorado = gladiador.obtenerEquipamiento();

        assertNotEquals(equipamientoInicial, equipamientoMejorado);
    }

    @Test
    public void testIncrementarEnergia() {
        int energiaInicial = gladiador.obtenerEnergia();
        int incremento = 10;
        gladiador.incrementarEnergia(incremento);
        int energiaIncrementada = gladiador.obtenerEnergia();

        assertEquals(energiaInicial + incremento, energiaIncrementada);
    }
    @Test
    public void testDecrementarEnergia() {
        int energiaInicial = gladiador.obtenerEnergia();
        int decremento = 5;
        gladiador.decrementarEnergia(decremento);
        int energiaDecrementada = gladiador.obtenerEnergia();

        assertEquals(energiaInicial - decremento, energiaDecrementada);
    }
}