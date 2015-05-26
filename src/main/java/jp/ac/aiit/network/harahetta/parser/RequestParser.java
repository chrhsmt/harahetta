/**
 * 
 */
package jp.ac.aiit.network.harahetta.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import jp.ac.aiit.network.harahetta.entity.Request;
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
	
	public Request parse(InputStream input) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line = null;
        Request request = new Request(); 
        int nowRequestPart = REQUEST_PART_FLG_LINE;
        while ((line = reader.readLine()) != null) {
        	logger.info(line);
        	if (nowRequestPart == 0) {
        		this.parseRequestLine(line, request);
        		// next
        		nowRequestPart = REQUEST_PART_FLG_HEADER;
        		continue;
        	} else if (nowRequestPart == REQUEST_PART_FLG_HEADER) {

        		if (line == null || line.isEmpty()) {
        			// next
        			nowRequestPart = REQUEST_PART_FLG_BODY;
            		continue;
        		} else {
        			this.parseRequestHeader(line, request);
        		}
        		
        	} else if (nowRequestPart == REQUEST_PART_FLG_BODY) {
        		
        		if (line == null || line.isEmpty()) {
        			// next
            		break;
        		}
        	}
        	
        }
        return request;
	}

	/**
	 *  リクエストライン解析.
	 * @param line
	 */
	private void parseRequestLine(String line, Request request) {
		String[] requestLine = line.split(" ");
		if (requestLine.length != 3) {
			throw new ParseException("invalid request line!");
		}
		request.setMethod(requestLine[0].trim());
		request.setRequestArgument(requestLine[1].trim());
		request.setProtocolVersion(requestLine[2].trim());
	}
	
	private void parseRequestHeader(String line, Request request) {
		if (line.startsWith("User-Agent:")) {
			request.setUserAgent(line.substring(line.indexOf(":") + 1).trim());
		} else if (line.startsWith("location-type:")) {
			request.setLocationType(line.substring(line.indexOf(":") + 1).trim());
		} else if (line.startsWith("location")) {
			request.setLocation(line.substring(line.indexOf(":") + 1).trim());
		}
	}
}
