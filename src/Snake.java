import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Snake {
    private int xVelocity;
    private int yVelocity;
    private ArrayList<Point> snakePoints;

    public Snake() {
        xVelocity = 0;
        yVelocity = 0;
        snakePoints = new ArrayList<Point>(Arrays.asList(new Point(4, 4),
                new Point(3, 4),
                new Point(2, 4)));
    }

    public void update() {
        /*
        K?yd??n madon pisteet listan lopusta alkaen (ensimm?inen alkio on madon p??).
        Jokainen piste saa sit? edelt?v?n pisteen koordinaatit, p??n koordinaatit p?ivitet??n momentumin mukaan.
         */
        for (int i = snakePoints.size() - 1; i > 0; i--) {
            snakePoints.get(i).setX(snakePoints.get(i - 1).getX());
            snakePoints.get(i).setY(snakePoints.get(i - 1).getY());
        }
        Point head = snakePoints.get(0);
        head.setX(head.getX() + xVelocity);
        head.setY(head.getY() + yVelocity);

    }

    public void grow() {
        snakePoints.add(new Point(
                snakePoints.get(snakePoints.size()-1).getX(),
                snakePoints.get(snakePoints.size()-1).getY()
        ));
    }

    /*Suunnanmuutosmetodit est?v?t suunnan muuttamisen nykyist? suuntaa vastaan,
      joten oikealle kulkeva mato ei voi muuttaa suuntaansa vasemmalle.
     */
    public boolean goUp() {
        if (yVelocity == 1) return false;
        yVelocity = -1;
        xVelocity = 0;
        return true;
    }

    public boolean goDown() {
        if (yVelocity == -1) return false;
        yVelocity = 1;
        xVelocity = 0;
        return true;
    }

    public boolean goRight() {
        if (xVelocity == -1) return false;
        xVelocity = 1;
        yVelocity = 0;
        return true;
    }

    public boolean goLeft() {
        if (xVelocity == 1 || (xVelocity == 0 && yVelocity == 0)) return false;
        xVelocity = -1;
        yVelocity = 0;
        return true;
    }

    public void reset() {
        xVelocity = 0;
        yVelocity = 0;
        snakePoints = new ArrayList<Point>(Arrays.asList(new Point(4, 4),
                new Point(3, 4),
                new Point(2, 4)));
    }

    //Onko madon p?? h?nn?n pisteiden listassa
    public boolean isSnakeDead() {
        Point head = getSnakeHead();
        return (isHeadInsideSnake() || head.getY() < 0 || head.getY() > 19 || head.getX() < 0 || head.getX() > 19);
    }

    public boolean isHeadInsideSnake(){
        List<Point> subList = snakePoints.subList(1, snakePoints.size());
        Point head = getSnakeHead();
        for(Point p : subList){
            if(head.getX() == p.getX() && head.getY() == p.getY()){
                return true;
            }
        }
        return false;
    }

    //Onko piste madon sis?ll?
    public boolean isInSnake(Point p) {
        for(Point snakeP : snakePoints){
            if(p.getX() == snakeP.getX() && p.getY() == snakeP.getY()){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Point> getSnakePoints() {
        return snakePoints;
    }

    public Point getSnakeHead() {
        return snakePoints.get(0);
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    /*
    Palauttaa pisteen, jossa matoa ei ole.
    Parempi ratkaisu olisi pit?? kirjaa pisteist?, joissa mato on, ja arpoa vapaana olevien pisteiden listasta
    ruoalle piste. T?ss? mittakaavassa alla oleva ratkaisu on kuitenkin riitt?v?n hyv?.
    */
    public Point getPointOutsideSnake() {
        Point point = new Point();
        do {
            point.setX((int) Math.floor(Math.random() * 20));
            point.setY((int) Math.floor(Math.random() * 20));
        } while (isInSnake(point));
        return point;
    }
}
