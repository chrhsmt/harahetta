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

    private String name;
    
    private String location;

    public Meal() {
    }

    public Meal(Shop shop) {
        this.setLocation(shop.getAddress());
        this.setName(shop.getName());
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
