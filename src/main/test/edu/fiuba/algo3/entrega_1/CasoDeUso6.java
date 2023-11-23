package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.casillero.equipamiento.Equipamiento;
import edu.fiuba.algo3.casillero.equipamiento.EscudoEspada;
import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.tablero.Casillero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasoDeUso6 {
    @Test // Caso de uso 6
    public void verificarQueSiRecibeUnPremioPorTerceraVezObtieneUnEscudoYespada() {
        Energia energia = new Energia(20);
        Casillero casillero = new Casillero(0, new Equipamiento());

        Gladiador gladiador = new Gladiador(energia, casillero);

        gladiador.avanzar(1, 0);
        gladiador.avanzar(1, 0);
        gladiador.avanzar(1, 0);

        assertEquals(EscudoEspada.class, gladiador.obtenerEquipamiento().getClass());
    }
}
