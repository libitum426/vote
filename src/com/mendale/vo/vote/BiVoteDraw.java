package com.mendale.vo.vote;

import java.util.*;

/**   
 *
 * @Title:  抽奖配置   
 * @Description:  TODO   
 * @ClassName:  BiVoteDraw     
 * @author: liuyang  
 * @date:   2016年9月28日 上午10:42:22   
 *      
 */
public class BiVoteDraw implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
    /**
     * 主键ID       
     * db_column: DRAW_ID 
     */	
	private Long drawId;
    /**
     * 抽奖标题       
     * db_column: DRAW_TITLE 
     */	
	private String drawTitle;
    /**
     * 开始时间       
     * db_column: START_DATE 
     */	
	private Date startDate;
	private String startDateBegin;
	private String startDateEnd;
    /**
     * 结束时间       
     * db_column: END_DATE 
     */	
	private Date endDate;
	private String endDateBegin;
	private String endDateEnd;
    /**
     * 是否启用 0启用 1不启用       
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
    /**
     * 抽奖类型 0 内部 1 外部       
     * db_column: DRAW_TYPE 
     */	
	private Long drawType;

	public BiVoteDraw(){
	}

	public BiVoteDraw(
		Long drawId
	){
		this.drawId = drawId;
	}

	public void setDrawId(Long value) {
		this.drawId = value;
	}
	
	public Long getDrawId() {
		return this.drawId;
	}
	
	public void setDrawTitle(String value) {
		this.drawTitle = value;
	}
	
	public String getDrawTitle() {
		return this.drawTitle;
	}
	
	public void setStartDate(Date value) {
		this.startDate = value;
	}
	
	public Date getStartDate() {
		return this.startDate;
	}
	
	public void setStartDateBegin(String value) {
		this.startDateBegin = value;
	}
	
	public String getStartDateBegin() {
		return this.startDateBegin;
	}
	
	public void setStartDateEnd(String value) {
		this.startDateEnd = value;
	}
	
	public String getStartDateEnd() {
		return this.startDateEnd;
	}
	
	public void setEndDate(Date value) {
		this.endDate = value;
	}
	
	public Date getEndDate() {
		return this.endDate;
	}
	
	public void setEndDateBegin(String value) {
		this.endDateBegin = value;
	}
	
	public String getEndDateBegin() {
		return this.endDateBegin;
	}
	
	public void setEndDateEnd(String value) {
		this.endDateEnd = value;
	}
	
	public String getEndDateEnd() {
		return this.endDateEnd;
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
	
	public void setDrawType(Long value) {
		this.drawType = value;
	}
	
	public Long getDrawType() {
		return this.drawType;
	}
	
}

