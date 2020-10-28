package com.mendale.web.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;

public class SmsUtil {
	/* 获取配置文件参数信息 */
	public final static String FILE_NAME="/sms.properties";	
	static String USER              = ""  ;
	static String PWD               = ""  ;
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                //System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;Windows NT 6.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
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
            result = "1`" + result;
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
            result = "0`" + e.getMessage();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }  
    public void getProperties() {
		try {
			Properties props = new Properties();   
			InputStream meidrpStream = this.getClass().getResourceAsStream(FILE_NAME); //Object.class.getResourceAsStream(FILE_NAME);   
			props.load(meidrpStream); 
			
			USER     = (String)props.get("sms_user");
			PWD      = (String)props.get("sms_password");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    public String sendOperate(String templateNames,String phones, String datetimes) {
    	String ret2 = "";
    	String ret3 = "";
    	try {
    		String url      = "http://www.ding88.net/SendSms.asp";
    		String url1     = "http://www.ding88.net/GetReport.asp";
	    	String account  = USER;
	    	String password = PWD;
	    	String content  = URLEncoder.encode(templateNames, "gb2312");
	    	String channel  = "1";
	    	String request  = "Account="+account+"&Password="+password+"&Phones="+phones+"&Content="+content+"&Channel="+channel+"&SendTime="+datetimes;
	    	String request1 = "Account="+account+"&Password="+password;
	    	
	        //发送 POST 请求
    		ret2=SmsUtil.sendPost(url, request);
	        //System.out.println(ret2);
	    	
	        String[] ret2Array = ret2.split("`");
	        String ret2Array0  = ret2Array[0];
	        ret2               = ret2Array[1];
	        if(ret2Array0.equals("0")){
	        	return "0`" + ret2;
	        }
	        
		    if(Integer.parseInt(ret2) <= 0){
		    	ret2 = "0`" + "失败";
		    	return ret2;
			}
		    
    	}catch (Exception e) {
    		return "0`" + e.getMessage();
    		//System.out.println("请求出现异常！"+e);
            //e.printStackTrace();
		}
    	return "1`" + "成功";
    }  
    
    public static void main(String[] args) throws UnsupportedEncodingException {
        //发送 GET 请求
    	String url="http://www.ding88.net/SendSms.asp";
    	String account="DRP";
    	String password="123456";
    	String phones="13357313162";
    	String content=URLEncoder.encode("你好吗？", "gb2312");
    	String channel="1";
    	String request="Account="+account+"&Password="+password+"&Phones="+phones+"&Content="+content+"&Channel="+channel;
        String ret1=SmsUtil.sendGet(url, request);
        //System.out.println(ret1);
        
        //发送 POST 请求
        String ret2=SmsUtil.sendPost(url, request);
        //System.out.println(ret2);
    }
}

