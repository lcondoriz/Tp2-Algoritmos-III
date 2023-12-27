package edu.fiuba.algo3.vista;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;


public class CeldaVisual {

    private Rectangle rectangle;

    public CeldaVisual() {
        this.rectangle = crearCelda();
    }
    private HashMap<String,String> ICONOS = new HashMap<>();
    {
        ICONOS.put("Camino","src/main/java/edu/fiuba/algo3/vista/camino.png");
        ICONOS.put("Llegada","src/main/java/edu/fiuba/algo3/vista/ARCO.png");
        ICONOS.put("Salida","src/main/java/edu/fiuba/algo3/vista/COLISEO.png");
    }
    public Rectangle crearCelda() {
        Rectangle celda = new Rectangle(40, 40);
        try {
            Image img = new Image(new FileInputStream("src/main/java/edu/fiuba/algo3/vista/BOSQUE.png"));
            celda.setFill(new ImagePattern(img));
        } catch (IllegalArgumentException ex) {
            System.out.println("No se encontro la imagen");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        celda.setStroke(Color.BLACK);
        celda.setStrokeWidth(2);
        celda.setEffect(new javafx.scene.effect.DropShadow());
        this.rectangle = celda;
        return celda;
    }

    public Rectangle crearCelda(String tipoCelda) {
        Rectangle celda = new Rectangle(40, 40);
        try {
            Image img = new Image(new FileInputStream(ICONOS.get(tipoCelda)));
            celda.setFill(new ImagePattern(img));
        } catch (IllegalArgumentException ex) {
            System.out.println("No se encontro la imagen");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        celda.setStroke(Color.BLACK);
        celda.setStrokeWidth(2);
        celda.setEffect(new javafx.scene.effect.DropShadow());

        this.rectangle = celda;
        return celda;
    }

    public Rectangle obtenerCelda() {
        return rectangle;
    }

    public void cambiarColorFondo(Color color) {
        rectangle.setFill(color);
    }
}
