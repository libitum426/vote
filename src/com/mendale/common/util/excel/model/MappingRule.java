package com.mendale.common.util.excel.model;

/**
 * 
 * 规则解析映射类,主要负责解析mapping_rule.xml模型,并生成对应的对象模型
 * @author xzm
 */

import org.apache.commons.digester.Digester;
public class MappingRule {

	/**
	 * 读取mapping_rule.xml,并根据mapping_rule.xml生成对象映射关系
	 * 
	 * @return 返回ExcelModel
	 */
	public static ExcelModel readMappingRuleXml() {
		String sourceFile = "/mapping_rule.xml";

		Digester digester = new Digester();
		// 此方法作用是是否对XML文档进行DTD验证
		digester.setValidating(false);

		// 创建excel
		digester.addObjectCreate("excel", ExcelModel.class);
		digester.addBeanPropertySetter("excel/excelName");
		digester.addBeanPropertySetter("excel/sheetNumber");
		digester.addBeanPropertySetter("excel/startRow");

		// 创建columns集合
		digester.addObjectCreate("excel/columns", "java.util.ArrayList");
		digester.addSetProperties("excel/columns");


		// 创建column对象
		digester.addObjectCreate("excel/columns/column",Column.class);
		digester.addSetProperties("excel/columns/column");

		
		// 将columns集合设置到excel对象中
		digester.addSetNext("excel/columns", "setColumns","java.util.ArrayList");

		// 将column对象设置到columns对象中
		digester.addSetNext("excel/columns/column","add","com.mendale.common.util.excel.model.Column");
		ExcelModel excel = null;
		try {
			excel = (ExcelModel)digester.parse(MappingRule.class.getResourceAsStream(sourceFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return excel;

	}
	public static void main(String[] args) {
		MappingRule.readMappingRuleXml();
	}
}
