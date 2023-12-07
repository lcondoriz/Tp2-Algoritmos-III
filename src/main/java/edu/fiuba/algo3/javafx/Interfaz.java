package edu.fiuba.algo3.javafx;

import java.util.List;

import edu.fiuba.algo3.javafx.TableroVisual;
import edu.fiuba.algo3.json.TableroConstructor;
import edu.fiuba.algo3.juego.Jugador;
import edu.fiuba.algo3.tablero.Tablero;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import edu.fiuba.algo3.juego.AlgoRoma;

public class Interfaz extends Application {

    private Stage primaryStage;
    private Tablero miTablero;
    private AlgoRoma algoRoma;

    public static void main(String[] args) {
        launch(args);
    }

    public Interfaz() {
    }

    public Interfaz(Tablero tablero, AlgoRoma algoRoma) {
        this.miTablero = tablero;
        this.algoRoma = algoRoma;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("AlgoRoma");

        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 400, 175);
        primaryStage.setScene(scene);

        Label usernameLabel = new Label("Usuario:");
        grid.add(usernameLabel, 0, 1);

        TextField usernameTextField = new TextField();
        grid.add(usernameTextField, 1, 1);

        Button agregarButtom = new Button("Agregar Usuario");
        grid.add(agregarButtom, 2, 1);

        agregarButtom.setOnAction(event -> {
            String username;
            username = usernameTextField.getText();
            if (!username.trim().isEmpty()) {
                algoRoma.agregarJugador(username);
                System.out.println("El usuario " + username + " se agregó correctamente");
            } else {
                System.out.println("El nombre de usuario debe tener al menos un carácter");
            }
        });

        Button submitButton = new Button("Comenzar");
        grid.add(submitButton, 1, 3);

        submitButton.setOnAction(event -> {
            if (algoRoma.obtenerJugadores().size() > 0) {
                // Llamar a un método para cambiar a la escena del tablero del juego
                algoRoma.inicializarJuego();
                mostrarTablero();
            } else {
                System.err.println("No se ha inicializado ningún jugador");
            }
        });

        primaryStage.show();
    }

    public void mostrarTablero() {
        // Verificar que miTablero no sea null
        if (miTablero != null) {
            // Crear una instancia de TableroVisual
            TableroVisual tableroVisual = new TableroVisual(miTablero, algoRoma);

            Button jugar1Button = new Button("Jugar 1 Ronda");
            jugar1Button.setOnAction(event -> {
                algoRoma.jugar1Ronda();
                actualizarTablero(tableroVisual);
            });

            tableroVisual.add(jugar1Button, 11, 9);
            // Crear una nueva escena con el tablero del juego
            Scene tableroScene = new Scene(tableroVisual, 800, 600);
            // Obtener el GridPane de la escena y configurar la alineación
            GridPane tableroGrid = (GridPane) tableroScene.getRoot();
            tableroGrid.setAlignment(javafx.geometry.Pos.CENTER);

            // Cambiar a la nueva escena
            primaryStage.setScene(tableroScene);
        } else {
            System.err.println("El tablero no se ha inicializado correctamente.");
        }
    }

    public void actualizarTablero(TableroVisual tableroVisual) {
        tableroVisual.actualizarContenido(algoRoma);
    }
}