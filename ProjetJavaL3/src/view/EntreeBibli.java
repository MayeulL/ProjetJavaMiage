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

public class EntreeBibli extends Application{

    private AnchorPane APane;
    private static final String SCENE_BACK_GROUND = "file:ressources/maps/Bibliotheque.png";
    private Dialogue DialogueBox;
    public Bob Bob;

    public EntreeBibli(Bob bob){
        Bob = bob;
    }

    // This method, when called, will receive the original primary stage
// on which a new scene will then be attached
    public void start(Stage stage) throws FileNotFoundException {
        Label lbl = new Label("Zone 3 - EntreeBibli.");
        APane = new AnchorPane();
        APane.getChildren().add(lbl);

        BackgroundImage Bg= new BackgroundImage(new Image(SCENE_BACK_GROUND, 1280, 720, false, true)
                , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        APane.setBackground(new Background(Bg));

        villageButton();
        BibliInterraction();
        BobInterraction();
        createDialogueBiblio();
        mouseListener();

        Scene scene = new Scene(APane, 1280, 720);
        stage.setScene(scene);
        stage.show();


    }

    private void createDialogueBiblio() throws FileNotFoundException {
        if (Bob.HasObject(4)){
            DialogueBox = new Dialogue("Bon retour !");
        } else {
            DialogueBox = new Dialogue("Bien le bonjour. Oui tout à fait j'ai ce qu'il te faut il me semble ... " +
                    "Laisse moi fouiller ce tas de feuille... Ah ! La voilà! J'espère qu vous réeussirez " +
                    "à repartir sans encombre!");
        }

        APane.getChildren().add(DialogueBox);

    }

    private void BibliInterraction() {
        InterractionButton btn = new InterractionButton("Bibliothequaire", 96, 144);
        btn.setLayoutX(615);
        btn.setLayoutY(485);
        APane.getChildren().add(btn);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (Bob.HasObject(4)){
                    try {
                        createDialogueBiblio();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }else{
                    Bob.gainObect(4);
                }
                DialogueBox.showDialogue();

                System.out.println("Akhon");
            }
        });
    }

    private void BobInterraction() {
        InterractionButton btn = new InterractionButton("Bob", 91, 144);
        btn.setLayoutX(750);
        btn.setLayoutY(511);
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
                DialogueBox.hideDialogue();
                System.out.println("X: "+mouseEvent.getX()+", Y: "+ mouseEvent.getY());
            }
        });
    }

    private void villageButton(){
        Directions DirButton = new Directions("Left");
        DirButton.setLayoutX(20);
        DirButton.setLayoutY(323);
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
}
