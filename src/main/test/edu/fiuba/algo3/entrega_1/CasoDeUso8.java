package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.casillero.comestibles.Comida;
import edu.fiuba.algo3.casillero.equipamiento.Equipamiento;
import edu.fiuba.algo3.casillero.equipamiento.EscudoEspada;
import edu.fiuba.algo3.casillero.obstaculos.FieraSalvaje;
import edu.fiuba.algo3.casillero.obstaculos.Obstaculo;
import edu.fiuba.algo3.casillero.vacio.Vacio;
import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.tablero.Casillero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasoDeUso8 {
    @Test // Caso de uso 8
    public void verificarQueSiPasan8TurnosElSeniorityDelGladiadorPasaDeNovatoASemiSeniorYVeSuEnergiaIncrementadaAlProximoTurno() {
        // Arrange
        Energia energia = new Energia(20);
        Casillero casillero = new Casillero(0, new Vacio());
        Gladiador gladiador = new Gladiador(energia, casillero);

        // Act
        for (int i = 1; i < 9; i++) {
            gladiador.avanzar(1, i);
        }

        // Assert
        //assertEquals("SemiSenior", gladiador.obtenerSeniority());
        assertEquals(25, gladiador.obtenerEnergia());

    }
}
