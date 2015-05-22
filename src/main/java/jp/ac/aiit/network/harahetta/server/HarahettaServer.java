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
import jp.ac.aiit.network.harahetta.service.RecommendService;

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

    /** default port number. */
    private static final int DEFAULT_PORT = 1234;

    private void run() throws Exception {

        new RecommendService().getRecommend();

    }

    /**
     * メイン.
     * @param args
     */
    public static void main(String[] args) {
        try {
            new HarahettaServer().run();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
