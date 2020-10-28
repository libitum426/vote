package com.mendale.common.aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.mendale.common.annotation.SetAttributes;

/**
 * 共公拦截
 * 
 */
public class CommonAspect {

	/**
	 * 设置属性
	 * 
	 * @param joinPoint
	 *            切片点
	 */
	public void setAttributes(final JoinPoint joinPoint) {
		final Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

		if (!method.isAnnotationPresent(SetAttributes.class)) {
			return;
		}

		if (ArrayUtils.getLength(joinPoint.getArgs()) < 1) {
			return;
		}

		final SetAttributes setAttributes = method.getAnnotation(SetAttributes.class);

		final Object poObject = joinPoint.getArgs()[setAttributes.index()];

		if (poObject == null) {
			return;
		}

		// 跳过基础数据类型
		String objClassName = poObject.getClass().getName();
		if(objClassName != null && objClassName.length() > 0) {
			if(objClassName.indexOf("java.lang") != -1) {
				return;
			}
		}
		
		final Map<String, Object> attributeMap = new HashMap<String, Object>();

		switch (setAttributes.type()) {
		case create:
			if(getValueFromObj(poObject, "createdBy") == null) {
				// 当程序端，没有设置 该字段的值
				//attributeMap.put("createdBy", CRMUtils.getSessionUserCode());
			}
			//attributeMap.put("creationDate", new Date());
			break;
		case update:
			if(getValueFromObj(poObject, "lastUpdatedBy") == null) {
				// 当程序端，没有设置 该字段的值
				//attributeMap.put("lastUpdatedBy", CRMUtils.getSessionUserCode());
			}
			//attributeMap.put("lastUpdateDate", new Date());
			break;
		default:
			if(getValueFromObj(poObject, "createdBy") == null) {
				// 当程序端，没有设置 该字段的值
				//attributeMap.put("createdBy", CRMUtils.getSessionUserCode());
			}
			
			if(getValueFromObj(poObject, "lastUpdatedBy") == null) {
				// 当程序端，没有设置 该字段的值
				//attributeMap.put("lastUpdatedBy", CRMUtils.getSessionUserCode());
			}
			
			//attributeMap.put("creationDate", new Date());
			//attributeMap.put("lastUpdateDate", new Date());
			break;
		}

		for (final Entry<String, Object> attributeEntry : attributeMap.entrySet()) {
			try {
				BeanUtils.setProperty(poObject, attributeEntry.getKey(), attributeEntry.getValue());
			} catch (final IllegalAccessException e) {
				e.printStackTrace();
			} catch (final InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 从对象里面获取指定字段的值
	 * 
	 * @param fromObj
	 * @param fieldName
	 * @return
	 */
	private String getValueFromObj(Object fromObj, String fieldName) {
		String result = null;
		try {
			result = BeanUtils.getProperty(fromObj, fieldName);
		} catch(Exception e) {}
		return result;
	}
}
