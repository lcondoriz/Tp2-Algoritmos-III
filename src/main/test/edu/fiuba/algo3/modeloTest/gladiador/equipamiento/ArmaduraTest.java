package edu.fiuba.algo3.modeloTest.gladiador.equipamiento;

import edu.fiuba.algo3.modelo.gladiador.Gladiador;
import edu.fiuba.algo3.modelo.gladiador.equipamiento.Armadura;
import edu.fiuba.algo3.modelo.gladiador.mejorador.Mejorador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ArmaduraTest {
    private static final int DANO_FIERA_ARMADURA = 10;
    private Armadura armadura;
    private Gladiador gladiadorMock;
    private Mejorador mejoradorMock;

    @BeforeEach
    public void setUp() {
        armadura = new Armadura();
        gladiadorMock = mock(Gladiador.class);
        mejoradorMock = mock(Mejorador.class);
    }

    @Test
    public void mejorarEquipamientoDebeLlamarObtenerSiguienteMejora() {
        armadura.mejorarEquipamiento(mejoradorMock);
        verify(mejoradorMock).obtenerSeguienteMejora(armadura);
    }

    @Test
    public void danioPorFieraSalvajeDebeDecrementGladiatorEnergy() {
        armadura.danioPorFieraSalvaje(gladiadorMock);
        verify(gladiadorMock).decrementarEnergia(DANO_FIERA_ARMADURA);
    }
}
