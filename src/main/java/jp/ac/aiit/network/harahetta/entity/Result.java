/**
 * 
 */
package jp.ac.aiit.network.harahetta.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author hashimoto
 *
 */
@XmlRootElement
public class Result {

    private String apiVersion;

    private int resultsAvailable;

    private int resultsReturned;

    private int resultsStart;

    private List smallArea;

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

    /**
     * @return the smallArea
     */
    public List getSmallArea() {
        return smallArea;
    }

    /**
     * @param smallArea the smallArea to set
     */
    @XmlElement(name="small_area")
    public void setSmallArea(List smallArea) {
        this.smallArea = smallArea;
    }

}
