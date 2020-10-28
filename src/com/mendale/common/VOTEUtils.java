package com.mendale.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mendale.common.util.ConstantUtil;
import com.mendale.common.util.PropertiesLoader;
import com.mendale.vo.baseData.User;

/**
 * <p> Title: DRP公共方法</p>
 * <p> Description: </p>
 * @作者 liliang
 * @创建时间 2014-10-09
 * @版本 1.00
 * @修改记录
 * <pre>
 * 版本   修改人    修改时间    修改内容描述
 * </pre>
 */
public class VOTEUtils {

	/**
	 * 取session用户
	 * 
	 * @return
	 */
	public static final User getSessionUser() {
		HttpSession session = getSession();
		session.setMaxInactiveInterval(-1);
		if (session.getAttributeNames().hasMoreElements()) {
			User user = (User) session.getAttribute(ConstantUtil.LOGIN_USER);
			return user;
		} else {
			return null;
		}
	}
	
	/**
	 * 取session用户编码
	 * 
	 * @return
	 */
	public static final String getSessionUserCode() {
		HttpSession session = getSession();
		session.setMaxInactiveInterval(-1);
		if (session.getAttributeNames().hasMoreElements()) {
			User user = (User) session.getAttribute(ConstantUtil.LOGIN_USER);
			return user.getUserCode();
		} else {
			return null;
		}
	}

	/**
	 * 判断用户是否为超级管理员
	 * @param user user
	 * 
	 * @return
	 */
	public static final boolean isSuperAdmin(User user) {
		if (user == null) {
			return false;
		}
		return "admin".equals(user.getUserCode());
	}

	/**
	 * SpringMvc下获取request
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * SpringMvc下获取session
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		HttpSession session = null; 
		try { 
		    session = getRequest().getSession(); 
		} catch (Exception e) {
			System.out.println(e);
		} 
	   return session; 
	}
	
	/**
	 * 读取属性值
	 * 
	 * @param key
	 * @return
	 */
	public static String getPropValue(String key) {
		PropertiesLoader prop = new PropertiesLoader("db.properties");
		return prop.getProperty(key);
	}
}
