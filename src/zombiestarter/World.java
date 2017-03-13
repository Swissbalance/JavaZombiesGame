/*
 * Author: Jake Chapman, Connor Hill, Adam Williams
 * Desc: This class contain all information on the World,
 *       It also processes how the client interacts with the world.
 */
package zombiestarter;

import java.util.ArrayList;
import java.util.List;
import world.WEntrance;
import world.WorldLoader;

public class World {

    //Get Info
    private final String info;

    //get start HTML
    private final String startHtml;

    //itemList htmls
    private ArrayList<Item> possibleItems = new ArrayList<>();

    //arraylist for all rooms in jason file
    private ArrayList<Room> rooms = new ArrayList();

    //the room currently in
    private Room currentRoom;

    //End Room
    private Room endRoom;

    //Inventory
    private Inventory inventory;

    //world contructor
    World(WorldLoader worldLoader) {
        this.info = worldLoader.getInfo();
        this.startHtml = worldLoader.getStartHtml();
        possibleItems = new ArrayList<>();
        rooms = new ArrayList<>();
    }

    public void addInventory(String inventoryHtml) {
        this.inventory = new Inventory(inventoryHtml);
    }

    //get info string
    public String getInfo() {
        return info;
    }

    //get start HTML string
    public String getStartHtml() {
        return startHtml;
    }

    //add room method
    public void addRoom(String name, String description, List<String> itemNames, int zombieCount) {
        ArrayList<Item> roomItems = new ArrayList<>();
        for (String itemName : itemNames) {
            for (Item item : possibleItems) {
                if (item.getName().equals(itemName)) {
                    roomItems.add(item);
                }
            }
        }
        rooms.add(new Room(name, description, roomItems, zombieCount));
    }

    public void addEntrances(String name, List<WEntrance> entrances) {
        ArrayList<Entrance> roomEntrances = new ArrayList<>();
        for (WEntrance entrance : entrances) {
            for (Room room : rooms) {
                if (room.getName().equals(entrance.getTo())) {
                    roomEntrances.add(new Entrance(entrance.getDirection(), room, entrance.isLocked()));
                }
            }
        }
        // write loop to find room from name
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                room.addEntrances(roomEntrances);
            }
        }

    }

    //add item to possible items list method
    void addItem(String name, String html) {
        possibleItems.add(new Item(name, html));
    }

    //set start
    public void setStart(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                currentRoom = room;
                break;
            }
        }
    }

    //set End Room
    public void setEnd(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                endRoom = room;
                break;
            }
        }
    }

    //look method
    public String look() {
        return currentRoom.look();
    }

    //check inventory method
    public String checkInventory() {
        String backpack = inventory.getInventoryHtml();
        String itemList = inventory.checkContents();
        return backpack + itemList;
    }

    //sets currentRoom to roomDest
    void setCurrentRoom(String cmd) {
        for (Entrance entrance : currentRoom.getEntrances()) {
            if (entrance.getDirection().equalsIgnoreCase(cmd)) {
                currentRoom = entrance.getRoomDest();
            }
        }
    }

    //checks if the specified item is in the current room
    boolean checkForItemRoom(String cmd) {
        for (Item item : currentRoom.getItems()) {
            if (item.getName().equalsIgnoreCase(cmd)) {
                return true;
            }
        }
        return false;
    }

    //check if the specified item is in the players inventory
    boolean checkForItemInventory(String cmd) {
        for (Item item : inventory.getInventory()) {
            if (item.getName().equalsIgnoreCase(cmd)) {
                return true;
            }
        }
        return false;
    }

    //checks if the specified entrance exsits in the room
    boolean checkForExit(String cmd) {
        for (Entrance entrance : currentRoom.getEntrances()) {
            if (entrance.getDirection().equalsIgnoreCase(cmd)) {
                return true;
            }
        }
        return false;
    }

    //if entrance is locked, checks that you have a key 
    boolean attemptToOpenDoor(String cmd) {
        if (currentRoom.checkLocked(cmd)) {
            for (Item item : inventory.getInventory()) {
                if (item.getName().equalsIgnoreCase("key")) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    //gets the number of zombies in current room
    public int getZombieCount() {
        return currentRoom.getZombieCount();
    }

    //adds an item to inventory and removes from the room
    public void pickupItem(String cmd) {
        //add item to inventory 
        for (Item item : possibleItems) {
            if (item.getName().equalsIgnoreCase(cmd)) {
                inventory.addItem(new Item(item.getName(), item.getHtml()));
            }
        }
        //remove item from room 
        currentRoom.removeItem(cmd);
    }

    //adds item to the room and removes from inventory
    public void putDownItem(String cmd) {
        //add item to inventory 
        for (Item item : inventory.getInventory()) {
            if (item.getName().equalsIgnoreCase(cmd)) {
                currentRoom.addItem(new Item(item.getName(), item.getHtml()));
            }
        }
        //remove item from room 
        inventory.removeItem(cmd);
    }

    //checks if player is holding a suitable weapon
    public boolean checkHaveWeapon() {
        for (Item item : inventory.getInventory()) {
            if (item.getName().equalsIgnoreCase("daisy") || item.getName().equalsIgnoreCase("chainsaw")) {
                return true;
            }
        }
        return false;
    }

    //checks if there are any zombies left
    public boolean checkForZombies() {
        return (getZombieCount() > 0);
    }

    //kills one zombie in the room
    public void killOneZombie() { //this needs work
        currentRoom.killOneZombie();
        String itemUsed = null;
        for (Item item : inventory.getInventory()) {
            if (item.getName().equalsIgnoreCase("daisy") || item.getName().equalsIgnoreCase("chainsaw")) {
                itemUsed = item.getName();
            }
        }
        inventory.removeItem(itemUsed);
    }

    //gets the current room
    public Room getCurrentRoom() {
        return currentRoom;
    }

}
