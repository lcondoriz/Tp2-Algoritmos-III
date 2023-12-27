package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.juego.AlgoRoma;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.celda.Celda;
import edu.fiuba.algo3.modelo.tablero.celda.afectable.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Random;
import javafx.scene.Node;

public class TableroVisual extends GridPane {
    private Tablero tablero;
    private CeldaVisual[][] celdas;  // Matriz de celdas visuales
    private String[] IMAGENES = { "src/main/java/edu/fiuba/algo3/vista/Jugador/gladiadorRojo.png",
            "src/main/java/edu/fiuba/algo3/vista/Jugador/gladiadorAzul.png",
            "src/main/java/edu/fiuba/algo3/vista/Jugador/gladiadorVerde.png",
            "src/main/java/edu/fiuba/algo3/vista/Jugador/gladiadorVioleta.png",
            "src/main/java/edu/fiuba/algo3/vista/Jugador/gladiadorMarron.png",
            "src/main/java/edu/fiuba/algo3/vista/Jugador/gladiadorNaranja.png",
            "src/main/java/edu/fiuba/algo3/vista/Jugador/gladiadorMarron.png",
            "src/main/java/edu/fiuba/algo3/vista/Jugador/gladiadorNaranja.png",
    };
    private HashMap<String,String> AFECTABLES = new HashMap<>();

    {
        AFECTABLES.put("Equipo", "src/main/java/edu/fiuba/algo3/vista/Afectables/equipo.png");
        AFECTABLES.put("Comida", "src/main/java/edu/fiuba/algo3/vista/Afectables/comida.png");
        AFECTABLES.put("Bacanal", "src/main/java/edu/fiuba/algo3/vista/Afectables/copa.png");
        AFECTABLES.put("FieraSalvaje", "src/main/java/edu/fiuba/algo3/vista/Afectables/fiera.png");
        AFECTABLES.put("Lesion", "src/main/java/edu/fiuba/algo3/vista/Afectables/lesion.png");
    };
    private HashMap<Jugador,String> IMAGENES_ASIGNADOS = new HashMap<>();


    public TableroVisual(Tablero tablero, AlgoRoma algoRoma) {
        this.tablero = tablero;
        this.celdas = new CeldaVisual[tablero.obtenerAncho()][tablero.obtenerLargo()];  // Inicializar la matriz
        int i = 0 ;
        for (Jugador jugador : algoRoma.obtenerJugadores()){
            IMAGENES_ASIGNADOS.put(jugador, IMAGENES[i]);
            i++;
        }
        cargarTableroVisual();
        cargarGladiadores(algoRoma);
    }



    public void mostrarObstaculosYPremios(Celda celda, Rectangle rectangle) {
        List<Afectable> afectables = celda.getAfectables();
        if (!afectables.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(afectables.size());
            Afectable afectable = afectables.get(randomIndex);
            try {
                Image img = new Image(new FileInputStream(AFECTABLES.get(afectable.getClass().getSimpleName())));
                rectangle.setFill(new ImagePattern(img));
            } catch (IllegalArgumentException ex) {
                System.out.println("No se encontro la imagen");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void cargarTableroVisual() {
        int tableroAncho = tablero.obtenerAncho();
        int tableroLargo = tablero.obtenerLargo();
        GridPane grid = new GridPane();
        setPadding(new Insets(0)); // Eliminar cualquier relleno
        setAlignment(Pos.CENTER);

        // Inicializar cada celda en la matriz
        for (int i = 0; i < tableroAncho; i++) {
            for (int j = 0; j < tableroLargo; j++) {
                celdas[i][j] = new CeldaVisual();
                Rectangle rectangle = celdas[i][j].crearCelda();
                this.add(rectangle, i, j);
            }
        }

        Celda celdaActual = tablero.obtenerCeldaDeSalida();
        LinkedList<Celda> celdasTablero = tablero.obtenerCeldas();
        for (int i = 0; i < celdasTablero.size(); i++) {
            int posicionXCelda = celdaActual.obtenerCoordenadas().obtenerCoordenadaX() - 1;
            int posicionYCelda = celdaActual.obtenerCoordenadas().obtenerCoordenadaY() - 1;
            celdas[posicionYCelda][posicionXCelda] = new CeldaVisual();
            Rectangle rectangle = celdas[posicionYCelda][posicionXCelda].crearCelda(celdaActual.obtenerTipo());
            mostrarObstaculosYPremios(celdaActual, rectangle);
            Text text = new Text(""+ i);
            text.setStyle("-fx-font-weight: bold; -fx-font-size: 10px;");
            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(rectangle, text);
            this.add(stackPane, posicionYCelda, posicionXCelda);
            celdaActual = celdaActual.obtenerSiguienteCelda();
        }
    }

    private void cargarGladiadores(AlgoRoma algoRoma) {
        int tableroAncho = tablero.obtenerAncho();
        int tableroLargo = tablero.obtenerLargo();
        //Color[] colores = { Color.BLUE, Color.RED, Color.ORANGE, Color.VIOLET, Color.BROWN };


        for (int i = 0; i < algoRoma.obtenerJugadores().size(); i++) {
            Celda celdaJugador = algoRoma.obtenerJugadores().get(i).obtenerGladiador().obtenerCelda();
            int posicionGladiadorX = celdaJugador.obtenerCoordenadas().obtenerCoordenadaX() - 1;
            int posicionGladiadorY = celdaJugador.obtenerCoordenadas().obtenerCoordenadaY() - 1;

            Rectangle rectangle = new Rectangle(40, 40);
            //rectangle.setFill(COLORES_ASIGNADOS.get(algoRoma.obtenerJugadores().get(i)));
            try{
                Image img = new Image(new FileInputStream(IMAGENES_ASIGNADOS.get(algoRoma.obtenerJugadores().get(i))));
                rectangle.setFill(new ImagePattern(img));
            } catch (IllegalArgumentException ex) {
                System.out.println("No se encontro la imagen");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(rectangle);

            this.add(stackPane, posicionGladiadorY, posicionGladiadorX);
        }
    }
    //public HashMap<Jugador,Color> coloresAsignados(){
    //    return this.COLORES_ASIGNADOS;
    //}
    public Tablero obtenerTablero(){
        return tablero;
    }

    private void limpiarContenido() {
        // Filtrar los nodos que no se deben eliminar (por ejemplo, botones)
        List<Node> nodosNoEliminados = getChildren().filtered(node -> node instanceof Button);

        // Limpiar solo los nodos que no son botones
        getChildren().retainAll(nodosNoEliminados);
    }

    public void actualizarContenido(AlgoRoma algoRoma){
        limpiarContenido();
        cargarTableroVisual();
        cargarGladiadores(algoRoma);
    }

    public HashMap<Jugador, String> getImagenesJugadores() {
        return IMAGENES_ASIGNADOS;
    }
}
