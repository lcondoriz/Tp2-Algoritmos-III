package edu.fiuba.algo3.vista;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class CeldaVisual {

    private Rectangle rectangle;

    public CeldaVisual() {
        this.rectangle = crearCelda();
    }

    public Rectangle crearCelda() {
        Rectangle celda = new Rectangle(25, 25);
        celda.setFill(Color.GRAY);
        celda.setStroke(Color.BLACK);
        celda.setStrokeWidth(2);
        celda.setEffect(new javafx.scene.effect.DropShadow());
        this.rectangle = celda;
        return celda;
    }

    public Rectangle crearCelda(String tipoCelda) {
        Rectangle celda = new Rectangle(25, 25);
        switch (tipoCelda) {
            case "Camino":
                celda.setFill(Color.YELLOW);
                break;
            case "Salida":
                celda.setFill(Color.RED);
                break;
            case "Llegada":
                celda.setFill(Color.GREEN);
                break;
            default:
                celda.setFill(Color.GRAY);
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
