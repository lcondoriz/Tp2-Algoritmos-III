package edu.fiuba.algo3.modeloTest.gladiador.seniority;
import edu.fiuba.algo3.modelo.gladiador.Energia;
import edu.fiuba.algo3.modelo.gladiador.seniority.Novato;
import edu.fiuba.algo3.modelo.gladiador.seniority.SemiSenior;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NovatoTest {
    @Test
    public void verificarQueElPlusDeEnergiaSeEstableceCorrectamente() {
        Energia energia = new Energia(20);
        Novato novato = new Novato();
        novato.obtenerPlusEnergia (energia);
        assertEquals(20, energia.obtenerEnergia());
    }
    
    @Test
    public void verificarQueElSeniorityNoIncrementeSiNoLlegoALaRondaOcho() {
        Novato novato = new Novato();
        assertEquals(Novato.class, novato.incrementarSeniority(7,null ).getClass());
    }

    @Test
    public void verificarQueElSeniorityIncrementeSiLlegoALaRondaOcho() {
        Novato novato = new Novato();
        assertEquals(SemiSenior.class, novato.incrementarSeniority(8,null ).getClass());
    }
}