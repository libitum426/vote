package com.mendale.common.util.page;

import java.util.Collections;
import java.util.List;
/**
 * 
* <p> 分页查询对象</p>
* <p> Description: </p>
* @作者 xzm
* @创建时间 Jul 27, 2014 5:14:16 PM
* @版本 1.00
* @修改记录
* <pre>
* 版本   修改人    修改时间        修改内容描述
* ----------------------------------------
* 1.00   xzm    Jul 27, 2014 5:14:16 PM   初始化版本
* ----------------------------------------
* </pre>
*
 */
public class PageQuery implements java.io.Serializable {
	private static final long serialVersionUID = -8000900575354501298L;

	public static final int DEFAULT_PAGE_SIZE = 10;
	/** 页数  */
	private int pageNumber;
	/** 分页大小 */
	private int pageSize = DEFAULT_PAGE_SIZE;
	/** 排序的多个列,如: username desc */
    private String sortColumns;
    
	public PageQuery() {
	}

	public PageQuery(int pageSize) {
		this.pageSize = pageSize;
	}

	public PageQuery(PageQuery query) {
		this.pageNumber = query.pageNumber;
		this.pageSize = query.pageSize;
	}

	public PageQuery(int pageNumber, int pageSize) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}
	
	public PageQuery(int pageNumber, int pageSize,String sortColumns) {
	    this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sortColumns = sortColumns;
    }
	
	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortColumns() {
        return sortColumns;
    }
    /**
     * 排序的列,可以同时多列,使用逗号分隔,如 username desc,age asc
     * @return
     */
    public void setSortColumns(String sortColumns) {
        checkSortColumnsSqlInjection(sortColumns);
        if(sortColumns != null && sortColumns.length() > 200) {
            throw new IllegalArgumentException("sortColumns.length() <= 200 must be true");
        }
        this.sortColumns = sortColumns;
    }

    /**
     * 将sortColumns进行解析以便返回SortInfo以便使用
     * @return
     */
    public List<SortInfo> getSortInfos() {
        return Collections.unmodifiableList(SortInfo.parseSortColumns(sortColumns));
    }
    
    private void checkSortColumnsSqlInjection(String sortColumns) {
        if(sortColumns == null) return;
        if(sortColumns.indexOf("'") >= 0 || sortColumns.indexOf("\\") >= 0) {
            throw new IllegalArgumentException("sortColumns:"+sortColumns+" has SQL Injection risk");
        }
    }
    
	@Override
    public String toString() {
		return "pageNumber:" + pageNumber + ",pageSize:" + pageSize;
	}

}
