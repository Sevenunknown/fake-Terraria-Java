import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Runner extends JPanel implements ActionListener, KeyListener,  MouseListener {
    private static final int TILE_VIEW_WIDTH = 10;
    private static final int TILE_VIEW_HEIGHT = 20;
    
    private static final int worldWidth = 100;
    private static final int worldHeight = 50;

    private double xOffset = 0;
    private double yOffset = 0;

    private static boolean leftPressed = false;
    private static boolean rightPressed = false;
    private static boolean upPressed = false;
    private static boolean downPressed = false;
    private static boolean spacePressed = false;

    private double velocityX = 0;
    private double velocityY = 0;
    private static final double ACCELERATION = 1;
    private static final double MAX_SPEED = 20;
    private static final double FRICTION = 1;

    public int tileWidth, tileHeight;

    Timer timer = new Timer(16, this); // 60 FPS (16ms per frame)
    private static final World map = new World(worldWidth, worldHeight, 1000, 700);
    private static final Colors color = new Colors();

    private double playerX, playerY;
    private static int playerWidth, playerHeight;
    private Player player;

    public Runner() {
        timer.start();
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);  // This line is needed
        player = new Player(500, 350, 20, 40);
        player.setMap(map);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int screenWidth = getWidth();
        int screenHeight = getHeight();

        tileWidth = screenWidth / TILE_VIEW_WIDTH;
        tileHeight = screenHeight / TILE_VIEW_HEIGHT;

        for (int i = 0; i < TILE_VIEW_HEIGHT + 1; i++) {
            for (int j = 0; j < TILE_VIEW_WIDTH + 1; j++) {
                int worldX = (int) (xOffset / tileWidth) + j;
                int worldY = (int) (yOffset / tileHeight) + i;

                if (worldX >= map.getWidth() || worldY >= map.getHeight() || worldX < 0 || worldY < 0)
                    continue;
                Color tileColor = map.atPos(worldY, worldX);
                g.setColor(tileColor);
                g.fillRect((int) (j * tileWidth - (xOffset % tileWidth)), 
                           (int) ((i) * tileHeight - (yOffset % tileHeight)), 
                           tileWidth, tileHeight);
            }
        }

        g.setColor(Color.RED);
        g.fillRect(screenWidth / 2, player.getY(), player.getWidth(), player.getHeight());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update(xOffset, yOffset, tileWidth, tileHeight);

        int maxXOffset = Math.max(0, map.getWidth() * tileWidth - getWidth());
        int maxYOffset = Math.max(0, map.getHeight() * tileHeight - getHeight());

        // Movement logic for left and right
        if (leftPressed) {
            velocityX -= ACCELERATION; // Move left
            velocityX = Math.max(-MAX_SPEED, velocityX); // Cap max speed
            //double velX, double xOffset, double yOffset, int tileWidth, int tileHeight
            if (player.move(velocityX, xOffset, yOffset, tileWidth, tileHeight))
            {
                xOffset = Math.max(0, Math.min(maxXOffset, xOffset + velocityX));
            }    
            else { velocityX = 0;
            }
        }
        if (rightPressed) {
            velocityX += ACCELERATION; // Move right
            velocityX = Math.min(MAX_SPEED, velocityX); // Cap max speed
            if (player.move(velocityX, xOffset, yOffset, tileWidth, tileHeight))
            {
                xOffset = Math.max(0, Math.min(maxXOffset, xOffset + velocityX));
            }    
            else { velocityX = 0;
            }
        }

        // Apply friction when no keys are pressed
        if (!leftPressed && !rightPressed) {
            if (velocityX > 0) velocityX = Math.max(0, velocityX - FRICTION);
            else if (velocityX < 0) velocityX = Math.min(0, velocityX + FRICTION);
        }
        
        if (spacePressed && player.onGround())
        {
            player.setVel(-20);
        }
        xOffset = Math.max(0, Math.min(maxXOffset, xOffset + velocityX));
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) leftPressed = true;
        else if (keyCode == KeyEvent.VK_RIGHT) rightPressed = true;
        if (keyCode == KeyEvent.VK_SPACE) spacePressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) leftPressed = false;
        if (keyCode == KeyEvent.VK_RIGHT) rightPressed = false;
        if (keyCode == KeyEvent.VK_UP) upPressed = false;
        if (keyCode == KeyEvent.VK_DOWN) downPressed = false;
        if (keyCode == KeyEvent.VK_SPACE) spacePressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    // MouseListener methods
    @Override
    public void mousePressed(MouseEvent e) {
        // Get the mouse position when clicked
        int mouseX = e.getX();
        int mouseY = e.getY();
        int mouseGX = (int) (mouseX + xOffset)/tileWidth;
        int mouseGY = (int) (mouseY + yOffset)/tileHeight;
        map.setPos(mouseGY, mouseGX, color.SKY_BLUE);
    }

    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}


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