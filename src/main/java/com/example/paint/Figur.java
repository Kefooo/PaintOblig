package com.example.paint;
import javafx.scene.paint.Color;

public interface Figur {
    double getOmkrets();
    double getAreal();
    String getFigurInfo();
    String getFigurType();

    boolean contains(double x, double y);

}