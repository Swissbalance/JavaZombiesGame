/*
 * Author: Jake Chapman, Connor Hill, Adam Williams
 * Desc: This class contain all information on the Entrance,
 *       It also processes how the client interacts with the entrances.
 */
package zombiestarter;

class Entrance {

    //string of direction the entrance leads
    private final String direction;

    //the room the entrance leads too
    private final Room roomDest;

    //if the entrance requires a key
    private final boolean locked;

    //constructor
    Entrance(String direction, Room roomDest, boolean locked) {
        this.direction = direction;
        this.roomDest = roomDest;
        this.locked = locked;
    }

    //get entrance direction
    public String getDirection() {
        return direction;
    }

    //get room destination
    public Room getRoomDest() {
        return roomDest;
    }

    //check door locked
    public boolean checkLocked() {
        return locked;
    }

}
