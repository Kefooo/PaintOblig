package com.example.paint;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class Linje extends Line implements Figur {
    public Linje(double startX, double startY, double sluttX, double sluttY){
        super(startX, startY, sluttX, sluttY);
        setStroke(Paint.fargeLinje.getValue());
        setFill(Paint.fargeFyll.getValue());
    }
    public static void flytt(MouseEvent e) {
        Linje linje;
        linje = (Linje) e.getSource();
        double hoyde = linje.getStartY() - linje.getEndY();
        double bredde = linje.getStartX() - linje.getEndX();
        linje.setStartX(e.getX() - bredde / 2);
        linje.setStartY(e.getY() - hoyde / 2);
        linje.setEndX(linje.getStartX() + bredde);
        linje.setEndY(linje.getStartY() + hoyde);
        e.consume();
    }
    @Override
    public double getAreal() {
        return 0;
    }
    @Override
    public double getOmkrets() {
        double deltaX = getEndX() - getStartX();
        double deltaY = getEndY() - getStartY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
    @Override
    public String getFigurType() {
        return "Linje";
    }
    @Override
    public String getFigurInfo() {
        String figurInfo = "Figurtype: " + getFigurType() + "\n";
        figurInfo += "Lengde: " + getOmkrets() + "\n";
        return figurInfo;
    }

}
