/*
 * Author: Benedict R. Gaster
 * Desc: Interface that implements a simple interaction interface to play 
 *       UWE's Zombie.
 */
package world;

import java.util.List;

/**
 * A bot that performs interactions with the player of OOSD1 Zombies.
 * An implementation of this interface effectively plays the game!
 * @author br-gaster
 */
public interface ZombieBot {    
    /**
     * should game quit 
     * @return return true if exit program, otherwise false 
     */ 
    boolean shouldQuit();

    /**
     * text to be displayed at beginning of game
     * @return text to be displayed
     */
    String begin();

    /**
     * compute current score
     * @return current score
     */
    int currentScore();

    /**
     * should zombie timer be enabled
     * @return true if timer should be enabled, otherwise false
     */
    boolean enableTimer();

    /**
     * should zombie timer be disabled
     * @return true if timer should be disabled, otherwise false
     */
    boolean disableTimer();
    
    /**
     * process player commands
     * @param cmd to be processed
     * @return output to be displayed
     */
    List<String> processCmd(String cmd);
}
