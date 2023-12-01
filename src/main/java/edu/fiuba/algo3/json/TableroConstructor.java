package edu.fiuba.algo3.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.fiuba.algo3.tablero.Tablero;
import edu.fiuba.algo3.tablero.celda.Camino;
import edu.fiuba.algo3.tablero.celda.Celda;
import edu.fiuba.algo3.tablero.celda.Llegada;
import edu.fiuba.algo3.tablero.celda.Salida;
import edu.fiuba.algo3.tablero.celda.afectable.Afectable;
import edu.fiuba.algo3.tablero.celda.fabrica.FabricaDeAfectables;
import edu.fiuba.algo3.tablero.celda.fabrica.FabricaDeObstaculos;
import edu.fiuba.algo3.tablero.celda.fabrica.FabricaDePremios;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import com.google.gson.JsonElement;

public class TableroConstructor {
    // Constantes para valores permitidos
    public static final String[] TIPOS_CELDA = {"Salida", "Camino", "Llegada"};
    public static final String[] OBSTACULOS_PERMITIDOS = {"", "Bacanal", "Lesion", "Fiera"};
    public static final String[] PREMIOS_PERMITIDOS = {"", "Comida", "Equipamiento"};
    public Tablero construirTableroDesdeJSON(String path) {
        try {
            JsonObject jsonTablero = leerJSON(path);
            validarFormatoMapa(jsonTablero); // Validación antes de construir el tablero
            return construirTablero(jsonTablero);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private void validarFormatoMapa(JsonObject jsonMapa) {
        if (!validarAnchoYlargo(jsonMapa.getAsJsonObject("mapa")) ||
                !validarFormatoCeldas(jsonMapa.getAsJsonObject("camino").getAsJsonArray("celdas"))) {
            throw new IllegalArgumentException("Formato de mapa JSON no válido.");
        }
    }
    public boolean validarAnchoYlargo(JsonObject jsonObject) {
        return jsonObject.has("ancho") && jsonObject.has("largo") &&
                esEnteroPositivo(jsonObject.get("ancho")) && esEnteroPositivo(jsonObject.get("largo"));
    }
    private boolean validarFormatoCeldas(JsonArray jsonCeldas) {
        for (int i = 0; i < jsonCeldas.size(); i++) {
            JsonObject jsonCelda = jsonCeldas.get(i).getAsJsonObject();
            if (!validarFormatoCelda(jsonCelda)) {
                return false;
            }
        }
        return true;
    }
    public boolean validarFormatoCelda(JsonObject jsonCelda) {
        return esEnteroPositivo(jsonCelda.get("x")) &&
                esEnteroPositivo(jsonCelda.get("y")) &&
                validarTipoCelda(jsonCelda) &&
                validarString(jsonCelda, "obstaculo", OBSTACULOS_PERMITIDOS) &&
                validarString(jsonCelda, "premio", PREMIOS_PERMITIDOS);
    }
    public boolean validarTipoCelda(JsonObject jsonObject) {
        String tipo = jsonObject.get("tipo").getAsString();
        return esValorPermitido(tipo, TIPOS_CELDA);
    }
    private boolean validarString(JsonObject jsonObject, String campo, String[] valoresPermitidos) {
        String valor = jsonObject.get(campo).getAsString();
        return esValorPermitido(valor, valoresPermitidos);
    }
    private boolean esEnteroPositivo(JsonElement jsonElement) {
        return jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isNumber() && jsonElement.getAsInt() > 0;
    }
    private boolean esValorPermitido(String valor, String[] valoresPermitidos) {
        for (String permitido : valoresPermitidos) {
            if (permitido.equals(valor)) {
                return true;
            }
        }
        return false;
    }
    private JsonObject leerJSON(String path) throws IOException {
        try (FileReader fileReader = new FileReader(path)) {
            Gson gson = new Gson();
            return gson.fromJson(fileReader, JsonObject.class);
        }
    }
    private Tablero construirTablero(JsonObject jsonTablero) {
        int ancho = obtenerDimension(jsonTablero, "ancho");
        int largo = obtenerDimension(jsonTablero, "largo");

        LinkedList<Celda> celdas = construirCeldas(jsonTablero.getAsJsonObject("camino").getAsJsonArray("celdas"));

        return new Tablero(ancho, largo, celdas);
    }
    private int obtenerDimension(JsonObject jsonTablero, String dimension) {
        return jsonTablero.getAsJsonObject("mapa").get(dimension).getAsInt();
    }
    private LinkedList<Celda> construirCeldas(JsonArray jsonCeldas) {
        LinkedList<Celda> celdas = new LinkedList<>();

        for (int i = 0; i < jsonCeldas.size(); i++) {
            JsonObject jsonCelda = jsonCeldas.get(i).getAsJsonObject();
            Celda celda = crearCelda(i, jsonCelda);

            celdas.add(celda);

            if (i > 0) {
                Celda celdaAnterior = celdas.get(i - 1);
                celdaAnterior.agregarSiguienteCelda(celda);
                celda.agregarCeldaAnterior(celdaAnterior);
            }
        }

        return celdas;
    }
    private Celda crearCelda(int numeracion, JsonObject jsonCelda) {
        int x = jsonCelda.get("x").getAsInt();
        int y = jsonCelda.get("y").getAsInt();
        String tipo = jsonCelda.get("tipo").getAsString();

        Celda celda = construirCeldaBase(x, y, tipo, numeracion);
        agregarAfectables(jsonCelda, celda);

        return celda;
    }
    private Celda construirCeldaBase(int x, int y, String tipo, int numeracion) {
        switch (tipo) {
            case "Salida":
                return new Salida(x, y, tipo, numeracion);
            case "Camino":
                return new Camino(x, y, tipo, numeracion);
            case "Llegada":
                return new Llegada(x, y, tipo, numeracion);
            default:
                throw new IllegalArgumentException("Tipo de celda desconocido: " + tipo);
        }
    }
    private void agregarAfectables(JsonObject jsonCelda, Celda celda) {
        FabricaDeObstaculos fabricaDeObstaculos = new FabricaDeObstaculos();
        FabricaDePremios fabricaDePremios = new FabricaDePremios();

        agregarAfectable(jsonCelda, "obstaculo", fabricaDeObstaculos, celda);
        agregarAfectable(jsonCelda, "premio", fabricaDePremios, celda);
    }
    private void agregarAfectable(JsonObject jsonCelda, String afectableType, FabricaDeAfectables fabrica, Celda celda) {
        String afectable = jsonCelda.get(afectableType).getAsString();
        if (!afectable.isEmpty()) {
            Afectable afectableInstancia = fabrica.crearAfectable(afectable);
            celda.agregarAfectable(afectableInstancia);
        }
    }
}