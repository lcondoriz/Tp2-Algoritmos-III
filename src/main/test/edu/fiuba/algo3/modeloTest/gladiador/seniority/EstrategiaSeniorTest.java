package edu.fiuba.algo3.modeloTest.gladiador.seniority;
import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.seniority.EstrategiaSenior;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EstrategiaSeniorTest {
    @Test
    public void verificarQueElPlusDeEnergiaSeEstableceCorrectamente() {
        Energia energia = new Energia(20);
        EstrategiaSenior estrategiaSenior = new EstrategiaSenior();
        estrategiaSenior.obtenerPlusEnergia (energia);
        assertEquals(30, energia.obtenerEnergia());
    }
    
    @Test
    public void verificarQueElSeniorityNoIncremente() {
        EstrategiaSenior estrategiaSenior = new EstrategiaSenior();
        assertEquals(EstrategiaSenior.class, estrategiaSenior.incrementarSeniority(8).getClass());
        assertEquals(EstrategiaSenior.class, estrategiaSenior.incrementarSeniority(12).getClass());
    }
}