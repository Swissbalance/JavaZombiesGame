/*
 * Author: Benedict R. Gaster
 * Desc: Represents a room in the world
 */
package world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * class representing a room in the world
 * @author br-gaster
 */
public class WRoom {

    // name of room
    private final String name;

    // description of room, displayed when room is looked at for example
    private final String description;

    // list of entrances found in room
    private final ArrayList<WEntrance> entrances;

    // list of initial items in room
    private final ArrayList<String> items;

    // number of zombies in room at world create time
    private final int zombieCount;
    
    /**
     * create a room
     * @param name
     * @param desc
     * @param entrances found in room
     * @param items placed in room at world creation
     */
    WRoom(
        String name, 
        String desc, 
        ArrayList<WEntrance> entrances,
        ArrayList<String> items,
        int zombieCount) {
        this.name = name;
        description = desc;
        this.entrances = entrances;
        this.items = items;
        this.zombieCount = zombieCount;
    }

    /**
     * room name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * room description
     * @return description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * room entrances
     * @return list of entrances
     */
    public List<WEntrance> getEntrances() {
        return entrances;
    }
    
    /**
     * rooms items
     * @return list of items
     */
    public List<String> getItems() {
        return items;
    }

    /**
     * rooms zombie count
     * @return number of zombies in room
     */
    public int getZombieCount() {
        return zombieCount;
    }
}