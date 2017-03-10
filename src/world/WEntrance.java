/*
 * Author: Benedict R. Gaster
 * Desc: Represents an entrance in the world
 */
package world;

/**
 * World representation of an entrance. This class is created from the loaded
 * world for each entrance attached to a room.
 * @author br-gaster
 */
public class WEntrance {
    // direction entrance is facing
    private final String direction;
    // room entrance leads to
    private final String to;
    // is entrance locked
    private final boolean locked;
    
    /**
     * create an entrance
     * @param dir entrance is reachable by moving in this direction
     * @param to room that is reached, when moving through entrance
     * @param locked true if tool is locked, otherwise entrance is open
     */
    public WEntrance(String dir, String to, boolean locked) {
        direction = dir;
        this.to = to;
        this.locked = locked;
    }
    
    /**
     * direction entrance is reachable by moving in this direction
     * @return direction
     */
    public String getDirection() {
        return direction;
    }
    
    /**
     * room that is reached, when moving through entrance
     * @return room
     */
    public String getTo() {
        return to;
    }
    
    /**
     * is room locked
     * @return true if tool is locked, otherwise entrance is open
     */
    public boolean isLocked() {
        return locked;
    }
}
