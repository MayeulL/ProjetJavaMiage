package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;
import sample.Main;

public class ZexreenView extends Application{

    private AnchorPane APane;
    private Stage CurentStage;
    private static final String SCENE_BACK_GROUND = "file:ressources/maps/Zexreen.png";
    private Zexreen Zexreen;
    public Bob Bob;

    public ZexreenView(Bob bob){
        Bob = bob;
    }

    // This method, when called, will receive the original primary stage
// on which a new scene will then be attached
    public void start(Stage stage)
    {
        Label lbl = new Label("Zone 5 - Camps de Zexreen.");
        APane = new AnchorPane();
        APane.getChildren().add(lbl);

        vaisseauButton();
        chmdakButton();
        ZexreenInterraction();
        BobInterraction();
        InstanciateCharacters();
        mouseListener();


        BackgroundImage Bg= new BackgroundImage(new Image(SCENE_BACK_GROUND, 1280, 720, false, true)
                , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        APane.setBackground(new Background(Bg));

        Scene scene = new Scene(APane, 1280, 720);
        stage.setScene(scene);
        stage.show();


    }

    private void InstanciateCharacters() {
        Zexreen = new Zexreen("Zexreen", 20);

    }

    private void ZexreenInterraction() {
        InterractionButton btn = new InterractionButton("Zexreen", 112, 160);
        btn.setLayoutX(530);
        btn.setLayoutY(490);
        APane.getChildren().add(btn);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (Bob.HasObject(1)){
                    System.out.println("Quoi  un récipient pour ton carburant? Haha jamais je te le donnerais! Tiens prend ça !");
                    // trigger the fight
                    Bob.gainObect(2);
                } else if(Bob.HasObject(2)){
                    System.out.println("AIE AIE AIE Ok ! Tiens voilà le récipient et maintenant hors de ma vue !");
                }
                else {
                    System.out.println("D'où tu viens toi ? Passe ton chemin étranger si tu veux rester en vie !");
                }
                System.out.println("Zexreen");
            }
        });
    }

    private void BobInterraction() {
        InterractionButton btn = new InterractionButton("Bob", 91, 144);
        btn.setLayoutX(580);
        btn.setLayoutY(230);
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
                System.out.println("X: "+mouseEvent.getX()+", Y: "+ mouseEvent.getY());
            }
        });
    }
    private void vaisseauButton(){
        Directions DirButton = new Directions("Up");
        DirButton.setLayoutY(20);
        DirButton.setLayoutX(604);
        APane.getChildren().add(DirButton);

        DirButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Instantiate the class that creates a new scene
                // Call method in newly instantiated class, passing primaryStage to it
                Vaisseau vaisseauScene = new Vaisseau(Bob);
                CurentStage = (Stage) APane.getScene().getWindow();
                try {
                    vaisseauScene.start(CurentStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void chmdakButton(){
        Directions DirButton = new Directions("Left");
        DirButton.setLayoutY(323);
        DirButton.setLayoutX(20);
        APane.getChildren().add(DirButton);

        DirButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Instantiate the class that creates a new scene
                // Call method in newly instantiated class, passing primaryStage to it
                Caverne caverneScene = new Caverne(Bob);
                CurentStage = (Stage) APane.getScene().getWindow();
                try {
                    caverneScene.start(CurentStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}