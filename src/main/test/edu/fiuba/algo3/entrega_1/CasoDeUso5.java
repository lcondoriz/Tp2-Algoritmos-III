package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.casillero.comestibles.Comida;
import edu.fiuba.algo3.casillero.equipamiento.Casco;
import edu.fiuba.algo3.casillero.equipamiento.Equipamiento;
import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.tablero.Casillero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasoDeUso5 {
    @Test // Caso de uso 5
    public void verificarQueSiRecibeUnPremioPorPrimeraVezObtieneUnCasco() {
        Energia energia = new Energia(20);
        Casillero casillero = new Casillero(0, new Equipamiento());

        Gladiador gladiador = new Gladiador(energia, casillero);

        gladiador.avanzar(1, 0);

        assertEquals(Casco.class, gladiador.obtenerEquipamiento().getClass());
    }
}
