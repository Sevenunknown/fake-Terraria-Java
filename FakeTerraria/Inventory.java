import java.awt.*;
public class Inventory
{
    private static final Items items = new Items();
    private static final Colors c = new Colors();
    
    private static int inventorySize = 3;
    private Item[] inventory = new Item[inventorySize];
    private int[] inventoryNums = new int[inventorySize];
    
    public Inventory()
    {
        
    }
    
    public boolean addToInventory(Item itm)
    {
        boolean didSomething = false;
        for (int slot = 0; slot < 3; slot++)
        {
            if (inventory[slot] == null)
            {
                inventory[slot] = itm;
                inventoryNums[slot]+=1;
                didSomething = true;
                break;
            }
            else if (inventory[slot] == itm && inventoryNums[slot] <= itm.getStackSize())
            {
                inventoryNums[slot]+=1;
                didSomething = true;
                break;
            }
        }
        return didSomething;
    }
    
    
    
    public void removeItem(int slot)
    {
        inventoryNums[slot]-=1;
        if (inventoryNums[slot] <= 0)
        {
            inventoryNums[slot] = 0;
            inventory[slot] = null;
        }
    }
    
    public Item[] getInventory()
    {
        return inventory;
    }
    
    public int[] getInventoryNums()
    {
        return inventoryNums;
    }
    
    public int getInventoryNums(int i)
    {
        return inventoryNums[i];
    }
    
    public void printInventory()
    {
        int count = 0;
        for (Item itm: inventory)
        {
            System.out.print(itm);
            System.out.println(" :"+inventoryNums[count]);
            count++;
        }
    }
    
}
