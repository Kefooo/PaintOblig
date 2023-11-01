package com.example.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Sirkel extends Circle implements Figur {

    public Sirkel(MouseEvent e) {
        super(e.getX(), e.getY(), 5);
        setStroke(Paint.fargeLinje.getValue());
        setFill(Paint.fargeFyll.getValue());
    }

    @Override
    public String getFigurType() {
        return "Sirkel";
    }

    @Override
    public double getAreal() {
        double radius = getRadius();
        return Math.PI * radius * radius;
    }

    @Override
    public double getOmkrets() {
        double radius = getRadius();
        return 2 * Math.PI * radius;
    }

    public void dragg(MouseEvent e) {
        setRadius((Math.abs(e.getX() - getCenterX()) + Math.abs(e.getY() - getCenterY())));
    }

    public static void flytt(MouseEvent e){
        Sirkel sirkel = (Sirkel) e.getSource();
        sirkel.setCenterX(e.getX());
        sirkel.setCenterY(e.getY());
        e.consume();
    }

    @Override
    public String getFigurInfo() {
        String figurInfo = "Figurtype: " + getFigurType() + "\n";
        figurInfo += "Farge kant: " + getStroke() + "\n";
        figurInfo += "Farge fyll: " + getFill() + "\n";
        figurInfo += "Radius: " + getRadius() + "\n";
        figurInfo += "Areal: " + getAreal() + "\n";
        figurInfo += "Omkrets: " + getOmkrets() + "\n";

        return figurInfo;
    }
}
