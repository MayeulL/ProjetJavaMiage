package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;
import sample.Main;

import java.io.FileNotFoundException;

public class Village extends Application{

    private AnchorPane APane;
    private Personnage Ristal;
    private Personnage Ruye;
    private Dialogue RistalDialogueBox;
    private Dialogue RuyeDialogueBox;
    public Bob Bob;

    private static final String SCENE_BACK_GROUND = "file:ressources/maps/Village.png";


    public Village(Bob bob){
        Bob = bob;
    }
    // This method, when called, will receive the original primary stage
// on which a new scene will then be attached
    public void start(Stage stage) throws FileNotFoundException {
        Label lbl = new Label("Zone 2 - Village.");
        APane = new AnchorPane();
        APane.getChildren().add(lbl);

        BackgroundImage Bg= new BackgroundImage(new Image(SCENE_BACK_GROUND, 1280, 720, false, true)
                , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        APane.setBackground(new Background(Bg));

        entreeBibliButton();
        vaisseauButton();
        fabriquantInterraction();
        marchandInterraction();
        BobInterraction();
        InstanciateCharacters();
        createDialogueVillage();
        mouseListener();

        Scene scene = new Scene(APane, 1280, 720);
        stage.setScene(scene);
        stage.show();


    }

    private void InstanciateCharacters() {
        Ristal = new Personnage("Ristal", 1000);
        Ruye = new Personnage("Ruye", 1000);

    }

    private void createDialogueVillage() throws FileNotFoundException {
        if(Bob.HasObject(0) && Bob.isRuyeHasSpoken()){
            RuyeDialogueBox = new Dialogue(" Parfait ! voilà ton Carburium en échange de ton Fer. Va voir Ristal pour la fabrication, " +
                    "ca dépasse mes compétences.");

        }else{
            RuyeDialogueBox = new Dialogue("Bonjour ! Oh tu cherches du carburant ? je peux te fournir un peu de carburium nécessaire en " +
                    "échange de fer Terrestre. Il te faudra également un catalyseur par contre. Tu peux utiliser des " +
                    "excréments de Chmdak pour ça. Il y en a justement un dans cette grote à l'ouest ! Bonne chance !");
        }

        if (Bob.HasObject(2) && Bob.HasObject(3) && Bob.HasObject(4) && Bob.isRistalHasSpoken()){
            RistalDialogueBox = new Dialogue("Parfait tu as tout les ingrédients et le processus de fabrication ! Laisse moi faire le mélange ..." +
                    "Hop un peu de ça ici... un dernier coup de tournevis ... Et voilà !Ton résevoir devrai etr opérationnel.");
            Bob.gainObect(5);
        } else {
            RistalDialogueBox = new Dialogue("Bonjour ! Oh tu veux construire un reservoir de carbuant. Il va me falloir du Carburium, \n" +
                    "un catalyseur et un récipient. Je crois que Zexreen en a un mais méfie toi de lui, il n'est pas aussi\n" +
                    "accueillant que nous !. Il me faudra également le processus de fabrication, je pense que Akhon devrai\n" +
                    "avoir ça dans sa bibliothèque à l'est d'ici.");
        }

        APane.getChildren().add(RuyeDialogueBox);
        APane.getChildren().add(RistalDialogueBox);

    }

    private void marchandInterraction() {
        InterractionButton btn = new InterractionButton("Marchand", 96, 144);
        btn.setLayoutX(325);
        btn.setLayoutY(230);
        APane.getChildren().add(btn);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(Bob.HasObject(0) && Bob.isRuyeHasSpoken()){
                    Bob.gainObect(1);
                }else{
                    Bob.setRuyeHasSpoken(true);
                }
                RuyeDialogueBox.showDialogue();
                System.out.println("Ruye");
            }
        });
    }

    private void BobInterraction() {
        InterractionButton btn = new InterractionButton("Bob", 91, 144);
        btn.setLayoutX(711);
        btn.setLayoutY(300);
        APane.getChildren().add(btn);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Bob !");
            }
        });
    }

    private void mouseListener() {
        APane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                RuyeDialogueBox.hideDialogue();
                RistalDialogueBox.hideDialogue();
                System.out.println("X: "+mouseEvent.getX()+", Y: "+ mouseEvent.getY());
            }
        });
    }

    private void fabriquantInterraction() {
        InterractionButton Fabriquant = new InterractionButton("Fabriquant", 96, 144);
        Fabriquant.setLayoutX(550);
        Fabriquant.setLayoutY(550);
        APane.getChildren().add(Fabriquant);

        Fabriquant.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (Bob.HasObject(2) && Bob.HasObject(3) && Bob.HasObject(4) && Bob.isRistalHasSpoken()){
                    Bob.gainObect(5);
                } else {
                    Bob.setRistalHasSpoken(true);
                }

                RistalDialogueBox.showDialogue();
                System.out.println("Ristal");
            }
        });
    }

    private void entreeBibliButton(){
        Directions DirButton = new Directions("Right");
        DirButton.setLayoutY(323);
        DirButton.setLayoutX(1188);
        APane.getChildren().add(DirButton);

        DirButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Instantiate the class that creates a new scene
                // Call method in newly instantiated class, passing primaryStage to it
                EntreeBibli entreeBibliScene = new EntreeBibli(Bob);
                Stage CurentStage = (Stage) APane.getScene().getWindow();
                try {
                    entreeBibliScene.start(CurentStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void vaisseauButton(){
        Directions DirButton = new Directions("Left");
        DirButton.setLayoutY(323);
        DirButton.setLayoutX(20);
        APane.getChildren().add(DirButton);

        DirButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Instantiate the class that creates a new scene
                // Call method in newly instantiated class, passing primaryStage to it
                Vaisseau vaisseauScene = new Vaisseau(Bob);
                Stage CurentStage = (Stage) APane.getScene().getWindow();
                try {
                    vaisseauScene.start(CurentStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}