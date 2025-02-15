import java.awt.*;

public class Player {
    private double X, Y;
    private int Width, Height;
    private World map;
    private int mass = 10;
    private double G = 0.2;
    private static final Colors c = new Colors();

    private double velocityX = 0, velocityY = 0;
    private static final double MAX_SPEED = 5;
    private static final double ACCELERATION = 0.5;
    private static final double FRICTION = 0.2;

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

        boolean onGround = false;

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
                if (map.atPos(bottomGridY, gridX).equals(groundColor)) {
                    onGround = true;
                    Y = (bottomGridY * tileHeight) - Height;
                    velocityY = 0;
                    //System.out.println("Landed on ground at: " + Y);
                    break;
                }
            }
        }

        if (!onGround) {
            velocityY += G;
            if (velocityY > MAX_SPEED) velocityY = MAX_SPEED;

            // Predict next Y position
            double nextY = Y + velocityY;
            int nextBottomGridY = (int) ((nextY + Height) / map.gridHeight());

            //System.out.println("Predicted next Y: " + nextY + ", Next Grid Y: " + nextBottomGridY);
        }

        // Apply velocity if no collision
        Y += velocityY;
        //System.out.println("Updated Player Y: " + Y);
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