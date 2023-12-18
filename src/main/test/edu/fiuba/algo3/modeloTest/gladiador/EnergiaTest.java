package edu.fiuba.algo3.modeloTest.gladiador;

import edu.fiuba.algo3.modelo.gladiador.Energia;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EnergiaTest {
    @Test
    public void verificarQueLaEnergiaInicialSeEstableceCorrectamente() {
        Energia energia = new Energia(20);
        assertEquals(20, energia.obtenerEnergia());
    }

    @Test
    public void verificarQueObtenerEnergiaDevuelveLaEnergiaCorrecta() {
        Energia energia = new Energia(30);
        assertEquals(30, energia.obtenerEnergia());
    }

    @Test
    public void verificarQueIncrementarAumentaLaEnergiaCorrectamente() {
        Energia energia = new Energia(20);
        energia.incrementar(10);
        assertEquals(30, energia.obtenerEnergia());
    }

    @Test
    public void verificarQueDecrementarDisminuyeLaEnergiaCorrectamente() {
        Energia energia = new Energia(20);
        energia.decrementar(10);
        assertEquals(10, energia.obtenerEnergia());
    }

    @Test
    public void verificarQueTieneEnergiaSuficienteDevuelveElValorCorrecto() {
        Energia energia = new Energia(20);
        assertTrue(energia.tieneEnergiaSuficiente());
        energia.decrementar(20);
        assertFalse(energia.tieneEnergiaSuficiente());
    }
}