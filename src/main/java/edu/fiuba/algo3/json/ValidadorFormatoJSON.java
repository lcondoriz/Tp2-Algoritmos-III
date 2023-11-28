package edu.fiuba.algo3.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ValidadorFormatoJSON {
    // Constantes para valores permitidos
    public static final String[] TIPOS_CELDA = {"Salida", "Camino", "Llegada"};
    public static final String[] OBSTACULOS_PERMITIDOS = {"", "Bacanal", "Lesion", "Fiera"};
    public static final String[] PREMIOS_PERMITIDOS = {"", "Comida", "Equipamiento"};

    // Métodos de validación públicos
    public static boolean validarFormatoMapa(JsonObject jsonMapa) {
        return validarAnchoYlargo(jsonMapa.getAsJsonObject("mapa")) &&
                validarFormatoCeldas(jsonMapa.getAsJsonObject("camino").getAsJsonArray("celdas"));
    }
    public static boolean validarAnchoYlargo(JsonObject jsonObject) {
        return jsonObject.has("ancho") && jsonObject.has("largo") &&
                esEnteroPositivo(jsonObject.get("ancho")) && esEnteroPositivo(jsonObject.get("largo"));
    }
    public static boolean validarFormatoCeldas(JsonArray jsonCeldas) {
        for (int i = 0; i < jsonCeldas.size(); i++) {
            JsonObject jsonCelda = jsonCeldas.get(i).getAsJsonObject();
            if (!validarFormatoCelda(jsonCelda)) {
                return false;
            }
        }
        return true;
    }
    public static boolean validarFormatoCelda(JsonObject jsonCelda) {
        return esEnteroPositivo(jsonCelda.get("x")) &&
                esEnteroPositivo(jsonCelda.get("y")) &&
                validarTipoCelda(jsonCelda) &&
                validarString(jsonCelda, "obstaculo", OBSTACULOS_PERMITIDOS) &&
                validarString(jsonCelda, "premio", PREMIOS_PERMITIDOS);
    }
    public static boolean validarTipoCelda(JsonObject jsonObject) {
        String tipo = jsonObject.get("tipo").getAsString();
        return esValorPermitido(tipo, TIPOS_CELDA);
    }
    public static boolean validarString(JsonObject jsonObject, String campo, String[] valoresPermitidos) {
        String valor = jsonObject.get(campo).getAsString();
        return esValorPermitido(valor, valoresPermitidos);
    }
    // Métodos de utilidad privados
    private static boolean esEnteroPositivo(JsonElement jsonElement) {
        return jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isNumber() && jsonElement.getAsInt() > 0;
    }
    private static boolean esValorPermitido(String valor, String[] valoresPermitidos) {
        for (String permitido : valoresPermitidos) {
            if (permitido.equals(valor)) {
                return true;
            }
        }
        return false;
    }
}