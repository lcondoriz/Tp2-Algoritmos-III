package edu.fiuba.algo3.parser;

import edu.fiuba.algo3.casillero.EstrategiaCasillero;
import edu.fiuba.algo3.casillero.equipamiento.Equipamiento;
import edu.fiuba.algo3.casillero.comestibles.Comida;
import edu.fiuba.algo3.casillero.obstaculos.FieraSalvaje;
import edu.fiuba.algo3.casillero.obstaculos.Bacanal;
import edu.fiuba.algo3.casillero.obstaculos.Obstaculo;
import edu.fiuba.algo3.casillero.obstaculos.Lesion;
import edu.fiuba.algo3.juego.Dado;
import edu.fiuba.algo3.casillero.vacio.Camino;
import edu.fiuba.algo3.casillero.vacio.Llegada;
import edu.fiuba.algo3.casillero.vacio.Salida;
import edu.fiuba.algo3.casillero.vacio.Vacio;


public class ParserEstrategiaCasillero implements Parser {
    @Override
    public EstrategiaCasillero parsear(String nombreEstrategia, int posicion){
        EstrategiaCasillero estrategia = new Vacio();
        Dado dado = new Dado(6);
        
        switch (nombreEstrategia) {
            case "Salida":
                estrategia = new Salida();
                break;
            case "Camino":
                estrategia = new Camino();
                break;
            case "Llegada":
                estrategia = new Llegada(posicion);
                break;
            case "Bacanal":
                estrategia = new Obstaculo(new Bacanal(dado));
                break;
            case "Fiera":
                estrategia = new Obstaculo(new FieraSalvaje());
                break;
            case "Lesion":
                estrategia = new Obstaculo(new Lesion());
                break;
            case "Comida":
                estrategia = new Comida();
                break;
            case "Equipamiento":
                estrategia = new Equipamiento();
                break;   
        }
        return estrategia;
    }
}