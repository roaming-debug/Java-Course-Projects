import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import javafx.geometry.Pos;
import java.util.Random;

public class DessertGame extends Application {
    private int score = 0;

    private void randomizeButtonPositions(Random random, Button [] buttons) {
        for(Button button: buttons) {
            button.setLayoutX(random.nextInt(601));
            button.setLayoutY(random.nextInt(401));
        }
    }

    @Override
    public void start(final Stage stage) {
        // Step 3 & 4
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 640, 480);
        stage.setTitle("Dessert in the Desert JavaFX Game");
        
        // Step 5
        Label scoreLabel = new Label("Score: 0");
        borderPane.setTop(scoreLabel);
        BorderPane.setAlignment(scoreLabel, Pos.TOP_LEFT);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> {
            Platform.exit();
        });
        exitButton.requestFocus();

        borderPane.setBottom(exitButton);
        BorderPane.setAlignment(exitButton, Pos.BOTTOM_RIGHT);
        
        // Step 6
        Pane pane = new Pane();
        borderPane.setCenter(pane);
        BorderPane.setAlignment(pane, Pos.CENTER);

        // TODO: Step 7-10
        // Step 7
        Button dessertButton = new Button("Dessert");
        Button desertButton1 = new Button("Desert");
        Button desertButton2 = new Button("Desert");
        Button desertButton3 = new Button("Desert");
        Button desertButton4 = new Button("Desert");
        Button desertButton5 = new Button("Desert");
        Button desertButton6 = new Button("Desert");
        Button desertButton7 = new Button("Desert");
        Button desertButton8 = new Button("Desert");
        Button [] buttons = new Button[]{dessertButton, desertButton1, desertButton2, desertButton3, desertButton4,
                desertButton5, desertButton6, desertButton7, desertButton8};
        randomizeButtonPositions(new Random(), buttons);
        for (Button button : buttons) {
            pane.getChildren().add(button);
            System.out.println();
            if(button.getText().equals("Desert")) {
                button.setOnAction(e -> {
                    randomizeButtonPositions(new Random(), buttons);
                    score--;
                    scoreLabel.setText("Score: " + score);
                    exitButton.requestFocus();
                });
            } else {
                button.setOnAction(e -> {
                    randomizeButtonPositions(new Random(), buttons);
                    score++;
                    scoreLabel.setText("Score: " + score);
                    exitButton.requestFocus();
                });
            }

        }
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
