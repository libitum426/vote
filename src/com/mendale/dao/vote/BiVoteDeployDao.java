package com.mendale.dao.vote;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mendale.common.annotation.AttributesType;
import com.mendale.common.annotation.SetAttributes;
import com.mendale.common.base.BaseDao;
import com.mendale.vo.vote.BiVoteDeploy;

@Repository
public interface BiVoteDeployDao extends BaseDao<BiVoteDeploy,Long>{
	
	/** 
	 * 根据参数查询投票列表
	 * @param biVoteDeploy
	 * @return List<BiVoteDeploy>  
	 */ 
	public List<BiVoteDeploy> findVoteDeploys(BiVoteDeploy biVoteDeploy);

	/** 
	 * 批量冻结和启用配置
	 * @param map
	 * @return int  
	 */ 
	@SetAttributes(type = AttributesType.update)
	public int updateVoteDeploys(Map<String, Object> map);
	
}
