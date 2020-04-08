package model;

import java.util.Random;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BattleSceneZex extends SubScene {
    private final static String FONT = "ressources/space-wham.ttf";
    private final static String BACKGROUND_IMAGE = "file:ressources/BattleScene.png";
    private Bob Bob;
    private Zexreen Zexreen;
    private boolean yourTurn = true;
    private boolean isHidden;
    private AnchorPane Root;
    private Label Ammo;
    private Label BobPv;
    private Label ZexPv;
    private  Label Info;

    public BattleSceneZex(Bob bob, Zexreen zex){
        super(new AnchorPane(), 600, 500);
        Bob = bob;
        Zexreen = zex;

        prefWidth(600);
        prefHeight(500);
        BackgroundImage bg= new BackgroundImage(new Image(BACKGROUND_IMAGE, 600, 500, false, true)
                , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        Root = (AnchorPane) this.getRoot();

        createLabels();
        createBlasterButton();
        createCDPButton();
        createSoinButton();


        Root.setBackground(new Background(bg ));


        setLayoutX(340);
        setLayoutY(750);
        isHidden = true;
        System.out.println("yo ");
    }

    private void createBlasterButton() {
        CustomButton Blaster = new CustomButton("BLaster");
        Blaster.setLayoutX(50);
        Blaster.setLayoutY(400);
        Root.getChildren().add(Blaster);

        Blaster.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Bob.TirBlaster(Zexreen);
                Bob.setMunitons(Bob.getMunitons()-1);
                Ammo.setText("Ammo : "+ Bob.getMunitons());
                ZexPv.setText("Zexreen PV :  " +Zexreen.getPointsDeVie());
            }
        });
    }
    private void createCDPButton() {
        CustomButton CoupPoing = new CustomButton("Coup Poing");
        CoupPoing.setLayoutX(200);
        CoupPoing.setLayoutY(400);
        Root.getChildren().add(CoupPoing);

        CoupPoing.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Bob.CoupDePoing(Zexreen);
                ZexPv.setText("Zexreen PV :  " +Zexreen.getPointsDeVie());
            }
        });

    }

    private void createSoinButton() {
        CustomButton Soin = new CustomButton("Soin");
        Soin.setLayoutX(350);
        Soin.setLayoutY(400);
        Root.getChildren().add(Soin);

        Soin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Bob.Soins();
                BobPv.setText("PV :  " +Bob.getPointsDeVie());
                zexreenTurn();
            }
        });

    }

    public void zexreenTurn(){
        Random rand = new Random();
        int Attack = rand.nextInt(4);
        // Zexreen a 1 chance sur 4 d'hypnotiser Bob
//         Sinon il utilise Yeux Laser
        if (Attack == 4){
            Zexreen.Hypnose(Bob);
            Info.setText("Zexreen a utilisé Hypnose !\n" +
                    "Vous vous blessez avec votre propre action ...");
        }else{
            Zexreen.YeuxLaser(Bob);
            Info.setText("Zexreen a utilisé Yeux Laser !\n" +
                    "Vous perdez 2 points de vie");
        }
        BobPv.setText("PV :  " +Bob.getPointsDeVie());
    }

    public void showScene(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.5));
        transition.setNode(this);

        if (isHidden){
            transition.setToY(-650);
            isHidden = false;
        }

        transition.play();
        System.out.println("show");
    }
    public void hideScene(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);
        if (!isHidden) {
            transition.setToY(0);
            isHidden = true;
        }

        transition.play();
    }

    public void createLabels(){
        Ammo = new Label("Ammo : " + Bob.getMunitons());
        Ammo.setMaxWidth(150);
        Ammo.setWrapText(true);
        setCustomFont(Ammo);

        BobPv = new Label("PV : " + Bob.getPointsDeVie());
        BobPv.setMaxWidth(150);
        BobPv.setWrapText(true);
        setCustomFont(BobPv);

        ZexPv = new Label("Zexreen PV : " + Zexreen.getPointsDeVie());
        ZexPv.setMaxWidth(150);
        ZexPv.setWrapText(true);
        setCustomFont(ZexPv);

        Info = new Label("");
        Info.setMaxWidth(500);
        Info.setWrapText(true);
        setCustomFont(Info);

        BobPv.setLayoutX(480);
        BobPv.setLayoutY(400);
        Ammo.setLayoutX(480);
        Ammo.setLayoutY(420);
        ZexPv.setLayoutX(400);
        ZexPv.setLayoutY(30);
        Info.setLayoutX(50);
        Info.setLayoutY(50);


        Root.getChildren().add(BobPv);
        Root.getChildren().add(ZexPv);
        Root.getChildren().add(Ammo);

    }



    public void setCustomFont(Label lbl){
        try{
            lbl.setFont(Font.loadFont(new FileInputStream(FONT), 12));
        } catch (FileNotFoundException e){
            lbl.setFont(Font.loadFont("Verdana", 12));
        }
        lbl.setTextFill(Color.WHITE);
    }
}
