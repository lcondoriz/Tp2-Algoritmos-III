package edu.fiuba.algo3.modeloTest.parser;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import edu.fiuba.algo3.modelo.parser.TableroConstructor;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TableroConstructorTest {
    @Test
    public void testValidarConstruirTableroDesdeJSON() {
        // Arrange
        TableroConstructor tableroConstructor = new TableroConstructor();

        // Act
        assertDoesNotThrow(() -> tableroConstructor.construirTableroDesdeJSON("files/mapa.json"));
    }
    @Test
    public void testValidarAnchoYlargoNumerosPositivos() {
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
    public void testValidarAnchoYlargoNumerosNegativos() {
        // Arrange
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("ancho", -10);
        jsonObject.addProperty("largo", -10);

        TableroConstructor tableroConstructor = new TableroConstructor();

        // Act
        boolean resultado = tableroConstructor.validarAnchoYlargo(jsonObject);

        // Assert
        assertEquals(false, resultado);
    }
    @Test
    public void testValidarFormatoCelda() {
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
    public void testValidarFormatoCeldaConObstaculoInvalido() {
        // Arrange
        JsonObject jsonCelda = new JsonObject();
        jsonCelda.addProperty("x", 1);
        jsonCelda.addProperty("y", 1);
        jsonCelda.addProperty("tipo", "Salida");
        jsonCelda.addProperty("obstaculo", "ObstaculoInvalido");
        jsonCelda.addProperty("premio", "");

        TableroConstructor tableroConstructor = new TableroConstructor();

        // Act
        boolean resultado = tableroConstructor.validarFormatoCelda(jsonCelda);

        // Assert
        assertEquals(false, resultado);
    }
    @Test
    public void testValidarTipoCelda() {
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
    @Test
    public void testValidarFormatoMapa() throws IOException {
        // Arrange
        String path = "files/mapaTest.json";
        FileReader fileReader = new FileReader(path);
        Gson gson = new Gson();
        JsonObject jsonTablero = gson.fromJson(fileReader, JsonObject.class);

        // Act
        // Cambiar el ancho a -10 para que falle la validación.
        jsonTablero.getAsJsonObject("mapa").addProperty("ancho", -10);

        // Guardar el archivo modificado
        try (FileWriter fileWriter = new FileWriter(path)) {
            gson.toJson(jsonTablero, fileWriter);
        }

        TableroConstructor tableroConstructor = new TableroConstructor();

        // Assert
        assertThrows(IllegalArgumentException.class, () -> tableroConstructor.construirTableroDesdeJSON("files/mapaTest.json"));

        // Volver a dejar el archivo como estaba
        jsonTablero.getAsJsonObject("mapa").addProperty("ancho", 10);
        try (FileWriter fileWriter = new FileWriter(path)) {
            gson.toJson(jsonTablero, fileWriter);
        }
    }
}


