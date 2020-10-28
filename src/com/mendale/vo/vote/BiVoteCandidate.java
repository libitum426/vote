package com.mendale.vo.vote;

import java.util.*;

/**   
 * 投票候选人POJO
 * @Title:     
 * @Description:  TODO   
 * @ClassName:  BiVoteCandidate     
 * @author: liuyang  
 * @date:   2015年11月28日 上午11:31:27   
 *      
 */
public class BiVoteCandidate implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
    /**
     * 主键       
     * db_column: ID 
     */	
	private Long id;
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
     * 部门编码       
     * db_column: DEPT_CODE 
     */	
	private String deptCode;
    /**
     * 部门名称       
     * db_column: DEPT_NAME 
     */	
	private String deptName;
    /**
     * 工号       
     * db_column: JOB_NUM 
     */	
	private String jobNum;
    /**
     * 姓名       
     * db_column: NAME 
     */	
	private String name;
    /**
     * 创建日期       
     * db_column: CREATION_DATE 
     */	
	private Date creationDate;
	private String creationDateBegin;
	private String creationDateEnd;
    /**
     * 最后更新日期       
     * db_column: LAST_UPDATE_DATE 
     */	
	private Date lastUpdateDate;
	private String lastUpdateDateBegin;
	private String lastUpdateDateEnd;
	/**
     * 是否已删除 0不删除 1删除       
     * db_column: IS_DELETE 
     */	
	private Integer isDelete;
	//照片
	private String empPhoto;

	public BiVoteCandidate(){
	}

	public BiVoteCandidate(
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
	
	public void setDeptCode(String value) {
		this.deptCode = value;
	}
	
	public String getDeptCode() {
		return this.deptCode;
	}
	
	public void setDeptName(String value) {
		this.deptName = value;
	}
	
	public String getDeptName() {
		return this.deptName;
	}
	
	public void setJobNum(String value) {
		this.jobNum = value;
	}
	
	public String getJobNum() {
		return this.jobNum;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getEmpPhoto() {
		return empPhoto;
	}

	public void setEmpPhoto(String empPhoto) {
		this.empPhoto = empPhoto;
	}
	
}

