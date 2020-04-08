package model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

public class Directions extends Button {
    protected String Direction;

    public Directions(String direction){
        Direction = direction;
        setPrefHeight(72);
        setPrefWidth(78);
        setBackround();
        initializeListener();
    }

    private void setBackround() {
        String bg = null;
        if (Direction == "Down"){
            bg = "file:ressources/misc/arrowDown.png";
        } else if (Direction == "Right"){
            bg = "file:ressources/misc/arrowRight.png";
        } else if (Direction == "Left"){
            bg = "file:ressources/misc/arrowLeft.png";
        }else if (Direction == "Up"){
            bg = "file:ressources/misc/arrowUp.png";
        }

        Background Bg = new Background(new BackgroundImage((new Image(bg, 78, 72, false, true))
                    , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null));

        this.setBackground(Bg);
    }
    private void setBackroundHover() {
        String bg = null;
        if (Direction == "Down"){
            bg = "file:ressources/misc/arrowDownHover.png";
        } else if (Direction == "Right"){
            bg = "file:ressources/misc/arrowRightHover.png";
        } else if (Direction == "Left"){
            bg = "file:ressources/misc/arrowLeftHover.png";
        } else if (Direction == "Up"){
            bg = "file:ressources/misc/arrowUpHover.png";
        }

        Background Bg = new Background(new BackgroundImage((new Image(bg, 78, 72, false, true))
                , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null));

        this.setBackground(Bg);
    }

    private void initializeListener(){

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setBackroundHover();
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setBackround();
            }
        });
    }


}
