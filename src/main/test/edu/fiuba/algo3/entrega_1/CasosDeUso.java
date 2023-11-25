package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.casillero.comestibles.Comida;
import edu.fiuba.algo3.casillero.equipamiento.Casco;
import edu.fiuba.algo3.casillero.equipamiento.Equipamiento;
import edu.fiuba.algo3.casillero.equipamiento.EscudoEspada;
import edu.fiuba.algo3.casillero.equipamiento.Llave;
import edu.fiuba.algo3.casillero.obstaculos.FieraSalvaje;
import edu.fiuba.algo3.casillero.obstaculos.Obstaculo;
import edu.fiuba.algo3.casillero.vacio.Final;
import edu.fiuba.algo3.casillero.vacio.Vacio;
import edu.fiuba.algo3.exceptions.CantidadTurnosException;
import edu.fiuba.algo3.exceptions.SinEnergiaException;
import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.juego.AlgoRoma;
import edu.fiuba.algo3.juego.Dado;
import edu.fiuba.algo3.tablero.Casillero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasosDeUso {
    @Test // Caso de uso 1
    public void verificarQueElJugadorEmpiezaConLaEnergiaYEquipamientoCorrespondiente() {
        // Arrange
        Energia energia = new Energia(20);
        Casillero casillero = new Casillero(0, new Comida());

        Gladiador gladiador = new Gladiador(energia, casillero);

        // Assert
        // Energía del gladiador
        assert (gladiador.obtenerEnergia() == 20);

        // Equipamiento del gladiador
        assert (gladiador.obtenerEquipamiento().getClass().getSimpleName().equals("SinEquipamiento"));
    }
    @Test // Caso de uso 2
    public void verificarQueElJugadorSalgaDeLaCasillaInicial() {
        // Arrange
        Energia energia = new Energia(20);
        Casillero casillero = new Casillero(0, new Comida());

        // Act
        Gladiador gladiador = new Gladiador(energia, casillero);

        // Assert
        // verifica mos que el casillero inicial sea posicion 0
        assertEquals(0, gladiador.obtenerPosicionCasillero());
    }

    @Test // Caso de uso 3
    public void verificarQueUnJugadorSinEnergiaNoPuedaJugarElTurno() {
        // Arrange
        Energia energia = new Energia(20);
        Casillero casillero = new Casillero(0, new Comida());

        Gladiador gladiador = new Gladiador(energia, casillero);

        gladiador.decrementarEnergia(20);

        Throwable exception = Assertions.assertThrows(SinEnergiaException.class, () -> {
            gladiador.avanzar(1, 0);
        });
        assertEquals("El gladiador no tiene suficiente energía para avanzar", exception.getMessage());
    }

    @Test // Caso de uso 4
    public void verificarQueSiRecibeComidaIncrementaEnergíaEn10() {
        Energia energia = new Energia(20);
        Casillero casillero = new Casillero(0, new Comida());

        Gladiador gladiador = new Gladiador(energia, casillero);

        gladiador.avanzar(1, 0);

        assertEquals(gladiador.obtenerEnergia(), 35);
    }

    @Test // Caso de uso 5
    public void verificarQueSiRecibeUnPremioPorPrimeraVezObtieneUnCasco() {
        Energia energia = new Energia(20);
        Casillero casillero = new Casillero(0, new Equipamiento());

        Gladiador gladiador = new Gladiador(energia, casillero);

        gladiador.avanzar(1, 0);

        assertEquals(Casco.class, gladiador.obtenerEquipamiento().getClass());
    }

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

    @Test // Caso de uso 9
    public void verificarQueSiLlegaALaMetaSinLaLlaveEnElEquipamientoRetrocedeALaMitadDeLasCasillas() {
        // Falta implementar.
        //Dado dado = new Dado(6);
        //AlgoRoma algoRoma = new AlgoRoma(dado);//quiza iria el JSON

        Casillero casilleroInicial = new Casillero(0, new Vacio());
        Casillero casilleroMitad = new Casillero(1, new Vacio());
        Casillero casilleroFinal = new Casillero(2, new Final(3));

        Gladiador gladiador = new Gladiador(new Energia(20), casilleroInicial);

       /* Tablero tablero = new Tablero({"Bacabal","Vacio","Equipamiento"
                casilleroInicial,casilleroMitad,casilleroFinal
        });
        */
        gladiador.avanzar(2,1);

        gladiador.setearCasillero(casilleroFinal);
        casilleroFinal.aplicarEfecto(gladiador);

        assertEquals(gladiador.obtenerPosicionCasillero(), casilleroMitad.obtenerPosicion());

    }

    @Test // Caso de uso 10
    public void verificarQueSiLoAtacaUnaFieraSalvajeYPoseeTodoElEquipamientoElDanioEnEnergiaEs0() {
        // Arrange
        Energia energia = new Energia(20);
        Casillero casillero = new Casillero(0, new Equipamiento());
        Gladiador gladiador = new Gladiador(energia, casillero);

        // Act
        // Avanza 4 casilleros, y tiene todo el equipamiento
        for (int i = 1; i < 5; i++) {
            gladiador.avanzar(1, i);
        }

        // Creo un obstaculo con una fiera salvaje
        Obstaculo obstaculo = new Obstaculo(new FieraSalvaje());
        gladiador.setearCasillero(new Casillero(5, obstaculo));

        // Assert
        // Al tener todo el equipamiento, el gladiador no pierde energia
        assertEquals(20, gladiador.obtenerEnergia());

    }

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

    @Test // Caso de uso 12
    public void verificarQueSiPasan30TurnosYNadieLlegoALaMetaSeTerminaElJuego() {
        // Falta implementar
        AlgoRoma algoRoma = new AlgoRoma(new Dado(6));
        for (int i = 0; i<29;i++){
            algoRoma.jugarTurno();
        }

        try {
            algoRoma.jugarTurno();
        }catch (CantidadTurnosException ex){
            assertEquals(ex.getClass(), CantidadTurnosException.class);
        }
    }
}
