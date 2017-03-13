/*
 * Author: Jake Chapman, Connor Hill, Adam Williams
 * Desc: This file contains the core of dynamic game play for Zombies. 
 *       Each of the commands has been Implemented, 
 *       and processes all inputs from the client.
 */
package zombiestarter;

import java.util.ArrayList;
import java.util.List;

public class ZombieBot implements world.ZombieBot {

    private final World world;

    ZombieBot(World world) {
        this.world = world;
    }

    //score value
    private int score = 0;

    //should quit value
    private boolean quit = false;

    //should enable timer? value
    private boolean enableTimer = false;

    //should enable timer? value
    private boolean disableTimer = false;

    /**
     * should game quit
     *
     * @return return true if exit program, otherwise false
     */
    @Override
    public boolean shouldQuit() {
        return quit;
    }

    /**
     * prompt to be displayed to user
     *
     * @return
     */
    @Override
    public String begin() {
        return world.getStartHtml();
    }

    /**
     * compute current score
     *
     * @return current score
     */
    @Override
    public int currentScore() {
        return score;
    }

    /**
     * should timer be enabled? if should be enabled, then method returns true,
     * and goes back into state of not enable.
     *
     * @return true if enable timer, otherwise false
     */
    @Override
    public boolean enableTimer() {
        return enableTimer;
    }

    /**
     * should timer be disabled? if should be disabled, then method returns
     * true, and goes back into state of don't disable.
     *
     * @return
     */
    public boolean disableTimer() {
        return disableTimer;
    }

    /**
     * process player commands
     *
     * @param cmd to be processed
     * @return output to be displayed
     */
    @Override
    public List<String> processCmd(String cmd) {
        ArrayList<String> result = new ArrayList<>();

        String[] cmds = cmd.split(" "); // split cmd by space

        switch (cmds[0]) {
            case "info":
                result.add(world.getInfo());
                break;
            case "look":
                //world.look();   what may have to use
                //world knows what current romm i
                //and from that should get details of that room
                //current room is critical
                result.add(world.look());
                break;
            case "commands":
                result.add("<br><b>Look</b> - Look around the room for items, zombies and other rooms.");
                result.add("<br><b>Move</b> - Moves in the specified direction. E.g, move SW.");
                result.add("<br><b>Pickup</b> - Pickup the item you have found e.g, pickup daisy.");
                result.add("<br><b>Drop</b> - Drop the selected item e.g, drop daisy.");
                result.add("<br><b>Kill</b> - Kills a zombie with any items available from your inventory.");
                result.add("<br><b>Inventory</b> - Displays the items that are in your inventory.");
                result.add("<br><b>Quit</b> - Quits the game, doh!");
                break;
            case "move":
                //move requirtes logic about the direction and the direction of entrance and set surrent room as the new room
                //if room has door in direction requested? cmds[1] to equal currentroom.
                if (world.checkForExit(cmds[1])) {
                    //if said door is not locked
                    if (!world.attemptToOpenDoor(cmds[1])) {
                        //set currnetRoom to roomdest of entrance
                        world.setCurrentRoom(cmds[1]);
                        result.add("You are now in " + world.getCurrentRoom().getName());
                        //if zombieCount > 0, enable timer 
                        if (world.getZombieCount() > 0) {
                            enableTimer = true;
                        }
                    } else {
                        //else locked say no, you need key
                        result.add("door is locked <br> get a key");
                    }
                } else {
                    //else say no door in that direction, and feedback which direction they inputed
                    result.add("no exit in the direction " + cmds[1]);
                }
                break;
            case "pickup":
                if (world.checkForItemRoom(cmds[1])) {
                    //if item is in the room 
                    world.pickupItem(cmds[1]);
                    result.add("you picked up " + cmds[1]);
                    score++;
                    //remove from room, put in inventory
                    //increment score
                } else {
                    result.add("you can see any " + cmds[1]);
                }
                break;
            case "kill":
                if (world.checkForZombies()) {
                    if (world.checkHaveWeapon()) {
                        world.killOneZombie();
                        result.add("You managed to kill a zombie!");
                        enableTimer = false;
                        disableTimer = !(world.checkForZombies());
                    } else {
                        result.add("You do not have a weapon...");
                    }
                } else {
                    result.add("You are alone...");
                }

                //if room contains zomnbie and chainsaw or daisy in inventory
                //zombie count decrement 
                //remove item used to kill zombie
                //if count 0
                //disableTimer = true;
                //else nothing
                //else
                break;
            case "drop":
                //switch statement for each type of item
                if (world.checkForItemInventory(cmds[1])) {
                    //if item is in the room 
                    world.putDownItem(cmds[1]);
                    result.add("You placed the " + cmds[1] + " on the ground");
                    score--;
                    //remove from room, put in inventory
                    //increment score
                } else {
                    result.add("You don't have a " + cmds[1]);
                }
                //if item is in the inventory 
                //remove from inventory, put in room
                //else say dont have item                
                break;
            case "timerexpired":
                // set quit to true and say you died
                result.add("<h1>You have been eaten. </h1>");
                quit = true;
                break;
            case "quit":
                quit = true;
                break;
            case "inventory":
                result.add(world.checkInventory());
                break;
            case "blank":
                result.add("<b>That is not a recognised command, type 'commands' to get a list of available commands.</b>");
                break;
            case "":
                break;
            default:
                result.add("<b>That is not a command that I recognise, type 'commands' to get a list of a available commands.</b>");
        }

        return result;
    }

}
