package edu.fiuba.algo3.modeloTest.gladiador.equipamiento;

import edu.fiuba.algo3.modelo.gladiador.Gladiador;
import edu.fiuba.algo3.modelo.gladiador.equipamiento.Llave;
import edu.fiuba.algo3.modelo.gladiador.mejorador.Mejorador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LlaveTest {
    private static final int DANO_FIERA_LLAVE = 0;
    private Llave llave;
    private Gladiador gladiadorMock;
    private Mejorador mejoradorMock;

    @BeforeEach
    public void setUp() {
        llave = new Llave();
        gladiadorMock = mock(Gladiador.class);
        mejoradorMock = mock(Mejorador.class);
    }

    @Test
    public void mejorarEquipamientoDebeLlamarObtenerSiguienteMejora() {
        llave.mejorarEquipamiento(mejoradorMock);
        verify(mejoradorMock).obtenerSeguienteMejora(llave);
    }

    @Test
    public void danioPorFieraSalvajeDebeDecrementGladiatorEnergy() {
        llave.danioPorFieraSalvaje(gladiadorMock);
        verify(gladiadorMock).decrementarEnergia(DANO_FIERA_LLAVE);
    }
}
