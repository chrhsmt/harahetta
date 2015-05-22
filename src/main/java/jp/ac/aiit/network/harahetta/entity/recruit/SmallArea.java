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
public class SmallArea extends Item implements Serializable {

    /** シリアルバージョン.  */
    private static final long serialVersionUID = -5589374304453406535L;

    private Item middleArea;

    private Item largeArea;

    private Item serviceArea;

    private Item largeServiceArea;

    /**
     * @return the middleArea
     */
    public Item getMiddleArea() {
        return middleArea;
    }

    /**
     * @param middleArea the middleArea to set
     */
    @XmlElement(name="middle_area")
    public void setMiddleArea(Item middleArea) {
        this.middleArea = middleArea;
    }

    /**
     * @return the largeArea
     */
    public Item getLargeArea() {
        return largeArea;
    }

    /**
     * @param largeArea the largeArea to set
     */
    @XmlElement(name="large_area")
    public void setLargeArea(Item largeArea) {
        this.largeArea = largeArea;
    }

    /**
     * @return the serviceArea
     */
    public Item getServiceArea() {
        return serviceArea;
    }

    /**
     * @param serviceArea the serviceArea to set
     */
    @XmlElement(name="service_area")
    public void setServiceArea(Item serviceArea) {
        this.serviceArea = serviceArea;
    }

    /**
     * @return the largeServiceArea
     */
    public Item getLargeServiceArea() {
        return largeServiceArea;
    }

    /**
     * @param largeServiceArea the largeServiceArea to set
     */
    @XmlElement(name="large_service_area")
    public void setLargeServiceArea(Item largeServiceArea) {
        this.largeServiceArea = largeServiceArea;
    }

}
