package edu.fiuba.algo3.javafx;

import edu.fiuba.algo3.tablero.Tablero;
import edu.fiuba.algo3.juego.AlgoRoma;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.FontWeight;
import javafx.geometry.Insets;
import edu.fiuba.algo3.tablero.celda.Celda;


public class TableroVisual extends GridPane {

    private Tablero tablero;
    private CeldaVisual[][] celdas;  // Matriz de celdas visuales

    public TableroVisual(Tablero tablero, AlgoRoma algoRoma) {
        this.tablero = tablero;
        this.celdas = new CeldaVisual[tablero.obtenerAncho()][tablero.obtenerLargo()];  // Inicializar la matriz
        cargarTableroVisual();
        cargarGladiadores(algoRoma);
    }

    private void cargarTableroVisual() {
        int tableroAncho = tablero.obtenerAncho();
        int tableroLargo = tablero.obtenerLargo();
        GridPane grid = new GridPane();
        setPadding(new Insets(0)); // Eliminar cualquier relleno
        // Inicializar cada celda en la matriz
        for (int i = 0; i < tableroAncho; i++) {
            for (int j = 0; j < tableroLargo; j++) {
                celdas[i][j] = new CeldaVisual();  
                Circle circle = celdas[i][j].crearCelda(); 

                DoubleProperty radio = circle.radiusProperty();

                radio.bind(Bindings.min(this.widthProperty().divide(tableroAncho * 2), this.heightProperty().divide(tableroLargo * 2)));

                this.add(circle, i, j);
            }
        }

        Celda celdaActual = tablero.obtenerCeldaDeSalida();
        for (int i = 0; i < tablero.obtenerCeldas().size(); i++) {
            int posicionXCelda = celdaActual.obtenerCoordenadas().obtenerCoordenadaX() - 1;
            int posicionYCelda = celdaActual.obtenerCoordenadas().obtenerCoordenadaY() - 1;
            celdas[posicionYCelda][posicionXCelda] = new CeldaVisual();  
            Circle circle = celdas[posicionYCelda][posicionXCelda].crearCelda(celdaActual.obtenerTipo()); 

            DoubleProperty radio = circle.radiusProperty();

            radio.bind(Bindings.min(this.widthProperty().divide(tableroAncho * 2), this.heightProperty().divide(tableroLargo * 2)));

            this.add(circle, posicionYCelda, posicionXCelda);
            celdaActual = celdaActual.obtenerSiguienteCelda();        
        }
    }

    private void cargarGladiadores(AlgoRoma algoRoma) {
        int tableroAncho = tablero.obtenerAncho();
        int tableroLargo = tablero.obtenerLargo();
        Color[] colores = { Color.BLUE, Color.YELLOW, Color.ORANGE, Color.PURPLE, Color.CYAN };

        
        for (int i = 0; i < algoRoma.obtenerJugadores().size(); i++) {
            Celda celdaJugador = algoRoma.obtenerJugadores().get(i).obtenerGladiador().obtenerCelda();
            int posicionGladiadorX = celdaJugador.obtenerCoordenadas().obtenerCoordenadaX() - 1;
            int posicionGladiadorY = celdaJugador.obtenerCoordenadas().obtenerCoordenadaY() - 1;
            
            Circle circle = new Circle();
            // Asignar un color diferente a cada jugador
            circle.setFill(colores[i % colores.length]);
            circle.setStroke(Color.BLACK);
            circle.setStrokeWidth(2);
        
            DoubleProperty radio = circle.radiusProperty();
            radio.bind(Bindings.min(this.widthProperty().divide(tableroAncho * 2), this.heightProperty().divide(tableroLargo * 2)));
        
            // Obtener la inicial del jugador
            char inicial = algoRoma.obtenerJugadores().get(i).obtenerNombre().charAt(0);
        
            // Crear un Text con la inicial y establecer su estilo
            Text inicialText = new Text(String.valueOf(inicial));
            inicialText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            inicialText.setFill(Color.WHITE);
        
            // Crear un StackPane para posicionar el Text sobre el círculo
            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(circle, inicialText);
        
            this.add(stackPane, posicionGladiadorY, posicionGladiadorX);
        }
    }

    public Tablero obtenerTablero(){
        return tablero;
    }

    public void actualizarContenido(AlgoRoma algoRoma){
        cargarTableroVisual();
        cargarGladiadores(algoRoma);
    }
}