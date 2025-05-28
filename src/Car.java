import java.awt.*;

public class Car {
    private int x, y;
    private final int width = 30;
    private final int height = 50;
    private final int speed = 10;

    public Car(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveLeft() {
        if (x > 0) x -= speed;
    }

    public void moveRight() {
        if (x < 350) x += speed; 
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
