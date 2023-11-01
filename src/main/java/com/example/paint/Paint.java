package com.example.paint;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;

public class Paint extends Application {
    BorderPane root = new BorderPane();
    Pane tegneOmrade = new Pane();
    VBox boks = new VBox(10);
    VBox fargeboks = new VBox(10);
    ToggleButton rektangelKnapp = new ToggleButton("RECTANGLE");
    ToggleButton linjeKnapp = new ToggleButton("LINE");
    ToggleButton sirkelKnapp = new ToggleButton("CIRCLE");
    ToggleButton tekstKnapp = new ToggleButton("TEXT");
    ToggleButton velgKnapp = new ToggleButton("MOVE FIGURE");
    ToggleButton byttFarge = new ToggleButton("CHANGE COLOR");
    ToggleButton flyttFram = new ToggleButton("MOVE FORWARD");
    ToggleButton flyttBak = new ToggleButton("MOVE BACKWARD");

    private final ToggleGroup knappGruppe = new ToggleGroup();
    ArrayList<Figur> figurerListe = new ArrayList<>();
    ToggleButton[] knappeListe = {rektangelKnapp, linjeKnapp, sirkelKnapp, tekstKnapp, velgKnapp, byttFarge, flyttFram, flyttBak};
    Label fyllTekst = new Label("Choose the color of the fill");
    static ColorPicker fargeFyll = new ColorPicker(Color.PINK);
    static ColorPicker nyfargeFyll = new ColorPicker(fargeFyll.getValue());
    Label linjeTekst = new Label("Choose the color of the edge Line");
    static ColorPicker fargeLinje = new ColorPicker(Color.BLACK);
    static ColorPicker nyfargeLinje = new ColorPicker(fargeLinje.getValue());
    Linje linje;
    static Tekst tekst;
    static Rektangel rektangel;
    Sirkel sirkel;
    Label skrivTekst = new Label("Write Text");
    public static TextField tekstOmrade = new TextField();
    public static Label info = new Label(" ");
    private double startX, startY;
    private Figur valgtFigur;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Erik-Tobias og Kevins TegneProgram");

        root.setCenter(tegneOmrade);

        root.setLeft(boks);

        root.setRight(fargeboks);

        root.setPadding(new Insets(15, 15, 15, 15));

        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

        boks.getChildren().addAll(rektangelKnapp, linjeKnapp, sirkelKnapp, tekstKnapp,
                skrivTekst, tekstOmrade, velgKnapp, flyttFram, flyttBak);

        fargeboks.getChildren().addAll(linjeTekst, fargeLinje, fyllTekst, fargeFyll, byttFarge, info);

        for (ToggleButton valg : knappeListe) {
            valg.setMinWidth(90);
            valg.setToggleGroup(knappGruppe);
            valg.setCursor(Cursor.HAND);
        }

        tegneOmrade.setOnMousePressed(this::MouseTrykk);
        tegneOmrade.setOnMouseDragged(this::MouseTrykk);
        tegneOmrade.setOnMouseReleased(this::MouseTrykk);
    }




    private void MouseTrykk(MouseEvent e) {
        if (byttFarge.isSelected()) {
            if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
                for (Figur figur : figurerListe) {
                    if (figur.contains(e.getX(), e.getY())) {
                        valgtFigur = figur;
                        if (figur instanceof Tekst) {
                            ((Tekst) figur).setStroke(fargeLinje.getValue());
                            ((Tekst) figur).setFill(fargeLinje.getValue());
                        } else if (figur instanceof Linje || figur instanceof Sirkel || figur instanceof Rektangel) {
                            ((Shape) figur).setStroke(fargeLinje.getValue());
                            ((Shape) figur).setFill(fargeFyll.getValue());
                        }
                        info.setText("Farge endret!");
                        break;
                    }
                }
            }
            } else { if (tekstKnapp.isSelected()) {
                if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
                    tekst = new Tekst(e);
                    tekst.setStroke(fargeLinje.getValue());
                    tekst.setFill(fargeFyll.getValue());
                    tegneOmrade.getChildren().add(tekst);
                    tekst.setOnMouseDragged(e2 -> tekst.flytt(e2));
                } else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                    tekst.dragg(e);
                } else if (e.getEventType() == MouseEvent.MOUSE_RELEASED) {
                    String type = "Figur: Tekst" + "\n";
                    type += "Farge: " + tekst.getStroke() + tekst.getFill();
                    info.setText(type);
                }
        } else if (linjeKnapp.isSelected()) {
            if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
                linje = new Linje(e.getX(), e.getY(), e.getX(), e.getY());
                linje.setStroke(fargeLinje.getValue());
                linje.setFill(fargeLinje.getValue());
                tegneOmrade.getChildren().add(linje);
                linje.setOnMouseDragged(e2 -> linje.flytt(e2));
                figurerListe.add(linje);
            } else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                linje.setEndX(e.getX());
                linje.setEndY(e.getY());
            } else if (e.getEventType() == MouseEvent.MOUSE_RELEASED) {
                String type = "Figur: Linje" + "\n";
                type += "Farge: " + linje.getFill() + linje.getStroke() + "\n";
                type += "Lengde: " + linje.getOmkrets();
                info.setText(type);
            }
            } else if (sirkelKnapp.isSelected()) {
                if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
                    sirkel = new Sirkel(e);
                    sirkel.setStroke(fargeLinje.getValue());
                    sirkel.setFill(fargeFyll.getValue());
                    tegneOmrade.getChildren().add(sirkel);
                    sirkel.setOnMouseDragged(e2 -> Sirkel.flytt(e2));
                    figurerListe.add(sirkel);
                } else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                    sirkel.dragg(e);
                } else if (e.getEventType() == MouseEvent.MOUSE_RELEASED) {
                    String type = "Figur: Sirkel" + "\n";
                    type += "Farge: " + sirkel.getStroke() + sirkel.getFill() + "\n";
                    type +=  "Areal: " + sirkel.getAreal() + "\n" + "Omkrets: " + sirkel.getOmkrets();
                    info.setText(type);
                }
            } else if (rektangelKnapp.isSelected()) {
                if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
                    rektangel = new Rektangel(e);
                    rektangel.setStroke(fargeLinje.getValue());
                    rektangel.setFill(fargeFyll.getValue());
                    tegneOmrade.getChildren().add(rektangel);
                    rektangel.setOnMouseDragged(e2 -> Rektangel.flytt(e2));

                    figurerListe.add(rektangel);
                } else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                    rektangel.dragg(e);
                } else if (e.getEventType() == MouseEvent.MOUSE_RELEASED) {
                    String type = "Figur: Rektangel" + "\n";
                    type += "Farge: " + rektangel.getStroke() + rektangel.getFill() + "\n";
                    type += "Areal: " + rektangel.getAreal() + "\n" + "Omkrets: " + rektangel.getOmkrets();
                    info.setText(type);
                }

            }
        }
    }

}
