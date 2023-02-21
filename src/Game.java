import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class Game {

    private Snake snake;
    private GraphicsContext gc;
    private Point food;
    private boolean running;
    private boolean keyPressed;
    private AnimationTimer timer;

    public Game(Snake snake, GraphicsContext gc) {
        this.snake = snake;
        this.gc = gc;
        initGame();
    }

    public void initGame() {

        running = true;
        keyPressed = false;
        //M‰‰ritet‰‰n ruoalle koordinaatit
        food = snake.getPointOutsideSnake();
        //Piirret‰‰n peliruutu
        Painter.paint(gc, food, snake);
        //Luodataan ajastin, joka p‰ivitt‰‰ peli‰
        timer = new AnimationTimer() {

            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (!running) {
                    this.stop();
                }
                if (now - lastUpdate >= 100_000_000) {
                    updateGame();
                    lastUpdate = now;
                }
            }
        };
        timer.start();
    }


    public void updateGame() {
        if (running) {
            //Resetoidaan keyPressed, jotta k‰ytt‰j‰n syˆte hyv‰ksyt‰‰n taas
            keyPressed = false;
            //Pit‰‰ madon kasassa alussa. Ilman t‰t‰ h‰nn‰st‰ siirtyisi joka p‰ivityksess‰ ruutu madon p‰‰n kanssa p‰‰llekk‰in.
            if (snake.getyVelocity() != 0 || snake.getxVelocity() != 0) {

                snake.update();
                if (food.getX() == snake.getSnakeHead().getX() && food.getY() == snake.getSnakeHead().getY()) {
                    food = snake.getPointOutsideSnake();
                    snake.grow();
                }
                if (snake.isSnakeDead()) {
                    running = false;
                    keyPressed = false;
                    Painter.paintDeath(gc, snake);
                    return;

                }
                //P‰ivitet‰‰n peliruudun kuva
                Painter.paint(gc, food, snake);
            }
        }
    }

    public void reset() {

        snake.reset();
        initGame();
    }

    public Snake getSnake() {
        return snake;
    }

    public void setRunning(boolean b) {
        running = b;
    }

    public boolean isRunning() {
        return running;
    }

    public void setKeyPressed(boolean b) {
        keyPressed = b;
    }

    public boolean getKeyPressed() {
        return keyPressed;
    }
}
