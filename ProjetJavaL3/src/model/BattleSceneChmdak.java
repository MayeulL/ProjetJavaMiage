package model;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class BattleSceneChmdak extends SubScene {
    private final static String FONT = "ressources/space-wham.ttf";
    private final static String BACKGROUND_IMAGE = "file:ressources/BattleScene.png";
    private Bob Bob;
    private Chmdak Chmdak;
    private boolean yourTurn = true;
    private boolean isHidden;
    private AnchorPane Root;
    private Label Ammo;
    private Label BobPv;
    private Label ChmdakPv;
    private Label Info;

    public BattleSceneChmdak(Bob bob, Chmdak chmdak){
        super(new AnchorPane(), 600, 500);
        Bob = bob;
        Chmdak = chmdak;

        prefWidth(600);
        prefHeight(500);
        BackgroundImage bg= new BackgroundImage(new Image(BACKGROUND_IMAGE, 600, 500, false, true)
                , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        Root = (AnchorPane) this.getRoot();

        createLabels();
        createBlasterButton();
        createCDPButton();
        createSoinButton();
        createOkButton();


        Root.setBackground(new Background(bg ));


        setLayoutX(340);
        setLayoutY(750);
        isHidden = true;
        System.out.println("yo ");
    }

    private void createOkButton() {
        CustomButton Ok = new CustomButton("Fin Tour ");
        Ok.setLayoutX(450);
        Ok.setLayoutY(350);
        Root.getChildren().add(Ok);

        Ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!Bob.isVivant()){
                    Stage stage = (Stage) Ok.getScene().getWindow();
                    stage.close();
                }
                if(!yourTurn) {
                    ChmdakTurn();
                }
            }
        });
    }

    private void createBlasterButton() {
        CustomButton Blaster = new CustomButton("BLaster");
        Blaster.setLayoutX(50);
        Blaster.setLayoutY(400);
        Root.getChildren().add(Blaster);

        Blaster.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(yourTurn) {
                    if (Bob.isHyponotise()) {
                        estHyptontise();
                    } else {
                        Bob.TirBlaster(Chmdak);
                        Ammo.setText("Ammo : " + Bob.getMunitons());
                        ChmdakPv.setText("Chmdak PV :  " + Chmdak.getPointsDeVie());
                        Info.setText("Vous avez utilisé Blaster !\n" +
                                "Chmdak perd 1 par tour pour 2 tours");

                        yourTurn = false;
                    }
                }
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
                if(yourTurn) {
                    if (Bob.isHyponotise()) {
                        estHyptontise();
                    } else {
                        Bob.CoupDePoing(Chmdak);
                        ChmdakPv.setText("Chmdak PV :  " + Chmdak.getPointsDeVie());
                        Info.setText("Vous avez utilisé Coup de Poing !\n" +
                                "Chmdak perd 2 points de vie");

                        yourTurn = false;
                    }
                }
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
                if(yourTurn) {
                    if (Bob.isHyponotise()){
                        estHyptontise();
                    }else {
                        Bob.Soins();
                        BobPv.setText("PV :  " + Bob.getPointsDeVie());

                        Info.setText("Vous avez utilisé Soins !\n" +
                                "Vous gagnez 3 points de vie");

                        yourTurn = false;
                    }
                }
            }
        });

    }

    public void estHyptontise(){
        Info.setText("Vous vous blessez avec votre propre action ...");
        yourTurn = false;
        Bob.setHyponotise(false);
    }

    public void ChmdakTurn(){
        Random rand = new Random();
        int Attack;
        if (Chmdak.getPointsDeVie() < 10 && !Chmdak.isEnrage()) {
            Chmdak.setEnrage(true);
            Info.setText("Le Chmdak devient enragé !");
            yourTurn = true;
        }
        if (Chmdak.isEnrage()){
            Attack = rand.nextInt(6);
        }else {
            Attack = rand.nextInt(4);
        }

        if (!Chmdak.isVivant()){
            hideScene();
        }

        if (Attack == 3 || Attack == 4){
            Chmdak.Charge(Bob);
            Info.setText("Chmdak a utilisé Charge !\n" +
                    "Vous perdez 3 points de vie mais il se blesse légèrement !");
        }else if(Attack == 6) {
            Chmdak.Meditation();
            Info.setText("Chmdak a utilisé Méditation !\n" +
                    "Il regagne 3 points de vie");
        } else{
            Chmdak.Fouet(Bob);
            Info.setText("Chmdak a utilisé Fouet !\n" +
                    "Vous perdez 2 points de vie");
        }

        Chmdak.Bruler();
        ChmdakPv.setText("Chmdak PV :  " +Chmdak.getPointsDeVie());
        BobPv.setText("PV :  " +Bob.getPointsDeVie());

        yourTurn = true;
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

        ChmdakPv = new Label("Chmdak PV : " + Chmdak.getPointsDeVie());
        ChmdakPv.setMaxWidth(150);
        ChmdakPv.setWrapText(true);
        setCustomFont(ChmdakPv);

        Info = new Label("");
        Info.setMaxWidth(500);
        Info.setWrapText(true);
        setCustomFont(Info);

        BobPv.setLayoutX(480);
        BobPv.setLayoutY(400);
        Ammo.setLayoutX(480);
        Ammo.setLayoutY(420);
        ChmdakPv.setLayoutX(400);
        ChmdakPv.setLayoutY(30);
        Info.setLayoutX(50);
        Info.setLayoutY(50);


        Root.getChildren().add(BobPv);
        Root.getChildren().add(ChmdakPv);
        Root.getChildren().add(Ammo);
        Root.getChildren().add(Info);

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

