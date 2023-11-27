package edu.fiuba.algo3.lector;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class LectorJSON implements Lector {
    @Override
    public void leerArchivo(String nombreArchivo) {
        try {
            
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File(nombreArchivo));

            procesarJsonNode(jsonNode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void procesarJsonNode(JsonNode jsonNode) {
        
    }
}