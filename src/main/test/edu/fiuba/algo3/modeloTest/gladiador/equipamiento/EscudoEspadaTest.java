package edu.fiuba.algo3.modeloTest.gladiador.equipamiento;

import edu.fiuba.algo3.modelo.gladiador.Gladiador;
import edu.fiuba.algo3.modelo.gladiador.equipamiento.*;
import edu.fiuba.algo3.modelo.gladiador.mejorador.Mejorador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EscudoEspadaTest {
    private static final int DANO_FIERA_ESCUDO_ESPADA = 2;
    private EscudoEspada escudoEspada;
    private Gladiador gladiadorMock;
    private Mejorador mejoradorMock;

    @BeforeEach
    public void setUp() {
        escudoEspada = new EscudoEspada();
        gladiadorMock = mock(Gladiador.class);
        mejoradorMock = mock(Mejorador.class);
    }

    @Test
    public void mejorarEquipamientoDebeLlamarObtenerSiguienteMejora() {
        escudoEspada.mejorarEquipamiento(mejoradorMock);
        verify(mejoradorMock).obtenerSeguienteMejora(escudoEspada);
    }

    @Test
    public void danioPorFieraSalvajeDebeDecrementGladiatorEnergy() {
        escudoEspada.danioPorFieraSalvaje(gladiadorMock);
        verify(gladiadorMock).decrementarEnergia(DANO_FIERA_ESCUDO_ESPADA);
    }
}
