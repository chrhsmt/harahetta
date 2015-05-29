/**
 * 
 */
package jp.ac.aiit.network.harahetta.entity.recruit;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author hashimoto
 *
 */
public class Shop implements Serializable {

    /** シリアルバージョン.  */
    private static final long serialVersionUID = 1187094905054987888L;

    private String id;
    
    private String name;
    
    private String address;

    private Item food;
    
    private String open;
    
    private Urls urls;

    private String catchCopy;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the food
     */
    public Item getFood() {
        return food;
    }

    /**
     * @param food the food to set
     */
    public void setFood(Item food) {
        this.food = food;
    }

    /**
     * @return the open
     */
    public String getOpen() {
        return open;
    }

    /**
     * @param open the open to set
     */
    public void setOpen(String open) {
        this.open = open;
    }

    /**
     * @return the catchCopy
     */
    public String getCatchCopy() {
        return catchCopy;
    }

    /**
     * @param catchCopy the catchCopy to set
     */
    @XmlElement(name="catch")
    public void setCatchCopy(String catchCopy) {
        this.catchCopy = catchCopy;
    }

    /**
     * @return the urls
     */
    public Urls getUrls() {
        return urls;
    }

    /**
     * @param urls the urls to set
     */
    public void setUrls(Urls urls) {
        this.urls = urls;
    }
}
