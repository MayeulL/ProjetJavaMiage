package model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CustomButton extends Button {
    private final int BUTTON_FONT_SIZE = 10;
    private final String FONT_PATH = "ressources\\space-wham.ttf";
    private final String BOUTON_APPUYE = "-fx-background-color: grey;";
    private final String BOUTON = "-fx-background-color: darkgray;";

    public CustomButton(String text){
        setText(text);
        setButtonFont();
        setPrefHeight(36);
        setPrefWidth(111);
        setStyle(BOUTON);
        initializeButtonListener();
    }

    public void setButtonFont(){
        try{
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), BUTTON_FONT_SIZE));
        } catch (FileNotFoundException e){
            setFont(Font.loadFont("Verdana", BUTTON_FONT_SIZE));
        }
    }

    private void setBoutonAppuyeStyle(){
        setStyle(BOUTON_APPUYE);
    }
    private void setBoutonStyle(){
        setStyle(BOUTON);
    }

    private void initializeButtonListener(){
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    setBoutonAppuyeStyle();
                    setPrefHeight(36);
                    setLayoutY(getLayoutY());
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    setBoutonStyle();
                    setPrefHeight(36);
                    setLayoutY(getLayoutY());
                }
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(new DropShadow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(null);
            }
        });
    }



}
