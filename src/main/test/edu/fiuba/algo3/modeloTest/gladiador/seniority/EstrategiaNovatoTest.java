package edu.fiuba.algo3.modeloTest.gladiador.seniority;
import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.seniority.EstrategiaNovato;
import edu.fiuba.algo3.gladiador.seniority.EstrategiaSemiSenior;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EstrategiaNovatoTest {
    @Test
    public void verificarQueElPlusDeEnergiaSeEstableceCorrectamente() {
        Energia energia = new Energia(20);
        EstrategiaNovato estrategiaNovato = new EstrategiaNovato();
        estrategiaNovato.obtenerPlusEnergia (energia);
        assertEquals(20, energia.obtenerEnergia());
    }
    
    @Test
    public void verificarQueElSeniorityNoIncrementeSiNoLlegoALaRondaOcho() {
        EstrategiaNovato estrategiaNovato = new EstrategiaNovato();
        assertEquals(EstrategiaNovato.class, estrategiaNovato.incrementarSeniority(7).getClass());
    }

    @Test
    public void verificarQueElSeniorityIncrementeSiLlegoALaRondaOcho() {
        EstrategiaNovato estrategiaNovato = new EstrategiaNovato();
        assertEquals(EstrategiaSemiSenior.class, estrategiaNovato.incrementarSeniority(8).getClass());
    }
}
