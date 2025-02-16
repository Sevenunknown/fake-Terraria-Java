import java.awt.*;

public class World {
    private int mapWidth, mapHeight;
    private int tileWidth, tileHeight;
    private int screenWidth, screenHeight;
    
    private int skyHeight;
    private static int SEED = 39;
    
    private static final Colors color = new Colors();
    private static final Noise noise = new Noise(SEED);
    
    
    private Color[][] map;
    
    public World(int mW, int mH, int sW, int sH) {
        mapWidth = mW;
        mapHeight = mH;
        screenWidth = sW;
        screenHeight = sH;
        tileWidth = screenWidth / mapWidth;
        tileHeight = screenHeight / mapHeight;
        skyHeight = mapHeight / 4;
        map = new Color[mapHeight][mapWidth];
        make();
    }
    
    public void make() {
        for (int j = 0; j < mapWidth; j++) {
            // Generate terrain heights using noise based on X (j)
            double dirtNoise = noise.generate((j & 255) * 0.1, 0, 3, 1, 0.5);  // Use scaled noise
            double stoneNoise = noise.generate((j & 255) * 0.1, -1, 3, 2, 0.4); // Another scaled noise
            
            int dirtLine = skyHeight + (int) ((dirtNoise + 1) / 2 * (mapHeight / 5));
            int stoneLine = 10+dirtLine + (int) ((stoneNoise + 1) / 2 * (mapHeight / 10));
            
            for (int i = 0; i < mapHeight; i++) {
                map[i][j] = getColor(i, dirtLine, stoneLine);
            }
        }
    }

    public Color getColor(int i, int dirtLine, int stoneLine) {
        if (i < dirtLine) return Colors.SKY_BLUE;
        if (i == dirtLine) return Colors.GRASS_GREEN;
        if (i > dirtLine && i < stoneLine) return Colors.DIRT_BROWN;
        return Colors.STONE_GRAY;
    }
    
    public void setPos(int i, int j, Color c)
    {
        map[i][j] = c;
        //System.out.println(i +","+j);
    }
    
    public Color[][] getMap() { return map; }
    public int getWidth() { return mapWidth; }
    public int getHeight() { return mapHeight; }
    public int gridWidth() { return tileWidth; }
    public int gridHeight() { return tileHeight; }
    public Color atPos(int i, int j) { return map[i][j]; }
}
