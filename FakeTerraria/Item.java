import java.awt.*;
public class Item
{
    private int stackSize;
    private String name;
    private boolean placeable;
    private int tileNum;
    
    private static final Colors c = new Colors();
    Color color;
    
    public Item(int stackNum, String nm, boolean canPlace, Color cl, int Num)
    {
        stackSize = stackNum;
        name = nm;
        placeable = canPlace;
        color = cl;
        tileNum = Num;
    }
    
    public int getStackSize()
    {
        return stackSize;
    }
    
    public String getName()
    {
        return name;
    }
    
    public boolean canPlace()
    {
        return placeable;
    }
    
    public Color getColor()
    {
        return color;
    }
    
    public String toString()
    {
        return name + "";
    }
    
    public int getTile()
    {
        return tileNum;
    }
    
}
