/**
 * 
 */
package jp.ac.aiit.network.harahetta.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import jp.ac.aiit.network.harahetta.exception.ParseException;

/**
 * リクエストパーサー.
 * @author chihiro
 *
 */
public class RequestParser {

	private static Logger logger = Logger.getGlobal();

	private static final int REQUEST_PART_FLG_LINE   = 0;
	private static final int REQUEST_PART_FLG_HEADER = 1;
	private static final int REQUEST_PART_FLG_BODY   = 2;
	
	private String method;
	private String requestArgument;
	private String protocolVersion;
	private String UserAgent;
	private String locationType;
	private String location;

	public RequestParser parse(InputStream input) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line = null;
        int nowRequestPart = REQUEST_PART_FLG_LINE;
        while ((line = reader.readLine()) != null) {
        	logger.info(line);
        	if (nowRequestPart == 0) {
        		this.parseRequestLine(line);
        		// next
        		nowRequestPart = REQUEST_PART_FLG_HEADER;
        		continue;
        	} else if (nowRequestPart == REQUEST_PART_FLG_HEADER) {

        		if (line == null || line.isEmpty()) {
        			// next
        			nowRequestPart = REQUEST_PART_FLG_BODY;
            		continue;
        		} else {
        			this.parseRequestHeader(line);
        		}
        		
        	} else if (nowRequestPart == REQUEST_PART_FLG_BODY) {
        		
        		if (line == null || line.isEmpty()) {
        			// next
            		break;
        		}
        	}
        	
        }
        return this;
	}

	/**
	 *  リクエストライン解析.
	 * @param line
	 */
	private void parseRequestLine(String line) {
		String[] requestLine = line.split(" ");
		if (requestLine.length != 3) {
			throw new ParseException("invalid request line!");
		}
		this.method          = requestLine[0].trim();
		this.requestArgument = requestLine[1].trim();
		this.protocolVersion = requestLine[2].trim();
	}
	
	private void parseRequestHeader(String line) {
		if (line.startsWith("User-Agent:")) {
			this.UserAgent = line.substring(line.indexOf(":") + 1).trim();
		} else if (line.startsWith("location-type:")) {
			this.locationType = line.substring(line.indexOf(":") + 1).trim();
		} else if (line.startsWith("location")) {
			this.location = line.substring(line.indexOf(":") + 1).trim();
		}
	}
}
