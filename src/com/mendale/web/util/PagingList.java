package com.mendale.web.util;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;



/**
* <p> Title: 查询分页工具类 </p>
* <p> Description: </p>
* @作者 xiefc
* @创建时间 2014-6-14 上午8:20:35
* @版本 1.00
* @修改记录
* <pre>
* 版本   修改人    修改时间    修改内容描述
* ----------------------------------------
* 1.00 xiefc 2014-6-14 上午8:20:35  初始化版本
* ----------------------------------------
* </pre>
*/
public class PagingList {   
	
    private int rowCount = 0; // 记录总数     
    private int pageCount = 1; // 分页总数     
    private int pageSize = 10; // 每页记录数     
    private int currPageNum = 1; // 当前页数     
    private int startIndex = 1; // 起始记录数     
    private int endIndex = 1; // 结束记录数     
    
    private List list;// 记录列表     
    /**    
     * 构造方法，进行分页    
     *     
     * @param statementName    
     *            iBatis中语句的ID    
     * @param parameterObject    
     *            SQL语句参数    
     * @param pageNum    
     *            起始页数    
     * @param pageSize    
     *            每页大小    
     * @param sqlMapClientTemplate    
     *            iBatis的sqlMapClientTemplate对象    
     */    
    public PagingList(String statementName, Object parameterObject,     
            int pageNum, int pageSize, SqlMapClientTemplate sqlMapClientTemplate) {     
        preProcessParams(pageNum, pageSize);     
        execute(statementName, parameterObject, pageNum, pageSize,     
                sqlMapClientTemplate);     
    }     
    /**    
     * 构造方法，进行分页    
     *     
     * @param statementName    
     *            iBatis中语句的ID    
     * @param pageNum    
     *            起始页数    
     * @param pageSize    
     *            每页大小    
     * @param sqlMapClientTemplate    
     *            iBatis的sqlMapClientTemplate对象    
     */    
    public PagingList(String statementName, int pageNum, int pageSize,     
            SqlMapClientTemplate sqlMapClientTemplate) {     
        preProcessParams(pageNum, pageSize);     
        execute(statementName, pageNum, pageSize, sqlMapClientTemplate);     
    }     
    /**    
     * 执行方法    
     *     
     * @param statementName    
     * @param parameterObject    
     * @param pageNum    
     * @param pageSize    
     * @param sqlMapClientTemplate    
     */    
    public void execute(String statementName, Object parameterObject,     
            int pageNum, int pageSize, SqlMapClientTemplate sqlMapClientTemplate) {     
        // 计算记录总数     
        this.rowCount = sqlMapClientTemplate.queryForList(statementName,     
                parameterObject).size();     
        // 计算分页数及起止记录     
        countPage();     
        // 获取分页列表     
        this.list = sqlMapClientTemplate.queryForList(statementName,     
                parameterObject, (pageNum - 1) * pageSize, pageSize);     
    }     
    /**    
     * 执行方法    
     *     
     * @param statementName    
     * @param pageNum    
     * @param pageSize    
     * @param sqlMapClientTemplate    
     */    
    public void execute(String statementName, int pageNum, int pageSize,     
            SqlMapClientTemplate sqlMapClientTemplate) {     
        // 计算记录总数     
        this.rowCount = sqlMapClientTemplate.queryForList(statementName).size();     
        // 计算分页数及起止记录     
        countPage();     
        // 获取分页列表     
        this.list = sqlMapClientTemplate.queryForList(statementName,     
                (pageNum - 1) * pageSize, pageSize);     
    }     
    /**    
     * 预处理SQL语句和页面参数    
     */    
    private void preProcessParams(int pageNum, int pageSize) {     
        if (pageNum > 0) {     
            this.currPageNum = pageNum;     
        }     
        if (pageSize > 0) {     
            this.pageSize = pageSize;     
        }     
        if (pageSize > 1000) {     
            this.pageSize = 1000;     
        }     
    }     
    /**    
     * 计算分页数及起止记录    
     */    
    private void countPage() {     
        // 计算分页总数     
        if ((rowCount % pageSize) == 0) {     
            pageCount = rowCount / pageSize;     
        } else {     
            pageCount = rowCount / pageSize + 1;     
        }     
        if (pageCount == 0) {     
            pageCount = 1;     
        }     
        // 判断pageNum是否过界     
        if (currPageNum > pageCount && rowCount != 0) {     
            currPageNum = pageCount;     
        }     
        // 计算起止记录     
        startIndex = (currPageNum - 1) * pageSize + 1;     
        endIndex = (currPageNum) * pageSize;     
    }     
    /**    
     * 获得对象列表    
     */    
    public List getList() {     
        return list;     
    }     
    /* 获得起始记录数 */    
    public int getStartIndex() {     
        return startIndex;     
    }     
    public Integer getStartIndexInteger() {     
        return new Integer(startIndex);     
    }     
    /* 获得结束记录数 */    
    public int getEndIndex() {     
        return endIndex;     
    }     
    public Integer getEndIndexInteger() {     
        return new Integer(endIndex);     
    }     
    /* 获得分页其它信息 */    
    public int getPageCount() {     
        return pageCount;     
    }     
    public int getPageNum() {     
        return currPageNum;     
    }     
    public int getPageSize() {     
        return pageSize;     
    }     
    public int getRowCount() {     
        return rowCount;     
    }     
}    
