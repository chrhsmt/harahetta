/**
 * 
 */
package jp.ac.aiit.network.harahetta.server;

import java.net.URI;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.UriBuilder;

import jp.ac.aiit.network.harahetta.entity.recruit.Entity;
import jp.ac.aiit.network.harahetta.entity.recruit.Result;
import jp.ac.aiit.network.harahetta.entity.recruit.SmallArea;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.json.impl.provider.entity.JsonProvider;

/**
 * 腹減ったサーバ.
 * @author hashimoto
 * @since 2015-05-21
 * @version 0.0.1
 *
 */
public class HarahettaServer {

    private static ResourceBundle bunlde = ResourceBundle.getBundle("release");
    private static String RECRUIT_API_KEY = bunlde.getString("api_key");

    private static Logger logger = Logger.getGlobal();

    private void run() throws Exception {
        
        URI uri = UriBuilder.fromPath("hotpepper/small_area/v1/")
                .scheme("http")
    //                .port(3000)
                .host("webservice.recruit.co.jp")
                .queryParam("key", RECRUIT_API_KEY)
                .queryParam("keyword", "青物横丁")
                .queryParam("format", "json")
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
            Result result = entity.getResults();
            logger.info(String.format("api version : %s", result.getApiVersion()));
            logger.info(String.format("result count : %d", result.getResultsReturned()));
            for (SmallArea area : result.getSmallArea()) {
                logger.info(String.format("[%s] : %s", area.getCode(), area.getName()));
            }
        } else {
            logger.info(response.getStatusInfo().toString());
        }
    }

    /**
     * メイン.
     * @param args
     */
    public static void main(String[] args) {
        try {
            logger.setLevel(Level.INFO);
            logger.info("yes");

            new HarahettaServer().run();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
