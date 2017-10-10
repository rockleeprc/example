package k17.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class K17Util {
	public static void main(String[] args) throws IOException {
		String url = "http://openapi.tadu.com/360Book/BookList";
		Map<String,String> map = new HashMap<String,String>();
		map.put("begin_time", "2017100911");
		map.put("end_time", "2017100915");
		HttpRequester req = new HttpRequester();
		HttpRespons sendPost = req.sendPost(url,map);
//		req.sendGet(url, map);
		System.out.println(sendPost);
	}
	
	public static String doPost(String uri,Map<String,String> params,String charset){
		 PrintWriter out = null;  
	        BufferedReader in = null;  
	        String result = "";  
	        try {  
	            URL realUrl = new URL(uri);  
	            // 打开和URL之间的连接  
	            URLConnection conn = realUrl.openConnection();  
	            // 设置通用的请求属性  
	            conn.setRequestProperty("accept", "*/*");  
	            conn.setRequestProperty("connection", "Keep-Alive");  
	            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
	            // conn.setRequestProperty("Charset", "UTF-8");  
	            // 发送POST请求必须设置如下两行  
	            conn.setDoOutput(true);  
	            conn.setDoInput(true);  
	            // 获取URLConnection对象对应的输出流  
	            out = new PrintWriter(conn.getOutputStream());  
	  
	            // 设置请求属性  
	            String param = "";  
	            if (params != null && params.size() > 0) {  
	                Iterator<String> ite = params.keySet().iterator();  
	                while (ite.hasNext()) {  
	                    String key = ite.next();// key  
	                    String value = params.get(key);  
	                    param += key + "=" + value + "&";  
	                }  
	                param = param.substring(0, param.length() - 1);  
	            }  
	  
	            // 发送请求参数  
	            out.print(param);  
	            // flush输出流的缓冲  
	            out.flush();  
	            // 定义BufferedReader输入流来读取URL的响应  
	            in = new BufferedReader(  
	                    new InputStreamReader(conn.getInputStream()));  
	            String line;  
	            while ((line = in.readLine()) != null) {  
	                result += line;  
	            }  
	        } catch (Exception e) {  
	            System.err.println("发送 POST 请求出现异常！" + e);  
	            e.printStackTrace();  
	        }  
	        // 使用finally块来关闭输出流、输入流  
	        finally {  
	            try {  
	                if (out != null) {  
	                    out.close();  
	                }  
	                if (in != null) {  
	                    in.close();  
	                }  
	            } catch (IOException ex) {  
	                ex.printStackTrace();  
	            }  
	        }  
	        return result;  
	}
	
	
	public static String doPost1(String uri,Map<String,String> params,String charset){
		try {
			   //需要放入请求流中的字符串
			   String s = "123=ABCD你好hello流";
			   System.out.println(s);
			    
			   //从这里开始起http请求
			   URL url = new URL(uri);
			   //构造节点流
			   ByteArrayInputStream in = new ByteArrayInputStream(
			     s.getBytes(charset));
			   //openConnection打开连接，此处并没有真正开始请求
			   URLConnection urlConnection = url.openConnection();
			   //将URLConnection对象转化为HttpURLConnection对象
			   HttpURLConnection httpUrlConnection = (HttpURLConnection) urlConnection;
			   //设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在流里，需要设置为true，默认false
			   httpUrlConnection.setDoInput(true);
			   //设置是否从httpUrlConnection读入（response内容），默认true
			   httpUrlConnection.setDoOutput(true);
			   //Post 请求不能使用缓存
			   httpUrlConnection.setUseCaches(false);   
			   httpUrlConnection.setConnectTimeout(5000);
			   // 设置request headers属性,Content-type属性很重要，服务器端根据这个值来处理消息内容，可自定义扩展；对应的还有一个Content-length，如果不清楚则不要填写
			   httpUrlConnection.setRequestProperty("Content-type","application/json");
			   //请求类型POST或GET，默认是GET   
			   httpUrlConnection.setRequestMethod("POST");
			    
			   // 连接，从上述url.openConnection()至此的配置必须要在connect之前完成
			   httpUrlConnection.connect();
			   //打开http流管道，写入数据（post给服务器端的数据）。此处getOutputStream会隐含的进行connect(即：如同调用上面的connect()方法，所以在开发中不调用上述的connect()也可以)
			   OutputStream outStrm = httpUrlConnection.getOutputStream();
			   //写入数据流的方法
			   int count = 0;
			   byte[] buffer = new byte[1024];
			   while ((count = in.read(buffer)) != -1) {
			    outStrm.write(buffer, 0, count);
			   }
			   outStrm.flush();
			   outStrm.close();
			   //<===注意，实际发送请求的代码段就在这里,将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端
			   InputStream inStrm = httpUrlConnection.getInputStream();
			 
			   //再次实用节点流，接收response回来的信息
			   ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			   byte[] retdata = new byte[1024];
			   int c = -1;
			   while ((c = inStrm.read(retdata, 0, 1024)) != -1)
			    outStream.write(retdata, 0, c);
			   outStream.flush();
			   outStream.close();
			   retdata = null;
			   String rets = new String(outStream.toByteArray(), "utf-8");
			   System.out.println("=============================inStrm:" + rets);
			   if (httpUrlConnection.getResponseCode() == 200) {
			    System.out.println("ok");
			   } else {
			    System.out.println("fail");
			   }
			   return rets;
			  } catch (MalformedURLException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			  } catch (ProtocolException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			  } catch (UnsupportedEncodingException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			  } catch (IOException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			  }
		return null;
	}
	
	
}
