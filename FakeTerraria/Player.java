import java.awt.*;

public class Player {
    private double X, Y;
    private int Width, Height;
    private World map;
    private static final Colors c = new Colors();

    private double velocityX = 0, velocityY = 0;
    private static final double MAX_SPEED = 5;
    private static final double ACCELERATION = 0.5;
    private static final double FRICTION = 0.2;
    private static final double G = 0.2;

    public Player(int startX, int startY, int width, int height) {
        X = startX;
        Y = startY;
        Width = width;
        Height = height;
    }

    public void setMap(World MP) {
        map = MP;
    }

    public void update(double worldX, double worldY) {
        boolean onGround = false;
        int gridX = (int) (X / map.gridWidth());
        int bottomGridY = (int) ((Y + Height) / map.gridHeight());
        int topGridY = (int) (Y / map.gridHeight());
        int rightGridX = (int) ((X + Width) / map.gridWidth());

        // Vertical collision detection
        if (velocityY > 0) { // Falling
            if (bottomGridY < map.getHeight() && isSolid(bottomGridY, gridX) || isSolid(bottomGridY, rightGridX)) {
                onGround = true;
                Y = bottomGridY * map.gridHeight() - Height;
                velocityY = 0;
            }
        } else if (velocityY < 0) { // Jumping
            if (topGridY >= 0 && (isSolid(topGridY, gridX) || isSolid(topGridY, rightGridX))) {
                Y = (topGridY + 1) * map.gridHeight();
                velocityY = 0;
            }
        }

        if (!onGround) {
            velocityY += G;
            if (velocityY > MAX_SPEED) velocityY = MAX_SPEED;
        }
        
        // Horizontal collision detection
        if (velocityX > 0) { // Moving right
            if (rightGridX < map.getWidth() && (isSolid(topGridY, rightGridX) || isSolid(bottomGridY, rightGridX))) {
                X = rightGridX * map.gridWidth() - Width;
                velocityX = 0;
            }
        } else if (velocityX < 0) { // Moving left
            if (gridX >= 0 && (isSolid(topGridY, gridX) || isSolid(bottomGridY, gridX))) {
                X = (gridX + 1) * map.gridWidth();
                velocityX = 0;
            }
        }

        // Apply velocity if no collision
        X += velocityX;
        Y += velocityY;
    }

    private boolean isSolid(int y, int x) {
        if (x < 0 || x >= map.getWidth() || y < 0 || y >= map.getHeight()) return false;
        for (Color groundColor : c.ground) {
            if (map.atPos(y, x).equals(groundColor)) {
                return true;
            }
        }
        return false;
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