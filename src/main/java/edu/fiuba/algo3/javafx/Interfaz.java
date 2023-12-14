package edu.fiuba.algo3.javafx;

import java.util.List;

import edu.fiuba.algo3.exceptions.PartidaFinalizada;
import javafx.collections.FXCollections;
import edu.fiuba.algo3.exceptions.CantidadJugadoresException;
import edu.fiuba.algo3.javafx.TableroVisual;
import edu.fiuba.algo3.json.TableroConstructor;
import edu.fiuba.algo3.juego.Jugador;
import edu.fiuba.algo3.tablero.Tablero;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import edu.fiuba.algo3.juego.AlgoRoma;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
public class Interfaz extends Application {

    private Stage primaryStage;
    private Tablero miTablero;
    private AlgoRoma algoRoma;
    private Scene escenaTablero;

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

        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        double tamanio_X = 400;
        double tamanio_Y = 175;
        try {
            Image image = new Image(new FileInputStream("src/main/java/edu/fiuba/algo3/javafx/pantallaPrincipal.jpg"));
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
        //Scene scene = new Scene(grid, 400, 175);
        Scene scene = new Scene(grid,tamanio_X,tamanio_Y);
        primaryStage.setScene(scene);


        Label usernameLabel = new Label("Usuario:");
        usernameLabel.setTextFill(Color.GREEN);
        usernameLabel.setFont(new Font(20));
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
            /*if (algoRoma.obtenerJugadores().size() >= 2) {
                // Llamar a un método para cambiar a la escena del tablero del juego
                algoRoma.inicializarJuego();
                cargarTablero();
                mostrarTablero();
            } else {
                System.err.println("Faltan jugadores antes de iniciar el juego");
            }*/
            try {
                algoRoma.inicializarJuego();
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
            Button btnGame = new Button("Jugar un turno");
            btnGame.setMinHeight(50);
            btnGame.setMinWidth(130);
            hbDone.getChildren().add(btnGame);
            btnGame.setOnAction(event -> {
                try {
                    algoRoma.jugar1Ronda();
                }catch (PartidaFinalizada ex){
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