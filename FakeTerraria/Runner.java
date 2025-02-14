import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Runner extends JPanel implements ActionListener, KeyListener {
    private static final int TILE_VIEW_WIDTH = 10;
    private static final int TILE_VIEW_HEIGHT = 15;

    private double xOffset = 0;
    private double yOffset = 0;
    


    private static boolean leftPressed = false;
    private static boolean rightPressed = false;
    private static boolean upPressed = false;
    private static boolean downPressed = false;

    private double velocityX = 0;
    private double velocityY = 0;
    private static final double ACCELERATION = 1;  // Lower acceleration for finer control
    private static final double MAX_SPEED = 20;     // Lower max speed to prevent chunky jumps
    private static final double FRICTION = 1;      // Lower friction for smoother stopping

    Timer timer = new Timer(16, this); // 60 FPS (16ms per frame)
    //(int mW, int mH, int sW, int sH)
    private static final World map = new World(20, 50, 1000, 700);
    
    private double playerX, playerY;
    private static int playerWidth, playerHeight;
    private Player player;
    public Runner() {
        timer.start();
        setFocusable(true);
        addKeyListener(this);
        player =  new Player(100, 0, 20, 40);
        player.setMap(map);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int screenWidth = getWidth();
        int screenHeight = getHeight();

        int tileWidth = screenWidth / TILE_VIEW_WIDTH;
        int tileHeight = screenHeight / TILE_VIEW_HEIGHT;
        
        for (int i = 0; i < TILE_VIEW_HEIGHT + 1; i++) {
            for (int j = 0; j < TILE_VIEW_WIDTH + 1; j++) {
                int worldX = (int) (xOffset / tileWidth) + j;
                int worldY = (int) (yOffset / tileHeight) + i;

                if (worldX >= map.getWidth() || worldY >= map.getHeight() || worldX < 0 || worldY < 0)
                    continue;
                Color tileColor = map.atPos(worldY, worldX);
                g.setColor(tileColor);
                g.fillRect((int) (j * tileWidth - (xOffset % tileWidth)), 
                           (int) (i * tileHeight - (yOffset % tileHeight)), 
                           tileWidth, tileHeight);
            }
        }
        
        g.setColor(Color.RED);
        g.fillRect((int)(player.getX() - xOffset), (int)(player.getY() - yOffset), player.getWidth(), player.getHeight());
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update(xOffset, yOffset);
        
        int tileWidth = getWidth() / TILE_VIEW_WIDTH;
        int tileHeight = getHeight() / TILE_VIEW_HEIGHT;

        int worldPixelWidth = map.getWidth() * tileWidth;
        int worldPixelHeight = map.getHeight() * tileHeight;

        int maxXOffset = Math.max(0, worldPixelWidth - getWidth());
        int maxYOffset = Math.max(0, worldPixelHeight - getHeight());
            // Apply acceleration
            if (leftPressed) velocityX -= ACCELERATION;
            if (rightPressed) velocityX += ACCELERATION;
            if (upPressed) velocityY -= ACCELERATION;
            if (downPressed) velocityY += ACCELERATION;
    
            // Limit speed
            velocityX = Math.max(-MAX_SPEED, Math.min(MAX_SPEED, velocityX));
            velocityY = Math.max(-MAX_SPEED, Math.min(MAX_SPEED, velocityY));
    
            // Apply friction when no keys are pressed
            if (!leftPressed && !rightPressed) {
                if (velocityX > 0) velocityX = Math.max(0, velocityX - FRICTION);
                else if (velocityX < 0) velocityX = Math.min(0, velocityX + FRICTION);
            }
            if (!upPressed && !downPressed) {
                if (velocityY > 0) velocityY = Math.max(0, velocityY - FRICTION);
                else if (velocityY < 0) velocityY = Math.min(0, velocityY + FRICTION);
            }
    
            // Update position
            xOffset = Math.max(0, Math.min(maxXOffset, xOffset + velocityX));
            yOffset = Math.max(0, Math.min(maxYOffset, yOffset + velocityY));

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) leftPressed = true;
        else if (keyCode == KeyEvent.VK_RIGHT) rightPressed = true;
        if (keyCode == KeyEvent.VK_UP) upPressed = true;
        else if (keyCode == KeyEvent.VK_DOWN) downPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) leftPressed = false;
        if (keyCode == KeyEvent.VK_RIGHT) rightPressed = false;
        if (keyCode == KeyEvent.VK_UP) upPressed = false;
        if (keyCode == KeyEvent.VK_DOWN) downPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Runner");
        Runner panel = new Runner();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.add(panel);
        frame.setVisible(true);
        frame.setResizable(true);
    }
}
