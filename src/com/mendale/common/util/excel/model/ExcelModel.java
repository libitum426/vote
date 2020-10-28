package com.mendale.common.util.excel.model;

import java.util.ArrayList;
import java.util.List;

/**
 *Excel模型类,对应mapping_rule.xml的<excel>节点模型
 * 
 * @author xzm
 */
public class ExcelModel {
	//excel路径
	public String excelName;
	//工作薄下标
	public String sheetNumber;
	//从excel抽取数据的起始行
	public String startRow;
	//总行数
	public int rows;
	//总列数
	public int cols;

	//静态列模型集合,mapping_rule.xml的<column>对象集合映射关系,加载模型时使用
	private List<Column> columns = new ArrayList<Column>();
	
	private List<List<Column>> dateRows = new ArrayList<List<Column>>();

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public String getSheetNumber() {
		return sheetNumber;
	}

	public void setSheetNumber(String sheetNumber) {
		this.sheetNumber = sheetNumber;
	}

	public String getStartRow() {
		return startRow;
	}

	public void setStartRow(String startRow) {
		this.startRow = startRow;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}


	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	

	public List<List<Column>> getDateRows() {
		return dateRows;
	}

	public void setDateRows(List<List<Column>> dateRows) {
		this.dateRows = dateRows;
	}

	public String toString(){
		return org.apache.commons.lang.builder.ToStringBuilder.reflectionToString(this);
	}
}
