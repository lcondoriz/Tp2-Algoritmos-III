package edu.fiuba.algo3.entrega_2;

import com.google.gson.JsonObject;
import edu.fiuba.algo3.modelo.parser.TableroConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CasosDeUso2 {
    @Test   // Caso de uso 13
    public void test13verificarElFormatoValidoDelJSONdelMapa() {
        // Arrange
        TableroConstructor tableroConstructor = new TableroConstructor();

        // Act
        assertDoesNotThrow(() -> tableroConstructor.construirTableroDesdeJSON("files/mapa.json"));
    }
    @Test   // Caso de uso 14
    public void text14verificarElFormatoValidoDelJSONdeObstaculosYpremios() {
        // Arrange
        JsonObject jsonCelda = new JsonObject();
        jsonCelda.addProperty("x", 1);
        jsonCelda.addProperty("y", 1);
        jsonCelda.addProperty("tipo", "Salida");
        jsonCelda.addProperty("obstaculo", "");
        jsonCelda.addProperty("premio", "");

        TableroConstructor tableroConstructor = new TableroConstructor();

        // Act
        boolean resultado = tableroConstructor.validarFormatoCelda(jsonCelda);

        // Assert
        Assertions.assertEquals(true, resultado);
    }
}


