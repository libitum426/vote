package com.mendale.vo.vote;

import java.util.*;

public class BiVoteLogin implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
    /**
     * 主键       
     * db_column: ID 
     */	
	private Long id;
    /**
     * 用户       
     * db_column: CODE 
     */	
	private String code;
    /**
     * 密码       
     * db_column: PASSWORD 
     */	
	private String password;
    /**
     * 品牌编码       
     * db_column: BRAND_CODE 
     */	
	private String brandCode;
    /**
     * 品牌名称       
     * db_column: BRAND_NAME 
     */	
	private String brandName;
    /**
     * 姓名       
     * db_column: NAME 
     */	
	private String name;
    /**
     * 部门编码       
     * db_column: DEPART_CODE 
     */	
	private String departCode;
    /**
     * 部门名称       
     * db_column: DEPART_NAME 
     */	
	private String departName;
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

	public BiVoteLogin(){
	}

	public BiVoteLogin(
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
	
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setBrandCode(String value) {
		this.brandCode = value;
	}
	
	public String getBrandCode() {
		return this.brandCode;
	}
	
	public void setBrandName(String value) {
		this.brandName = value;
	}
	
	public String getBrandName() {
		return this.brandName;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setDepartCode(String value) {
		this.departCode = value;
	}
	
	public String getDepartCode() {
		return this.departCode;
	}
	
	public void setDepartName(String value) {
		this.departName = value;
	}
	
	public String getDepartName() {
		return this.departName;
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

