import java.awt.*;

public class Player {
    private double X, Y;
    private int Width, Height;
    private World map;
    private int mass = 1;
    private double G = 2;
    private static final Colors c = new Colors();

    private double velocityX = 0, velocityY = 0;
    private static final double MAX_FALL_SPEED = 20;
    private static final double ACCELERATION = 0.5;
    private static final double FRICTION = 0.2;
    private static final double MAX_SPEED = 20;
    
    private boolean onGround;

    public Player(int startX, int startY, int width, int height) {
        X = startX;
        Y = startY;
        Width = width;
        Height = height;
    }

    public void setMap(World MP) {
        map = MP;
    }

    public void update(double xOffset, double yOffset, int tileWidth, int tileHeight) {
        int worldWidth = map.getWidth();
        int worldHeight = map.getHeight();

        onGround = false;

        int gridX = (int) ((X + xOffset) / tileWidth);
        int bottomGridY = (int) ((Y + yOffset + Height)/ tileHeight);

        // Debugging: Print player's current position and velocity
        //System.out.println("Player Position: (" + X + ", " + Y + ")");
        //System.out.println("Player Velocity: (" + velocityX + ", " + velocityY + ")");
        //System.out.println("Grid Position: (" + gridX + ", " + bottomGridY + ")");

        // Ensure player doesn't fall below the map
        if (bottomGridY >= worldHeight) {
            onGround = true;
            Y = worldHeight * tileHeight - Height; // Align to the last row of the map
            velocityY = 0;
            //System.out.println("Hit bottom of map, adjusting Y to: " + Y);
        } else if (gridX >= 0 && gridX < worldWidth) {
            for (Color groundColor : c.ground) {
                if (c.NumToColor.get(map.atPos(bottomGridY, gridX)) == (groundColor)) {
                    onGround = true;
                    Y = (bottomGridY * tileHeight) - Height;
                    velocityY = 0;
                    //System.out.println("Landed on ground at: " + Y);
                    break;
                }
            }
        }

        if (!onGround) {
            velocityY += G*mass;
            if (velocityY > MAX_FALL_SPEED) velocityY = MAX_FALL_SPEED;

            // Predict next Y position
            double nextY = Y + velocityY;
            int nextBottomGridY = (int) ((nextY + Height) / map.gridHeight());

            //System.out.println("Predicted next Y: " + nextY + ", Next Grid Y: " + nextBottomGridY);
        }

        // Apply velocity if no collision
        Y += velocityY;
        //System.out.println("Updated Player Y: " + Y);
    }
    
    
    public boolean fall(double velY, double xOffset, double yOffset, int tileWidth, int tileHeight) {
        int worldWidth = map.getWidth();
        int worldHeight = map.getHeight();
        
        int gridX = (int) ((X + xOffset) / tileWidth);
        int bottomGridY = (int) ((Y + Height+yOffset) / tileHeight);
    
        // Bounds check to prevent out-of-bounds access
        if (gridX < 0 || gridX >= worldWidth || bottomGridY < 0 || bottomGridY >= worldHeight) {
            velocityY = Math.max(-MAX_FALL_SPEED, Math.min(MAX_FALL_SPEED, velocityY+G));
            return true; // Assume falling if outside map bounds
        }
    
        for (Color groundColor : c.ground) {
            if (c.NumToColor.get(map.atPos(bottomGridY, gridX)) == groundColor) {
                velocityY = 0;
                return false; // Landed on solid ground
            }
        }
        Y += velocityY;
        return true;
    }

    

    public boolean move(double velX, double xOffset, double yOffset, int tileWidth, int tileHeight) {
        int worldWidth = map.getWidth();    
        int nextGridX = (int) ((X + xOffset + velX) / tileWidth);
        int bottomGridY = (int) ((Y + Height+yOffset-tileHeight/2) / tileHeight);
        int topGridY = (int) (Y /tileHeight);

        if (nextGridX >= 0 && nextGridX < worldWidth) {
            for (Color groundColor : c.ground) {
                if (c.NumToColor.get(map.atPos(topGridY, nextGridX)) == groundColor ||
                    c.NumToColor.get(map.atPos(bottomGridY, nextGridX)) == groundColor)
                {
                    return false; // Blocked, don't move
                }
            }
        }
        return true; // Safe to move
    }

    
    public void setVel(int val)
    {
        velocityY = val;
        Y+=velocityY;
    }
    
    public int getMass()
    {
        return mass;
    }
    
    public boolean onGround()
    {
        return onGround;
    }
    
    public int getX() {
        return (int) X;
    }
    
    public int getY() {
        return (int) Y;
    }

    public int getWidth() {
        return Width;
    }

    public int getHeight() {
        return Height;
    }
}