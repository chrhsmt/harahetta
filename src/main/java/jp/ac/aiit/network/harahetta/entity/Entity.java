/**
 * 
 */
package jp.ac.aiit.network.harahetta.entity;

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

    private Result results;

    /**
     * @return the results
     */
    public Result getResults() {
        return results;
    }

    /**
     * @param results the results to set
     */
    public void setResults(Result results) {
        this.results = results;
    }
}
