package model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

public class InterractionButton extends Button {

    private String Type;
    private int Height;
    private int Width;

    public InterractionButton(String type, int width, int height){
        Type = type;
        Width = width;
        Height = height;
        setPrefWidth(width);
        setPrefHeight(height);

        setBackround();
        initializeListener();

    }

    private void setBackround() {
        String bg = null;
        if (Type == "Bob"){
            bg = "file:ressources/characters/Bob.png";
        } else if (Type == "Chmdak"){
            bg = "file:ressources/characters/chmdak.png";
        } else if (Type == "Zexreen"){
            bg = "file:ressources/characters/zexreen.png";
        }else if (Type == "Fabriquant"){
            bg = "file:ressources/characters/Fabriquant.png";
        }else if (Type == "Marchand"){
            bg = "file:ressources/characters/Marchand.png";
        }else if (Type == "Bibliothequaire"){
            bg = "file:ressources/characters/Bibliothequaire.png";
        }else if (Type == "Fusee"){
            bg = "file:ressources/misc/rocket.png";
        }else if (Type == "Cata"){
            bg = "file:ressources/misc/catalyseur.png";
        }else if (Type == "Box"){
            bg = "file:ressources/misc/box.png";
        }

        Background Bg = new Background(new BackgroundImage((new Image(bg, Width, Height, false, true))
                , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null));

        this.setBackground(Bg);
    }
    private void setBackroundHover() {
        String bg = null;
        if (Type == "Bob"){
            bg = "file:ressources/characters/BobHover.png";
        } else if (Type == "Chmdak"){
            bg = "file:ressources/characters/chmdakHover.png";
        } else if (Type == "Zexreen"){
            bg = "file:ressources/characters/zexreenHover.png";
        }else if (Type == "Fabriquant"){
            bg = "file:ressources/characters/FabriquantHover.png";
        }else if (Type == "Marchand"){
            bg = "file:ressources/characters/MarchandHover.png";
        }else if (Type == "Bibliothequaire"){
            bg = "file:ressources/characters/BibliothequaireHover.png";
        }else if (Type == "Fusee"){
            bg = "file:ressources/misc/rocketHover.png";
        }else if (Type == "Cata"){
            bg = "file:ressources/misc/catalyseurHover.png";
        }else if (Type == "Box"){
            bg = "file:ressources/misc/boxHover.png";
        }

        Background Bg = new Background(new BackgroundImage((new Image(bg, Width, Height, false, true))
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
