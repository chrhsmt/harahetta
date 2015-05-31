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
    private String catchCopy;
    private String jenre;
    private String urlPc;
    private String urlMobile;

    public Meal() {
    }

    public Meal(Shop shop) {
        this.setLocation(shop.getAddress());
        this.setName(shop.getName());
        this.setCatchCopy(shop.getCatchCopy());
        this.setJenre(shop.getFood().getName());
        this.setUrlPc(shop.getUrls().getPc());
        this.setUrlMobile(shop.getUrls().getMobile());
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

	public String getCatchCopy() {
		return catchCopy;
	}

	public void setCatchCopy(String catchCopy) {
		this.catchCopy = catchCopy;
	}

	public String getJenre() {
		return jenre;
	}

	public void setJenre(String jenre) {
		this.jenre = jenre;
	}

	public String getUrlPc() {
		return urlPc;
	}

	public void setUrlPc(String urlPc) {
		this.urlPc = urlPc;
	}

	public String getUrlMobile() {
		return urlMobile;
	}

	public void setUrlMobile(String urlMobile) {
		this.urlMobile = urlMobile;
	}

	
}
