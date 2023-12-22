package edu.fiuba.algo3.modeloTest.gladiador.seniority;
import edu.fiuba.algo3.modelo.gladiador.Energia;
import edu.fiuba.algo3.modelo.gladiador.seniority.SemiSenior;
import edu.fiuba.algo3.modelo.gladiador.seniority.Senior;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SemiSeniorTest {
    @Test
    public void verificarQueElPlusDeEnergiaSeEstableceCorrectamente() {
        Energia energia = new Energia(20);
        SemiSenior semiSenior = new SemiSenior();
        semiSenior.obtenerPlusEnergia (energia);
        assertEquals(25, energia.obtenerEnergia());
    }
    
    @Test
    public void verificarQueElSeniorityNoIncrementeSiNoLlegoALaRondaDoce() {
        SemiSenior semiSenior = new SemiSenior();
        assertEquals(SemiSenior.class, semiSenior.incrementarSeniority(11,null ).getClass());
    }

    @Test
    public void verificarQueElSeniorityIncrementeSiLlegoALaRondaDoce() {
        SemiSenior semiSenior = new SemiSenior();
        assertEquals(Senior.class, semiSenior.incrementarSeniority(12,null ).getClass());
    }
}
