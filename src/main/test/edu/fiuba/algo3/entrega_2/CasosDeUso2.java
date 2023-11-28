package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.casillero.EstrategiaCasillero;
import edu.fiuba.algo3.casillero.comestibles.Comida;
import edu.fiuba.algo3.casillero.equipamiento.Casco;
import edu.fiuba.algo3.casillero.equipamiento.Equipamiento;
import edu.fiuba.algo3.casillero.equipamiento.EscudoEspada;
import edu.fiuba.algo3.casillero.equipamiento.Llave;
import edu.fiuba.algo3.casillero.obstaculos.FieraSalvaje;
import edu.fiuba.algo3.casillero.obstaculos.Lesion;
import edu.fiuba.algo3.casillero.obstaculos.Bacanal;
import edu.fiuba.algo3.casillero.obstaculos.Obstaculo;
import edu.fiuba.algo3.casillero.vacio.Llegada;
import edu.fiuba.algo3.casillero.vacio.Camino;
import edu.fiuba.algo3.casillero.vacio.Vacio;
import edu.fiuba.algo3.casillero.vacio.Salida;
import edu.fiuba.algo3.juego.Dado;
import edu.fiuba.algo3.tablero.Casillero;
import edu.fiuba.algo3.tablero.Coordenadas;
import edu.fiuba.algo3.tablero.Tablero;
import edu.fiuba.algo3.lector.LectorJSON;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CasosDeUso2 {

    @Test // Caso de uso 13
    public void verificarElFormatoValidoDelJSONDelMapa() {
        // Arrange
        File jsonMapa = new File("src\\main\\test\\edu\\fiuba\\algo3\\entrega_2\\mapatest.json");

        // Act
        boolean isValid = true;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonMapa);

            if (rootNode.has("mapa")) {
                JsonNode mapaNode = rootNode.get("mapa");
                if (mapaNode.has("ancho") && mapaNode.has("largo")) {
                    JsonNode celdasNode = rootNode.get("camino").get("celdas");
                    for (JsonNode celdaNode : celdasNode) {
                        if (!(celdaNode.has("x") && celdaNode.has("y") && celdaNode.has("tipo") && celdaNode.has("obstaculo") && celdaNode.has("premio"))) {
                            isValid = false;
                            break; 
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }
        // Assert
        assertTrue(isValid);
    }
    @Test // Caso de uso 14
    public void verificarLaLecturaYLaPosteriorConversionAUnidadesDelModeloDeDominioDelJSON() {
        //Arrange
        LectorJSON lector = new LectorJSON();
        Tablero tablero = new Tablero(1, 1);
        Tablero tableroEsperado = new Tablero (7 , 2);
        Casillero casillero1 = new Casillero(0, new ArrayList<>() {{add(new Salida());add(new Vacio());add(new Vacio());}}, new Coordenadas(1, 7));
        Casillero casillero2 = new Casillero(1, new ArrayList<>() {{add(new Camino());add(new Obstaculo(new FieraSalvaje()));add(new Comida());}}, new Coordenadas(2, 7)); 
        Casillero casillero3 = new Casillero(2, new ArrayList<>() {{add(new Camino());add(new Obstaculo(new Bacanal(new Dado(6))));add(new Equipamiento());}}, new Coordenadas(2, 6));
        Casillero casillero4 = new Casillero(3, new ArrayList<>() {{add(new Llegada(3));add(new Obstaculo(new Lesion()));add(new Vacio());}}, new Coordenadas(2, 5));   
        tableroEsperado.agregarCasillero(casillero1);
        tableroEsperado.agregarCasillero(casillero2);
        tableroEsperado.agregarCasillero(casillero3);
        tableroEsperado.agregarCasillero(casillero4);
        //Act
        lector.leerArchivo("src\\main\\test\\edu\\fiuba\\algo3\\entrega_2\\mapatest.json", tablero);

        //Assert
        //Verificar que tengan las mismas dimensiones
        assertEquals(tablero.obtenerAncho(), tableroEsperado.obtenerAncho());
        assertEquals(tablero.obtenerLargo(), tableroEsperado.obtenerLargo());
        for (int i = 0; i < tablero.cantidadCasilleros(); i++) {
            Casillero casilleroEnTablero = tablero.obtenerCasillero(i);
            Casillero casilleroEnTableroEsperado = tableroEsperado.obtenerCasillero(i);

            List<EstrategiaCasillero> estrategiasEnTablero = casilleroEnTablero.obtenerEstrategiasCasillero();
            List<EstrategiaCasillero> estrategiasEsperadas = casilleroEnTableroEsperado.obtenerEstrategiasCasillero();

            // Verificar que las listas de estrategiasCasillero tengan la misma longitud
            assertEquals(casilleroEnTableroEsperado.obtenerCoordenadas().obtenerCoordenadaX(), casilleroEnTablero.obtenerCoordenadas().obtenerCoordenadaX());
            assertEquals(casilleroEnTableroEsperado.obtenerCoordenadas().obtenerCoordenadaY(), casilleroEnTablero.obtenerCoordenadas().obtenerCoordenadaY());
            assertEquals(estrategiasEsperadas.size(), estrategiasEnTablero.size());

            // Iterar sobre las estrategias y verificar que tengan las mismas estrategias en el mismo orden
            for (int j = 0; j < estrategiasEsperadas.size(); j++) {
                assertEquals(estrategiasEsperadas.get(j).getClass(), estrategiasEnTablero.get(j).getClass());
            }
        }
        
    }
    @Test // Caso de uso 15
    public void verificarElSistemaDeLogAUtilizar() {
        //Arrange
        //Act
        //Assert
    }
}
