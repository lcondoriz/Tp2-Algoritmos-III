package edu.fiuba.algo3;

import edu.fiuba.algo3.juego.AlgoRoma;
import edu.fiuba.algo3.juego.Dado;
import java.security.AlgorithmConstraints;

import edu.fiuba.algo3.javafx.Interfaz;
import edu.fiuba.algo3.json.TableroConstructor;
import edu.fiuba.algo3.tablero.Tablero;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        String path = "files/mapa.json";

        TableroConstructor constructor = new TableroConstructor();
        Tablero miTablero = constructor.construirTableroDesdeJSON(path);
        AlgoRoma algoRoma = new AlgoRoma(new Dado());
        algoRoma.cargarTablero(path);
        Interfaz interfaz = new Interfaz(miTablero, algoRoma);
        interfaz.start(primaryStage);
    }
}
