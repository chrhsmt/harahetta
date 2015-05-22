/**
 * 
 */
package jp.ac.aiit.network.harahetta.entity.recruit;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author hashimoto
 *
 */
@XmlRootElement
@Consumes({"text/javascript; charset=UTF-8"})
public class Entity implements Serializable {

    /** シリアルバージョン.  */
    private static final long serialVersionUID = -9098711549018573017L;

    private ShopResult results;

    /**
     * @return the results
     */
    public ShopResult getResults() {
        return results;
    }

    /**
     * @param results the results to set
     */
    public void setResults(ShopResult results) {
        this.results = results;
    }
}
