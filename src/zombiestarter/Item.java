/*
 * Author: Jake Chapman, Connor Hill, Adam Williams
 * Desc: This class contain all information on the Item,
 *       It also processes how the client interacts with the Item.
 */
package zombiestarter;

public class Item {

    //name of the item
    private final String name;

    //html string that displays image
    private final String html;

    //constructor
    Item(String name, String html) {
        this.name = name;
        this.html = html;
    }

    //get name method
    public String getName() {
        return name;
    }

    //get html for image method
    public String getHtml() {
        return html;
    }
    
}
