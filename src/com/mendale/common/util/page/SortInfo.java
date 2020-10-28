package com.mendale.common.util.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
* <p>分页排序类（暂时没用到）</p>
* <p> Description: </p>
* @作者 xzm
* @创建时间 Jul 27, 2014 5:14:56 PM
* @版本 1.00
* @修改记录
* <pre>
* 版本   修改人    修改时间        修改内容描述
* ----------------------------------------
* 1.00   xzm    Jul 27, 2014 5:14:56 PM   初始化版本
* ----------------------------------------
* </pre>
*
 */
public class SortInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String columnName;
	private String sortOrder;
	
	public SortInfo() {
	}
	
	public SortInfo(String columnName, String sortOrder) {
		super();
		this.columnName = columnName;
		this.sortOrder = sortOrder;
	}
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public static List<SortInfo> parseSortColumns(String sortColumns) {
		if(sortColumns == null) {
			return new ArrayList<SortInfo>(0);
		}
		
		List<SortInfo> results = new ArrayList<SortInfo>();
		String[] sortSegments = sortColumns.trim().split(",");
		for(int i = 0; i < sortSegments.length; i++) {
			String sortSegment = sortSegments[i];
			String[] array = sortSegment.split("\\s+");
			
			SortInfo sortInfo = new SortInfo();
			sortInfo.setColumnName(array[0]);
			sortInfo.setSortOrder(array.length == 2 ? array[1] : null);
			results.add(sortInfo);
		}
		return results;
	}
	
	@Override
    public String toString() {
		return columnName +" "+ (sortOrder == null ? "" : " " + sortOrder);
	}
}
