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
import model.CustomButton;
import model.Directions;
import model.InterractionButton;
import model.Zexreen;
import sample.Main;

public class Caverne extends Application{

    private AnchorPane APane;
    private static final String SCENE_BACK_GROUND = "file:ressources/maps/Caverne.png";

    // This method, when called, will receive the original primary stage
// on which a new scene will then be attached
    public void start(Stage stage)
    {
        Label lbl = new Label("Zone 6 - Caverne du Chmdak.");
        APane = new AnchorPane();
        APane.getChildren().add(lbl);

        BackgroundImage Bg= new BackgroundImage(new Image(SCENE_BACK_GROUND, 1280, 720, false, true)
                , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        APane.setBackground(new Background(Bg));


        zexreenButton();
        mouseListener();
        chmdakInterraction();
        cataInterraction();
        BobInterraction();

        Scene scene = new Scene(APane, 1280, 720);
        stage.setScene(scene);
        stage.show();
    }

    private void cataInterraction() {
        InterractionButton btn = new InterractionButton("Cata", 250, 182);
        btn.setLayoutX(145);
        btn.setLayoutY(380);
        APane.getChildren().add(btn);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("cata");
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

                ZexreenView zexreenScene = new ZexreenView();
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
                System.out.println("X: "+mouseEvent.getX()+", Y: "+ mouseEvent.getY());
            }
        });
    }
}