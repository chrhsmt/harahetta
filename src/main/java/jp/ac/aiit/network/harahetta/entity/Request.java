/**
 * 
 */
package jp.ac.aiit.network.harahetta.entity;

import java.io.Serializable;

/**
 * @author chihiro
 *
 */
public class Request implements Serializable {

    /** シリアルバージョン.  */
	private static final long serialVersionUID = -5767652596698842606L;

	private String method;
	private String requestArgument;
	private String protocolVersion;
	private String UserAgent;
	private String locationType;
	private String location;

	public boolean containZip() {
		return this.locationType.contains("zip");
	}

	public boolean containPhone() {
		return this.locationType.contains("phone");
	}

	public boolean containAddress() {
		return this.locationType.contains("address");
	}

	public boolean containStation() {
		return this.locationType.contains("station");
	}

	public boolean containGeographic() {
		return this.locationType.contains("geographic");
	}

	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getRequestArgument() {
		return requestArgument;
	}
	public void setRequestArgument(String requestArgument) {
		this.requestArgument = requestArgument;
	}
	public String getProtocolVersion() {
		return protocolVersion;
	}
	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}
	public String getUserAgent() {
		return UserAgent;
	}
	public void setUserAgent(String userAgent) {
		UserAgent = userAgent;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
