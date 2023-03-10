import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    private Game game;
    private GraphicsContext gc;
    private Snake snake;
    private boolean inputValid;


    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(200, 200);

        gc = canvas.getGraphicsContext2D();

        scene.setOnKeyPressed(e -> {
            if (!game.getKeyPressed()) {
                switch (e.getCode()) {
                    case UP:
                        inputValid = snake.goUp();
                        break;
                    case DOWN:
                        inputValid = snake.goDown();
                        break;
                    case LEFT:
                        inputValid = snake.goLeft();
                        break;
                    case RIGHT:
                        inputValid = snake.goRight();
                        break;

                    case ENTER:
                        inputValid = false;
                        if (!game.isRunning()) {
                            game.reset();
                        }
                        break;
                }

            }
            /*
            KeyPressed-muuttuja est?? kahden sy?tteen rekister?innin saman peliloopin kierroksen aikana.
            Jos k?ytt?j?n sy?tett? ei kuitenkaan kelpuuteta (sy?te vasemmalle, kun mato liikkuu oikealle),
            hyv?ksyt??n k?ytt?j?n seuraavakin sy?te.
             */
            game.setKeyPressed(inputValid);
        });


        snake = new Snake();
        game = new Game(snake, gc);

        root.getChildren().add(canvas);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Snake");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}


