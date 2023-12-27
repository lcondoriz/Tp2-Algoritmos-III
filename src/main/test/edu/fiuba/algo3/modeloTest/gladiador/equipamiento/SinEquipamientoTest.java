package edu.fiuba.algo3.modeloTest.gladiador.equipamiento;

import edu.fiuba.algo3.modelo.gladiador.Gladiador;
import edu.fiuba.algo3.modelo.gladiador.equipamiento.SinEquipamiento;
import edu.fiuba.algo3.modelo.gladiador.mejorador.Mejorador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SinEquipamientoTest {
    private SinEquipamiento sinEquipamiento;
    private Gladiador gladiadorMock;
    private Mejorador mejorador;

    @BeforeEach
    public void setUp() {
        sinEquipamiento = new SinEquipamiento();
        gladiadorMock = mock(Gladiador.class);
        mejorador = mock(Mejorador.class);
    }

    @Test
    public void mejorarEquipamientoDebeLlamarObtenerSiguienteMejora() {
        sinEquipamiento.mejorarEquipamiento(mejorador);
        verify(mejorador).obtenerSeguienteMejora(sinEquipamiento);
    }
    @Test
    public void danioPorFieraSalvajeDebeDecrementGladiatorEnergy() {
        sinEquipamiento.danioPorFieraSalvaje(gladiadorMock);
        verify(gladiadorMock).decrementarEnergia(20);
    }
}
