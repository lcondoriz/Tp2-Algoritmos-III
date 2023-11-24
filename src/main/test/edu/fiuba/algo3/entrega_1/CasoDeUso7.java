package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.casillero.equipamiento.Equipamiento;
import edu.fiuba.algo3.casillero.equipamiento.EscudoEspada;
import edu.fiuba.algo3.casillero.obstaculos.FieraSalvaje;
import edu.fiuba.algo3.casillero.obstaculos.Obstaculo;
import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.tablero.Casillero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasoDeUso7 {
    @Test // Caso de uso 7
    public void verificarQueSiHayUnCombateConUnaFieraSalvajeYTieneUnCascoPierde15PuntosDeEnergia() {
        Energia energia = new Energia(20);
        Casillero casillero = new Casillero(0, new Equipamiento());

        Gladiador gladiador = new Gladiador(energia, casillero);

        gladiador.avanzar(1,0);   // Se obtiene un casco

        // Creo un obstaculo con una fiera salvaje
        Obstaculo obstaculo = new Obstaculo(new FieraSalvaje());

        gladiador.setearCasillero(new Casillero(0, obstaculo));

        gladiador.avanzar(1, 0);   // Se produce el combate

        // al tener un casco, el gladiador pierde 15 puntos de energia
        assertEquals(5, gladiador.obtenerEnergia());
    }
}
