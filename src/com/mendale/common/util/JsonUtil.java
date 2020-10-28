package com.mendale.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 内容摘要: 提供json转换为java bean ，
 *       以及java bean 转换为json的接口方法
 * @author:   jz
 * @version:  1.0  
 * @Date:     2014-9-3 上午8:37:04
 * 
 * 修改历史:  
 * 修改日期           修改人员     版本	修改内容  
 * --------------------------
 * 2014-9-3   jz   1.0   版本初始化
 * 版权:   版权所有(C) 2014
 */
public class JsonUtil {
     
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss" ;
	
	private static JsonConfig jsonConfig= null;

	  /**  
     * 设置日期转换格式  
     */  
    static {   

        //值处理器
        jsonConfig = new JsonConfig();
		//config.registerJsonBeanProcessor(Date.class, new DateJsonBeanProcessor());
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateValueProcessor(YYYY_MM_DD_HH_MM_SS));
    } 
    
    

   protected static class DateValueProcessor implements JsonValueProcessor {
	   
	private String format ;
	

	public void setFormat(String format) {
		this.format = format;
	}
	
	public DateValueProcessor(String format){
		this.format = format;
	}

	
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		
		return processValue(value);
		
	}

	
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		
		return processValue(value);
	}
	
	public Object processValue(Object value){
		String result = "" ;
		if(value != null && value instanceof Date){
			SimpleDateFormat fmt = new SimpleDateFormat(format);
			result = fmt.format(value);
		}else if(value == null){
			result = JSONNull.getInstance().toString();
		}else{
			throw new JSONException("非日期类型的对象无法转换");
		}
		return result;
	}
	
   }
	 /**
	 * bean转json
	 * <p>
	 * 〈功能详细描述〉
	 * </p>
	 *@param bean  
	 * @return String
	 */
	public static String converBean2Json(Object bean) {
		return JSONObject.fromObject(bean,jsonConfig).toString();
	}

	/**
	 * bean转json
	 * <p>
	 * 〈功能详细描述〉
	 * </p>
	 *@param bean  
	 * @return String
	 */
	public static String converList2Json(Object bean){
		return JSONArray.fromObject(bean,jsonConfig).toString();
	}
	
	
	
	public static void main(String[] args) {
		
		//System.out.println(MyJsonUtil.converBean2Json(new Date()));
		/*
		 JsonConfig config = new JsonConfig();
		//config.registerJsonBeanProcessor(Date.class, new DateJsonBeanProcessor());
		config.registerJsonValueProcessor(java.util.Date.class, new DateValueProcessor("yyyy-MM-dd"));
		Aa a = new Aa(new Date());
		List l = new ArrayList(); l.add(a);
//		JSONObject jsonObj = JSONObject.fromObject(l, config);
//		String json = jsonObj.toString();
//		System.out.println(json);
		JSONArray array = JSONArray.fromObject(l,config);
		System.out.println(array.toString());
		 */
		
	}
	
	
}
