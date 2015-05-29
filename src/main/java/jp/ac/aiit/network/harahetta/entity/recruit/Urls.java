/**
 * 
 */
package jp.ac.aiit.network.harahetta.entity.recruit;

import java.io.Serializable;

/**
 * @author hashimoto
 *
 */
public class Urls implements Serializable {

    /** シリアルバージョン.  */
    private static final long serialVersionUID = 45215032739412217L;

    private String pc;
    
    private String mobile;
    
    private String qr;

    /**
     * @return the pc
     */
    public String getPc() {
        return pc;
    }

    /**
     * @param pc the pc to set
     */
    public void setPc(String pc) {
        this.pc = pc;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the qr
     */
    public String getQr() {
        return qr;
    }

    /**
     * @param qr the qr to set
     */
    public void setQr(String qr) {
        this.qr = qr;
    }
    
}
