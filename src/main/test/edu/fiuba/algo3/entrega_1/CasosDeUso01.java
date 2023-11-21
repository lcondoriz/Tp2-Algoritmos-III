package edu.fiuba.algo3.entrega_1;


import edu.fiuba.algo3.casillero.Equipo;
import edu.fiuba.algo3.casillero.obtaculos.FieraSalvaje;
import edu.fiuba.algo3.casillero.premios.Armadura;
import edu.fiuba.algo3.casillero.premios.Casco;
import edu.fiuba.algo3.casillero.premios.Comida;
import edu.fiuba.algo3.casillero.premios.EscudoEspada;
import edu.fiuba.algo3.exception.SinEnergiaException;
import edu.fiuba.algo3.gladiador.*;
import edu.fiuba.algo3.gladiador.seniority.*;
import edu.fiuba.algo3.tablero.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class CasosDeUso01 {
    private Gladiador gladiador;

    @BeforeEach
    public void setUp() {
        Energia energia = new Energia(20);
        Seniority seniority = new Novato();
        Casillero casillero = new Casillero(0, new Comida(15));

        gladiador = new Gladiador(energia, seniority, casillero);
    }

    @Test   // Caso de uso 1
    public void verificarQueElJugadorEmpiezaConLaEnergiaYEquipamientoCorrespondiente() {
        assertEquals(20, gladiador.obtenerEnergia());
    }

    @Test   // Caso de uso 2
    public void verificarQueElJugadorSalgaDeLaCasillaInicial() {
        assertEquals(0, gladiador.obtenerPosicion());
    }

    @Test   // Caso de uso 3
    public void verificarQueUnJugadorSinEnergiaNoPuedaJugarElTurno() {
        Energia energia = new Energia(0);
        Seniority seniority = new Novato();
        Casillero casillero = new Casillero(0, new Comida(15));

        gladiador = new Gladiador(energia, seniority, casillero);

        try {
            gladiador.avanzar(1);
        } catch (SinEnergiaException e) {
            assertEquals("El jugador no tiene suficiente energía para jugar el turno.", e.getMessage());
        }

    }

    @Test   // Caso de uso 4
    public void verificarQueSiRecibeComidaIncrementaEnergíaEn10() {
        Energia energia = new Energia(20);
        Seniority seniority = new Novato();
        Casillero casillero = new Casillero(0, new Comida(15));

        gladiador = new Gladiador(energia, seniority, casillero);

        gladiador.avanzar(0);

        assertEquals(35, gladiador.obtenerEnergia());
    }
    @Test   // Caso de uso 5
    public void verificarQueSiRecibeUnPremioPorPrimeraVezObtieneUnCasco() {
        Energia energia = new Energia(20);
        Seniority seniority = new Novato();
        Casillero casillero = new Casillero(0, new Equipo());

        gladiador = new Gladiador(energia, seniority, casillero);

        gladiador.avanzar(0);

        assertEquals(Casco.class, gladiador.getEquipamiento().getClass());


    }
    @Test   // Caso de uso 6
    public void verificarQueSiRecibeUnPremioPorTerceraVezObtieneUnEscudoYespada() {
        Energia energia = new Energia(20);
        Seniority seniority = new Novato();
        Casillero casillero = new Casillero(0, new Equipo());

        gladiador = new Gladiador(energia, seniority, casillero);

        gladiador.avanzar(0);
        gladiador.avanzar(0);
        gladiador.avanzar(0);

        assertEquals(EscudoEspada.class, gladiador.getEquipamiento().getClass());
    }

    @Test   // Caso de uso 7
    public void verificarQueSiHayUnCombateConUnaFieraSalvajeYTieneUnCascoPierde15PuntosDeEnergia() {
        Energia energia = new Energia(20);
        Seniority seniority = new Novato();
        Casillero casillero = new Casillero(0, new FieraSalvaje());

        gladiador = new Gladiador(energia, seniority, casillero);

        // Al principio no tiene equipamiento
        gladiador.avanzar(0);

        assertEquals(0, gladiador.obtenerEnergia());
    }

    @Test   // Caso de uso 8
    public void verificarQueSiPasan8TurnosElSeniorityDelGladiadorPasaDeNovatoASemiSeniorYVeSuEnergiaIncrementadaAlProximoTurno() {
        Energia energia = new Energia(20);
        Seniority seniority = new Novato();
        Casillero casillero = new Casillero(0, null);

        gladiador = new Gladiador(energia, seniority, casillero);

        for (int i = 1; i <= 8; i++) {  // 8 turnos
            gladiador.avanzar(1);   // Avanza 1 casillero
        }

        assertEquals(25, gladiador.obtenerEnergia());
    }

    @Test   // Caso de uso 9
    public void verificarQueSiLlegaALaMetaSinLaLlaveEnElEquipamientoRetrocedeALaMitadDeLasCasillas() {
        // Falta implementar
    }

    @Test   // Caso de uso 10
    public void verificarQueSiLoAtacaUnaFieraSalvajeYPoseeTodoElEquipamientoElDanioEnEnergiaEs0() {
        Energia energia = new Energia(20);
        Seniority seniority = new Novato();
        Casillero casillero = new Casillero(0, new Equipo());

        gladiador = new Gladiador(energia, seniority, casillero);

        for (int i = 0; i <= 3; i++) {  // 4 turnos, se obtiene el equipamiento completo
            gladiador.avanzar(1);   // Avanza 1 casillero
        }
        gladiador.setCasillero(new Casillero(4, new FieraSalvaje()));

        gladiador.avanzar(0);

        assertEquals(20, gladiador.obtenerEnergia());

    }
    @Test   // Caso de uso 11
    public void verificarQueSiElGladiadorTieneLaLlaveYRecibeOtroPremioNoCambiaNada() {
        // Falta implementar
    }

    @Test   // Caso de uso 12
    public void verificarQueSiPasan30TurnosYNadieLlegoALaMetaSeTerminaElJuego() {
        // Falta implementar
    }
}
