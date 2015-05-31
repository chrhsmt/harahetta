package jp.ac.aiit.network.harahetta.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import jp.ac.aiit.network.harahetta.entity.Meal;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * 腹減ったクライアント.
 * @author ko
 * @author hashimoto
 *
 */
public class HarahettaClient {

    private static final int SUCCESS = 0;
    private static final int ERROR = 1;
    private static final String LF = "\n";
    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 8810;
	private static final String USER_AGENT = "harahetta-client-1.0";
	private static final String PROTOCOL_VERSION = "HARAHETTA/1.0";

	/**
     * メイン.
     * @param args
     * @throws IOException
     * @throws UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException, IOException {

    	Socket sock = null;
    	BufferedReader sockin = null;
    	BufferedReader commandLine = null;
    	BufferedWriter sockout = null;
    	String str;// 返す値を受け取る変数

		int exitCode = SUCCESS;
		String host = (args.length >= 1) ? args[0] : DEFAULT_HOST;
		String portStr = (args.length >= 2) ? args[1] : String.valueOf(DEFAULT_PORT);
		int port = DEFAULT_PORT;
		try {
			port = Integer.parseInt(portStr);
		} catch(NumberFormatException e) {
			System.err.println(String.format("ポートの指定が不正です。デフォルトポート %d を利用します", DEFAULT_PORT));
		}

		/**
    	* リクエストヘッダ
    	*/

    	//住所、駅名
    	String address = "address";
    	String station = "station";

    	//駅名などは一つ目のパラメータから取得
    	String locationName = "";
    	// レスポンスの配列
    	String[] response = new String[5] ;

    	String userName = System.getenv("USER") != null ? System.getenv("USER") : "Guest";
    	System.out.println(String.format("こんにちは %s さん", userName));
    	System.out.println("[第一引数:host, 第二引数:port で接続先を変更できます]");
    	while (true){
        	String command1 = "SUGGESTION"; //通常はSUGGESTION、変わる可能性あり
    	    try{
    	        //コンソールからパラメータを取得
    	        commandLine = new BufferedReader(new InputStreamReader(System.in));

    	        try {
	    	        System.out.println("現在の場所を入力してください、用済みの場合は\"exit\"を入力してください：");
	    	        locationName = commandLine.readLine();
	    	        if ("exit".equals(locationName)) {
		    	        System.out.println("サービスを終了します。");
		    	        break;
	    	        }
	    	        System.out.println("他に条件があれば入力してください(お店ジャンル[例えば「アジア」]など。無ければreturn)。");
	    	        String other = commandLine.readLine();
	    	        if (other != null && !other.equals("")) {
	    	        	command1 = other;
	    	        }
    	        } catch(IOException e){
	    	        System.out.println("入力エラー:" + e.getMessage());
	    	        continue;
    	        }

				System.out.println("今店を念力で探しますね。");

				sock = new Socket(host, port);
    	        sock.setSoTimeout(10000); //10秒でタイムアウト

    	        sockin = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    	        sockout = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
		
    	        // HTTPリクエスト送信
				sockout.write(String.format("GET %s %s %s", command1, PROTOCOL_VERSION, LF));
				sockout.write(String.format("User-Agent: %s %s", USER_AGENT, LF));
				sockout.write(String.format("location-type: %s, %s %s", address, station, LF));
				sockout.write(String.format("location: %s %s", locationName, LF));
				sockout.write(LF);
				//リクエストボディ
				sockout.write(LF);
				sockout.flush();

				int i = 0;
				while ((str = sockin.readLine()) != null && i < 5) {
				  response[i] = str;
				  i++;
				}
				//ステータスコード判定
				if (response[0].indexOf("300") > 0) {
				  System.out.println("場所が見つからない。");
				  continue;
				} else if (response[0].indexOf("400") > 0) {
				  System.out.println("店が見つからない。");
				  continue;
				} else if (response[0].indexOf("500") > 0) {
				  System.out.println("サーバ側のシステムエラー。");
				  break;
				} else if (response[0].indexOf("501") > 0) {
				  System.out.println("要求された機能は実装されていない。");
				  continue;
				}
				ObjectMapper mapper = new ObjectMapper();
				Meal meal = mapper.readValue(response[3], Meal.class);

				// 生成したメッセージたを出力する
				StringBuilder result = new StringBuilder();
				result.append("店名：")
				      .append(meal.getName())
				      .append("\n場所：")
				      .append(meal.getLocation());
				if (meal.getCatchCopy() != null){
					result.append("\n一言紹介：")
					      .append(meal.getCatchCopy());
				}
				if (meal.getCatchCopy() != null){
					result.append("\nジャンル：")
					      .append(meal.getJenre());
				}
				if (meal.getUrlPc() != null){
					result.append("\nURL(PC)：")
					      .append(meal.getUrlPc());
				}
				if (meal.getUrlMobile() != null){
					result.append("\nURL(Mobile)：")
					      .append(meal.getUrlMobile());
				}
				
				System.out.println("占いであなたと縁がある店を探したよ。");
				System.out.println(result.toString());
				System.out.println("");
    	    } catch (ConnectException ex) {
    	    	System.out.println("接続タイムアウトが発生しました。");
    	    	exitCode = ERROR;
    	    	break;
    	    } catch (SocketTimeoutException ex){
    	    	System.out.println("読み取る処理にタイムアウトが発生しました。");
    	    	exitCode = ERROR;
    	    	break;
    	    } finally {
				// ソケットのクローズ
    	    	if (sock != null) {
    	    		sock.close();
    	    	}
    	    	if (sockin != null) {
    	    		sockin.close();
    	    	}
    	    	if (sockin != null) {
    	    		sockin.close();
    	    	}
    	    }
    	}
    	System.exit(exitCode);
    }
}
