package com.mendale.common.base;

import java.util.Date;

import com.mendale.common.util.page.PageQuery;
/**
 * 
* <p>VO对象的基类(包含查询的基础类)</p>
* <p> Description: </p>
* @作者 xzm
* @创建时间 Jul 27, 2014 5:15:44 PM
* @版本 1.00
* @修改记录
* <pre>
* 版本   修改人    修改时间        修改内容描述
* ----------------------------------------
* 1.00   xzm    Jul 27, 2014 5:15:44 PM   初始化版本
* ----------------------------------------
* </pre>
*
 */
public class BaseVO extends PageQuery implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_PAGE_SIZE = 10;
    
	public BaseVO(){
		setPageSize(DEFAULT_PAGE_SIZE);
	}
	
    /**
     * 是否已删除       db_column: IS_DELETE 
     */	
	
	private Boolean isDelete;
    /**
     * 创建人       db_column: CREATED_BY 
     */	
	private String createdBy;
    /**
     * 创建日期       db_column: CREATION_DATE 
     */	
	
	private Date creationDate;
    /**
     * 最后更新人       db_column: LAST_UPDATED_BY 
     */	
	private String lastUpdatedBy;
    /**
     * 最后更新日期       db_column: LAST_UPDATE_DATE 
     */	
	
	private Date lastUpdateDate;

	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	

	


	
}
