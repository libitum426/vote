package com.mendale.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* <p> Title: 不缓存页面 </p>
* <p> Description: </p>
* @作者 xiefc
* @创建时间 2014-6-28 下午11:42:18
* @版本 1.00
* @修改记录
* <pre>
* 版本   修改人    修改时间    修改内容描述
* ----------------------------------------
* 1.00 xiefc 2014-6-28 下午11:42:18  初始化版本
* ----------------------------------------
* </pre>
*/
public class NoCacheFilter implements Filter {
	 
	 public void init(FilterConfig filterConfig) throws ServletException {}
	 
	 public void doFilter(ServletRequest req, ServletResponse res,
	    FilterChain chain) throws IOException, ServletException {
	 
	   HttpServletRequest  request = (HttpServletRequest)req;
	   HttpServletResponse response = (HttpServletResponse)res;
	  
	   response.setDateHeader("expires",-1);
	   response.setHeader("Cache-Control","no-cache");
	   response.setHeader("Pragma","no-cache");
	  
	   chain.doFilter(request, response);
	 
	 }
	 
	public void destroy() {}
	 

}