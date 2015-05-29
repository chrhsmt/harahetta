/**
 * 
 */
package jp.ac.aiit.network.harahetta.service;

import java.net.URI;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.UriBuilder;

import jp.ac.aiit.network.harahetta.entity.Meal;
import jp.ac.aiit.network.harahetta.entity.Request;
import jp.ac.aiit.network.harahetta.entity.recruit.Entity;
import jp.ac.aiit.network.harahetta.entity.recruit.Shop;
import jp.ac.aiit.network.harahetta.entity.recruit.ShopResult;
import jp.ac.aiit.network.harahetta.exception.NotImplementedException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.json.impl.provider.entity.JsonProvider;

/**
 * お勧めサービス.
 * @author hashimoto
 *
 */
public class RecommendService {

    private static String RECRUIT_API_KEY;
    static {
    	ResourceBundle bundle = null;
    	try {
        	bundle = ResourceBundle.getBundle("release");
    	} catch(MissingResourceException e) {
    	}
    	
    	if (bundle != null && bundle.getString("api_key") != null) {
        	RECRUIT_API_KEY = bundle.getString("api_key");
    	} else if (System.getenv("api_key") != null) {
        	RECRUIT_API_KEY = System.getenv("api_key");
    	} else {
    		throw new RuntimeException("not found api key !!");
    	}
    }

    private static Logger logger = Logger.getLogger("info");

    private static final String HOTPEPPER_API_SCHEME       = "http";
    private static final String HOTPEPPER_API_HOST         = "webservice.recruit.co.jp";
    @SuppressWarnings("unused")
    private static final String HOTPEPPER_API_AREA_PATH    = "hotpepper/small_area/v1/";
    private static final String HOTPEPPER_API_AREA_GOURMET = "hotpepper/gourmet/v1/";
    
    /**
     * お勧め情報取得.
     * @return
     */
    public Meal getRecommend(Request request) {

        List<Shop> areas = this.getShops(request);

        if (areas == null || areas.isEmpty()) {
        	return null;
        }

        // 暴君ロジックで色々と

        Random random = new Random();
        return new Meal(areas.get(random.nextInt(areas.size() == 1 ? areas.size() : (areas.size() - 1))));
    }
    
    /**
     *  店舗情報取得.
     */
    private List<Shop> getShops(Request request) {

        URI uri = this.buildUri(request);
        logger.info(uri.toString());
        
        ClientConfig config = new DefaultClientConfig();
        //config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        config.getClasses().add(JsonProvider.class);
        Client client = Client.create(config);
        WebResource resource = client.resource(uri);
        ClientResponse response = 
            resource.accept(MediaType.APPLICATION_JSON_TYPE)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .get(ClientResponse.class);
        
        if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
            Entity entity = response.getEntity(Entity.class);
            ShopResult result = entity.getResults();
            logger.info(String.format("api version : %s", result.getApiVersion()));
            logger.info(String.format("result count : %d", result.getResultsReturned()));
            if (result.getResultsReturned() == 0) {
            	return null;
            }
            for (Shop shop: result.getShops()) {
                logger.info(String.format("[%s] : %s[%s] @ %s",
                        shop.getId(), shop.getName(), shop.getFood().getName(), shop.getAddress()));
            }
            return result.getShops();
        } else {
            logger.info(response.getStatusInfo().toString());
            return null;
        }
    }
    
    private URI buildUri(Request request) {
    	UriBuilder uri = UriBuilder.fromPath(HOTPEPPER_API_AREA_GOURMET)
    							   .scheme(HOTPEPPER_API_SCHEME)
                                   .host(HOTPEPPER_API_HOST)
                                   .queryParam("key", RECRUIT_API_KEY)
//                                   .queryParam("type", "lite")
					               .queryParam("format", "json");
    //                .queryParam("format", "xml")
    	StringBuilder keywordBuilder = new StringBuilder();
    	
    	if (request.containAddress() || request.containStation()) {
    		if (request.containAddress()) {
                uri.queryParam("address", request.getLocation());
    		}
    		if (request.containStation()) {
    		    keywordBuilder.append(request.getLocation());
    		}
    	} else if (request.containPhone()) {
    		uri.queryParam("tel", request.getLocation());
    	} else if (request.containGeographic()) {
    		uri.queryParam("lat", request.getLocation().split(",")[0].trim());
    		uri.queryParam("lng", request.getLocation().split(",")[1].trim());
    	} else if (request.containZip()) {
    		throw new NotImplementedException();
    	}
    	
    	if (!"SUGGESTION".equals(request.getRequestArgument())
    	        && request.getRequestArgument() != null
    	        && !request.getRequestArgument().equals("")) {
    	    if (keywordBuilder.length() > 0) {
    	        keywordBuilder.append(" ");
    	    }
    	    keywordBuilder.append(request.getRequestArgument());
    	}
        uri.queryParam("keyword", keywordBuilder.toString());
        return uri.build();
    }
}
