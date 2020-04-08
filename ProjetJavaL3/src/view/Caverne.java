package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
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

public class Caverne extends Application{

    private AnchorPane APane;
    private static final String SCENE_BACK_GROUND = "file:ressources/maps/Caverne.png";
    private Dialogue DialogueBox;
    private Dialogue DialogueBoxChmdak;
    private BattleSceneChmdak BattleScene;
    public Bob Bob;
    public Chmdak Chmdak;

    public Caverne(Bob bob){
        Bob = bob;
    }

    // This method, when called, will receive the original primary stage
// on which a new scene will then be attached
    public void start(Stage stage) throws FileNotFoundException {
        Label lbl = new Label("Zone 6 - Caverne du Chmdak.");
        APane = new AnchorPane();
        APane.getChildren().add(lbl);

        BackgroundImage Bg= new BackgroundImage(new Image(SCENE_BACK_GROUND, 1280, 720, false, true)
                , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        APane.setBackground(new Background(Bg));

        Chmdak = new Chmdak("Chmdak", 20);
        zexreenButton();
        mouseListener();
        chmdakInterraction();
        cataInterraction();
        createDialogueChmdak();
        createBattleScene();
        BobInterraction();



        Scene scene = new Scene(APane, 1280, 720);
        stage.setScene(scene);
        stage.show();
    }

    private void createBattleScene() {
        BattleScene = new BattleSceneChmdak(Bob, Chmdak);
        APane.getChildren().add(BattleScene);
    }

    private void createDialogueChmdak() throws FileNotFoundException {

        if(!Bob.HasObject(3)){
            DialogueBox = new Dialogue("GRAAAOUUU !! ");
        }else{
            DialogueBox = new Dialogue("Vous ramasser non sans dégoût le fameux cataliseur");
        }

        DialogueBoxChmdak = new Dialogue("Graou... !");



        APane.getChildren().add(DialogueBox);
        APane.getChildren().add(DialogueBoxChmdak);

    }




    private void cataInterraction() {
        InterractionButton btn = new InterractionButton("Cata", 250, 182);
        btn.setLayoutX(145);
        btn.setLayoutY(380);
        APane.getChildren().add(btn);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //trigger the fight
                if (!Bob.HasObject(3)){
                    Bob.gainObect(3);
                    BattleScene.showScene();
                    DialogueBox.showDialogue();
                }else{
                    try {
                        createDialogueChmdak();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    private void BobInterraction() {
        InterractionButton btn = new InterractionButton("Bob", 91, 144);
        btn.setLayoutX(1060);
        btn.setLayoutY(465);
        APane.getChildren().add(btn);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Bob !");
            }
        });
    }

    private void chmdakInterraction() {
        InterractionButton btn = new InterractionButton("Chmdak", 350, 238);
        btn.setLayoutX(530);
        btn.setLayoutY(385);
        APane.getChildren().add(btn);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               DialogueBoxChmdak.showDialogue();
                System.out.println("Chmdak");
            }
        });
    }

    private void zexreenButton(){
        Directions DirButton = new Directions("Right");
        DirButton.setLayoutY(323);
        DirButton.setLayoutX(1180);
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

    private void mouseListener() {
        APane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                DialogueBox.hideDialogue();
                DialogueBoxChmdak.hideDialogue();
                System.out.println("X: "+mouseEvent.getX()+", Y: "+ mouseEvent.getY());
            }
        });
    }
}