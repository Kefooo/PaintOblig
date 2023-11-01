package com.example.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tekst extends Text implements Figur {

    public Tekst(MouseEvent e) {
        super(e.getX(), e.getY(), Paint.tekstOmrade.getText());
        setStroke(Paint.fargeLinje.getValue());
        setFill(Paint.fargeFyll.getValue());
    }

    @Override
    public String getFigurType() {
        return "Tekst";
    }

    @Override
    public double getAreal() {
        return 0;
    }

    @Override
    public double getOmkrets() {
        return 0;
    }

    public void dragg(MouseEvent e) {
        setFont(Font.font(e.getX() - e.getX() / 3));
    }

    public void flytt(MouseEvent e) {
        Tekst text = (Tekst) e.getSource();
        text.setX(e.getX());
        text.setY(e.getY());
        e.consume();
    }

    @Override
    public String getFigurInfo() {
        return "Figurtype: " + getFigurType() + "\n" +
                "Farge kant: " + getStroke() + "\n" +
                "Farge fyll: " + getFill() + "\n";
    }
}
