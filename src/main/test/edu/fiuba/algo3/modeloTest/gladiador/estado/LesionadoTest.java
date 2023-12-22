package edu.fiuba.algo3.modeloTest.gladiador.estado;

import static org.mockito.Mockito.*;
import edu.fiuba.algo3.modelo.gladiador.estado.Lesionado;
import edu.fiuba.algo3.modelo.gladiador.estado.Normal;
import edu.fiuba.algo3.modelo.gladiador.Gladiador;
import org.junit.jupiter.api.Test;

public class LesionadoTest {

    @Test
    public void testAccionar() {
        // Arrange
        Gladiador gladiador = mock(Gladiador.class);
        Lesionado lesionado = new Lesionado();

        // Act
        lesionado.accionar(gladiador, 5, 1);

        // Assert
        verify(gladiador, times(1)).cambiarEstado(any(Normal.class));
    }
}