package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.casillero.comestibles.Comida;
import edu.fiuba.algo3.exceptions.SinEnergiaException;
import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.tablero.Casillero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasoDeUso3 {
    @Test
    public void verificarQueUnJugadorSinEnergiaNoPuedaJugarElTurno() {
        Energia energia = new Energia(20);
        Casillero casillero = new Casillero(0, new Comida());

        Gladiador gladiador = new Gladiador(energia, casillero);

        gladiador.decrementarEnergia(20);

        Throwable exception = Assertions.assertThrows(SinEnergiaException.class, () -> {
            gladiador.avanzar(1, 0);
        });
        assertEquals("El gladiador no tiene suficiente energía para avanzar", exception.getMessage());

    }
}
