package model;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Dialogue extends SubScene {
    private final static String FONT = "ressources/space-wham.ttf";
    private final static String BACKGROUND_IMAGE = "file:ressources/DialogueBox.png";

    private boolean isHidden;

    public Dialogue(String text) throws FileNotFoundException {

        super(new AnchorPane(), 600, 140);
        prefWidth(600);
        prefHeight(140);
        BackgroundImage bgDiloques= new BackgroundImage(new Image(BACKGROUND_IMAGE, 600, 140, false, true)
                , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        Label Text = new Label(text);
        Text.setMaxWidth(580);
        Text.setWrapText(true);
        Text.setPadding(new Insets(10,30,30,30));
        Text.setTextFill(Color.WHITE);
        try{
            Text.setFont(Font.loadFont(new FileInputStream(FONT), 12));
        } catch (FileNotFoundException e){
            Text.setFont(Font.loadFont("Verdana", 12));
        }


        AnchorPane Root2 = (AnchorPane) this.getRoot();
        Root2.setBackground(new Background(bgDiloques ));
        Root2.getChildren().add(Text);

        setLayoutX(0);
        setLayoutY(-180);
        isHidden = true;

    }

    public void showDialogue(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        if (isHidden){
            transition.setToY(200);
            isHidden = false;
        }

        transition.play();
    }
    public void hideDialogue(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);
        if (!isHidden) {
            transition.setToY(0);
            isHidden = true;
        }

        transition.play();

    }
}
