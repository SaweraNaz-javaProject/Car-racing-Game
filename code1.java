import java.awt.*;
public class code1 {
    

public class EnemyCar {
    private int x, y;
    private final int width = 30;
    private final int height = 50;
    private final int speed = 5;

    public EnemyCar(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveDown() {
        y += speed;
        if (y > 600) {
            y = -100;
            x = 100 + (int)(Math.random() * 150); 
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
}
}
    
}
