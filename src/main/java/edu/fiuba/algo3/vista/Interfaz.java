package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.exceptions.CantidadJugadoresException;
import edu.fiuba.algo3.modelo.exceptions.PartidaFinalizada;
import edu.fiuba.algo3.modelo.gladiador.Gladiador;
import edu.fiuba.algo3.modelo.gladiador.equipamiento.Equipable;
import edu.fiuba.algo3.modelo.juego.AlgoRoma;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.log.Log;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
public class Interfaz extends Application {

    private Stage primaryStage;
    private Tablero miTablero;
    private AlgoRoma algoRoma;
    private ReproductorSonido reproductorSonido;

    private HashMap<Color,String> ICONOS = new HashMap<>();{
        ICONOS.put(Color.RED,"src/main/java/edu/fiuba/algo3/vista/Jugador/gladiadorRojo.png");
        ICONOS.put(Color.BLUE,"src/main/java/edu/fiuba/algo3/vista/Jugador/gladiadorAzul.png");
        ICONOS.put(Color.GREEN,"src/main/java/edu/fiuba/algo3/vista/Jugador/gladiadorVerde.png");
        ICONOS.put(Color.PURPLE,"src/main/java/edu/fiuba/algo3/vista/Jugador/gladiadorVioleta.png");
        ICONOS.put(Color.BROWN,"src/main/java/edu/fiuba/algo3/vista/Jugador/gladiadorMarron.png");
        ICONOS.put(Color.ORANGE,"src/main/java/edu/fiuba/algo3/vista/Jugador/gladiadorNaranja.png");
    }

    private LinkedHashMap<String,String> EQUIPAMIENTO = new LinkedHashMap<>();{
        EQUIPAMIENTO.put("Casco","src/main/java/edu/fiuba/algo3/vista/Equipamiento/casco.png");
        EQUIPAMIENTO.put("Armadura","src/main/java/edu/fiuba/algo3/vista/Equipamiento/armadura.png");
        EQUIPAMIENTO.put("EscudoEspada","src/main/java/edu/fiuba/algo3/vista/Equipamiento/escudoYespada.png");
        EQUIPAMIENTO.put("Llave","src/main/java/edu/fiuba/algo3/vista/Equipamiento/llave.png");
    }

    public static void main(String[] args) {
        launch(args);
    }


    public Interfaz(Tablero tablero, AlgoRoma algoRoma) {
        this.miTablero = tablero;
        this.algoRoma = algoRoma;
        this.reproductorSonido = new ReproductorSonido();
    }

    public void pantallaInicial(Stage primaryStage) {
        primaryStage.setTitle("AlgoRoma");
        primaryStage.setResizable(false);
        double tamanio_X = 400;
        double tamanio_Y = 400;

        VBox vbRootInitial = new VBox();
        HBox hbInitialButton = new HBox();
        HBox hbInitialTitle = new HBox();
        Label title = new Label("Bienvenidos a ALGOROMA");
        title.setFont(new Font("Blackletter", 60));
        title.setTextFill(Color.WHITE);
        hbInitialTitle.getChildren().add(title);
        hbInitialTitle.setAlignment(Pos.CENTER);

        Button btnInitial = new Button("Comenzar");
        btnInitial.setStyle("-fx-opacity: 0.8; -fx-font-weight: bold; -fx-font-size: 15px;");
        btnInitial.setMinHeight(50);
        btnInitial.setMinWidth(130);
        hbInitialButton.getChildren().add(btnInitial);
        hbInitialButton.setAlignment(Pos.CENTER);
        btnInitial.setOnAction(event -> {
            this.pantallaJugadores(primaryStage);
            reproductorSonido.reproducirSonido(2);
        });

        vbRootInitial.getChildren().addAll(hbInitialTitle, hbInitialButton);
        vbRootInitial.setAlignment(Pos.CENTER);

        try {
            Image image = new Image(new FileInputStream("src/main/java/edu/fiuba/algo3/vista/pantallaPrincipal.png"));

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
            vbRootInitial.setBackground(background);
            tamanio_X = image.getHeight();
            tamanio_Y = image.getWidth();
            primaryStage.setWidth(tamanio_Y);
            primaryStage.setHeight(tamanio_X);

        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro la imagen");
        }


        Scene scene = new Scene(vbRootInitial, tamanio_X, tamanio_Y);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public void pantallaJugadores(Stage primaryStage) {
        double tamanio_X = 400;
        double tamanio_Y = 400;

        VBox vbRootPlayers = new VBox();
        VBox vbPlayerTitle =  new VBox();
        VBox vbPlayers = new VBox();
        HBox hbPlayersInfo = new HBox();
        HBox hbPlayButton = new HBox();

        Label title = new Label("Nombre");
        title.setTextFill(Color.WHITE);
        vbPlayerTitle.getChildren().add(title);

        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("Escriba su nombre");
        usernameTextField.setStyle("-fx-opacity: 0.8;");
        vbPlayerTitle.getChildren().add(usernameTextField);

        Button userButtom = new Button("Agregar Jugador");
        vbPlayerTitle.getChildren().add(userButtom);
        vbPlayerTitle.setStyle("-fx-spacing: 10px; ");
        vbPlayerTitle.setAlignment(Pos.CENTER);

        Label titlePlayers = new Label("JUGADORES");
        titlePlayers.setTextFill(Color.WHITE);
        titlePlayers.setAlignment(Pos.CENTER);

        ScrollPane players = new ScrollPane();
        players.setVmax(1.0);
        players.setStyle("-fx-pref-width: 200px; -fx-pref-height: 180px; -fx-padding: 10px; -fx-border: 2px solid black; -fx-vbar-policy: always;  -fx-opacity: 0.8; ");

        userButtom.setOnAction(event -> {
            String username;
            username = usernameTextField.getText();
            if (!username.trim().isEmpty()) {
                algoRoma.agregarJugador(username);
                reproductorSonido.reproducirSonido(3);
                StringBuilder contenido = new StringBuilder();
                for (Jugador jugador: algoRoma.obtenerJugadores()) {
                    //Text textLinea = new Text(linea);
                    contenido.append("--").append(jugador.obtenerNombre()).append("\n");
                }
                Text text = new Text(contenido.toString());
                players.setContent(text);
                players.setVvalue(players.getHeight());
                System.out.println("El usuario " + username + " se agregó correctamente");
                usernameTextField.clear();
            } else {
                reproductorSonido.reproducirSonido(4);
                System.out.println("El nombre de usuario debe tener al menos un carácter");
            }
        });
        vbPlayers.getChildren().add(titlePlayers);
        vbPlayers.getChildren().add(players);
        vbPlayers.setStyle("-fx-spacing: 10px; ");
        vbPlayers.setPadding(new Insets(20));
        vbPlayers.setAlignment(Pos.TOP_LEFT);
        Button playButton = new Button("JUGAR");
        playButton.setMinHeight(50);
        playButton.setMinWidth(130);
        playButton.setStyle("-fx-opacity: 0.8;");
        hbPlayersInfo.getChildren().addAll(vbPlayerTitle, vbPlayers);
        hbPlayersInfo.setAlignment(Pos.CENTER);
        hbPlayButton.getChildren().add(playButton);
        hbPlayButton.setAlignment(Pos.CENTER);
        vbRootPlayers.getChildren().addAll(hbPlayersInfo, hbPlayButton);
        vbRootPlayers.setAlignment(Pos.CENTER);
        vbRootPlayers.setStyle("-fx-padding:10px; -fx-font-weight: bold; -fx-font-size: 15px; ");
        playButton.setOnAction(event -> {
            try {
                algoRoma.inicializarJuego();
                reproductorSonido.reproducirSonido(2);
                cargarTablero();
            }catch (CantidadJugadoresException ex){
                System.err.println(ex.getMessage());
            }
        });

        try {
            Image image = new Image(new FileInputStream("src/main/java/edu/fiuba/algo3/vista/pantallaPrincipal.png"));

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
            vbRootPlayers.setBackground(background);
            tamanio_X = image.getHeight();
            tamanio_Y = image.getWidth();
            primaryStage.setWidth(tamanio_Y);
            primaryStage.setHeight(tamanio_X);

        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro la imagen");
        }


        Scene scene = new Scene(vbRootPlayers, tamanio_X, tamanio_Y);
        primaryStage.setScene(scene);
        primaryStage.show();

        //primaryStage.show();
    }
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.pantallaInicial(primaryStage);

    }
    public void mostrarEnergia(HBox energy, Jugador jugador) {
        energy.getChildren().clear();
        Label titleEnergy = new Label("Energia: " + jugador.obtenerEnergia());
        energy.getChildren().add(titleEnergy);
        Rectangle celda = new Rectangle(20, 20);
        try {
            Image img = new Image(new FileInputStream("src/main/java/edu/fiuba/algo3/vista/energia.png"));
            celda.setFill(new ImagePattern(img));
        } catch (IllegalArgumentException ex) {
            System.out.println("No se encontro la imagen");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        energy.getChildren().add(celda);
    }

    public void actualizarInfoJugadores(TableroVisual tableroVisual, HBox hbPlayersTable ) {
        hbPlayersTable.getChildren().clear();
        HashMap<Jugador,String> imgs = tableroVisual.getImagenesJugadores();

        VBox vbPlayersInfo = new VBox();
        List<Jugador> jugadores = algoRoma.obtenerJugadores();
        for (Jugador jugador : jugadores) {
            VBox vbPlayer = new VBox();
            VBox vbPlayerInfo = new VBox();
            HBox hbPlayer = new HBox();
            Label titleName = new Label(jugador.obtenerNombre());
            vbPlayer.getChildren().add(titleName);
            vbPlayer.setStyle("-fx-font-size: 20px");
            vbPlayer.setAlignment(Pos.CENTER);
            Rectangle rectangle = new Rectangle(50, 50);
            try {
                Image img = new Image(new FileInputStream(imgs.get(jugador)));
                rectangle.setFill(new ImagePattern(img));
            } catch (IllegalArgumentException ex) {
                System.out.println("No se encontro la imagen");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            vbPlayer.getChildren().add(rectangle);
            //player Info
            HBox energy= new HBox();
            this.mostrarEnergia(energy, jugador);
            HBox equipo = new HBox();
            Label titleEquipaiento = new Label("Equipamiento: ");
            equipo.getChildren().add(titleEquipaiento);
            Gladiador gladiador = jugador.obtenerGladiador();
            Equipable equipable = gladiador.obtenerEquipamiento();
            if (!equipable.getClass().getSimpleName().equals("SinEquipamiento")){
                for (String equipamiento: EQUIPAMIENTO.keySet()) {
                    System.out.println(EQUIPAMIENTO.keySet());
                    Rectangle arma= new Rectangle(30, 30);
                    try {
                        Image img = new Image(new FileInputStream(EQUIPAMIENTO.get(equipamiento)));
                        arma.setFill(new ImagePattern(img));
                    } catch (IllegalArgumentException ex) {
                        System.out.println("No se encontro la imagen");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    equipo.getChildren().add(arma);
                    if(equipable.getClass().getSimpleName().equals(equipamiento)){
                        break;
                    }
                }
            }
            vbPlayerInfo.getChildren().addAll(energy, equipo);
            vbPlayerInfo.setStyle("-fx-pandding: 10px");
            hbPlayer.getChildren().addAll(vbPlayer, vbPlayerInfo);
            hbPlayer.setSpacing(10);
            vbPlayersInfo.getChildren().add(hbPlayer);
        }
        vbPlayersInfo.setStyle("-fx-background-color: white; -fx-font-size: 15px;");
        hbPlayersTable.getChildren().addAll(vbPlayersInfo, tableroVisual);
        hbPlayersTable.setSpacing(100);
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
            hbGameTitle.getChildren().add(title);
            hbGameTitle.setStyle("-fx-font-size: 15px");
            hbGameTitle.setAlignment(Pos.CENTER);

            vbPlayers.setAlignment(Pos.CENTER);

            vbGridInfo.setAlignment(Pos.CENTER);

            hbGameDetails.getChildren().addAll(vbGridInfo, vbPlayers);
            hbGameDetails.setAlignment(Pos.CENTER);

            vbHeader.getChildren().addAll(hbGameTitle, hbGameDetails);
            vbHeader.setAlignment(Pos.CENTER);

            //===============GRID===============================
            // Crear una instancia de TableroVisual
            TableroVisual tableroVisual = new TableroVisual(miTablero, algoRoma);
            tableroVisual.setAlignment(Pos.CENTER);
            vbGrid.setPadding(new Insets(20));
            vbGrid.setAlignment(Pos.CENTER);
            HBox hbPlayersTable = new HBox();
            this.actualizarInfoJugadores(tableroVisual, hbPlayersTable);
            vbGrid.getChildren().addAll(hbPlayersTable);

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
                    reproductorSonido.reproducirSonido(0);
                }catch(PartidaFinalizada ex){
                    //Mostrar pantalla con ganador del juego,nueva escena o un label
                    //refactorizar esto a un eventhandlerFinalizarPartida
                    reproductorSonido.reproducirSonido(1);
                    hbDone.getChildren().remove(btnJugar1Turno);
                    Label ganador = new Label(ex.getMessage());
                    ganador.autosize();
                    ganador.setBorder(new Border(new BorderStroke(Color.RED,BorderStrokeStyle.DASHED,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                    vbRoot.getChildren().add(ganador);
                    vbRoot.setAlignment(Pos.CENTER);
                }
                actualizarTablero(tableroVisual);
                this.actualizarInfoJugadores(tableroVisual, hbPlayersTable);



            });


            Button btnGame = new Button("Jugar una ronda");
            btnGame.setMinHeight(50);
            btnGame.setMinWidth(130);
            hbDone.getChildren().add(btnGame);

            btnGame.setAlignment(Pos.CENTER);

            btnGame.setOnAction(event -> {
                try {
                    algoRoma.jugar1Ronda();
                    reproductorSonido.reproducirSonido(0);
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
                    reproductorSonido.reproducirSonido(1);
                    hbDone.getChildren().remove(btnGame);
                    Label ganador = new Label(ex.getMessage());
                    ganador.autosize();
                    ganador.setBorder(new Border(new BorderStroke(Color.RED,BorderStrokeStyle.DASHED,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                    vbRoot.getChildren().add(ganador);
                    vbRoot.setAlignment(Pos.CENTER);
                }
                actualizarTablero(tableroVisual);
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
            vbRoot.setStyle("-fx-font-weight: bold; -fx-font-size: 15px;");

            double tamanio_X = 400;
            double tamanio_Y = 400;
            try {
                Image image = new Image(new FileInputStream("src/main/java/edu/fiuba/algo3/vista/fondoDelJuego.png"));

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
                vbRoot.setBackground(background);
                tamanio_X = image.getHeight();
                tamanio_Y = image.getWidth();
                primaryStage.setWidth(tamanio_Y);
                primaryStage.setHeight(tamanio_X);

            } catch (FileNotFoundException ex) {
                System.out.println("No se encontro la imagen");
            }


            Scene scene = new Scene(vbRoot, tamanio_X, tamanio_Y);
            primaryStage.setScene(scene);
            primaryStage.show();

        } else {
            System.err.println("El tablero no se ha inicializado correctamente.");
        }
    }

    public void reset() {};
    public void exit() {};
    public void actualizarTablero(TableroVisual tableroVisual) {
        tableroVisual.actualizarContenido(algoRoma);
    }
}
