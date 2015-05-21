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

import jp.ac.aiit.network.harahetta.entity.Entity;

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

    /**
     * メイン.
     * @param args
     */
    public static void main(String[] args) {

        URI uri = UriBuilder.fromPath("hotpepper/small_area/v1/")
                            .scheme("http")
//                            .port(3000)
                            .host("webservice.recruit.co.jp")
                            .queryParam("key", RECRUIT_API_KEY)
                            .queryParam("keyword", "青物横丁")
                            .queryParam("format", "json")
//                            .queryParam("format", "xml")
                            .build();
        Logger logger = Logger.getGlobal();
        logger.setLevel(Level.INFO);
        logger.info("yes");

        System.out.println(uri.toString());
        
        ClientConfig config = new DefaultClientConfig();
//        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        config.getClasses().add(JsonProvider.class);
        Client client = Client.create(config);
        WebResource resource = client.resource(uri);
        ClientResponse response = 
                resource.accept(MediaType.APPLICATION_JSON_TYPE)
                        .type(MediaType.APPLICATION_JSON_TYPE)
                        .get(ClientResponse.class);

        if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
            Entity entity = response.getEntity(Entity.class);
            System.out.println(entity.getResults().getApiVersion());
            System.out.println(entity.getResults().getSmallArea().size());
        } else {
            System.out.println(response.getStatusInfo());
        }
    }
}
