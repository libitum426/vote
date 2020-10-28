package com.mendale.web.filter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mendale.common.VOTEUtils;
import com.mendale.common.util.ConstantUtil;
import com.mendale.common.util.SpringUtils;

@Component
public class SystemInterceptor extends HandlerInterceptorAdapter {
	public String[] allowUrls;// 还没发现可以直接配置不拦截的资源，所以在代码里面来排除

	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");
		if (null != allowUrls && allowUrls.length >= 1) {
			for (String url : allowUrls) {
				if (requestUrl.contains(url)) {
					return true;
				}
			}
		}
		HttpSession session = request.getSession(true);
		if (session.getAttribute(ConstantUtil.LOGIN_USER) == null) {
			response.sendRedirect(request.getContextPath() + "/pages/adminLogin.jsp" + "?tip=visitinvild");
			request.setAttribute("tip", "请先登录");
			return false;
		} else {
			if (!hasMenuAuth(requestUrl)) {
				response.sendRedirect(request.getContextPath() + "/pages/noAuth.jsp");
				return false;
			}
			//setOperatinAttr(requestUrl, request);
			return true;
		}
	}

	/**
	 * 设置操作按钮属性
	 * 
	 * @param requestUrl 请求url
	 * @param request
	 */
	private void setOperatinAttr(String requestUrl, HttpServletRequest request) {
		
	}

	/**
	 * 判断是否有菜单权限
	 * 
	 * @param requestUrl 请求url
	 * @return
	 */
	private boolean hasMenuAuth(String requestUrl) {
		return true;
	}

}
