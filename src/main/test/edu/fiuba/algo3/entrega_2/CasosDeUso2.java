package edu.fiuba.algo3.entrega_2;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.fiuba.algo3.json.ValidadorFormatoJSON;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class CasosDeUso2 {
    @Test   // Caso de uso 13
    public void test13verificarElFormatoValidoDelJSONdelMapa() throws FileNotFoundException {
        String path = "files/mapa.json";

        Gson gson = new Gson();
        FileReader fileReader = new FileReader(path);
        JsonObject jsonMapa = gson.fromJson(fileReader, JsonObject.class);

        Assertions.assertTrue(ValidadorFormatoJSON.validarFormatoMapa(jsonMapa));
    }

    @Test   // Caso de uso 14
    public void text14verificarElFormatoValidoDelJSONdeObstaculosYpremios() throws FileNotFoundException {
        // Arrange
        String path = "files/mapa.json";

        Gson gson = new Gson();
        FileReader fileReader = new FileReader(path);
        JsonObject jsonMapa = gson.fromJson(fileReader, JsonObject.class);

        // obtenemos las celdas
        JsonArray jsonCeldas = jsonMapa.getAsJsonObject("camino").getAsJsonArray("celdas");

        Assertions.assertTrue(ValidadorFormatoJSON.validarFormatoCeldas(jsonCeldas));
    }
    @Test  // Caso de uso 15
    public void test15verificarLaLecturaYPosteriorConversionAUnidadesDelModeloDeDominioDelJSONdeEnemigos() {

    }
}


