package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.casillero.comestibles.Comida;
import edu.fiuba.algo3.casillero.equipamiento.Equipamiento;
import edu.fiuba.algo3.casillero.equipamiento.EscudoEspada;
import edu.fiuba.algo3.casillero.equipamiento.Llave;
import edu.fiuba.algo3.casillero.obstaculos.FieraSalvaje;
import edu.fiuba.algo3.casillero.obstaculos.Obstaculo;
import edu.fiuba.algo3.casillero.vacio.Vacio;
import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.tablero.Casillero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasoDeUso11 {
    @Test // Caso de uso 11
    public void verificarQueSiElGladiadorTieneLaLlaveYRecibeOtroEquipamientoNoCambiaNada() {
        // Arrange
        Energia energia = new Energia(20);
        Casillero casillero = new Casillero(0, new Equipamiento());
        Gladiador gladiador = new Gladiador(energia, casillero);

        // Act
        // Avanza 4 casilleros, y tiene todo el equipamiento. Se obtiene la llave.
        for (int i = 1; i < 5; i++) {
            gladiador.avanzar(1, i);
        }

        // Se avanza una vez mas para obtener otro equipamiento
        gladiador.avanzar(1, 5);

        // Assert
        // Se mantuvo el equipamiento anterior
        assertEquals(Llave.class, gladiador.obtenerEquipamiento().getClass());

    }
}
