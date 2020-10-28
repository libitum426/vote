package com.mendale.web.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p> Title: 页面返回 HttpServletResponse 工具类</p>
 * <p> Description: </p>
 * @作者 xfc
 * @创建时间 2014-7-31 下午3:53:51
 * @版本 1.00
 * @修改记录
 * <pre>
 * 版本   修改人    修改时间    修改内容描述
 * </pre>
 */
public class ResponseUtil {

	/**
	 * 写JSON字符串
	 * 
	 * @param response
	 * @param json
	 */
	public static void outJsonString(HttpServletResponse response,String json) {  
        response.setContentType("application/json;charset=UTF-8");  
        outString(response,json);  
    }  
  
    /**
     * 写JSON对象
     * 
     * @param response
     * @param obj
     */
    public static void outJson(HttpServletResponse response,Object obj) {  
        outJsonString(response,JSONObject.fromObject(obj).toString());  
    }  
  
    /**
     * 写JSON数组
     * 
     * @param response
     * @param array
     */
    public static void outJsonArray(HttpServletResponse response,Object array) {  
        outJsonArray(response,JSONArray.fromObject(array).toString());  
    }  
  
    /**
     * 写字符串
     * 
     * @param response
     * @param json
     */
    public static void outString(HttpServletResponse response,String json) {  
        try {  
            PrintWriter out = response.getWriter();  
            out.write(json);  
            System.out.println(json);
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    /**
     * 输出XML格式
     * 
     * @param response
     * @param xmlStr
     */
    public static void outXMLString(HttpServletResponse response,String xmlStr) {  
        response.setContentType("application/xml;charset=UTF-8");  
        outString(response,xmlStr);  
    }  
    
    /**
     * 输出文本格式
     * 
     * @param response
     * @param xmlStr
     */
    public static void outTextXMLString(HttpServletResponse response,String xmlStr) {  
        response.setContentType("text/xml;charset=UTF-8");  
        outString(response,xmlStr);  
    }  
}
