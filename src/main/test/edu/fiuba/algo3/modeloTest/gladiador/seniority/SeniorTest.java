package edu.fiuba.algo3.modeloTest.gladiador.seniority;
import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.seniority.Senior;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeniorTest {
    @Test
    public void verificarQueElPlusDeEnergiaSeEstableceCorrectamente() {
        Energia energia = new Energia(20);
        Senior senior = new Senior();
        senior.obtenerPlusEnergia (energia);
        assertEquals(30, energia.obtenerEnergia());
    }
    
    @Test
    public void verificarQueElSeniorityNoIncremente() {
        Senior senior = new Senior();
        assertEquals(Senior.class, senior.incrementarSeniority(8).getClass());
        assertEquals(Senior.class, senior.incrementarSeniority(12).getClass());
    }
}
