package edu.fiuba.algo3.javafx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CeldaVisual {

    private Circle circle;

    public CeldaVisual() {
        this.circle = crearCelda();
    }

    public Circle crearCelda() {
        Circle celda = new Circle();
        celda.setFill(Color.BEIGE);
        celda.setStroke(Color.BLACK);
        celda.setStrokeWidth(2);
        celda.setEffect(new javafx.scene.effect.DropShadow());
        this.circle = celda;
        return celda;
    }

    public Circle crearCelda(String tipoCelda) {
        Circle celda = new Circle();
        switch (tipoCelda) {
            case "Camino":
                celda.setFill(Color.GRAY);
                break;
            case "Salida":
                celda.setFill(Color.RED);
                break;
            case "Llegada":
                celda.setFill(Color.GREEN);
                break;
            default:
                celda.setFill(Color.BEIGE);
        }

        celda.setStroke(Color.BLACK);
        celda.setStrokeWidth(2);
        celda.setEffect(new javafx.scene.effect.DropShadow());

        this.circle = celda;
        return celda;
    }

    public Circle obtenerCelda() {
        return circle;
    }

    public void cambiarColorFondo(Color color) {
        circle.setFill(color);
    }
}
