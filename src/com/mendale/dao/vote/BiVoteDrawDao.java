package com.mendale.dao.vote;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mendale.common.base.BaseDao;
import com.mendale.vo.vote.BiVoteDraw;

/**   
 *
 * @Title:  抽奖配置   
 * @Description:  TODO   
 * @ClassName:  BiVoteDrawDao     
 * @author: liuyang  
 * @date:   2016年9月28日 上午10:46:13   
 *      
 */
@Repository
public interface BiVoteDrawDao extends BaseDao<BiVoteDraw,Long>{
	
	/** 
	 * 根据参数查询抽奖列表
	 * @param biVoteDraw
	 * @return List<BiVoteDraw>  
	 */ 
	public List<BiVoteDraw> queryList(BiVoteDraw biVoteDraw);
	
	/** 
	 * 批量操作
	 * @param map
	 * @return int  
	 */ 
	public int operaDrawz(Map<String, Object> map);
	
}
