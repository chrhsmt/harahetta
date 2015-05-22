/**
 * 
 */
package jp.ac.aiit.network.harahetta.entity.recruit;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * スモールエリア.
 * @author hashimoto
 *
 */
public class SmallArea extends ServiceArea implements Serializable {

    /** シリアルバージョン.  */
    private static final long serialVersionUID = -5589374304453406535L;

    private ServiceArea middleArea;

    private ServiceArea largeArea;

    private ServiceArea serviceArea;

    private ServiceArea largeServiceArea;

    /**
     * @return the middleArea
     */
    public ServiceArea getMiddleArea() {
        return middleArea;
    }

    /**
     * @param middleArea the middleArea to set
     */
    @XmlElement(name="middle_area")
    public void setMiddleArea(ServiceArea middleArea) {
        this.middleArea = middleArea;
    }

    /**
     * @return the largeArea
     */
    public ServiceArea getLargeArea() {
        return largeArea;
    }

    /**
     * @param largeArea the largeArea to set
     */
    @XmlElement(name="large_area")
    public void setLargeArea(ServiceArea largeArea) {
        this.largeArea = largeArea;
    }

    /**
     * @return the serviceArea
     */
    public ServiceArea getServiceArea() {
        return serviceArea;
    }

    /**
     * @param serviceArea the serviceArea to set
     */
    @XmlElement(name="service_area")
    public void setServiceArea(ServiceArea serviceArea) {
        this.serviceArea = serviceArea;
    }

    /**
     * @return the largeServiceArea
     */
    public ServiceArea getLargeServiceArea() {
        return largeServiceArea;
    }

    /**
     * @param largeServiceArea the largeServiceArea to set
     */
    @XmlElement(name="large_service_area")
    public void setLargeServiceArea(ServiceArea largeServiceArea) {
        this.largeServiceArea = largeServiceArea;
    }

}
