import java.awt.*;

public class World {
    private int mapWidth, mapHeight;
    private int tileWidth, tileHeight;
    private int screenWidth, screenHeight;
    
    private int skyHeight;
    private int waterLevel;  // New variable to determine where MAGIC_WATER starts
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
        skyHeight = mapHeight / 7;
        waterLevel = (int ) Math.ceil(mapHeight/4.1);  // Set water level at the midpoint
        
        map = new Color[mapHeight][mapWidth];
        make();
    }
    
    public void make() {
        for (int j = 0; j < mapWidth; j++) {
            double dirtNoise = noise.generate((j & 255) * 0.1, 0, 2, 1, 0.5);
            double stoneNoise = noise.generate((j & 255) * 0.1, -1, 3, 2, 0.4);
            double darkStoneNoise = noise.generate((j & 255)* 0.1, -2, 5, 2, 0.3);
            
            int dirtLine = skyHeight + (int) ((dirtNoise + 1) / 2 * (mapHeight / 5));
            int stoneLine = 10 + dirtLine + (int) ((stoneNoise + 1) / 2 * (mapHeight / 10));
            int darkStoneLine = 5 + stoneLine + (int) ((darkStoneNoise+1)/2 * (mapHeight / 10));
            
            double caveNoise1 = noise.generate((j & 255)* 0.1, -3, 5, 2, 0.3);
            double caveNoise2 = noise.generate((j & 255)* 0.2, -2, 5, 3, 0.1);
            
            int caveLine1 = darkStoneLine +(int) ((caveNoise1 + 1) / 2 * (mapHeight / 12));
            int caveLine2 = caveLine1+(int) ((caveNoise2 + 1) / 2 * (mapHeight / 12));;
                        
            for (int i = 0; i < mapHeight; i++) {
                map[i][j] = getColor(i, dirtLine, stoneLine, darkStoneLine, caveLine1, caveLine2);
            }
        }
    }

    public Color getColor(int i, int dirtLine, int stoneLine, int darkStoneLine, int caveLine1, int caveLine2) {
        if (i < dirtLine) {
            if (i > waterLevel) return Colors.MAGIC_WATER;
            else return Colors.SKY_BLUE;
        }
        if (i == dirtLine) return (i >= waterLevel) ? Colors.MAGIC_WATER : Colors.GRASS_GREEN;
        if (i > dirtLine && i < stoneLine) return Colors.DIRT_BROWN;
        if (i >= stoneLine && i < darkStoneLine) return Colors.STONE_GRAY;
        if (i >= caveLine1 && i < caveLine2) return Color.BLACK;
        return Colors.CAVE_GRAY;
    }
    
    public void breakBlock(int i, int j)
    {
        if (map[i][j] == Colors.DIRT_BROWN || map[i][j] == Colors.SKY_BLUE || map[i][j] == Colors.GRASS_GREEN)
        {
            map[i][j] = Colors.SKY_BLUE;
        }
        else
        {
            map[i][j] = Color.BLACK;
        }
    }
    
    public void setPos(int i, int j, Color c) {
        map[i][j] = c;
    }
    
    public Color[][] getMap() { return map; }
    public int getWidth() { return mapWidth; }
    public int getHeight() { return mapHeight; }
    public int gridWidth() { return tileWidth; }
    public int gridHeight() { return tileHeight; }
    public Color atPos(int i, int j) { return map[i][j]; }
}
