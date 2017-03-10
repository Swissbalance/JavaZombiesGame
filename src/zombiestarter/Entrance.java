// Authors: Connor Hill, Jake Chapman, Adam Williams
// Description: Code regarding entrances
package zombiestarter;

public class Entrance {

    private final String direction;
    private final Room to;
    private final boolean locked;

    Entrance(String direction, Room to, boolean locked) {
        this.direction = direction;
        this.to = to;
        this.locked = locked;
    }

    // gets the entrance direction
    public String getDirection() {
        return direction;
    }

    // getsthe room destination
    public Room getTo() {
        return to;
    }

    // check if door is locked
    public boolean getLocked() {
        return locked;
    }
}
