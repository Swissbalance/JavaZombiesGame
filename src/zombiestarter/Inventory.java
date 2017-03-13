/*
 * Author: Jake Chapman, Connor Hill, Adam Williams
 * Desc: This class contain all information on the Inventory,
 *       It also processes how the client interacts with the Inventory.
 */
package zombiestarter;

import java.util.ArrayList;

public class Inventory {

    //list of items
    private final ArrayList<Item> inventory;

    //get inventory HTML
    private final String inventoryHtml;

    //constructor
    Inventory(String inventoryHtml) {
        inventory = new ArrayList<>();
        this.inventoryHtml = inventoryHtml;
    }
    
    //getter for entire enventory
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    //getter for the html that displays inventory image
    public String getInventoryHtml() {
        return inventoryHtml;
    }

    //check all items that are in inventory
    public String checkContents() {
        String contentsList = "";
        for (Item item : inventory) {
            contentsList = contentsList + item.getHtml();
        }
        return contentsList;
    }
    
    //add an item to inventory
    public void addItem(Item pickedUp) {
        inventory.add(pickedUp);
    }

    //removes an item from inventory
    public void removeItem(String cmd) {
        Item itemToRemove = null;
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(cmd)) {
                itemToRemove = item;
            }
        }
        inventory.remove(itemToRemove);
    }
}
