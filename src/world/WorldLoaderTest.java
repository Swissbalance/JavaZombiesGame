/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

/**
 *
 * @author br-gaster
 */
public class WorldLoaderTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WorldLoader map = new WorldLoader();
        
        for (WRoom room : map) {
            System.out.println(room.getName());
            System.out.println("  \"" + room.getDescription() + "\"");
            System.out.print("  [ ");
            room.getEntrances().stream().forEach((e) -> {
                System.out.print("(" + e.getDirection() + ", " + e.getTo() + ") ");
            });
            System.out.println("]");
            System.out.print("  [ ");
            room.getItems().stream().forEach((i) -> {
                System.out.print(i + " ");
            });
            System.out.println("]");
        }
    }
}
