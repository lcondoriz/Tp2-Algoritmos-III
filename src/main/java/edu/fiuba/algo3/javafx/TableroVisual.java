package edu.fiuba.algo3.javafx;

import edu.fiuba.algo3.tablero.Tablero;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import edu.fiuba.algo3.juego.AlgoRoma;
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
import edu.fiuba.algo3.tablero.celda.Celda;
import javafx.scene.Node;



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
        setAlignment(Pos.CENTER);
        double tamanio_X = 400;
        double tamanio_Y = 175;
        try {
            Image image = new Image(new FileInputStream("src/main/java/edu/fiuba/algo3/javafx/fondoDelJuego.jpg"));

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
        // Inicializar cada celda en la matriz
        for (int i = 0; i < tableroAncho; i++) {
            for (int j = 0; j < tableroLargo; j++) {
                celdas[i][j] = new CeldaVisual();  
                Rectangle rectangle = celdas[i][j].crearCelda();

                //rectangle.widthProperty().bind(this.widthProperty().divide(tableroAncho*1.5));
                //rectangle.heightProperty().bind(this.heightProperty().divide(tableroLargo*1.5 ));

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
        Color[] colores = { Color.BLUE, Color.YELLOW, Color.ORANGE, Color.PURPLE, Color.CYAN };

        
        for (int i = 0; i < algoRoma.obtenerJugadores().size(); i++) {
            Celda celdaJugador = algoRoma.obtenerJugadores().get(i).obtenerGladiador().obtenerCelda();
            int posicionGladiadorX = celdaJugador.obtenerCoordenadas().obtenerCoordenadaX() - 1;
            int posicionGladiadorY = celdaJugador.obtenerCoordenadas().obtenerCoordenadaY() - 1;
            
            Rectangle rectangle = new Rectangle(20, 20);
            // Asignar un color diferente a cada jugador
            rectangle.setFill(colores[i % colores.length]);
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