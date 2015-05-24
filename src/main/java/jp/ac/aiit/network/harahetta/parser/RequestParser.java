/**
 * 
 */
package jp.ac.aiit.network.harahetta.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

/**
 * リクエストパーサー.
 * @author chihiro
 *
 */
public class RequestParser {

	private static Logger logger = Logger.getGlobal();

	public RequestParser parse(InputStream input) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line = null;
        while (reader.ready() && (line = reader.readLine()) != null) {
        	logger.info(line);
            if (line == null)
				break;
        }
        return this;
	}
}
