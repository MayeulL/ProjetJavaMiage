package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;
import sample.Main;

import java.io.FileNotFoundException;

public class Vaisseau extends Application{

    private AnchorPane APane;
    private Dialogue FuseeDialogue;
    private Dialogue BoxDialogue;
    private static final String SCENE_BACK_GROUND = "file:ressources/maps/Vaisseau.png";
    private boolean FinDuGame = false;
    public Bob Bob;
    private Stage thisStage;

    public Vaisseau(Bob bob){
        Bob = bob;
    }

    // This method, when called, will receive the original primary stage
    // on which a new scene will then be attached
    public void start(Stage stage) throws FileNotFoundException {
        Label lbl = new Label("Zone 1 - Vaisseau.");
        APane = new AnchorPane();
        APane.getChildren().add(lbl);
        thisStage = stage;

        villageButton();
        zexreenButton();
        fuseeInterraction();
        BobInterraction();
        boxInterraction();
        createDialoguePanel();
        mouseListener();


        BackgroundImage Bg= new BackgroundImage(new Image(SCENE_BACK_GROUND, 1280, 720, false, true)
                , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        APane.setBackground(new Background(Bg));

        Scene scene = new Scene(APane, 1280, 720);
        stage.setScene(scene);
        stage.show();


    }

    private void createDialoguePanel() throws FileNotFoundException {
        if (Bob.HasObject(5)){
            FuseeDialogue = new Dialogue("Bien maintenant que le réservoir est réparé, on peut décoller !");

        }else{
            FuseeDialogue = new Dialogue("Bonjour Bob, nous avons eu de la chance de trouver cette planète pour " +
                    "y atterrir d'urgence ! Il semble en revanche que le reservoir de " +
                    "carburant ait été endomagé pendant l'atterrissage, il va falloir " +
                    "le remplacer.");
        }

        if (!Bob.HasObject(0)){
            BoxDialogue = new Dialogue("Vous avez ramassé un peu de ferraille de votre vaisseau");
        }else {
            BoxDialogue = new Dialogue("Il n'y a plus rien d'exploitable ici...");
        }

        APane.getChildren().add(FuseeDialogue);
        APane.getChildren().add(BoxDialogue);

    }

    private void BobInterraction() {
        InterractionButton btn = new InterractionButton("Bob", 91, 144);
        btn.setLayoutX(540);
        btn.setLayoutY(402);
        APane.getChildren().add(btn);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (Bob.HasObject(3)){
                    System.out.println("cataliseur !");
                }else {
                    System.out.println("Bob !");
                }

            }
        });
    }

    private void fuseeInterraction() {
        InterractionButton btn = new InterractionButton("Fusee", 215, 300);
        btn.setLayoutX(550);
        btn.setLayoutY(150);
        APane.getChildren().add(btn);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FuseeDialogue.showDialogue();
                if (Bob.HasObject(5)){
                    FinDuGame = true;
                }
                System.out.println("fusee");
            }
        });
    }

    private void boxInterraction() {
        InterractionButton btn = new InterractionButton("Box", 100, 100);
        btn.setLayoutX(450);
        btn.setLayoutY(260);
        APane.getChildren().add(btn);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!Bob.HasObject(0)){
                    Bob.gainObect(0);
                } else {
                    try {
                        createDialoguePanel();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                BoxDialogue.showDialogue();
                System.out.println("Box");
                System.out.println(Bob.HasObject(0));
            }
        });
    }



    private void villageButton(){
        Directions DirButton = new Directions("Right");
        DirButton.setLayoutY(323);
        DirButton.setLayoutX(1180);
        APane.getChildren().add(DirButton);

        DirButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Instantiate the class that creates a new scene
                // Call method in newly instantiated class, passing primaryStage to it

                Village villageScene = new Village(Bob);
                Stage CurentStage = (Stage) APane.getScene().getWindow();
                try {
                    villageScene.start(CurentStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void mouseListener() {
        APane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FuseeDialogue.hideDialogue();
                BoxDialogue.hideDialogue();

                System.out.println("X: "+mouseEvent.getX()+", Y: "+ mouseEvent.getY());
                if (FinDuGame){
                    thisStage.close();
                }
                System.out.println("fer : " +Bob.HasObject(0));
                System.out.println("Carb : " +Bob.HasObject(1));
                System.out.println("Recipient : " +Bob.HasObject(2));
                System.out.println("Cata : " +Bob.HasObject(3));
                System.out.println("Process : " +Bob.HasObject(4));
                System.out.println("Full stuff : " +Bob.HasObject(5));
            }
        });
    }

    private void zexreenButton(){
        Directions DirButton = new Directions("Down");
        DirButton.setLayoutY(622);
        DirButton.setLayoutX(604);
        APane.getChildren().add(DirButton);

        DirButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Instantiate the class that creates a new scene
                // Call method in newly instantiated class, passing primaryStage to it

                ZexreenView zexreenScene = new ZexreenView(Bob);
                Stage CurentStage = (Stage) APane.getScene().getWindow();
                try {
                    zexreenScene.start(CurentStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}