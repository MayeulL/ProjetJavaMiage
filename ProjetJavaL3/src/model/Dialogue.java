package model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Dialogue extends Label {
    private final static String FONT = "ressources/space-wham.ttf";
    private final static String BACKGROUND_IMAGE = "ressources/DialogueBox.png";

    public Dialogue(String text){
        setPrefWidth(600);
        setPrefHeight(140);
        setAlignment(Pos.CENTER);
        setText(text);
        setLabelFont();
        BackgroundImage bgDiloques= new BackgroundImage(new Image(BACKGROUND_IMAGE, 600, 140, false, true)
                , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(bgDiloques ));
    }

    private void setLabelFont(){
        try {
            setFont(Font.loadFont(new FileInputStream(new File(FONT)), 10));
        } catch (FileNotFoundException e){
            setFont(Font.font("Verdana", 10));
        }
    }
}
