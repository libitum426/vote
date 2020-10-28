package com.mendale.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 设置属性值
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SetAttributes {
	/**
	 * 属性类别
	 * 
	 * @return 属性类别
	 */
	AttributesType type() default AttributesType.update;

	/**
	 * 方法参数上，取值位置
	 * 
	 * @return 方法参数上，取值位置
	 */
	int index() default 0;
}