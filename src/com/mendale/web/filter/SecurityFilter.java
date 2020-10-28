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
import javax.servlet.http.HttpSession;

import com.mendale.common.util.ConstantUtil;


/**
* <p> Title: 是否登录过滤器 </p>
* <p> Description: </p>
* @作者 xiefc
* @创建时间 2014-6-14 上午8:32:15
* @版本 1.00
* @修改记录
* <pre>
* 版本   修改人    修改时间    修改内容描述
* ----------------------------------------
* 1.00 xiefc 2014-6-14 上午8:32:15  初始化版本
* ----------------------------------------
* </pre>
*/
public class SecurityFilter implements Filter {

	private FilterConfig config;
	

	public void init(FilterConfig config) throws ServletException {
		this.config = config; 
	} 

	
	public void doFilter(ServletRequest sRequest, ServletResponse sResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) sRequest;
		HttpServletResponse response = (HttpServletResponse) sResponse;
		HttpSession session = request.getSession(true);
        // 获取当前访问路径
		String requestPath = request.getRequestURI().replace(
				request.getContextPath(), "");
		//String requestPath = request.getServletPath();
		// 不需要验证的路径（一般是登录页面包含的CSS JS JSP HTML等）
		String exclusiveStr = this.config.getInitParameter("exclusive");
		
		if (session.getAttribute(ConstantUtil.LOGIN_USER) == null 
				&& !this.isInExclusive(requestPath, exclusiveStr)) {
			// 没有登录，访问了限制页面 ，页面重定向去 登录页面
			// 获取配置中重定向参数
			String redirectPage = config.getInitParameter("redirectPage");
			response.sendRedirect(request.getContextPath() + redirectPage
					+ "?tip=visitinvild");
			request.setAttribute("tip", "请先登录");
			// 非法入侵 地址
			System.out.println("SecurityFilter request path :  " + requestPath);
		} else {
			// 一切正常，继续...
			chain.doFilter(sRequest, sResponse);
		}
	}
	
	/**
	 * 请求页面路径，是否在不包含路径中
	 * 
	 * @param requestPath
	 * @param exclusiveStr
	 * @return true 包含
	 */
	private boolean isInExclusive(String requestPath,String exclusiveStr) {
		boolean result = false;
		String[] exclusiveArray = exclusiveStr.split(",");
    	for(String tempStr : exclusiveArray ) {
    		if(requestPath.contains(tempStr)) {
    			result = true;
    			break;
    		}
    	}
		return result;
	}
	
	
	public void destroy() {
		this.config = null; 
	}
}
