package edu.fiuba.algo3.modeloTest.gladiador.estado;

import edu.fiuba.algo3.modelo.gladiador.Gladiador;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import edu.fiuba.algo3.modelo.gladiador.estado.SinEnergia;
import edu.fiuba.algo3.modelo.gladiador.estado.Normal;

public class SinEnergiaTest {

    @Test
    public void testAccionar() {
        // Arrange
        Gladiador gladiador = mock(Gladiador.class);
        when(gladiador.obtenerEnergia()).thenReturn(5);
        SinEnergia sinEnergia = new SinEnergia();

        // Act
        sinEnergia.accionar(gladiador, 5, 1);

        // Assert
        verify(gladiador, times(1)).incrementarEnergia(5);
        verify(gladiador, times(1)).cambiarEstado(any(Normal.class));
    }
}