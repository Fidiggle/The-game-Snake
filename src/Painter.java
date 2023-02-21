import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Painter {

    public static void paint(GraphicsContext gc, Point food, Snake snake) {
        paintBackground(gc);
        paintSnake(gc, snake);
        paintFood(gc, food);
    }

    private static void paintBackground(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 200, 200);
    }

    private static void paintSnake(GraphicsContext gc, Snake snake) {
        gc.setFill(Color.LIGHTGRAY);
        for (Point p : snake.getSnakePoints()) {
            gc.fillRect(10 * p.getX(), 10 * p.getY(), 10, 10);
        }
    }

    private static void paintFood(GraphicsContext gc, Point p) {
        gc.setFill(Color.RED);
        gc.fillRect(10 * p.getX(), 10 * p.getY(), 10, 10);
    }

    public static void paintDeath(GraphicsContext gc, Snake snake) {
        Point head = snake.getSnakePoints().get(1);
        gc.setFill(Color.DARKRED);
        gc.fillRect(10 * head.getX(), 10 * head.getY(), 10, 10);

    }
}

