package com.mendale.dao.vote;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mendale.common.base.BaseDao;
import com.mendale.vo.vote.BiVoteDrawconfig;

@Repository
public interface BiVoteDrawconfigDao extends BaseDao<BiVoteDrawconfig,Long>{
	
	/** 
	 * 根据条件查询奖项配置
	 * @param biVoteDrawconfig
	 * @return List<BiVoteDrawconfig>  
	 */ 
	public List<BiVoteDrawconfig> queryList(BiVoteDrawconfig biVoteDrawconfig);
	
	/** 
	 * 批量操作
	 * @param map
	 * @return int  
	 */ 
	public int operaDrawconfig(Map<String, Object> map);
	
	/** 
	 * 根据参数查询数据数量
	 * @param biVoteDrawconfig
	 * @return int  
	 */ 
	public int queryCount(BiVoteDrawconfig biVoteDrawconfig);
	
}
