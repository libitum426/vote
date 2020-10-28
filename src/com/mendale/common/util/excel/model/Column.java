package com.mendale.common.util.excel.model;


/**
 * 列模型类,对应mapping_rule.xml的<column>节点模型
 * 
 * @author xzm
 */
public class Column {
	//获取EXCEL表指定位置的值
	private String value;
	//输出EXCEL列名
	private String name;
	//所取EXCEL的列编号
	private String cellIndex;
	
	//分隔符
	private String splitValue;
	
	//分隔后取数据的位置
	private Integer splitIndex;
	
	//默认值
	private String defaultValue;


	
	
	
	
	



	public Integer getSplitIndex() {
		return splitIndex;
	}



	public void setSplitIndex(Integer splitIndex) {
		this.splitIndex = splitIndex;
	}



	public String getSplitValue() {
		return splitValue;
	}



	public void setSplitValue(String splitValue) {
		this.splitValue = splitValue;
	}



	public String getDefaultValue() {
		return defaultValue;
	}



	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}



	public String getValue() {
		return value;
	}



	public void setValue(String value) {
		this.value = value;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCellIndex() {
		return cellIndex;
	}



	public void setCellIndex(String cellIndex) {
		this.cellIndex = cellIndex;
	}



	public String toString(){
		return org.apache.commons.lang.builder.ToStringBuilder.reflectionToString(this);
	}
}
