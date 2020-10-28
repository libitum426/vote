package com.mendale.dao.vote;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mendale.common.annotation.AttributesType;
import com.mendale.common.annotation.SetAttributes;
import com.mendale.common.base.BaseDao;
import com.mendale.vo.vote.BiVoteCandidate;

@Repository
public interface BiVoteCandidateDao extends BaseDao<BiVoteCandidate,Long>{
	
	/** 
	 * 根据参数查询投票候选人
	 * @param biVoteCandidate
	 * @return List<BiVoteCandidate>  
	 */ 
	public List<BiVoteCandidate> findVoteCandidates(BiVoteCandidate biVoteCandidate);
	
	/** 
	 * 查询部门
	 * @return List<String>  
	 */ 
	public List<String> findDeptNameList(BiVoteCandidate biVoteCandidate);
	
	/** 
	 * 批量更新候选人权限
	 * @param userId
	 * @return
	 * @throws DataAccessException int  
	 */ 
	@SetAttributes(type = AttributesType.update)
	public int updateCandidateVotes(Map<String, Object> map)
			throws DataAccessException;
	
	/** 
	 * 批量插入数据
	 * @param:list
	 * @param:     
	 * @return:int  
	 */ 
	public int insertCandidateList(List<BiVoteCandidate> list);
	
	/** 
	 * 根据工号查询图片
	 * @param biVoteCandidate
	 * @return Map<String,Object>  
	 */ 
	public Map<String, Object> queryEmpPhotoByJobNum(BiVoteCandidate biVoteCandidate);
	
}
