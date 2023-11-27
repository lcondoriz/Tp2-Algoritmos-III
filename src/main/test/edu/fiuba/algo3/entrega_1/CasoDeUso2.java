package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.casillero.comestibles.Comida;
import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.tablero.Casillero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasoDeUso2 {
    @Test
    public void verificarQueElJugadorSalgaDeLaCasillaInicial() {
        Energia energia = new Energia(20);
        Casillero casillero = new Casillero(0, new Comida());

        Gladiador gladiador = new Gladiador(energia, casillero);

//        Throwable exception = assertThrows(MovimientoExeption.class, () -> {
//            gladiador.retroceder();
//        });

        // verifica mos que el casillero inicial sea posicion 0
        assertEquals(0, gladiador.obtenerPosicionCasillero());
    }

}
