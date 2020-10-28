package com.mendale.vo.vote;

import java.util.*;

/**   
 * 投票标题配置表Pojo
 * @Title:     
 * @Description:  TODO   
 * @ClassName:  BiVoteDeploy     
 * @author: liuyang  
 * @date:   2015年11月28日 上午11:42:08   
 *      
 */
public class BiVoteDeploy implements java.io.Serializable{
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
     * 投票标题       
     * db_column: VOTE_TITLE 
     */	
	private String voteTitle;
    /**
     * 最大投票数 0 表示无限制       
     * db_column: MAX_VOTE 
     */	
	private Integer maxVote;
    /**
     * 最小投票数 0 表示无限制       
     * db_column: MIN_VOTE 
     */	
	private Integer minVote;
    /**
     * 备注       
     * db_column: REMARK 
     */	
	private String remark;
    /**
     * 投票开始时间       
     * db_column: START_DATE 
     */	
	private Date startDate;
	private String startDateBegin;
	private String startDateEnd;
    /**
     * 投票结束时间       
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
     * 投票人数       
     * db_column: VOTE_NUM 
     */	
	private String voteNum;
	/**
     * 投票率       
     * db_column: VOTE_RATE 
     */	
	private String voteRate;

	public BiVoteDeploy(){
	}

	public BiVoteDeploy(
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
	
	public void setVoteTitle(String value) {
		this.voteTitle = value;
	}
	
	public String getVoteTitle() {
		return this.voteTitle;
	}
	
	public void setMaxVote(Integer value) {
		this.maxVote = value;
	}
	
	public Integer getMaxVote() {
		return this.maxVote;
	}
	
	public void setMinVote(Integer value) {
		this.minVote = value;
	}
	
	public Integer getMinVote() {
		return this.minVote;
	}
	
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public String getRemark() {
		return this.remark;
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

	public String getVoteNum() {
		return voteNum;
	}

	public void setVoteNum(String voteNum) {
		this.voteNum = voteNum;
	}

	public String getVoteRate() {
		return voteRate;
	}

	public void setVoteRate(String voteRate) {
		this.voteRate = voteRate;
	}
	
}

