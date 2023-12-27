package edu.fiuba.algo3.modeloTest.gladiador;

import edu.fiuba.algo3.modelo.tablero.Coordenadas;
import edu.fiuba.algo3.modelo.tablero.celda.Camino;
import edu.fiuba.algo3.modelo.gladiador.Gladiador;
import edu.fiuba.algo3.modelo.gladiador.equipamiento.Equipable;
import edu.fiuba.algo3.modelo.gladiador.Energia;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GladiadorTest {
    private Gladiador gladiador;
    private Camino celda;

    @BeforeEach
    public void setUp() {
        Energia energia = new Energia(20);
        celda = new Camino(new Coordenadas(1, 0), "Comida" , 0);
        gladiador = new Gladiador(energia, celda);
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