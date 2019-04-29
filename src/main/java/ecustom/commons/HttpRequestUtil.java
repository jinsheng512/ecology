package ecustom.commons;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HttpRequestUtil {
	
	private final static Logger log = Logger.getLogger(HttpRequestUtil.class);
	private static BufferedReader reader;
	private static URL url;
	
	/**
	 * 发送HttpPost请求
	 * @param strURL 服务地址
	 * @param params 请求数据
	 * @return 成功:返回json字符串<br/>
	 */
//	public static String postJson(String strURL, Object params) {
//		return postJson(strURL, params, false);
//	}
	
	/**
	 * 发送HttpPost请求
	 * @param strURL 服务地址
	 * @param params 请求数据
	 * @param logIO 是否打印输入输出
	 * @return 成功:返回json字符串<br/>
	 */
	public static JsonObject postJson(String strURL, String params, boolean logIO) {
		log.info("requestUrl = " + strURL);
	
			  try {
		            url = new URL(strURL);
		            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		            httpURLConnection.setRequestMethod("POST");// 提交模式
		            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
		            // conn.setReadTimeout(2000);//读取超时 单位毫秒
		            // 发送POST请求必须设置如下两行
		            httpURLConnection.setDoOutput(true);
		            httpURLConnection.setDoInput(true);
		           
		            //解决HttpUrlConnection乱码问题
		            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//		            httpURLConnection.setRequestProperty("Content-type", "text/html");  
		            httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");  
		            httpURLConnection.setRequestProperty("contentType", "utf-8");  
		            httpURLConnection.setRequestProperty("Charset", "utf-8");
		            
		            // 获取URLConnection对象对应的输出流
		            
		            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
		            // 发送请求参数
		          
//		            printWriter.write(  URLEncoder.encode(params,"utf-8"));//post的参数 xx=xx&yy=yy
		            printWriter.write( params);//post的参数 xx=xx&yy=yy
		            // flush输出流的缓冲
		            printWriter.flush();
		            //开始获取数据
		            BufferedInputStream bis = new  BufferedInputStream(httpURLConnection.getInputStream());
		            ByteArrayOutputStream bos = new ByteArrayOutputStream();
		            int len;
		            byte[] arr = new byte[1024];
		            while((len=bis.read(arr))!= -1){
		                bos.write(arr,0,len);
		                bos.flush();
		            }
		            bos.close();
		            JsonParser parse = new JsonParser();
		            return (JsonObject)parse.parse(bos.toString("utf-8"));
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return null;

		
	}
	
	
	/**
	 * 发送HttpPost请求
	 * @param strURL 服务地址
	 * @param params 请求数据(json格式)
	 * @param logIO 是否打印输入输出
	 * @return 成功:返回json字符串<br/>
	 */
	public static String postJson1(String strURL, Object params, boolean logIO) {
		log.info("requestUrl = " + strURL);
		try {
			URL url = new URL(strURL);// 创建连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST"); // 设置请求方式
			connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
			connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
			connection.connect();
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
			String inputJson = new Gson().toJson(params);
			if (logIO) {
				log.info("inputJson = " + inputJson);
			} else {
				log.debug("inputJson = " + inputJson);
			}
			out.append(inputJson);
			out.flush();
			out.close();

			int code = connection.getResponseCode();
			InputStream is = null;
			if (code == 200) {
				is = connection.getInputStream();
			} else {
				is = connection.getErrorStream();
			}

				  // 读取响应
	            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
	            String line;
	            String res = "";
	            while ((line = reader.readLine()) != null) {
	                res += line;
	            }
	            reader.close();
	            return res;

		} catch (IOException e) {
			log.error("Exception occur when send http post request!", e);
		}
		return "error"; // 自定义错误信息
	}
}
