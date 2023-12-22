package edu.fiuba.algo3.vista;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import edu.fiuba.algo3.modelo.exceptions.PartidaFinalizada;
import edu.fiuba.algo3.modelo.log.Log;
import edu.fiuba.algo3.modelo.exceptions.CantidadJugadoresException;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import edu.fiuba.algo3.modelo.juego.AlgoRoma;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
public class Interfaz extends Application {

    private Stage primaryStage;
    private Tablero miTablero;
    private AlgoRoma algoRoma;
    private Scene escenaTablero;
    private ReproductorSonido reproductorSonido;

    private HashMap<Color,String> ICONOS = new HashMap<>();{
        ICONOS.put(Color.RED,"src/main/java/edu/fiuba/algo3/vista/Jugador/gladiadorRojo.png");
        ICONOS.put(Color.BLUE,"src/main/java/edu/fiuba/algo3/vista/Jugador/gladiadorAzul.png");
        ICONOS.put(Color.GREEN,"src/main/java/edu/fiuba/algo3/vista/Jugador/gladiadorVerde.png");
        ICONOS.put(Color.PURPLE,"src/main/java/edu/fiuba/algo3/vista/Jugador/gladiadorVioleta.png");
        ICONOS.put(Color.BROWN,"src/main/java/edu/fiuba/algo3/vista/Jugador/gladiadorMarron.png");
        ICONOS.put(Color.ORANGE,"src/main/java/edu/fiuba/algo3/vista/Jugador/gladiadorNaranja.png");
    }

    public static void main(String[] args) {
        launch(args);
    }


    public Interfaz(Tablero tablero, AlgoRoma algoRoma) {
        this.miTablero = tablero;
        this.algoRoma = algoRoma;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("AlgoRoma");

        reproductorSonido = new ReproductorSonido();

        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        double tamanio_X = 400;
        double tamanio_Y = 175;
        try {
            Image image = new Image(new FileInputStream("src/main/java/edu/fiuba/algo3/vista/pantallaPrincipal.jpg"));

            HBox hbBackground = new HBox();
            BackgroundImage imageBG = new BackgroundImage(
                    image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT
            );

            Background background = new Background(imageBG);
            hbBackground.setBackground(background);
            grid.setBackground(background);
            tamanio_X = image.getHeight();
            tamanio_Y = image.getWidth();

        }catch(FileNotFoundException ex){
            System.out.println("No se encontro la imagen");
        }
        Scene scene = new Scene(grid,tamanio_X,tamanio_Y);
        primaryStage.setScene(scene);

        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("Introduce el nombre de un usuario");
        grid.add(usernameTextField, 1, 1);

        Button agregarButtom = new Button("Agregar Usuario");

        grid.add(agregarButtom, 2, 1);

        agregarButtom.setOnAction(event -> {
            String username;
            username = usernameTextField.getText();
            if (!username.trim().isEmpty()) {
                reproductorSonido.reproducirSonido(3); //Sonido de agregar usuario exitoso
                algoRoma.agregarJugador(username);
                System.out.println("El usuario " + username + " se agregó correctamente");
                usernameTextField.clear();
            } else {
                System.out.println("El nombre de usuario debe tener al menos un carácter");
                reproductorSonido.reproducirSonido(4); //Sonido de agregar usuario fallido
            }
        });

        Button submitButton = new Button("Comenzar");
        grid.add(submitButton, 1, 3);

        submitButton.setOnAction(event -> {
            try {
                algoRoma.inicializarJuego();
                reproductorSonido.reproducirSonido(2); //Sonido de inicio del tablero
                cargarTablero();
                mostrarTablero();
            }catch (CantidadJugadoresException ex){
                System.err.println(ex.getMessage());
            }
        });

        primaryStage.show();
    }

    public void cargarTablero() {
        // Verificar que miTablero no sea null
        if (miTablero != null) {
            VBox vbRoot = new VBox(),
                    vbGrid = new VBox(),
                    vbFooter = new VBox(),
                    vbPlayers = new VBox(),
                    vbGridInfo = new VBox(),
                    vbHeader = new VBox();
            HBox hbOptions = new HBox(),
                    hbDone = new HBox(),
                    hbGameDetails = new HBox(),
                    hbGameTitle = new HBox();
            //================ HEADER ======================
            Label title = new Label("ALGOROMA: COMBAT GAME");
            title.setFont(new Font(19));
            title.autosize();
            hbGameTitle.getChildren().add(title);
            hbGameTitle.setAlignment(Pos.CENTER);

            List<Jugador> jugadores = algoRoma.obtenerJugadores();
            for (Jugador jugador : jugadores) {
                String energiaJugador = jugador.obtenerNombre()+" tiene: "+ jugador.obtenerEnergia()+" puntos de Energia.";
                Label player = new Label(energiaJugador);
                vbPlayers.getChildren().add(player);
            }
            vbPlayers.setAlignment(Pos.CENTER);

            Label players = new Label("Players: ");
            vbGridInfo.getChildren().add(players);
            vbGridInfo.setAlignment(Pos.CENTER);

            hbGameDetails.getChildren().addAll(vbGridInfo, vbPlayers);
            hbGameDetails.setAlignment(Pos.CENTER);

            vbHeader.getChildren().addAll(hbGameTitle, hbGameDetails);
            vbHeader.setAlignment(Pos.CENTER);

            //===============GRID===============================
            // Crear una instancia de TableroVisual
            TableroVisual tableroVisual = new TableroVisual(miTablero, algoRoma);
            vbGrid.getChildren().addAll(tableroVisual);
            vbGrid.setPadding(new Insets(20));
            vbGrid.setAlignment(Pos.CENTER);
            //==============FOOTER===============================
            ScrollPane historial = new ScrollPane();
            historial.maxHeight(primaryStage.getMaxHeight()/4);
            historial.maxWidth(primaryStage.getMaxWidth());
            vbGrid.getChildren().add(historial);

            Button btnGame = new Button("Jugar un turno");
            btnGame.setMinHeight(50);
            btnGame.setMinWidth(130);
            hbDone.getChildren().add(btnGame);


            btnGame.setOnAction(event -> {
                try {
                    algoRoma.jugar1Ronda();
                    reproductorSonido.reproducirSonido(0); //Sonido de dados
                    Log log = algoRoma.getLog();
                    String[] lineas = new String[0];
                    try {
                        lineas = log.getLines();
                    }catch(IOException ex){
                        //hacer alog}
                        System.out.println("algo");
                    }
                    String contenido ="";
                    for (String linea : lineas) {
                        //Text textLinea = new Text(linea);
                        contenido = contenido+"\n"+linea;
                    }
                    historial.setContent(new Text(contenido));


                }catch (PartidaFinalizada ex){
                    reproductorSonido.reproducirSonido(1); //Sonido de victoria
                    //Mostrar pantalla con ganador del juego,nueva escena o un label
                    hbDone.getChildren().remove(btnGame);
                    Label ganador = new Label(ex.getMessage());
                    ganador.autosize();
                    ganador.setBorder(new Border(new BorderStroke(Color.RED,BorderStrokeStyle.DASHED,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                    vbRoot.getChildren().add(ganador);
                    vbRoot.setAlignment(Pos.CENTER);
                }
                actualizarTablero(tableroVisual, vbPlayers);
            });
            btnGame.setPadding(new Insets(5));


            hbDone.setPadding(new Insets(5));
            hbDone.setAlignment(Pos.CENTER);

            Button btnExit = new Button("Exit");
            Button btnReset = new Button("Reset");

            btnExit.setMinHeight(25);
            btnExit.setMinWidth(60);
            btnExit.setPadding(new Insets(10));
            btnExit.setOnAction(event ->{
                primaryStage.close();
            });


            btnReset.setMinHeight(25);
            btnReset.setMinWidth(60);
            btnReset.setPadding(new Insets(10));
            btnReset.setOnAction(event -> reset());

            hbOptions.getChildren().addAll(btnExit, btnReset);
            hbOptions.setPadding(new Insets(5));
            hbOptions.setAlignment(Pos.CENTER);

            vbFooter.getChildren().addAll(hbDone, hbOptions);

            vbRoot.getChildren().addAll(vbHeader, vbGrid, vbFooter);

            double tamanio_X = 400;
            double tamanio_Y = 175;
            try {
                Image image = new Image(new FileInputStream("src/main/java/edu/fiuba/algo3/vista/fondoDelJuego.jpg"));

                HBox hbBackground = new HBox();
                BackgroundImage imageBG = new BackgroundImage(
                        image,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT
                );

                Background background = new Background(imageBG);
                hbBackground.setBackground(background);
                hbBackground.setAlignment(Pos.CENTER);
                vbRoot.setBackground(background);
                tamanio_X = image.getHeight();
                tamanio_Y = image.getWidth();

            }catch(FileNotFoundException ex){
                System.out.println("No se encontro la imagen");
            }


            //tableroVisual.add(jugar1Button, miTablero.obtenerAncho() , miTablero.obtenerLargo());
            // Crear una nueva escena con el tablero del juego
            escenaTablero = new Scene(vbRoot);
            // Obtener el GridPane de la escena y configurar la alineación

        } else {
            System.err.println("El tablero no se ha inicializado correctamente.");
        }
    }
    public void mostrarTablero() {
        primaryStage.setScene(escenaTablero);
    }
    public void reset() {};
    public void exit() {};
    public void actualizarTablero(TableroVisual tableroVisual, VBox players) {
        tableroVisual.actualizarContenido(algoRoma);
        players.getChildren().clear();
        List<Jugador> jugadores = algoRoma.obtenerJugadores();
        for (Jugador jugador : jugadores) {
            String energiaJugador = jugador.obtenerNombre() +" tiene: "+ jugador.obtenerEnergia()+" puntos de Energia.";
            Label player = new Label(energiaJugador);
            players.getChildren().add(player);
        }
    }
}