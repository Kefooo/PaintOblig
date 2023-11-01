package com.example.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Rektangel extends Rectangle implements Figur {
    double x1, y1;

    public Rektangel(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
        setStroke(Paint.fargeLinje.getValue());
        setFill(Paint.fargeFyll.getValue());
    }

    public static void flytt(MouseEvent e) {
        Rektangel rektangel = (Rektangel) e.getSource();
        rektangel.setX(e.getX() - rektangel.getWidth() / 2);
        rektangel.setY(e.getY() - rektangel.getHeight() / 2);
        e.consume();
    }

    public void dragg(MouseEvent e) {
        double deltaX = e.getX() - x1;
        double deltaY = e.getY() - y1;

        if (deltaX < 0) {
            Paint.rektangel.setX(e.getX() + deltaX / 2);
            Paint.rektangel.setWidth(-deltaX);
        } else {
            Paint.rektangel.setX(e.getX() - deltaX / 2);
            Paint.rektangel.setWidth(deltaX);
        }

        if (deltaY < 0) {
            Paint.rektangel.setY(e.getY() + deltaY / 2);
            Paint.rektangel.setHeight(-deltaY);
        } else {
            Paint.rektangel.setY(e.getY() - deltaY / 2);
            Paint.rektangel.setHeight(deltaY);
        }
    }

    @Override
    public String getFigurType() {
        return "Rektangel";
    }

    @Override
    public double getAreal() {
        return getWidth() * getHeight();
    }

    @Override
    public double getOmkrets() {
        return 2 * (getWidth() + getHeight());
    }

    @Override
    public String getFigurInfo() {
        String figurInfo = "Figurtype: " + getFigurType() + "\n";
        figurInfo += "Farge kant: " + getStroke() + "\n";
        figurInfo += "Farge fyll: " + getFill() + "\n";
        figurInfo += "Areal: " + getAreal() + "\n";
        figurInfo += "Omkrets: " + getOmkrets() + "\n";

        return figurInfo;
    }
}
