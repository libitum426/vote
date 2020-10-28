package com.mendale.vo.vote;

import java.util.*;

/**   
 * 投票结果明细POJO
 * @Title:     
 * @Description:  TODO   
 * @ClassName:  BiVoteResult     
 * @author: liuyang  
 * @date:   2015年11月28日 上午11:47:36   
 *      
 */
public class BiVoteResult implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
    /**
     * 主键       
     * db_column: ID 
     */	
	private Long id;
    /**
     * 投票人ID       
     * db_column: USER_ID 
     */	
	private Long userId;
    /**
     * 投票配置ID       
     * db_column: DEPLOY_ID 
     */	
	private Long deployId;
    /**
     * 候选人       
     * db_column: CANDI_ID 
     */	
	private Long candiId;
    /**
     * 票数       
     * db_column: VOTE_NUM 
     */	
	private Long voteNum;
    /**
     * 是否作废 0 有效 1 作废       
     * db_column: IS_DELETE 
     */	
	private Long isDelete;
    /**
     * 创建日期       
     * db_column: CREATION_DATE 
     */	
	private Date creationDate;
	private String creationDateBegin;
	private String creationDateEnd;

	public BiVoteResult(){
	}

	public BiVoteResult(
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
	
	public void setUserId(Long value) {
		this.userId = value;
	}
	
	public Long getUserId() {
		return this.userId;
	}
	
	public void setDeployId(Long value) {
		this.deployId = value;
	}
	
	public Long getDeployId() {
		return this.deployId;
	}
	
	public void setCandiId(Long value) {
		this.candiId = value;
	}
	
	public Long getCandiId() {
		return this.candiId;
	}
	
	public void setVoteNum(Long value) {
		this.voteNum = value;
	}
	
	public Long getVoteNum() {
		return this.voteNum;
	}
	
	public void setIsDelete(Long value) {
		this.isDelete = value;
	}
	
	public Long getIsDelete() {
		return this.isDelete;
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
	
}

