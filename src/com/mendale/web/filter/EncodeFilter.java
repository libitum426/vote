package com.mendale.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;



/**
* <p> Title: 字符编码过滤器 </p>
* <p> Description: </p>
* @作者 xiefc
* @创建时间 2014-6-13 上午9:20:04
* @版本 1.00
* @修改记录
* <pre>
* 版本   修改人    修改时间    修改内容描述
* ----------------------------------------
* 1.00 xiefc 2014-6-13 上午9:20:04  初始化版本
* ----------------------------------------
* </pre>
*/
public class EncodeFilter implements Filter {

	// 默认编码类型
	private String encoding = "UTF-8";

	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		// 通过web.xml得到 编码参数
		this.encoding = filterConfig.getInitParameter("encoding");
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		// 对请求进行编码
		request.setCharacterEncoding(encoding);
		response.setContentType("text/html;charset=" + encoding);  
		response.setCharacterEncoding(encoding);

		filterChain.doFilter(request, response);
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		this.encoding = null;
	}

	
}

