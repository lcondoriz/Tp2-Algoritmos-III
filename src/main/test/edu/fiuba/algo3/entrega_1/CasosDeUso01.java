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

    @Test // Caso de uso 1
    public void verificarQueElJugadorEmpiezaConLaEnergiaYEquipamientoCorrespondiente() {
        assertEquals(20, gladiador.obtenerEnergia());
    }

    @Test // Caso de uso 2
    public void verificarQueElJugadorSalgaDeLaCasillaInicial() {
        assertEquals(0, gladiador.obtenerPosicion());
    }

    @Test // Caso de uso 3
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

    @Test // Caso de uso 4
    public void verificarQueSiRecibeComidaIncrementaEnergíaEn10() {
        Energia energia = new Energia(20);
        Seniority seniority = new Novato();
        Casillero casillero = new Casillero(0, new Comida(15));

        gladiador = new Gladiador(energia, seniority, casillero);

        gladiador.avanzar(0);

        assertEquals(35, gladiador.obtenerEnergia());
    }

    @Test // Caso de uso 5
    public void verificarQueSiRecibeUnPremioPorPrimeraVezObtieneUnCasco() {
        Energia energia = new Energia(20);
        Seniority seniority = new Novato();
        Casillero casillero = new Casillero(0, new Equipo());

        gladiador = new Gladiador(energia, seniority, casillero);

        gladiador.avanzar(0);

        assertEquals(Casco.class, gladiador.getEquipamiento().getClass());

    }

    @Test // Caso de uso 6
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

    @Test // Caso de uso 7
    public void verificarQueSiHayUnCombateConUnaFieraSalvajeYTieneCascoPierdeDiezPuntosDeEnergia() {
        Energia energia = new Energia(20);
        Seniority seniority = new Novato();
        Casillero casillero = new Casillero(0, new FieraSalvaje(10));

        gladiador = new Gladiador(energia, seniority, casillero);
        gladiador.incrementarEquipamiento();
        gladiador.avanzar(0);
        assertEquals(10, gladiador.obtenerEnergia());
    }
    /* 
    @Test // Caso de uso 8
    public void verificarQueSiPasanOchoTurnosElSeniorityDelGladiadorPasaDeNovatoASeniorYVeSuEnergiaIncrementadaAlProximoTurno() {
        Energia energia = new Energia(20);
        Seniority seniority = new Novato();
        Casillero casillero = new Casillero(0, new Equipo());

        gladiador = new Gladiador(energia, seniority, casillero);
        gladiador.avanzar(0);
        assertEquals(Senior.class, gladiador.getSeniority().getClass());
        assertEquals(30, gladiador.obtenerEnergia());
    }

    @Test // Caso de uso 9
    public void verificarQueSiLlegaALaMetaSinLaLlaveEnElEquipamientoRetrocedeALaMitadDeLasCasillas() {
        assertEquals(true, 1);
    }

    @Test // Caso de uso 10
    public void verificarQueSiLoAtacaUnaFieraSalvajeYPoseeTodoElEquipamientoElDanioEnEnergiaEsCero() {
        assertEquals(true, 1);
    }

    @Test // Caso de uso 11
    public void verificarQueSiElGladiadorTieneLaLlaveYRecibeOtroPremioNoCambiaNada() {
        assertEquals(true, 1);
    }

    @Test // Caso de uso 12
    public void verificarQueSiPasanTreintaTurnosYNadieLlegoALaMetaSeTerminaElJuego() {
        assertEquals(true, 1);
    }
    */
}
