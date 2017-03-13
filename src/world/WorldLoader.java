/*
 * Author: Benedict R. Gaster
 * Desc: Class that loads map file (map.json, assumed to be in jar). Provides
 *       iterator interface return Room object representing each room in map.
 */
package world;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import org.json.*;

/**
 * Class to load a world file. 
 * @author br-gaster
 */
public class WorldLoader implements Iterable<WRoom> {
    
    // rooms within the world
    private final JSONArray rooms;
    
    // item definitions, include html to send for given item
    private final JSONArray items;

    // starting room
    private final String start;
    private final String end;
    private final String info;

    private final String startHtml;
    //private final String keyHtml;
    //private final String cssOutput;
    //private final String htmOutput;
    //private final String cssNameScore;
    //private final String htmNameScore;
    private final String inventoryHtml;
    
    private Image panelImg;
    
    /**
     * constructor, uses JSON world stored in jar, this is the default option
     */
    public WorldLoader() {
        this(readResource( "world.json" ));
    }
    
    /**
     * constructor, allows world to be passed in
     * @param world JSON file representing world
     */
    public WorldLoader(String world) {
        JSONObject jobj = new JSONObject(world);
        
        items  = jobj.getJSONArray("items");
        rooms  = jobj.getJSONArray("rooms");
        start  = jobj.getString("start");
        end = jobj.getString("finish");
        info   = jobj.getString("info");
        inventoryHtml = jobj.getString("inventoryHtml");
        startHtml = jobj.getString("startHtml");
    }
    
    /**
     * read text file as resource (i.e. one stored in jar)
     * (note this has to be static, to allow default constructor to call it 
     * and pass result to another constructor.
     * @param name resource to read
     * @return string of resource read
     */
    private static String readResource(String name) {
        InputStream stream = WorldLoader.class.getResourceAsStream(name);
        Scanner scan = new Scanner(stream).useDelimiter("\\A");
        return scan.next();
    }
    
    /**
     * HTML for displaying with player's inventory
     * @return HTML for inventory image
     */
    public String getInventoryHtml() {
        return inventoryHtml;
    }

    /**
     * HTML to display when client makes connection to server
     * @return html to display
     */
    public String getStartHtml() { return startHtml; }
    
    /**
     * gets panel image
     * @return panel image 
     */
    public Image getPanelImg() {
        return panelImg;
    }
    
    /**
     * starting room
     * @return room
     */
    public String getStart() {
        return start;
    }
    
    /**
     * end room
     * @return room
     */
    public String getEnd() {
        return end;
    }
    
    /**
     * game info
     * @return info
     */
    public String getInfo() {
        return info;
    }
   
  
    
    /**
     * 
     * @return 
     */
    public List<WItem> getItems() {
        ArrayList<WItem> result = new ArrayList<>();
        
        for (Object o : items) {
            if (o instanceof JSONObject) {
                JSONObject obj = (JSONObject)o;
                result.add(
                    new WItem(obj.getString("name"), obj.getString("html")));
            }
        }
            
        return result;
    }
    
    /**
     * Each room in the world can be iterated over.
     * @return Room iterator over the loaded map. 
     */
    @Override
    public Iterator<WRoom> iterator() {
        return new RoomIterator(rooms);
    }
    
    /**
     * inner class implementing the room iterator
     */
    private class RoomIterator implements Iterator<WRoom> {
        private final JSONArray jarray;
        private final Iterator<java.lang.Object> iter; 
        
        /**
         * constructor that takes the JSON array of rooms
         * @param ja JSON array of rooms
         */
        RoomIterator(JSONArray ja) {
            jarray = ja;
            iter = ja.iterator();
        }
        
        /**
         * helper method that produces a list of entrances
         * @param jarray JSON array of entrances
         * @return 
         */
        private ArrayList<WEntrance> entrances(JSONArray jarray) {
            ArrayList<WEntrance> result = new ArrayList<>();
            
            for (Object o : jarray) {
                if (o instanceof JSONObject) {
                    JSONObject obj = (JSONObject)o;
                    result.add(
                            new WEntrance(
                                    obj.getString("direction"),
                                    obj.getString("to"),
                                    obj.getBoolean("locked")));
                }
            }
            
            return result;
        }
        
        /**
         * helper method that produces a list of items
         * @param jarray JSON array of items
         * @return list of items
         */
        private ArrayList<String> items(JSONArray jarray) {
            ArrayList<String> result = new ArrayList<>();
            for (Object o : jarray) {
                if (o instanceof JSONObject) {
                    JSONObject obj = (JSONObject)o;
                    result.add(obj.getString("item"));
                }
            }
            return result;
        }
           
        /**
         * another room
         * @return true if there is another room, otherwise false
         */
        @Override
        public boolean hasNext() {
            return iter.hasNext(); 
        }
                
        /**
         * move forward in the iterator of rooms
         * @return next room
         */
        @Override
        public WRoom next() {
            Object o = iter.next();
            if (o instanceof JSONObject) {
                JSONObject obj = (JSONObject) o;

                return new WRoom(
                                obj.getString("name"),
                                obj.getString("description"),
                                entrances(obj.getJSONArray("entrances")),
                                items(obj.getJSONArray("items")),
                                obj.getInt("zombies"));
            }
            // object not valid
            return null;
        }

        /**
         * method not implemented
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
