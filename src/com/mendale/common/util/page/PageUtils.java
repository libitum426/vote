package com.mendale.common.util.page;

/**
 * 
* <p> 分页工具类</p>
* <p> Description: </p>
* @作者 xzm
* @创建时间 Jul 27, 2014 5:14:34 PM
* @版本 1.00
* @修改记录
* <pre>
* 版本   修改人    修改时间        修改内容描述
* ----------------------------------------
* 1.00   xzm    Jul 27, 2014 5:14:34 PM   初始化版本
* ----------------------------------------
* </pre>
*
 */
public class PageUtils {
	
	private PageUtils(){}
	
	public static int getFirstResult(int pageNumber,int pageSize) {
		if(pageSize <= 0) throw new IllegalArgumentException("[pageSize] must great than zero");
		return (pageNumber - 1) * pageSize;
	}
	
	public static Integer[] generateLinkPageNumbers(int currentPageNumber,int lastPageNumber,int count) {
		int avg = count / 2;
		
		int startPageNumber = currentPageNumber - avg;
		if(startPageNumber <= 0) {
			startPageNumber = 1;
		}
		
		int endPageNumber = startPageNumber + count - 1;
		if(endPageNumber > lastPageNumber) {
			endPageNumber = lastPageNumber;
		}
		
		if(endPageNumber - startPageNumber < count) {
			startPageNumber = endPageNumber - count;
			if(startPageNumber <= 0 ) {
				startPageNumber = 1;
			}
		}
		
		java.util.List<Integer> result = new java.util.ArrayList<Integer>();
		for(int i = startPageNumber; i <= endPageNumber; i++) {
			result.add(new Integer(i));
		}
		return result.toArray(new Integer[result.size()]);
	}
	
	
	public static int computeLastPageNumber(int totalElements,int pageSize) {
		int result = totalElements % pageSize == 0 ? 
				totalElements / pageSize 
				: totalElements / pageSize + 1;
		if(result <= 1)
			result = 1;
		return result;
	}
	
    public static int computePageNumber(int pageNumber, int pageSize,int totalElements) {
		if(pageNumber <= 1) {
			return 1;
		}
    	if (Integer.MAX_VALUE == pageNumber
				|| pageNumber > computeLastPageNumber(totalElements,pageSize)) { //last page
			return computeLastPageNumber(totalElements,pageSize);
		}
		return pageNumber;
    }
}
