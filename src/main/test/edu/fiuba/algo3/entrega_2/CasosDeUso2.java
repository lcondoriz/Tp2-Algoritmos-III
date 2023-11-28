package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.casillero.comestibles.Comida;
import edu.fiuba.algo3.casillero.equipamiento.Casco;
import edu.fiuba.algo3.casillero.equipamiento.Equipamiento;
import edu.fiuba.algo3.casillero.equipamiento.EscudoEspada;
import edu.fiuba.algo3.casillero.equipamiento.Llave;
import edu.fiuba.algo3.casillero.obstaculos.FieraSalvaje;
import edu.fiuba.algo3.casillero.obstaculos.Obstaculo;
import edu.fiuba.algo3.casillero.vacio.Llegada;
import edu.fiuba.algo3.casillero.vacio.Camino;
import edu.fiuba.algo3.exceptions.CantidadTurnosException;
import edu.fiuba.algo3.exceptions.SinEnergiaException;
import edu.fiuba.algo3.gladiador.Energia;
import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.juego.AlgoRoma;
import edu.fiuba.algo3.juego.Dado;
import edu.fiuba.algo3.tablero.Casillero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

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
    public void verificarLaLecturaYLaPosteriorConversionAUnidadesDelModeloDeDominioDelJSONDelMapa() {
        //Arrange
        //Act
        //Assert
    }
    @Test // Caso de uso 15
    public void verificarQueElJuegoSeCreaAcordeAlJSON() {
        //Arrange
        //Act
        //Assert
    }
    @Test // Caso de uso 16
    public void verificarElSistemaDeLogAUtilizar() {
        //Arrange
        //Act
        //Assert
    }
}
