// Authors: Connor Hill, Jake Chapman, Adam Williams
// Description: Code regarding items
package zombiestarter;

public class Item {

    private final String name;
    private final String html;

    Item(String name, String html) {
        this.name = name;
        this.html = html;
    }

    // gets the item name
    public String getName() {
        return name;
    }

    // gets the item html for the image
    public String getHtml() {
        return html;
    }

}
