package edu.fiuba.algo3.modeloTest.gladiador.equipamiento;

import edu.fiuba.algo3.modelo.gladiador.Gladiador;
import edu.fiuba.algo3.modelo.gladiador.equipamiento.*;
import edu.fiuba.algo3.modelo.gladiador.mejorador.Mejorador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CascoTest {
    private Casco casco;
    private Gladiador gladiadorMock;
    private Mejorador mejorador;

    @BeforeEach
    public void setUp() {
        casco = new Casco();
        gladiadorMock = mock(Gladiador.class);
        mejorador = mock(Mejorador.class);
    }

    @Test
    public void mejorarEquipamientoDebeLlamarObtenerSiguienteMejora() {
        casco.mejorarEquipamiento(mejorador);
        verify(mejorador, times(1)).obtenerSeguienteMejora(casco);
    }

    @Test
    public void danioPorFieraSalvajeDebeDecrementGladiatorEnergy() {
        casco.danioPorFieraSalvaje(gladiadorMock);
        verify(gladiadorMock,times(1)).decrementarEnergia(15);
    }
}