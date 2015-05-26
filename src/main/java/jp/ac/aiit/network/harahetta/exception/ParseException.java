/**
 * 
 */
package jp.ac.aiit.network.harahetta.exception;

/**
 * @author chihiro
 *
 */
public class ParseException extends RuntimeException {

	/** シリアルバージョン. */
	private static final long serialVersionUID = -6546685109960236400L;

	public ParseException() {
		super();
	}

	public ParseException(String message) {
		super(message);
	}

}
