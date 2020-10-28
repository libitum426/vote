package com.mendale.dao.vote;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mendale.common.base.BaseDao;
import com.mendale.vo.vote.BiVoteDepart;

@Repository
public interface BiVoteDepartDao extends BaseDao<BiVoteDepart,Long>{
	
	/** 
	 * 查询部门列表
	 * @param biVoteDepart
	 * @return List<BiVoteDepart>  
	 */ 
	public List<BiVoteDepart> queryDepartList(BiVoteDepart biVoteDepart);
	
	/** 
	 * 查询部门条数
	 * @param biVoteBrand
	 * @return int  
	 */ 
	public int queryDepartCount(BiVoteDepart biVoteDepart);
	
	/** 
	 * 批量删除
	 * @param map
	 * @return int  
	 */ 
	public int updateDeparts(Map<String, Object> map);
	
}
