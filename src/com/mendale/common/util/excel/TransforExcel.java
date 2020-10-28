package com.mendale.common.util.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import com.mendale.common.util.DateUtil;
import com.mendale.common.util.excel.model.Column;
import com.mendale.common.util.excel.model.ExcelModel;
import com.mendale.common.util.excel.model.MappingRule;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class TransforExcel {
	/**
	 * 根据excelModel中的模型从excel中抽取数据
	 * 
	 * @param path
	 *            导入excel的路径
	 * 
	 * @return ExcelModel
	 * 
	 * @author xzm
	 */
	public ExcelModel loadExcel(String path) throws Exception {

		File excelFile = null;
		if (path != null && path.indexOf(".xls") > 0) {
			excelFile = new File(path);
		}
		if (!excelFile.exists()) {
			return null;
		}

		ExcelModel excelModel = MappingRule.readMappingRuleXml();

		Workbook wb = Workbook.getWorkbook(excelFile);
		Sheet s = wb.getSheet(Integer.parseInt(excelModel.getSheetNumber()));// 第1个sheet

		excelModel.setCols(s.getColumns());
		excelModel.setRows(s.getRows());
		int startRow = Integer.parseInt(excelModel.getStartRow());

		// 每一行数据集合
		List dateRows = new ArrayList(excelModel.getRows() - startRow);
		// 循环excel每行的数据
		for (int rowIndex = startRow; rowIndex <= excelModel.getRows(); rowIndex++) {

			dateRows.add(processRow(excelModel, s, rowIndex));
		}
		excelModel.setDateRows(dateRows);
		
		if (excelFile.exists()) {
			excelFile.delete();
		}
		
		return excelModel;
	}

	/**
	 * 根据传入的excelModel对象生成excel文档
	 * 
	 * @param excelModel
	 * @param filePath
	 * @param fileName
	 * @return
	 * @throws Exception
	 * 
	 * @author xzm
	 */

	public File exportExcel(ExcelModel excelModel, String filePath,
			String fileName) throws Exception {
		File file = null;
		WritableWorkbook workbook = null;
		try {
			file = new File(filePath);
			List<Column> columns = null;
			Column column = null;
			List<List<Column>> dateRows = excelModel.getDateRows();
			workbook = Workbook.createWorkbook(file);
			WritableSheet sheet = workbook.createSheet(fileName, 0); // 添加第一个工作表

			Label label;
			// 加载列头
			WritableCellFormat wc = null;
			WritableFont titleWf = new WritableFont(WritableFont
					.createFont("宋体"),// 字体
					9,// WritableFont.DEFAULT_POINT_SIZE, // 字号
					WritableFont.BOLD);
			WritableFont contentWf = new WritableFont(WritableFont
					.createFont("宋体"),// 字体
					10,// WritableFont.DEFAULT_POINT_SIZE, // 字号
					WritableFont.NO_BOLD);
			wc = new WritableCellFormat(titleWf);
			for (int i = 0; i < dateRows.get(0).size(); i++) {
				sheet.setColumnView(i, 15);
				column = dateRows.get(0).get(i);
				label = new Label(i, 0, column.getName(), wc);
				sheet.addCell(label);
			}
			sheet.setColumnView(3, 30); // 设置列的宽度
			sheet.setColumnView(8, 30);
			wc = new WritableCellFormat(contentWf);
			wc.setAlignment(Alignment.JUSTIFY);
			for (int i = 0; i < dateRows.size(); i++) {
				columns = dateRows.get(i);
				if (columns != null) {
					for (int j = 0; j < columns.size(); j++) {
						column = columns.get(j);
						if (column != null) {
							label = new Label(j, i + 1, column.getValue(), wc);
							sheet.addCell(label);
						}
					}
				}
			}
		} finally {
			workbook.write();
			workbook.close();
		}

		return file;
	}

	
	/**
	 * 获得Excel Sheet指定列和PO属性对构成的集合
	 * @param sheet Excel Sheet
	 * @param firstCol 起始列
	 * @param firstRow 起始行
	 * @param lastCol 终点列
	 * @param lastRow 终点行
	 * @param mTP sheet中的名称和PO对象对应的属性名称对集合,例如<"单位名称","orgName">
	 * @return Map<Integer, String>,Integer对应列标,String对应PO属性
	 */
	public Map<Integer, String> findCell(Sheet sheet, int firstCol,
			int firstRow, int lastCol, int lastRow, Map<String, String> mTP) throws Exception {
		Map<Integer, String> mColP = new HashMap<Integer, String>();
		int numCols = lastCol - firstCol;
		int numRows = lastRow - firstRow;
		for (int i = 0; i <= numRows; i++) {
			int curRow = firstRow + i;
			for (int j = 0; j <= numCols; j++) {
				int curCol = firstCol + j;
				if (curCol < sheet.getColumns() && curRow < sheet.getRows()) {
					Cell c = sheet.getCell(curCol, curRow);
					if (c.getType() != CellType.EMPTY) {
						if (mTP.get(c.getContents().trim()) != null) {
							mColP.put(curCol, mTP.get(c.getContents().trim()));
						}
					}
				}
			}
		}
		return mColP;
	}
	
	/**
	 * 处理每行数据
	 * 
	 * @param ExcelModel
	 *            ExcelModel excel模型对象
	 * @param Sheet
	 *            s excel单元格对象
	 * @param int
	 *            rowIndex 行下标
	 * @return List 包含动态DBTable对象的集合
	 */
	private List<Column> processRow(ExcelModel excelModel, Sheet s, int rowIndex)
			throws Exception {
		Column column = null;
		List<Column> returnValue = new ArrayList<Column>();
		List<Column> columnList = excelModel.getColumns();
		// 循环dbTable中每列的数据
		for (int columnIndex = 0; columnIndex < columnList.size(); columnIndex++) {
			Column tempColumn = (Column) columnList.get(columnIndex);
			column = new Column();
			// 将tempcolumn数据复制到新的column中
			PropertyUtils.copyProperties(column, tempColumn);
			if (column.getCellIndex() != null
					&& !column.getCellIndex().equals("")) {
				// 如果存在一个数据库列对应多列excel数据中情况,需要合并列
				if (column.getCellIndex().indexOf(",") != -1) {
					joinCell(s, column, rowIndex);
				} else {
					Cell c = s.getCell(column.getCellIndex() + rowIndex);
					if(column.getName().indexOf("日期")>-1){
						column.setValue(convertCellValue(c));
					}else if(column.getSplitValue()!=null && !column.getSplitValue().equals("")){
						column.setValue(splitCellValue(c,column.getSplitValue(),column.getSplitIndex()));
					}else{
						column.setValue(c.getContents());
					}
					
				}
			} else {
				column.setValue(column.getDefaultValue());
			}
			returnValue.add(column);
		}

		return returnValue;
	}

	/**
	 * 转换excel中列的数据
	 * 
	 * @param Cell excel中列对象
	 * @return String 转换后的excel列值
	 */
	private String convertCellValue(Cell c) {
		String val = null;
		if (c.getType() == CellType.DATE) {
			DateCell nc = (DateCell) c;
			Date b2 = nc.getDate();
			val = DateUtil.getDateTime(b2, "yyyy-MM-dd");
		} else {
			val = DateUtil.getDateTime(DateUtil.parseDateTime(c.getContents(),"yyyy-MM-dd"),"yyyy-MM-dd");
		}
		return val;
	}

	/**
	 * 分隔excel中列的数据
	 * 
	 * @param Cell excel中列对象
	 * @param splitV 分隔符
	 * @param index 分隔后取数据的位置
	 * @return String 分隔后的excel列值
	 */
	private String splitCellValue(Cell c,String splitV,int index) {
		String val = null;
		if(c.getContents()!=null){
			String[] tempV = c.getContents().split(splitV);
			if(tempV.length>index){
				val = tempV[index];
			}
		}
		return val;
	}
	/**
	 * 合并单元格
	 * @param s
	 * @param column
	 * @param rowIndex
	 */
	private void joinCell(Sheet s, Column column, int rowIndex) {
		String[] cellIndexList = column.getCellIndex().split(",");
		String[] defaultValue = null;
		if(column.getDefaultValue()!=null && !column.getDefaultValue().equals("")){
			defaultValue = column.getDefaultValue().split(",");
		}
		String values = "";
		for (int h = 0; h < cellIndexList.length; h++) {
			String tempCellIndex = cellIndexList[h];
			if (tempCellIndex != null && !"".equals(tempCellIndex)) {
				Cell c = s.getCell(tempCellIndex + rowIndex);
				String valueString = c.getContents();
				if(valueString!=null && !valueString.equals("")){
					if(defaultValue!=null && defaultValue[h]!=null){
						values = values +"  "+ defaultValue[h] + valueString;
					}else{
						values = values +"  "+ valueString;
					}
				}
			}
		}
		column.setValue(values);

	}
}