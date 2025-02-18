import java.awt.*;
import java.util.HashMap;
public class Items
{
    private static final Colors color = new Colors();
    
    
    public static Item grass = new Item(42, "Grass", true, color.GRASS_GREEN);
    public static Item dirt = new Item(42, "Dirt", true, color.DIRT_BROWN);
    public static Item stone = new Item(42, "Stone", true, color.STONE_GRAY);
    public static Item dark_stone = new Item(42, "Dark Stone", true, color.CAVE_GRAY);
    
    
    public static HashMap<String, Item> Items = new HashMap<>();
    
    public Items()
    {
        Items.put("grass", grass);
        Items.put("dirt", dirt);
        Items.put("stone", stone);
        Items.put("dark stone", dark_stone);
    }
    
    
}
