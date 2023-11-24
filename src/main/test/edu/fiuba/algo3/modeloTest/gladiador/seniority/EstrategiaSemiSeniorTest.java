package edu.fiuba.algo3.modeloTest.gladiador.seniority;
import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.seniority.EstrategiaSemiSenior;
import edu.fiuba.algo3.gladiador.seniority.EstrategiaSenior;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EstrategiaSemiSeniorTest {
    @Test
    public void verificarQueElPlusDeEnergiaSeEstableceCorrectamente() {
        Energia energia = new Energia(20);
        EstrategiaSemiSenior estrategiasemiSenior = new EstrategiaSemiSenior();
        estrategiasemiSenior.obtenerPlusEnergia (energia);
        assertEquals(25, energia.obtenerEnergia());
    }
    
    @Test
    public void verificarQueElSeniorityNoIncrementeSiNoLlegoALaRondaDoce() {
        EstrategiaSemiSenior estrategiaSemiSenior = new EstrategiaSemiSenior();
        assertEquals(EstrategiaSemiSenior.class, estrategiaSemiSenior.incrementarSeniority(11).getClass());
    }

    @Test
    public void verificarQueElSeniorityIncrementeSiLlegoALaRondaDoce() {
        EstrategiaSemiSenior estrategiaSemiSenior = new EstrategiaSemiSenior();
        assertEquals(EstrategiaSenior.class, estrategiaSemiSenior.incrementarSeniority(12).getClass());
    }
}