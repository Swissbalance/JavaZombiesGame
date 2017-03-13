/*
 * Author: Jake Chapman, Connor Hill, Adam Williams
 * Desc: This class contain all information on the Room,
 *       It also processes how the client interacts with the room.
 */
package zombiestarter;

import java.util.List;

public class Room {

    //attribute description
    private final String description;
    
    //attribute name
    private final String name;
    
    //list of entrances
    private List<Entrance> entrances;
    
    //list of items
    private final List<Item> items;
    
    //number of zombies
    private int zombieCount;

    //constructor    take a description take a name
    Room(String name, String description, List<Item> items, int zombieCount) {
        this.description = description;
        this.name = name;
        this.items = items;
        this.zombieCount = zombieCount;

    }

    //add entrances method
    public void addEntrances(List<Entrance> entrances) {
        this.entrances = entrances;
    }    
    
    //getter for list of items in room
    public List<Item> getItems() {
        return items;
    }

    //get entrance list
    public List<Entrance> getEntrances() {
        return entrances;
    }

    //get room name
    public String getName() {
        return name;
    }

    //get room description
    public String getDescription() {
        return description;
    }

    //get zombie count
    public int getZombieCount() {
        return zombieCount;
    }

    //remove items from room
    public void removeItem(String cmd) {
        Item itemToRemove = null;
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(cmd)) {
                itemToRemove = item;
            }
        }
        items.remove(itemToRemove);
    }

    //look method
    public String look() {
        String directionList = "";
        for (Entrance entrance : entrances) {
            directionList = directionList + entrance.getDirection() + ", ";
        }

        String itemList = "";
        for (Item item : items) {
            itemList = itemList + " " + item.getHtml();
        }
        return "<h4>you are in the " + name + "</h4><br>" + description + "<br>there are entrances to the : " + directionList + "<br>" + itemList;
    }

    //checking if an entrance in the room is locked
    public boolean checkLocked(String cmd) {
        for (Entrance entrance : entrances) {
            if (entrance.getDirection().equalsIgnoreCase(cmd)) {
                return entrance.checkLocked();
            }
        }
        return false;
    }
    
    //adding item form inventory into room
    public void addItem(Item putDown) {
        items.add(putDown);
    }
        
    //takes one zombie of the rooms zombie count
    public void killOneZombie() {
        zombieCount--;
    }

}
