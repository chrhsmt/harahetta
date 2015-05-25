/**
 * 
 */
package jp.ac.aiit.network.harahetta.entity;

import java.io.Serializable;

import jp.ac.aiit.network.harahetta.entity.recruit.Shop;

/**
 * 食事情報.
 * @author hashimoto
 *
 */
public class Meal implements Serializable {

    /** シリアルバージョン.  */
    private static final long serialVersionUID = -4176394766221583778L;

    private String shopName;
    
    private String address;

    public Meal() {
    }

    public Meal(Shop shop) {
        this.setAddress(shop.getAddress());
        this.setShopName(shop.getName());
    }

    /**
     * @return the shopName
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * @param shopName the shopName to set
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
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
}
