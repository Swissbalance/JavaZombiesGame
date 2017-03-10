/*
 * Author: Benedict R. Gaster
 * Desc: Represents an item in the world
 */
package world;

/**
 * Created by br-gaster on 11/16/16.
 */
public class WItem {

    // item name
    private final String name;

    // html used when item is to be display in client
    private final String html;

    /**
     * constructor to create an item
     *
     * @param name of item
     * @param html for displaying item in client
     */
    WItem(String name, String html) {
        this.name = name;
        this.html = html;
    }

    /**
     * name of item
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * html to display item in client
     *
     * @return HTML
     */
    public String getHtml() {
        return html;
    }
}
