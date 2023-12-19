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
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import edu.fiuba.algo3.modelo.juego.AlgoRoma;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
public class Interfaz extends Application {

    private Stage primaryStage;
    private Tablero miTablero;
    private AlgoRoma algoRoma;
    private Scene escenaTablero;

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
        //Scene scene = new Scene(grid, 400, 175);
        Scene scene = new Scene(grid,tamanio_X,tamanio_Y);
        primaryStage.setScene(scene);


        /*Label usernameLabel = new Label("Usuario:");
        usernameLabel.setTextFill(Color.LIGHTCYAN);
        usernameLabel.setFont(new Font(25));
        usernameLabel.setBackground(new Background(new BackgroundFill(Color.RED,CornerRadii.EMPTY,new Insets(0,0,0,0))));
        grid.add(usernameLabel, 0, 1);
        */

        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("Introduce el nombre de un usuario");
        grid.add(usernameTextField, 1, 1);

        Button agregarButtom = new Button("Agregar Usuario");

        grid.add(agregarButtom, 2, 1);

        agregarButtom.setOnAction(event -> {
            String username;
            username = usernameTextField.getText();
            if (!username.trim().isEmpty()) {
                algoRoma.agregarJugador(username);
                System.out.println("El usuario " + username + " se agregó correctamente");
                usernameTextField.clear();
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
            tableroVisual.setAlignment(Pos.CENTER);
            vbGrid.getChildren().addAll(tableroVisual);
            vbGrid.setPadding(new Insets(20));
            vbGrid.setAlignment(Pos.CENTER);



            VBox vbJugadoresInfo = new VBox();
            for (Jugador jugador : jugadores) {

                Text nombreJugador =  new Text();
                nombreJugador.setText(jugador.obtenerNombre());
                nombreJugador.setFont(new Font(20));
                nombreJugador.setTextAlignment(TextAlignment.CENTER);
                nombreJugador.setUnderline(true);

                HBox hboxNombre = new HBox(nombreJugador);
                hboxNombre.setAlignment(Pos.CENTER);
                HBox iconoJugador = new HBox();
                VBox contenedorJugador = new VBox(hboxNombre,iconoJugador);
                HBox hBoxJugador = new HBox(contenedorJugador);


                try {
                    String ruta = ICONOS.get(tableroVisual.coloresAsignados().get(jugador));
                            Image image = new Image(new FileInputStream(ruta));
                    iconoJugador.setBackground(new Background(new BackgroundImage(
                            image,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundPosition.DEFAULT,
                            BackgroundSize.DEFAULT
                    )));

                    iconoJugador.setMinHeight(image.getHeight());
                    iconoJugador.setMinWidth(image.getWidth());

                    //iconoJugador.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(2))));
                } catch (FileNotFoundException ex) {
                    System.out.println("No se encontro la imagen");
                }
                hBoxJugador.setBorder(new Border(new BorderStroke(Color.BLUE,BorderStrokeStyle.SOLID,new CornerRadii(20),new BorderWidths(2))));
                hBoxJugador.setBackground(new Background(new BackgroundFill(Color.BEIGE,CornerRadii.EMPTY,new Insets(0))));
                hBoxJugador.setMaxHeight(190);
                hBoxJugador.setMaxWidth(250);

                vbJugadoresInfo.getChildren().add(hBoxJugador);
            }

            vbGrid.getChildren().add(vbJugadoresInfo);
            vbJugadoresInfo.setAlignment(Pos.BOTTOM_LEFT);
            vbJugadoresInfo.setMinHeight(180);
            vbJugadoresInfo.setMinWidth(120);
            vbJugadoresInfo.setSpacing(5);



            //==============FOOTER===============================
            ScrollPane historial = new ScrollPane();
            historial.prefHeight(10);
            historial.prefWidth(10);
            //vbGrid.getChildren().add(historial);


            Button btnJugar1Turno = new Button("Jugar un turno");
            btnJugar1Turno.setMinHeight(50);
            btnJugar1Turno.setMinWidth(130);
            hbDone.getChildren().add(btnJugar1Turno);
            btnJugar1Turno.setAlignment(Pos.CENTER);

            btnJugar1Turno.setOnAction(event -> {
                try{
                    algoRoma.jugar1Turno();
                }catch(PartidaFinalizada ex){
                    //Mostrar pantalla con ganador del juego,nueva escena o un label
                    //refactorizar esto a un eventhandlerFinalizarPartida
                    hbDone.getChildren().remove(btnJugar1Turno);
                    Label ganador = new Label(ex.getMessage());
                    ganador.autosize();
                    ganador.setBorder(new Border(new BorderStroke(Color.RED,BorderStrokeStyle.DASHED,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                    vbRoot.getChildren().add(ganador);
                    vbRoot.setAlignment(Pos.CENTER);
                }
                actualizarTablero(tableroVisual, vbPlayers);



            });


            Button btnGame = new Button("Jugar una ronda");
            btnGame.setMinHeight(50);
            btnGame.setMinWidth(130);
            hbDone.getChildren().add(btnGame);

            btnGame.setAlignment(Pos.CENTER);

            btnGame.setOnAction(event -> {
                try {
                    algoRoma.jugar1Ronda();
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
            Button btnReset = new Button("Reset"); //el boton de reset esta demás. no hacer si qeudan otras cosas por mejorar

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

            hbOptions.getChildren().addAll(btnExit);//, btnReset);//el boton de reset esta demás. no hacer si qeudan otras cosas por mejorar
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
            escenaTablero = new Scene(vbRoot,tamanio_X,tamanio_Y);
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