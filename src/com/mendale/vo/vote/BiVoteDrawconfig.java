package com.mendale.vo.vote;

import java.util.*;

/**   
 * 奖项配置表POJO
 * @Title:     
 * @Description:  TODO   
 * @ClassName:  BiVoteDrawconfig     
 * @author: liuyang  
 * @date:   2016年1月22日 下午2:04:14   
 *      
 */
public class BiVoteDrawconfig implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
    /**
     * 主键       
     * db_column: ID 
     */	
	private Long id;
    /**
     * 奖项名称       
     * db_column: NAME 
     */	
	private String name;
    /**
     * 人数       
     * db_column: DRAW_NUM 
     */	
	private Long drawNum;
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
     * 是否启用 0 启用 1 不启用       
     * db_column: IS_ACTIVE 
     */	
	private String isActive;
    /**
     * 是否删除 0 不删除 1 删除       
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
	/**
     * 奖品       
     * db_column: DRAW_REMARK 
     */	
	private String drawRemark;
	/**
     * 排序码       
     * db_column: ORDER_NUM 
     */	
	private String orderNum;
	/**
     * 每次个数      
     * db_column: EVERY_NUM 
     */	
	private Long everyNum;
	/**
     * 当前次数       
     * db_column: NUM 
     */	
	private Long num;
	/**
     * 排序码       
     * db_column: PIC_PATH 
     */	
	private String picPath;
	/**
     * 外键    
     * db_column: DRAW_ID 
     */	
	private Long drawId;
	/**
	 * 查询条件  是否是外部抽奖
	 */
	private String isDrawIdNull;
	/**
	 * 查询条件  是否能抽奖
	 */
	private String isDraw;

	public BiVoteDrawconfig(){
	}

	public BiVoteDrawconfig(
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
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setDrawNum(Long value) {
		this.drawNum = value;
	}
	
	public Long getDrawNum() {
		return this.drawNum;
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
	
	public void setIsActive(String value) {
		this.isActive = value;
	}
	
	public String getIsActive() {
		return this.isActive;
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

	public String getDrawRemark() {
		return drawRemark;
	}

	public void setDrawRemark(String drawRemark) {
		this.drawRemark = drawRemark;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Long getEveryNum() {
		return everyNum;
	}

	public void setEveryNum(Long everyNum) {
		this.everyNum = everyNum;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public Long getDrawId() {
		return drawId;
	}

	public void setDrawId(Long drawId) {
		this.drawId = drawId;
	}

	public String getIsDrawIdNull() {
		return isDrawIdNull;
	}

	public void setIsDrawIdNull(String isDrawIdNull) {
		this.isDrawIdNull = isDrawIdNull;
	}

	public String getIsDraw() {
		return isDraw;
	}

	public void setIsDraw(String isDraw) {
		this.isDraw = isDraw;
	}
	
}

