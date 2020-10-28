package com.mendale.vo.vote;

import java.util.Date;

/**   
 * 投票人POJO
 * @Title:     
 * @Description:  TODO   
 * @ClassName:  BiVoteUser     
 * @author: liuyang  
 * @date:   2015年11月28日 上午11:31:09   
 *      
 */
public class BiVoteUser implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
    /**
     * 主键       
     * db_column: ID 
     */	
	private Long id;
    /**
     * 编号       
     * db_column: CODE 
     */	
	private String code;
    /**
     * 验证码       
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
     * 入职时间       
     * db_column: ENTRY_DATE 
     */	
	private Integer entryDate;
    /**
     * 是否有投票权限 0无 1有       
     * db_column: IS_VOTE 
     */	
	private String isVote;
	/**
     * 是否管理员 0不是 1是       
     * db_column: IS_VOTE 
     */	
	private String isAdmin;
	/**
     * 是否管理员 0不是 1是       
     * db_column: IS_VOTE 
     */	
	private String isDelete;
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
     * 座位区       
     * db_column: AREA_NUM 
     */	
	private String areaNum;
    /**
     * 座位行       
     * db_column: LINE_NUM 
     */	
	private String lineNum;
    /**
     * 座位列       
     * db_column: COLUMN_NUM 
     */	
	private String columnNum;
	/**
     * 姓名     
     * db_column: NMAE 
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
     * 工号       
     * db_column: JOB_NUM 
     */	
	private String jobNum;
	/**
     * 是否有抽奖权限 0 有 1 无      
     * db_column: IS_DRAW
     */	
	private String isDraw;
	
	//奖项名
	private String drawName;
	//奖品
	private String drawRemark;
	
	private int weightNum;
	
	//中奖状态
	private Long drawStatus;
	
	private Long drawResultId;

	public BiVoteUser(){
	}

	public BiVoteUser(
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
	
	public void setEntryDate(Integer value) {
		this.entryDate = value;
	}
	
	public Integer getEntryDate() {
		return this.entryDate;
	}
	
	public void setIsVote(String value) {
		this.isVote = value;
	}
	
	public String getIsVote() {
		return this.isVote;
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
	
	public void setAreaNum(String value) {
		this.areaNum = value;
	}
	
	public String getAreaNum() {
		return this.areaNum;
	}
	
	public void setLineNum(String value) {
		this.lineNum = value;
	}
	
	public String getLineNum() {
		return this.lineNum;
	}
	
	public void setColumnNum(String value) {
		this.columnNum = value;
	}
	
	public String getColumnNum() {
		return this.columnNum;
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

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getJobNum() {
		return jobNum;
	}

	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}

	public String getIsDraw() {
		return isDraw;
	}

	public void setIsDraw(String isDraw) {
		this.isDraw = isDraw;
	}

	public String getDrawName() {
		return drawName;
	}

	public void setDrawName(String drawName) {
		this.drawName = drawName;
	}

	public String getDrawRemark() {
		return drawRemark;
	}

	public void setDrawRemark(String drawRemark) {
		this.drawRemark = drawRemark;
	}

	public int getWeightNum() {
		return weightNum;
	}

	public void setWeightNum(int weightNum) {
		this.weightNum = weightNum;
	}

	public Long getDrawStatus() {
		return drawStatus;
	}

	public void setDrawStatus(Long drawStatus) {
		this.drawStatus = drawStatus;
	}

	public Long getDrawResultId() {
		return drawResultId;
	}

	public void setDrawResultId(Long drawResultId) {
		this.drawResultId = drawResultId;
	}
	
}

