// Authors: Connor Hill, Jake Chapman, Adam Williams
// Description: Code regarding the player's inventory.
package zombiestarter;

import java.util.ArrayList;

public class Inventory {

    public ArrayList<Item> inventory; // list of items
    private final String inventoryHtml; // gets inventory HTML

    Inventory(String inventoryHtml) {
        inventory = new ArrayList<>();
        this.inventoryHtml = inventoryHtml;
    }

    //  pickup and drop
    public String getInventoryHtml() {
        return inventoryHtml;
    }

    public String checkContents() {
        String contentsList = "";
        for (Item item : inventory) {
            contentsList = contentsList + item.getHtml();
        }
        return contentsList;
    }

    public void addItem(Item pickedUp) {
        inventory.add(pickedUp);
    }
}

// Need to add drop method.
