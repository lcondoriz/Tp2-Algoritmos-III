package edu.fiuba.algo3.json;

import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.tablero.Tablero;
import edu.fiuba.algo3.tablero.celda.Celda;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.ListIterator;

public class TableroConstructorTest {
    @Test
    public void test01() {
        TableroConstructor tableroConstructor = new TableroConstructor();
        Tablero tablero = tableroConstructor.construirTableroDesdeJSON("files/mapa.json");

        //LinkedList<Celda> celdas = tablero.getCeldas();

        // Obtener un objeto ListIterator para recorrer la lista
//        ListIterator<Celda> iterador = celdas.listIterator();
//        while(iterador.hasNext()){
//            Celda celda = iterador.next();
//            celda.aplicarEfecto(new Gladiador());
//        }
    }

}
