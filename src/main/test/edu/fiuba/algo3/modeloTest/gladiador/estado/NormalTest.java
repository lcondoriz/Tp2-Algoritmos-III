package edu.fiuba.algo3.modeloTest.gladiador.estado;

import edu.fiuba.algo3.modelo.gladiador.Gladiador;
import edu.fiuba.algo3.modelo.gladiador.estado.Normal;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class NormalTest {
    @Test
    public void testAccionar() {
        // Arrange
        Gladiador gladiador = mock(Gladiador.class);
        Normal normal = new Normal();
        int cantidadCeldas = 5;
        int turno = 1;

        // Act
        normal.accionar(gladiador, cantidadCeldas, turno);

        // Assert
        verify(gladiador, times(1)).mover(cantidadCeldas, turno);
    }
}