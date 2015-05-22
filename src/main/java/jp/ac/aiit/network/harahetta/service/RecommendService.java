/**
 * 
 */
package jp.ac.aiit.network.harahetta.service;

import java.net.URI;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.UriBuilder;

import jp.ac.aiit.network.harahetta.entity.Meal;
import jp.ac.aiit.network.harahetta.entity.recruit.Entity;
import jp.ac.aiit.network.harahetta.entity.recruit.Shop;
import jp.ac.aiit.network.harahetta.entity.recruit.ShopResult;

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

    private static final ResourceBundle bundle = ResourceBundle.getBundle("release");
    private static final String RECRUIT_API_KEY = bundle.getString("api_key");

    private static Logger logger = Logger.getGlobal();

    private static final String HOTPEPPER_API_SCHEME       = "http";
    private static final String HOTPEPPER_API_HOST         = "webservice.recruit.co.jp";
    @SuppressWarnings("unused")
    private static final String HOTPEPPER_API_AREA_PATH    = "hotpepper/small_area/v1/";
    private static final String HOTPEPPER_API_AREA_GOURMET = "hotpepper/gourmet/v1/";
    
    /**
     * お勧め情報取得.
     * @return
     */
    public Meal getRecommend() {

        List<Shop> areas = this.getShops("青物横丁");

        // 暴君ロジックで色々と

        return new Meal();
    }
    
    /**
     *  店舗情報取得.
     */
    private List<Shop> getShops(String name) {

        URI uri = UriBuilder.fromPath(HOTPEPPER_API_AREA_GOURMET)
                .scheme(HOTPEPPER_API_SCHEME)
                .host(HOTPEPPER_API_HOST)
                .queryParam("key", RECRUIT_API_KEY)
                .queryParam("keyword", name)
                .queryParam("format", "json")
                .queryParam("type", "lite")
    //                .queryParam("format", "xml")
                .build();
        
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
            for (Shop shop: result.getShops()) {
                logger.info(String.format("[%s] : %s @ %s", shop.getId(), shop.getName(), shop.getAddress()));
            }
            return result.getShops();
        } else {
            logger.info(response.getStatusInfo().toString());
            return null;
        }
    }
}
