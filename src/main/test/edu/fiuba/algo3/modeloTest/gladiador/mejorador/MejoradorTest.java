package edu.fiuba.algo3.modeloTest.gladiador.mejorador;


import edu.fiuba.algo3.modelo.gladiador.equipamiento.*;
import edu.fiuba.algo3.modelo.gladiador.equipamiento.SinEquipamiento;
import edu.fiuba.algo3.modelo.gladiador.mejorador.Mejorador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MejoradorTest {
    @Test
    public void testObtenerSiguienteMejoraSinEquipamiento() {
        Mejorador mejorador = new Mejorador();
        SinEquipamiento sinEquipamiento = new SinEquipamiento();
        assertTrue(mejorador.obtenerSeguienteMejora(sinEquipamiento) instanceof Casco);
    }

    @Test
    public void testObtenerSiguienteMejoraCasco() {
        Mejorador mejorador = new Mejorador();
        Casco casco = new Casco();
        assertTrue(mejorador.obtenerSeguienteMejora(casco) instanceof Armadura);
    }

    @Test
    public void testObtenerSiguienteMejoraArmadura() {
        Mejorador mejorador = new Mejorador();
        Armadura armadura = new Armadura();
        assertTrue(mejorador.obtenerSeguienteMejora(armadura) instanceof EscudoEspada);
    }

    @Test
    public void testObtenerSiguienteMejoraEscudoEspada() {
        Mejorador mejorador = new Mejorador();
        EscudoEspada escudoEspada = new EscudoEspada();
        assertTrue(mejorador.obtenerSeguienteMejora(escudoEspada) instanceof Llave);
    }

    @Test
    public void testObtenerSiguienteMejoraLlave() {
        Mejorador mejorador = new Mejorador();
        Llave llave = new Llave();
        assertTrue(mejorador.obtenerSeguienteMejora(llave) instanceof Llave);
    }
}