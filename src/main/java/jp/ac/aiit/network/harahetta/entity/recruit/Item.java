/**
 * 
 */
package jp.ac.aiit.network.harahetta.entity.recruit;

import java.io.Serializable;

/**
 * アイテム.
 * @author hashimoto
 *
 */
public class Item implements Serializable {

    /** シリアルバージョン.  */
    private static final long serialVersionUID = -3537438421017095297L;

    private String code;

    private String name;

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
