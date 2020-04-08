package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CustomButton;
import model.Objets;
import view.Vaisseau;
import model.Bob;

public class Main extends Application {

    private static final int HAUTEUR = 600;
    private static final int LARGEUR = 1200;

    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    public Bob Bob;
    public Objets Cataliseur;

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            mainPane = new AnchorPane();
            mainScene = new Scene( mainPane, LARGEUR, HAUTEUR);
            mainStage = new Stage();
            mainStage.setScene(mainScene);
            createPlayButton();
            createExitButton();
            SetTitle();

            InitializeBob();

            primaryStage = mainStage;
            primaryStage.show();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void InitializeBob() {
        Bob = new Bob("Bob", 20, 5);
    }

//    public Bob getBob(){
//        return Bob;
//    }

    private void createPlayButton(){
        CustomButton playButton = new CustomButton("Jouer");
        playButton.setLayoutY(150);
        playButton.setLayoutX(150);
        mainPane.getChildren().add(playButton);

        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Instantiate the class that creates a new scene
                // Call method in newly instantiated class, passing primaryStage to it
                Vaisseau vaisseauScene = new Vaisseau(Bob);
                vaisseauScene.start(mainStage);
            }
        });
    }

    private void createExitButton(){
        CustomButton ExitButton = new CustomButton("Quitter");
        ExitButton.setLayoutY(200);
        ExitButton.setLayoutX(150);
        mainPane.getChildren().add(ExitButton);

        ExitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.close();
            }
        });

    }

    private void SetTitle(){
        ImageView titre = new ImageView("file:ressources/titre.png");
        titre.setLayoutX(10);
        titre.setLayoutY(10);
        titre.setFitHeight(100);

        mainPane.getChildren().add(titre);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
