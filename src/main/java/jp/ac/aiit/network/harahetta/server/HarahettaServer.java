/**
 * 
 */
package jp.ac.aiit.network.harahetta.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

import jp.ac.aiit.network.harahetta.entity.Meal;
import jp.ac.aiit.network.harahetta.entity.Request;
import jp.ac.aiit.network.harahetta.exception.NotImplementedException;
import jp.ac.aiit.network.harahetta.parser.RequestParser;
import jp.ac.aiit.network.harahetta.service.RecommendService;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * 腹減ったサーバ.
 * @author hashimoto
 * @since 2015-05-21
 * @version 0.0.1
 *
 */
public class HarahettaServer {

    /** default port number. */
    private static final int DEFAULT_PORT = 8810;
    private static Logger logger = Logger.getLogger("info");

    private void run() throws Exception {

        logger.info("server start ....");

        // ソケット
        ServerSocket serverSocket = null;
        BufferedReader reader = null;

        try {
            // ソケットを生成
            serverSocket = new ServerSocket(DEFAULT_PORT);

            while (true) {

                Socket socket = null;
                try {
                    // 入出力ストリームを用意し，クライアントからの要求を待つ
                    socket = serverSocket.accept();

                    // リクエスト処理
                    Request request = new RequestParser().parse(socket.getInputStream());
                    PrintStream writer = new PrintStream(socket.getOutputStream());

                    try {
                        // レコメンド処理
                    	Meal meal = new RecommendService().getRecommend(request);

                        if (meal == null) {
                            writer.println("HARAHETTA/1.0 400 NOWHERE TO EAT");
                            writer.println("");
                            writer.println("");
                        } else {
                            writer.println("HARAHETTA/1.0 200 OK");
                            writer.println("Content-Type: application/json");
                            writer.println("");
                            writer.println(new ObjectMapper().writeValueAsString(meal));
                            writer.println("");
                        }
                    } catch (NotImplementedException e) {
                        writer.println("HARAHETTA/1.0 500 NOT IMPLEMENTED");
                        writer.println("");
                        writer.println("");
                    } catch (Exception e) {
                        writer.println("HARAHETTA/1.0 500 SYSTEM ERROR");
                        writer.println("");
                        writer.println("");
					} finally {
	                    writer.flush();
	                    writer.close();
					}

                } catch (Exception e) {
                	logger.log(Level.SEVERE, e.getMessage(), e);
                } finally {
                    if (socket != null) {
                        socket.close();
                    }
                }
            }
        } catch (BindException e) {
        	logger.log(Level.SEVERE, "server is still running on same port.");
        	throw e;
        } catch (SocketException e) {
        	logger.log(Level.SEVERE, e.getMessage(), e);
        } catch (IOException e) {
        	logger.log(Level.SEVERE, e.getMessage(), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                }
            }
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (Exception e) {
                }
            }
        }
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
