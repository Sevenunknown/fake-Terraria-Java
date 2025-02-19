import java.awt.*;
import java.util.HashMap;
public class Items
{
    private static final Colors color = new Colors();
    
    
    public static Item grass = new Item(42, "Grass", true, color.GRASS_GREEN, 12);
    public static Item dirt = new Item(42, "Dirt", true, color.DIRT_BROWN, 10);
    public static Item stone = new Item(42, "Stone", true, color.STONE_GRAY, 13);
    public static Item dark_stone = new Item(42, "Dark Stone", true, color.CAVE_GRAY, 16);
    public static Item wood = new Item(42, "Wood", true, color.WOOD_BROWN, 50);
    
    
    public static HashMap<String, Item> Items = new HashMap<>();
    
    public Items()
    {
        Items.put("grass", grass);
        Items.put("dirt", dirt);
        Items.put("stone", stone);
        Items.put("dark stone", dark_stone);
        Items.put("Wood", wood);
    }
    
    
}
