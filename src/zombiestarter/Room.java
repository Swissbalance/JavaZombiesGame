// Authors: Connor Hill, Jake Chapman, Adam Williams
// Description: Code regarding rooms
package zombiestarter;

import java.util.List;

public class Room {

    private final String name; // attribute name
    private final String desc; // attribute description
    private final int zombieCount; // number of zombies
    final List<Item> items; // list of items
    public List<Entrance> entrances; // list of entrances

    Room(String name, String desc, List<Item> items, int zombieCount) {
        this.name = name;
        this.desc = desc;
        this.items = items;
        this.zombieCount = zombieCount;
    }

    // adds the entrances
    public void addEntrances(List<Entrance> entrances) {
        this.entrances = entrances;
    }

    // gets the entrance list
    public List<Entrance> getEntrances() {
        return entrances;
    }

    // gets the room name
    String getName() {
        return name;
    }

    // gets the room description
    public String getDescription() {
        return desc;
    }

    // gets the zombie count
    public int getZombieCount() {
        return zombieCount;
    }

    // removes items from the room
    public void removeItem(String cmd) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(cmd)) {
                items.remove(cmd); // NOT WORKING
            }
        }
    }

    String look() {
        String result = name + " " + desc + " ";

        for (Entrance entrance : entrances) {
            result = result + entrance.getDirection() + entrance.getTo() + entrance.getLocked();
        }

        for (Item item : items) {
            result = result + item.getHtml() + "Zombie Count is:" + zombieCount;
        }

        return result;
    }

    //    public String getRoomDest(String cmd) {
    //        return entrances.get(entrances.indexOf(cmd)).getRoomDest();
    //    }
    boolean checkLocked(String cmd) {
        for (Entrance entrance : entrances) {
            if (entrance.getDirection().equalsIgnoreCase(cmd)) {
                return entrance.getLocked();
            }
        }
        return false;
    }

}
