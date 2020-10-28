package com.mendale.vo.vote;

import java.util.*;

/**   
 * 投票品牌表POJO
 * @Title:     
 * @Description:  TODO   
 * @ClassName:  BiVoteBrand     
 * @author: liuyang  
 * @date:   2016年1月12日 上午9:15:07   
 *      
 */
public class BiVoteBrand implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
    /**
     * 主键       
     * db_column: ID 
     */	
	private Long id;
    /**
     * 品牌编码       
     * db_column: CODE 
     */	
	private String code;
    /**
     * 品牌名称       
     * db_column: NAME 
     */	
	private String name;
    /**
     * 是否已删除 0不删除 1删除       
     * db_column: IS_DELETE 
     */	
	private String isDelete;
    /**
     * 创建人       
     * db_column: CREATED_BY 
     */	
	private String createdBy;
    /**
     * 创建日期       
     * db_column: CREATION_DATE 
     */	
	private Date creationDate;
	private String creationDateBegin;
	private String creationDateEnd;
    /**
     * 最后更新人       
     * db_column: LAST_UPDATED_BY 
     */	
	private String lastUpdatedBy;
    /**
     * 最后更新日期       
     * db_column: LAST_UPDATE_DATE 
     */	
	private Date lastUpdateDate;
	private String lastUpdateDateBegin;
	private String lastUpdateDateEnd;

	public BiVoteBrand(){
	}

	public BiVoteBrand(
		Long id
	){
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setCode(String value) {
		this.code = value;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setIsDelete(String value) {
		this.isDelete = value;
	}
	
	public String getIsDelete() {
		return this.isDelete;
	}
	
	public void setCreatedBy(String value) {
		this.createdBy = value;
	}
	
	public String getCreatedBy() {
		return this.createdBy;
	}
	
	public void setCreationDate(Date value) {
		this.creationDate = value;
	}
	
	public Date getCreationDate() {
		return this.creationDate;
	}
	
	public void setCreationDateBegin(String value) {
		this.creationDateBegin = value;
	}
	
	public String getCreationDateBegin() {
		return this.creationDateBegin;
	}
	
	public void setCreationDateEnd(String value) {
		this.creationDateEnd = value;
	}
	
	public String getCreationDateEnd() {
		return this.creationDateEnd;
	}
	
	public void setLastUpdatedBy(String value) {
		this.lastUpdatedBy = value;
	}
	
	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}
	
	public void setLastUpdateDate(Date value) {
		this.lastUpdateDate = value;
	}
	
	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}
	
	public void setLastUpdateDateBegin(String value) {
		this.lastUpdateDateBegin = value;
	}
	
	public String getLastUpdateDateBegin() {
		return this.lastUpdateDateBegin;
	}
	
	public void setLastUpdateDateEnd(String value) {
		this.lastUpdateDateEnd = value;
	}
	
	public String getLastUpdateDateEnd() {
		return this.lastUpdateDateEnd;
	}
	
}

