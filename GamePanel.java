import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private Timer timer;
    private Car playerCar;
    private ArrayList<EnemyCar> enemyCars;
    private int roadY = 0;
    private boolean gameOver = false;
    private int score = 0;
    private String playerName;

    public GamePanel() {
        setPreferredSize(new Dimension(400, 600));
        setBackground(Color.DARK_GRAY);

        // Ask for player name
        playerName = JOptionPane.showInputDialog(null, "Enter your name:");

        playerCar = new Car(175, 450);
        enemyCars = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            int x = 100 + (int)(Math.random() * 150);
            int y = -100 - (i * 200);
            enemyCars.add(new EnemyCar(x, y));
        }

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);

        timer = new Timer(20, this); 
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawRoad(g);

        if (!gameOver) {
            playerCar.draw(g);
            for (EnemyCar enemy : enemyCars) {
                enemy.draw(g);
            }
            drawScore(g);
        } else {
            drawGameOver(g);
        }
    }

    private void drawRoad(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(100, 0, 200, getHeight());

        g.setColor(Color.WHITE);
        for (int i = roadY; i < getHeight(); i += 40) {
            g.fillRect(195, i, 10, 30);
        }

        roadY += 5;
        if (roadY >= 40) roadY = 0;
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Score: " + score, 10, 20);
    }

    private void drawGameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("GAME OVER", 100, 250);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Final Score: " + score, 120, 300);

        // Save to database after game over
        DatabaseManager.saveScore(playerName, score);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            for (EnemyCar enemy : enemyCars) {
                enemy.moveDown();
            }
            score++; 
            checkCollision();
        }
        repaint();
    }

    private void checkCollision() {
        Rectangle playerBounds = new Rectangle(playerCar.getX(), playerCar.getY(), 50, 100);
        for (EnemyCar enemy : enemyCars) {
            if (playerBounds.intersects(enemy.getBounds())) {
                gameOver = true;
                timer.stop();
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameOver) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                playerCar.moveLeft();
            } else if (key == KeyEvent.VK_RIGHT) {
                playerCar.moveRight();
            }
        }
        repaint();
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
