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
    private static final double MAX_SPEED = 50;
    private static final double FRICTION = 1;
    public static double G = 1;

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
    
        // Ensure square tiles
        int tileSize = Math.min(screenWidth / TILE_VIEW_WIDTH, screenHeight / TILE_VIEW_HEIGHT);
        tileWidth = tileSize;
        tileHeight = tileSize;
    
        // Calculate how many tiles fit in the view, and add extra coverage to avoid gaps
        int extraTiles = 30; // Prevents gaps when scrolling
        int startX = Math.max(0, (int) (xOffset / tileWidth));
        int startY = Math.max(0, (int) (yOffset / tileHeight));
        int endX = Math.min(map.getWidth(), startX + TILE_VIEW_WIDTH + extraTiles);
        int endY = Math.min(map.getHeight(), startY + TILE_VIEW_HEIGHT + extraTiles);
    
        for (int i = startY; i < endY; i++) {
            for (int j = startX; j < endX; j++) {
                Color tileColor = map.atPos(i, j);
                g.setColor(tileColor);
    
                int drawX = (j * tileWidth - (int) xOffset);
                int drawY = (i * tileHeight - (int) yOffset);
    
                g.fillRect(drawX, drawY, tileWidth, tileHeight);
            }
        }
    
        // Draw the player
        g.setColor(Color.RED);
        g.fillRect(screenWidth / 2 - player.getWidth() / 2, player.getY(), player.getWidth(), player.getHeight());
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        //player.update(0, 0, tileWidth, tileHeight);
        //double velY, double xOffset, double yOffset, int tileWidth, int tileHeight

        // Now print the updated result of fall() at the same position
        
        boolean shouldFall = player.fall(velocityY, xOffset, yOffset, tileWidth, tileHeight);
        //System.out.println(velocityY +" " +xOffset +" " +yOffset +" " +tileWidth +" " +tileHeight);
        
        int maxXOffset = Math.max(0, map.getWidth() * tileWidth - getWidth());
        int maxYOffset = Math.max(0, map.getHeight() * tileHeight - getHeight());
        System.out.println(shouldFall);
        if (shouldFall)
        {
            velocityY += G*player.getMass();
            yOffset = Math.max(0, Math.min(maxYOffset, yOffset + velocityY));
        }
        else
        {
            velocityY = 0;
        }
        
        // Movement logic for left and right
        if (leftPressed) 
        {
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
        if (rightPressed) 
        {
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
        
        if (spacePressed && !shouldFall)
        {
            velocityY = 20;
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