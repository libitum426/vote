package com.mendale.vo.vote;

import java.util.*;

/**   
 * 抽奖人员配置表
 * @Title:     抽奖人员配置表
 * @Description:  TODO   
 * @ClassName:  BiVoteDrawUser     
 * @author: liuyang  
 * @date:   2016年9月30日 下午3:20:20   
 *      
 */
public class BiVoteDrawUser implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
    /**
     * 主键ID       
     * db_column: USER_ID 
     */	
	private Long userId;
    /**
     * 手机号       
     * db_column: PHONE 
     */	
	private String phone;
    /**
     * 姓名       
     * db_column: NAME 
     */	
	private String name;
    /**
     * 抽奖ID       
     * db_column: DRAW_ID 
     */	
	private Long drawId;
	private String drawTitle;
    /**
     * 是否有抽奖权限 0 有 1 无       
     * db_column: IS_FLAG 
     */	
	private Long isFlag;
    /**
     * 是否已删除 0不删除 1删除       
     * db_column: IS_DELETE 
     */	
	private Long isDelete;
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

	public BiVoteDrawUser(){
	}

	public BiVoteDrawUser(
		Long userId
	){
		this.userId = userId;
	}

	public void setUserId(Long value) {
		this.userId = value;
	}
	
	public Long getUserId() {
		return this.userId;
	}
	
	public void setPhone(String value) {
		this.phone = value;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setDrawId(Long value) {
		this.drawId = value;
	}
	
	public Long getDrawId() {
		return this.drawId;
	}
	
	public String getDrawTitle() {
		return drawTitle;
	}

	public void setDrawTitle(String drawTitle) {
		this.drawTitle = drawTitle;
	}

	public void setIsFlag(Long value) {
		this.isFlag = value;
	}
	
	public Long getIsFlag() {
		return this.isFlag;
	}
	
	public void setIsDelete(Long value) {
		this.isDelete = value;
	}
	
	public Long getIsDelete() {
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

