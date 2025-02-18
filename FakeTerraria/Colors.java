import java.awt.*;
import java.util.HashMap;

/*
 * This will hold all the colors I will be using that the standard library for this does not include
 */
public class Colors
{
    // --- Sky and Background Colors ---
    public static Color SKY_BLUE = new Color(135, 206, 235);
    public static Color LIGHT_BLUE = new Color(173, 216, 230);
    public static Color MIDNIGHT_BLUE = new Color(25, 25, 112);
    public static Color SUNSET_ORANGE = new Color(255, 94, 77);
    public static Color NIGHT_PURPLE = new Color(48, 25, 52);
    public static Color CLOUD_WHITE = new Color(240, 248, 255);
    public static Color STARRY_YELLOW = new Color(255, 223, 0);
    public static Color DARK_SKY_BLUE = new Color(70, 130, 180);
    public static Color DUSK_PURPLE = new Color(75, 0, 130);
    public static Color SUNRISE_PINK = new Color(255, 160, 122);

    public static Color[] sky = {SKY_BLUE, LIGHT_BLUE, MIDNIGHT_BLUE, SUNSET_ORANGE, NIGHT_PURPLE, CLOUD_WHITE, STARRY_YELLOW, DARK_SKY_BLUE, DUSK_PURPLE, SUNRISE_PINK};
    
    
    
    // --- Ground and Terrain Colors ---
    public static Color DIRT_BROWN = new Color(139, 69, 19);
    public static Color LIGHT_BROWN = new Color(160, 82, 45);
    public static Color GRASS_GREEN = new Color(34, 139, 34);
    public static Color STONE_GRAY = new Color(112, 128, 144);
    public static Color SAND_YELLOW = new Color(237, 201, 175);
    public static Color MUD_BROWN = new Color(101, 67, 33);
    public static Color CAVE_GRAY = new Color(88, 89, 91);
    public static Color SNOW_WHITE = new Color(255, 250, 250);
    public static Color LAVA_RED = new Color(207, 16, 32);
    public static Color ASH_GRAY = new Color(178, 190, 181);

    public static Color[] ground = {DIRT_BROWN, LIGHT_BROWN, GRASS_GREEN, STONE_GRAY, SAND_YELLOW,MUD_BROWN, CAVE_GRAY, SNOW_WHITE, LAVA_RED, ASH_GRAY};

    // --- Water and Liquid Colors ---
    public static Color OCEAN_BLUE = new Color(0, 105, 148);
    public static Color RIVER_BLUE = new Color(30, 144, 255);
    public static Color POISON_GREEN = new Color(0, 255, 127);
    public static Color SWAMP_GREEN = new Color(47, 79, 79);
    public static Color LAVA_ORANGE = new Color(255, 69, 0);
    public static Color BLOOD_RED = new Color(138, 7, 7);
    public static Color TRANSPARENT_WATER = new Color(0, 191, 255, 180);
    public static Color ICE_BLUE = new Color(173, 216, 230);
    public static Color POND_GREEN = new Color(0, 128, 128);
    public static Color MAGIC_WATER = new Color(75, 0, 130);

    public static Color[] waterColors = {OCEAN_BLUE, RIVER_BLUE, POISON_GREEN, SWAMP_GREEN, LAVA_ORANGE, BLOOD_RED, TRANSPARENT_WATER, ICE_BLUE, POND_GREEN, MAGIC_WATER};

    // --- Character Colors ---
    public static Color HERO_BLUE = new Color(0, 0, 255);
    public static Color HERO_RED = new Color(220, 20, 60);
    public static Color HERO_GREEN = new Color(34, 139, 34);
    public static Color HERO_YELLOW = new Color(255, 215, 0);
    public static Color SHADOW_BLACK = new Color(20, 20, 20);
    public static Color NPC_SKIN = new Color(255, 224, 189);
    public static Color MONSTER_PURPLE = new Color(128, 0, 128);
    public static Color ROBOT_SILVER = new Color(192, 192, 192);
    public static Color GOBLIN_GREEN = new Color(60, 179, 113);
    public static Color DEMON_RED = new Color(178, 34, 34);

    public static Color[] characterColors = {HERO_BLUE, HERO_RED, HERO_GREEN, HERO_YELLOW, SHADOW_BLACK, NPC_SKIN, MONSTER_PURPLE, ROBOT_SILVER, GOBLIN_GREEN, DEMON_RED};

    // --- Enemy Colors ---
    public static Color ZOMBIE_GREEN = new Color(102, 205, 170);
    public static Color SKELETON_WHITE = new Color(245, 245, 245);
    public static Color GHOST_TRANSPARENT = new Color(200, 200, 200, 150);
    public static Color DARK_NECROMANCER = new Color(72, 61, 139);
    public static Color SPIDER_BLACK = new Color(40, 40, 40);
    public static Color EVIL_WIZARD_PURPLE = new Color(148, 0, 211);
    public static Color VAMPIRE_RED = new Color(139, 0, 0);
    public static Color WEREWOLF_BROWN = new Color(101, 67, 33);
    public static Color DRAGON_ORANGE = new Color(255, 140, 0);
    public static Color SLIME_GREEN = new Color(50, 205, 50);

    public static Color[] enemyColors = {ZOMBIE_GREEN, SKELETON_WHITE, GHOST_TRANSPARENT, DARK_NECROMANCER, SPIDER_BLACK, EVIL_WIZARD_PURPLE, VAMPIRE_RED, WEREWOLF_BROWN, DRAGON_ORANGE, SLIME_GREEN};

    // --- Platform and Obstacle Colors ---
    public static Color WOOD_BROWN = new Color(139, 69, 19);
    public static Color METAL_GRAY = new Color(169, 169, 169);
    public static Color SPIKE_SILVER = new Color(192, 192, 192);
    public static Color BOX_YELLOW = new Color(218, 165, 32);
    public static Color ROPE_TAN = new Color(210, 180, 140);
    public static Color BRIDGE_BROWN = new Color(160, 82, 45);
    public static Color MINECART_GRAY = new Color(105, 105, 105);
    public static Color MAGNET_RED = new Color(220, 20, 60);
    public static Color SPRING_BLUE = new Color(70, 130, 180);
    public static Color LADDER_BROWN = new Color(165, 42, 42);

    public static Color[] platformColors = {WOOD_BROWN, METAL_GRAY, SPIKE_SILVER, BOX_YELLOW, ROPE_TAN, BRIDGE_BROWN, MINECART_GRAY, MAGNET_RED, SPRING_BLUE, LADDER_BROWN};

    // --- UI and Effect Colors ---
    public static Color HEALTH_BAR_RED = new Color(255, 0, 0);
    public static Color MANA_BAR_BLUE = new Color(0, 0, 255);
    public static Color EXP_BAR_GREEN = new Color(50, 205, 50);
    public static Color COIN_GOLD = new Color(255, 223, 0);
    public static Color GEM_BLUE = new Color(0, 191, 255);
    public static Color GEM_GREEN = new Color(60, 179, 113);
    public static Color GEM_PURPLE = new Color(147, 112, 219);
    public static Color DAMAGE_NUMBER_RED = new Color(255, 69, 0);
    public static Color HEALING_GREEN = new Color(144, 238, 144);
    public static Color SHIELD_BLUE = new Color(135, 206, 250);

    public static Color[] uiColors = {HEALTH_BAR_RED, MANA_BAR_BLUE, EXP_BAR_GREEN, COIN_GOLD, GEM_BLUE, GEM_GREEN, GEM_PURPLE, DAMAGE_NUMBER_RED, HEALING_GREEN, SHIELD_BLUE};

    // --- Special Effect Colors ---
    public static Color FIRE_ORANGE = new Color(255, 140, 0);
    public static Color LIGHTNING_YELLOW = new Color(255, 255, 0);
    public static Color EXPLOSION_RED = new Color(255, 69, 0);
    public static Color SMOKE_GRAY = new Color(169, 169, 169, 180);
    public static Color PORTAL_PURPLE = new Color(186, 85, 211);
    public static Color POISON_CLOUD_GREEN = new Color(127, 255, 0, 150);
    public static Color TELEPORT_BLUE = new Color(30, 144, 255);
    public static Color SHADOW_DARK = new Color(0, 0, 0, 180);
    public static Color MAGIC_AURA_CYAN = new Color(0, 255, 255, 200);
    public static Color FLASH_WHITE = new Color(255, 255, 255, 220);

    public static Color[] effectColors = {FIRE_ORANGE, LIGHTNING_YELLOW, EXPLOSION_RED, SMOKE_GRAY, PORTAL_PURPLE, POISON_CLOUD_GREEN, TELEPORT_BLUE, SHADOW_DARK, MAGIC_AURA_CYAN, FLASH_WHITE};

    // --- Indoor / Dungeon Colors ---
    public static Color BRICK_RED = new Color(178, 34, 34);
    public static Color TORCH_YELLOW = new Color(255, 215, 0);
    public static Color WALL_GRAY = new Color(169, 169, 169);
    public static Color FLOOR_BROWN = new Color(139, 69, 19);
    public static Color BLOOD_STAIN = new Color(138, 7, 7);
    public static Color CHAIN_SILVER = new Color(192, 192, 192);
    public static Color GOLDEN_STATUE = new Color(255, 215, 0);
    public static Color CANDLE_LIGHT = new Color(255, 239, 151);
    public static Color TREASURE_CHEST = new Color(218, 165, 32);
    public static Color SECRET_DOOR = new Color(112, 128, 144);

    public static Color[] dungeonColors = {BRICK_RED, TORCH_YELLOW, WALL_GRAY, FLOOR_BROWN, BLOOD_STAIN, CHAIN_SILVER, GOLDEN_STATUE, CANDLE_LIGHT, TREASURE_CHEST, SECRET_DOOR};

    public static Color[] wood = {MUD_BROWN};
    
    public HashMap<Integer, Color> NumToColor = new HashMap<>();
        public HashMap<Integer, Color> SkyColors = new HashMap<>();
        public HashMap<Integer, Color> GroundColors = new HashMap<>();
        public HashMap<Integer, Color> WaterColors = new HashMap<>();
        public HashMap<Integer, Color> CharacterColors = new HashMap<>();
        public HashMap<Integer, Color> EnemyColors = new HashMap<>();
        public HashMap<Integer, Color> PlatformColors = new HashMap<>();
        public HashMap<Integer, Color> UIColors = new HashMap<>();
        public HashMap<Integer, Color> EffectColors = new HashMap<>();
        public HashMap<Integer, Color> DungeonColors = new HashMap<>();
        public HashMap<Integer, Color> WoodColors = new HashMap<>();
    
        
        
    public Colors() {
        int index = 0;
        for (Color c : sky) {
            NumToColor.put(index, c);
            SkyColors.put(index, c);
            index++;
        }
        for (Color c : ground) {
            NumToColor.put(index, c);
            GroundColors.put(index, c);
            index++;
        }
        for (Color c : waterColors) {
            NumToColor.put(index, c);
            WaterColors.put(index, c);
            index++;
        }
        for (Color c : characterColors) {
            NumToColor.put(index, c);
            CharacterColors.put(index, c);
            index++;
        }
        for (Color c : enemyColors) {
            NumToColor.put(index, c);
            EnemyColors.put(index, c);
            index++;
        }
        for (Color c : platformColors) {
            NumToColor.put(index, c);
            PlatformColors.put(index, c);
            index++;
        }
        for (Color c : uiColors) {
            NumToColor.put(index, c);
            UIColors.put(index, c);
            index++;
        }
        for (Color c : effectColors) {
            NumToColor.put(index, c);
            EffectColors.put(index, c);
            index++;
        }
        for (Color c : dungeonColors) {
            NumToColor.put(index, c);
            DungeonColors.put(index, c);
            index++;
        }
        for (Color c : wood)
        {
            NumToColor.put(index, c);
            WoodColors.put(index, c);
            index++;
        }
    }

    public static Color getColor(double R, double G, double B) {
        return new Color((int) R, (int) G, (int) B);
    }

    public static boolean contains(Color[] container, Color item) {
        for (Color c : container) {
            if (c.equals(item)) {
                return true;
            }
        }
        return false;
    }
    
    /**
    0  -> SKY_BLUE  (135, 206, 235)
    1  -> LIGHT_BLUE  (173, 216, 230)
    2  -> MIDNIGHT_BLUE  (25, 25, 112)
    3  -> SUNSET_ORANGE  (255, 94, 77)
    4  -> NIGHT_PURPLE  (48, 25, 52)
    5  -> CLOUD_WHITE  (240, 248, 255)
    6  -> STARRY_YELLOW  (255, 223, 0)
    7  -> DARK_SKY_BLUE  (70, 130, 180)
    8  -> DUSK_PURPLE  (75, 0, 130)
    9  -> SUNRISE_PINK  (255, 160, 122)
    
    10 -> DIRT_BROWN  (139, 69, 19)
    11 -> LIGHT_BROWN  (160, 82, 45)
    12 -> GRASS_GREEN  (34, 139, 34)
    13 -> STONE_GRAY  (112, 128, 144)
    14 -> SAND_YELLOW  (237, 201, 175)
    15 -> MUD_BROWN  (101, 67, 33)
    16 -> CAVE_GRAY  (88, 89, 91)
    17 -> SNOW_WHITE  (255, 250, 250)
    18 -> LAVA_RED  (207, 16, 32)
    19 -> ASH_GRAY  (178, 190, 181)
    
    20 -> OCEAN_BLUE  (0, 105, 148)
    21 -> RIVER_BLUE  (30, 144, 255)
    22 -> POISON_GREEN  (0, 255, 127)
    23 -> SWAMP_GREEN  (47, 79, 79)
    24 -> LAVA_ORANGE  (255, 69, 0)
    25 -> BLOOD_RED  (138, 7, 7)
    26 -> TRANSPARENT_WATER  (0, 191, 255, 180)
    27 -> ICE_BLUE  (173, 216, 230)
    28 -> POND_GREEN  (0, 128, 128)
    29 -> MAGIC_WATER  (75, 0, 130)
    
    30 -> HERO_BLUE  (0, 0, 255)
    31 -> HERO_RED  (220, 20, 60)
    32 -> HERO_GREEN  (34, 139, 34)
    33 -> HERO_YELLOW  (255, 215, 0)
    34 -> SHADOW_BLACK  (20, 20, 20)
    35 -> NPC_SKIN  (255, 224, 189)
    36 -> MONSTER_PURPLE  (128, 0, 128)
    37 -> ROBOT_SILVER  (192, 192, 192)
    38 -> GOBLIN_GREEN  (60, 179, 113)
    39 -> DEMON_RED  (178, 34, 34)
    
    40 -> ZOMBIE_GREEN  (102, 205, 170)
    41 -> SKELETON_WHITE  (245, 245, 245)
    42 -> GHOST_TRANSPARENT  (200, 200, 200, 150)
    43 -> DARK_NECROMANCER  (72, 61, 139)
    44 -> SPIDER_BLACK  (40, 40, 40)
    45 -> EVIL_WIZARD_PURPLE  (148, 0, 211)
    46 -> VAMPIRE_RED  (139, 0, 0)
    47 -> WEREWOLF_BROWN  (101, 67, 33)
    48 -> DRAGON_ORANGE  (255, 140, 0)
    49 -> SLIME_GREEN  (50, 205, 50)
    
    50 -> WOOD_BROWN  (139, 69, 19)
    51 -> METAL_GRAY  (169, 169, 169)
    52 -> SPIKE_SILVER  (192, 192, 192)
    53 -> BOX_YELLOW  (218, 165, 32)
    54 -> ROPE_TAN  (210, 180, 140)
    55 -> BRIDGE_BROWN  (160, 82, 45)
    56 -> MINECART_GRAY  (105, 105, 105)
    57 -> MAGNET_RED  (220, 20, 60)
    58 -> SPRING_BLUE  (70, 130, 180)
    59 -> LADDER_BROWN  (165, 42, 42)
    
    60 -> HEALTH_BAR_RED  (255, 0, 0)
    61 -> MANA_BAR_BLUE  (0, 0, 255)
    62 -> EXP_BAR_GREEN  (50, 205, 50)
    63 -> COIN_GOLD  (255, 223, 0)
    64 -> GEM_BLUE  (0, 191, 255)
    65 -> GEM_GREEN  (60, 179, 113)
    66 -> GEM_PURPLE  (147, 112, 219)
    67 -> DAMAGE_NUMBER_RED  (255, 69, 0)
    68 -> HEALING_GREEN  (144, 238, 144)
    69 -> SHIELD_BLUE  (135, 206, 250)
    
    70 -> FIRE_ORANGE  (255, 140, 0)
    71 -> LIGHTNING_YELLOW  (255, 255, 0)
    72 -> EXPLOSION_RED  (255, 69, 0)
    73 -> SMOKE_GRAY  (169, 169, 169, 180)
    74 -> PORTAL_PURPLE  (186, 85, 211)
    75 -> POISON_CLOUD_GREEN  (127, 255, 0, 150)
    76 -> TELEPORT_BLUE  (30, 144, 255)
    77 -> SHADOW_DARK  (0, 0, 0, 180)
    78 -> MAGIC_AURA_CYAN  (0, 255, 255, 200)
    79 -> FLASH_WHITE  (255, 255, 255, 220)
    
    80 -> BRICK_RED  (178, 34, 34)
    81 -> TORCH_YELLOW  (255, 215, 0)
    82 -> WALL_GRAY  (169, 169, 169)
    83 -> FLOOR_BROWN  (139, 69, 19)
    84 -> BLOOD_STAIN  (138, 7, 7)
    85 -> CHAIN_SILVER  (192, 192, 192)
    86 -> GOLDEN_STATUE  (255, 215, 0)
    87 -> CANDLE_LIGHT  (255, 239, 151)
    88 -> TREASURE_CHEST  (218, 165, 32)
    89 -> SECRET_DOOR  (112, 128, 144)
    **/
   
}