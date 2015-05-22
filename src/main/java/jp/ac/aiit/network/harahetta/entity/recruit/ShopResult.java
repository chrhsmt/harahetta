/**
 * 
 */
package jp.ac.aiit.network.harahetta.entity.recruit;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 店舗結果.
 * @author hashimoto
 *
 */
@XmlRootElement
public class ShopResult extends Result implements Serializable {

    /** シリアルバージョン.  */
    private static final long serialVersionUID = 8331336950429606074L;

    private List<Shop> shops;

    /**
     * @return the shops
     */
    public List<Shop> getShops() {
        return shops;
    }

    /**
     * @param shops the shops to set
     */
    @XmlElement(name="shop")
    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
    
}
