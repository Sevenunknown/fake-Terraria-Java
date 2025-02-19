import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class Runner extends JPanel implements ActionListener, KeyListener,  MouseListener {
    private static final int TILE_VIEW_WIDTH = 20;
    private static final int TILE_VIEW_HEIGHT = 25;
    
    private static final int worldWidth = 1000;
    private static final int worldHeight = 200;
    
    public int sceneTracker = 0;
    // 0 = Game, 1 = settings
    
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
    private static final double MAX_SPEED = 5;
    private static final double MAX_FALL_SPEED = 20;
    private static final double FRICTION = 1;
    public static double G = 2;
    
    public boolean breaking = false;
    public boolean placing = false;
    public int selectedSlot = -1;

    public int tileWidth, tileHeight;

    Timer timer = new Timer(16, this); // 60 FPS (16ms per f    rame)
    private static final World map = new World(worldWidth, worldHeight, 1000, 700);
    private static final Colors color = new Colors();
    
    private HashMap<Integer, Color> NumToColor  = color.NumToColor;
    
    private double playerX, playerY;
    private static int playerWidth, playerHeight;
    private Player player;

    public Runner() {
        timer.start();
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);  // This line is needed
        player = new Player(500, 100, 20, 40);
        player.setMap(map);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        int screenWidth = getWidth();
        int screenHeight = getHeight();
        if (sceneTracker == 0)
        {
            drawGame(g);
        }
        else if (sceneTracker == 1)
        {
            drawSettings(g);
        }
    }
    
    public void drawSettings(Graphics g)
    {
       g.setColor(Color.BLACK);
       g.setFont(new Font("Arial", Font.PLAIN, 50));
       g.drawString("SETTINGS", getWidth()/2-100, 100);
       g.drawString("Seed: " + map.getSeed()+"",  0, 200);
       g.drawString("World Width: " + map.getWidth()+"",  0, 250);
       g.drawString("World Height: " + map.getHeight()+"",  0, 300);
       g.drawString("Position: (" + (int) ((player.getX() + xOffset) / tileWidth) + "," + (map.getHeight()-((int) ((player.getY() + yOffset) / tileWidth)))+")", 0, 350);
       
    }
    
    public void drawGame(Graphics g)
    {
        int screenWidth = getWidth();
        int screenHeight = getHeight();
        if (sceneTracker == 0)
        {
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
                    int tileNum = map.atPos(i, j);
                    Color tileColor = NumToColor.get(tileNum);
                    g.setColor(tileColor);
        
                    int drawX = (j * tileWidth - (int) xOffset);
                    int drawY = (i * tileHeight - (int) yOffset);
        
                    g.fillRect(drawX, drawY, tileWidth, tileHeight);
                }
            }
        
            // Draw the player
            int InvGridSize = 50;
            Item[] inv = player.getInventoryItems();
            // Draw Inventory
            for (int i = -1; i<2; i++)
            {
                if (i+1 == selectedSlot)
                {
                    g.setColor(Color.WHITE);
                }
                else
                {
                    g.setColor(Color.GRAY);
                }
                g.fillRect(getWidth()/2+i*InvGridSize, getHeight()-100-InvGridSize, InvGridSize, InvGridSize);
                g.setColor(Color.BLACK);
                g.drawLine(getWidth()/2+i*InvGridSize, getHeight()-100-InvGridSize, getWidth()/2+i*InvGridSize, getHeight()-100);
                if (inv[i+1] != null)
                {
                    g.setColor(inv[i+1].getColor());
                    g.fillRect(getWidth()/2+i*(InvGridSize)+InvGridSize/4, getHeight()-100-(InvGridSize)+InvGridSize/4, InvGridSize/2, InvGridSize/2);
                }
                
                g.setColor(Color.BLACK);
                int x1 = getWidth()/2+i*(InvGridSize)+1;
                int y1 =  getHeight()-100;
                g.drawString(""+player.getInventory().getInventoryNums(i+1),x1, y1);
            }
            
            g.drawLine(getWidth()/2+2*InvGridSize, getHeight()-100-InvGridSize, getWidth()/2+2*InvGridSize, getHeight()-100);
            
            g.drawLine(getWidth()/2-InvGridSize, getHeight()-100-InvGridSize, getWidth()/2+2*InvGridSize, getHeight()-100-InvGridSize);
            g.drawLine(getWidth()/2-InvGridSize, getHeight()-100, getWidth()/2+2*InvGridSize, getHeight()-100);
                   
            
            g.setColor(Color.RED);
            g.fillRect(screenWidth / 2 - player.getWidth() / 2, player.getY(), player.getWidth(), player.getHeight());
        }
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
        //System.out.println(shouldFall);
        if (shouldFall)
        {
            velocityY += G;
            if (player.move(velocityX, xOffset, yOffset, tileWidth, tileHeight))
            {
                xOffset = Math.max(0, Math.min(maxXOffset, xOffset + velocityX));
            }    
            else { velocityX = 0;
            }
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
            velocityY = -10;
        }
        xOffset = Math.max(0, Math.min(maxXOffset, xOffset + velocityX));
        yOffset = Math.max(0, Math.min(maxYOffset, yOffset + velocityY));

        
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (sceneTracker != 0) return;
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) leftPressed = true;
        else if (keyCode == KeyEvent.VK_RIGHT) rightPressed = true;
        if (keyCode == KeyEvent.VK_SPACE) spacePressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    
        int keyCode = e.getKeyCode();
        if (sceneTracker == 0 || sceneTracker == 1)
        {
            if (keyCode == KeyEvent.VK_LEFT) leftPressed = false;
            if (keyCode == KeyEvent.VK_RIGHT) rightPressed = false;
            if (keyCode == KeyEvent.VK_UP) upPressed = false;
            if (keyCode == KeyEvent.VK_DOWN) downPressed = false;
            if (keyCode == KeyEvent.VK_SPACE) spacePressed = false;
        
            if (keyCode == KeyEvent.VK_I) {
                Inventory INV = player.getInventory();
                INV.printInventory();
                //System.out.println();
            }
        
            if (keyCode == KeyEvent.VK_P) {
                placing = !placing;
                breaking = false;
                if (placing) selectedSlot = 0;
                else selectedSlot = -1;
            }
        
            if (keyCode == KeyEvent.VK_B) {
                breaking = !breaking;
                placing = false;
                selectedSlot = -1;
            }
        
            // Selecting inventory slot
            if (keyCode == KeyEvent.VK_1) {
                if (selectedSlot == 0)
                {
                    selectedSlot = -1;
                    placing = false;
                }
                else
                {
                selectedSlot = 0;
                placing = true;
                }  
            }
            if (keyCode == KeyEvent.VK_2) {
                if (selectedSlot == 1)
                {
                    selectedSlot = -1;
                    placing = false;
                }
                else
                {
                selectedSlot = 1;
                placing = true;
                }  
            }
            if (keyCode == KeyEvent.VK_3) 
            {
                if (selectedSlot == 2)
                {
                    selectedSlot = -1;
                    placing = false;
                }
                else
                {
                selectedSlot = 2;
                placing = true;
                } 
            }
        }
        if (keyCode == KeyEvent.VK_S) sceneTracker = (sceneTracker+1)%2;
        //System.out.println("Selected Slot: " + selectedSlot);
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
        Item itm = map.getBrokenItem(map.atPos(mouseGY, mouseGX));
        
        if (itm != null && breaking)
        {
            if (player.giveItem(itm))
            {
                map.breakBlock(mouseGY, mouseGX);
            }
        }
        else if (itm == null && placing && selectedSlot != -1)
        {
            Item selectedItem = player.getInventoryItems()[selectedSlot];
            if (selectedItem != null)
            {
                map.setPos(mouseGY, mouseGX, selectedItem.getTile());
                player.getInventory().removeItem(selectedSlot);
            }
        }
        //System.out.println("Breaking: " + breaking + " Placing: " + placing + " Slot: " + selectedSlot);
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