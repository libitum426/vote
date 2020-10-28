package com.mendale.vo.vote;

import java.util.*;

public class BiVoteDrawResult implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
    /**
     * 主键ID       
     * db_column: ID 
     */	
	private Long id;
    /**
     * 奖项ID       
     * db_column: DRAW_ID 
     */	
	private Long drawId;
    /**
     * 用户ID       
     * db_column: USER_ID 
     */	
	private Long userId;
    /**
     * 奖品       
     * db_column: DRAW_REMARK 
     */	
	private String drawRemark;
    /**
     * 创建日期       
     * db_column: CREATION_DATE 
     */	
	private Date creationDate;
	private String creationDateBegin;
	private String creationDateEnd;
	/**
     * 抽奖类型       
     * db_column: DRAW_TYPE
     */	
	private Long drawType;
	/**
     * 奖品状态       
     * db_column: DRAW_STATUS
     */	
	private Long drawStatus;

	public BiVoteDrawResult(){
	}

	public BiVoteDrawResult(
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
	
	public void setDrawId(Long value) {
		this.drawId = value;
	}
	
	public Long getDrawId() {
		return this.drawId;
	}
	
	public void setUserId(Long value) {
		this.userId = value;
	}
	
	public Long getUserId() {
		return this.userId;
	}
	
	public void setDrawRemark(String value) {
		this.drawRemark = value;
	}
	
	public String getDrawRemark() {
		return this.drawRemark;
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

	public Long getDrawType() {
		return drawType;
	}

	public void setDrawType(Long drawType) {
		this.drawType = drawType;
	}

	public Long getDrawStatus() {
		return drawStatus;
	}

	public void setDrawStatus(Long drawStatus) {
		this.drawStatus = drawStatus;
	}
	
}

