package zombiestarter;

import world.*;

public class WorldDisplay {

    public static void main(String[] args) {
        WorldLoader wl = new WorldLoader();

        //Info
        System.out.println(wl.getInfo());

        //Items
        for (WItem item : wl.getItems()) {
            System.out.println(item.getName() + " has HTML " + item.getHtml());
        }

        //Have method in world that gets items
        //Rooms
        for (WRoom room : wl) {
            //Room Name
            System.out.println(room.getName());
            //Room Description
            System.out.println(room.getDescription());
            //Entrances
            for (WEntrance e : room.getEntrances()) {
                System.out.println(e.getDirection() + " leads to " + e.getTo());
                System.out.println("Locked? " + e.isLocked());
            }
            //Items
            for (String i : room.getItems()) {
                System.out.println(i);
            }
            //Zombie Count
            System.out.println("There are " + room.getZombieCount() + " zombies");
        }

        //how you represent rooms
        //how rooms change
        //rooms need abilit to pickup / drop items
        System.out.println("The starting toom is " + wl.getStart());

    }

}
