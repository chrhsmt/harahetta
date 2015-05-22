/**
 * 
 */
package jp.ac.aiit.network.harahetta.entity.recruit;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author hashimoto
 *
 */
@XmlRootElement
public class Result implements Serializable {

    /** シリアルバージョン.  */
    private static final long serialVersionUID = -3005245852370897814L;

    private String apiVersion;

    private int resultsAvailable;

    private int resultsReturned;

    private int resultsStart;

    /**
     * @return the apiVersion
     */
    public String getApiVersion() {
        return apiVersion;
    }

    /**
     * @param apiVersion the apiVersion to set
     */
    @XmlElement(name="api_version")
    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    /**
     * @return the resultsAvailable
     */
    public int getResultsAvailable() {
        return resultsAvailable;
    }

    /**
     * @param resultsAvailable the resultsAvailable to set
     */
    @XmlElement(name="results_available")
    public void setResultsAvailable(int resultsAvailable) {
        this.resultsAvailable = resultsAvailable;
    }

    /**
     * @return the resultsReturned
     */
    public int getResultsReturned() {
        return resultsReturned;
    }

    /**
     * @param resultsReturned the resultsReturned to set
     */
    @XmlElement(name="results_returned")
    public void setResultsReturned(int resultsReturned) {
        this.resultsReturned = resultsReturned;
    }

    /**
     * @return the resultsStart
     */
    public int getResultsStart() {
        return resultsStart;
    }

    /**
     * @param resultsStart the resultsStart to set
     */
    @XmlElement(name="results_start")
    public void setResultsStart(int resultsStart) {
        this.resultsStart = resultsStart;
    }

}
