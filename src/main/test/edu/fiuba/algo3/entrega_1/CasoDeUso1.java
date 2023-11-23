package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.casillero.comestibles.Comida;
import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.tablero.Casillero;
import org.junit.jupiter.api.Test;

public class CasoDeUso1 {
    @Test
    public void verificarQueElJugadorEmpiezaConLaEnergiaYEquipamientoCorrespondiente() {
        // Arrange
        // Act
        // Assert

        Energia energia = new Energia(20);
        Casillero casillero = new Casillero(0, new Comida());

        Gladiador gladiador = new Gladiador(energia, casillero);

        // Assert
        // Energía del gladiador
        assert (gladiador.obtenerEnergia() == 20);

        // Equipamiento del gladiador
        assert (gladiador.obtenerEquipamiento().getClass().getSimpleName().equals("SinEquipamiento"));
    }
}
