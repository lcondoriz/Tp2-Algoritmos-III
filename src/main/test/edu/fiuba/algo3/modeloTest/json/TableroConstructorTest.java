package edu.fiuba.algo3.modeloTest.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.fiuba.algo3.gladiador.Gladiador;
import edu.fiuba.algo3.json.TableroConstructor;
import edu.fiuba.algo3.tablero.Tablero;
import edu.fiuba.algo3.tablero.celda.Celda;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableroConstructorTest {
    @Test
    public void test01validarConstruirTableroDesdeJSON() {
        // Arrange
        TableroConstructor tableroConstructor = new TableroConstructor();

        // Act
        assertDoesNotThrow(() -> tableroConstructor.construirTableroDesdeJSON("files/mapa.json"));
    }
    @Test
    public void test02validarAnchoYlargoNumerosPositivos() {
        // Arrange
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("ancho", 10);
        jsonObject.addProperty("largo", 10);

        TableroConstructor tableroConstructor = new TableroConstructor();

        // Act
        boolean resultado = tableroConstructor.validarAnchoYlargo(jsonObject);

        // Assert
        assertEquals(true, resultado);
    }
    @Test
    public void test03validarFormatoCelda() {
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
        assertEquals(true, resultado);

    }
    @Test
    public void test03validarTipoCelda() {
        // Arrange
        JsonObject jsonCelda = new JsonObject();
        jsonCelda.addProperty("x", 1);
        jsonCelda.addProperty("y", 1);
        jsonCelda.addProperty("tipo", "Salida");
        jsonCelda.addProperty("obstaculo", "");
        jsonCelda.addProperty("premio", "");

        TableroConstructor tableroConstructor = new TableroConstructor();

        // Act
        boolean resultado = tableroConstructor.validarTipoCelda(jsonCelda);

        // Assert
        assertEquals(true, resultado);
    }
}

