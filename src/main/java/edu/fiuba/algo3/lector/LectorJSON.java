package edu.fiuba.algo3.lector;
import edu.fiuba.algo3.tablero.Casillero;
import edu.fiuba.algo3.tablero.Tablero;
import edu.fiuba.algo3.casillero.EstrategiaCasillero;
import edu.fiuba.algo3.casillero.comestibles.Comida;
import edu.fiuba.algo3.parser.ParserEstrategiaCasillero;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LectorJSON implements Lector {
    @Override
    public void leerArchivo(String pathArchivo, Tablero tablero) {
        try {
            
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File(pathArchivo));

            procesarJsonNode(jsonNode, tablero);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void procesarJsonNode(JsonNode jsonNode, Tablero tablero) {
        JsonNode mapaNode = jsonNode.get("mapa");
        if (mapaNode != null && mapaNode.isObject()) {
            int ancho = mapaNode.get("ancho").asInt();
            int largo = mapaNode.get("largo").asInt();

            tablero.setDimensiones(ancho, largo); // creo el tablero con las dimensiones dadas
        }

        JsonNode caminoNode = jsonNode.get("camino");
        if (caminoNode != null && caminoNode.isObject()) {
            JsonNode celdasNode = caminoNode.get("celdas");
            if (celdasNode != null && celdasNode.isArray()) {
                procesarCeldas(celdasNode, tablero);
            }
        }
    }

    private void procesarCeldas(JsonNode celdasNode, Tablero tablero) {

        ParserEstrategiaCasillero parserEstrategias = new  ParserEstrategiaCasillero();
        int posicion = 0 ; // la primera casilla (Salida) es la posicion 0
        
        for (JsonNode celdaNode : celdasNode) {
            
            //por el momento no se utilizan las coordenadas x e y
            int x = celdaNode.get("x").asInt(); 
            int y = celdaNode.get("y").asInt(); 
            EstrategiaCasillero tipo = parserEstrategias.parsear(celdaNode.get("tipo").asText(), posicion);
            EstrategiaCasillero obstaculo = parserEstrategias.parsear(celdaNode.get("obstaculo").asText(), posicion);
            EstrategiaCasillero premio = parserEstrategias.parsear(celdaNode.get("premio").asText(), posicion);
            
            // se inicializa la lista de estrategias con todas las estrategias del casillero
            List<EstrategiaCasillero> estrategiasCasillero = new ArrayList<>(Arrays.asList(tipo, obstaculo, premio)); 
            // creo el casillero con la posicion y sus estrategias
            Casillero casillero = new Casillero(posicion , estrategiasCasillero);
            // agrego el casillero al tablero
            tablero.agregarCasillero(casillero);
            posicion ++;
        }
    }
}