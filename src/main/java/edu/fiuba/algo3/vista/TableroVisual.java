package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.fiuba.algo3.modelo.juego.AlgoRoma;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.FontWeight;
import javafx.geometry.Insets;
import edu.fiuba.algo3.modelo.tablero.celda.Celda;
import javafx.scene.Node;

public class TableroVisual extends GridPane {

    private Tablero tablero;
    private CeldaVisual[][] celdas;  // Matriz de celdas visuales

    private Color[] COLORES = { Color.BLUE, Color.RED, Color.ORANGE, Color.PURPLE, Color.BROWN };

    private HashMap<Jugador,Color> COLORES_ASIGNADOS= new HashMap<>();
    public TableroVisual(Tablero tablero, AlgoRoma algoRoma) {
        this.tablero = tablero;
        this.celdas = new CeldaVisual[tablero.obtenerAncho()][tablero.obtenerLargo()];  // Inicializar la matriz
        int i = 0 ;
        for (Jugador jugador : algoRoma.obtenerJugadores()){
            COLORES_ASIGNADOS.put(jugador,COLORES[i]);
            i++;
        }
        cargarTableroVisual();
        cargarGladiadores(algoRoma);
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
        for (int i = 0; i < tablero.obtenerCeldas().size(); i++) {
            int posicionXCelda = celdaActual.obtenerCoordenadas().obtenerCoordenadaX() - 1;
            int posicionYCelda = celdaActual.obtenerCoordenadas().obtenerCoordenadaY() - 1;
            celdas[posicionYCelda][posicionXCelda] = new CeldaVisual();  
            Rectangle rectangle = celdas[posicionYCelda][posicionXCelda].crearCelda(celdaActual.obtenerTipo());

            //rectangle.widthProperty().bind(this.widthProperty().divide(tableroAncho*1.5));
            //rectangle.heightProperty().bind(this.heightProperty().divide(tableroLargo*1.5));

            this.add(rectangle, posicionYCelda, posicionXCelda);
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
            
            Rectangle rectangle = new Rectangle(35, 35);
            // Asignar un color diferente a cada jugador

            //rectangle.setFill(colores[i % colores.length]);
            rectangle.setFill(COLORES_ASIGNADOS.get(algoRoma.obtenerJugadores().get(i)));
            rectangle.setStroke(Color.BLACK);
            rectangle.setStrokeWidth(2);

            //rectangle.widthProperty().bind(this.widthProperty().divide(tableroAncho*1.5));
            //rectangle.heightProperty().bind(this.heightProperty().divide(tableroLargo*1.5));

            // Obtener la inicial del jugador
            char inicial = algoRoma.obtenerJugadores().get(i).obtenerNombre().charAt(0);
        
            // Crear un Text con la inicial y establecer su estilo
            Text inicialText = new Text(String.valueOf(inicial));
            inicialText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            inicialText.setFill(Color.WHITE);
        
            // Crear un StackPane para posicionar el Text sobre el círculo
            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(rectangle, inicialText);
        
            this.add(stackPane, posicionGladiadorY, posicionGladiadorX);
        }
    }
    public HashMap<Jugador,Color> coloresAsignados(){
        return this.COLORES_ASIGNADOS;
    }
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
}