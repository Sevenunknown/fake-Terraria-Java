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
    
    private int[][] map;
    
    public World(int mW, int mH, int sW, int sH) {
        mapWidth = mW;
        mapHeight = mH;
        screenWidth = sW;
        screenHeight = sH;
        tileWidth = screenWidth / mapWidth;
        tileHeight = screenHeight / mapHeight;
        skyHeight = mapHeight / 7;
        waterLevel = (int ) Math.ceil(mapHeight/4.1);  // Set water level at the midpoint
        
        map = new int[mapHeight][mapWidth];
        make();
    }
    
    
    
    public void make() {
        for (int j = 0; j < mapWidth; j++) {
            double dirtNoise = noise.generate((j) * 0.1, 0, 2, 1, 0.5, 5);
            double stoneNoise = noise.generate((j) * 0.1, -1, 3, 2, 0.4, 5);
            double darkStoneNoise = noise.generate((j)* 0.1, -2, 5, 2, 0.3, 5);
            double treeNoise = noise.generate((j)*0.1, 5, 2, 3, 1, 20);
            
            int dirtLine = skyHeight + (int) ((dirtNoise + 1) / 2 * (mapHeight / 5));
            int stoneLine = 10 + dirtLine + (int) ((stoneNoise + 1) / 2 * (mapHeight / 10));
            int darkStoneLine = 5 + stoneLine + (int) ((darkStoneNoise+1)/2 * (mapHeight / 10));
            
            double caveNoise1 = noise.generate((j & 255)* 0.1, -3, 5, 2, 0.3, 20);
            double caveNoise2 = noise.generate(j* 0.2, -2, 5, 3, 0.1, 20);
            
            int caveLine1 = darkStoneLine +(int) ((caveNoise1 + 1) / 2 * (mapHeight / 12));
            int caveLine2 = caveLine1+(int) ((caveNoise2 + 1) / 2 * (mapHeight / 12));;
            
            
            for (int i = 0; i < mapHeight; i++) {
                
                int tile = getTile(i, dirtLine, stoneLine, darkStoneLine, caveLine1, caveLine2);
                map[i][j] = tile;
                //System.out.println(((treeNoise)));
                if (tile == 12 && (Math.abs(treeNoise)) <= 0.1 && (Math.abs(treeNoise)) >= 0.02)
                {
                    if (map[i-1][j-1] == 0 && map[i-1][j+1] == 0)
                    {
                        for (int I = 1; I < Math.max(4, Math.min(7, (int) (treeNoise*500))); I++)
                        {
                            map[i-I][j] = 15;
                        }
                    }
                }
            }
        }
    }

    public int getTile(int i, int dirtLine, int stoneLine, int darkStoneLine, int caveLine1, int caveLine2) {
        if (i < dirtLine) {
            if (i > waterLevel) return 21; // Water
            else return 0; //Sky
        }
        if (i == dirtLine) return (i >= waterLevel) ? 21 : 12; //water and grass
        if (i > dirtLine && i < stoneLine) return 10; // Dirt
        if (i >= stoneLine && i < darkStoneLine) return 13; // stone
        if (i >= caveLine1 && i < caveLine2) return 4; // black
        return 16; // dark stone
    }
    
    public void breakBlock(int i, int j)
    {
        if (map[i][j] == 2 || map[i][j] == 0 || map[i][j] == 1)
        {
            map[i][j] = 0;
        }
        else
        {
            map[i][j] = 5;
        }
    }
    
    public void setPos(int i, int j, int TileNum) {
        map[i][j] = TileNum;
    }
    
    public int[][] getMap() { return map; }
    public int getWidth() { return mapWidth; }
    public int getHeight() { return mapHeight; }
    public int gridWidth() { return tileWidth; }
    public int gridHeight() { return tileHeight; }
    public int atPos(int i, int j) { return map[i][j]; }
}
