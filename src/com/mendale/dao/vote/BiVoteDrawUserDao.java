package com.mendale.dao.vote;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mendale.common.base.BaseDao;
import com.mendale.vo.vote.BiVoteDrawUser;

/**   
 * 抽奖人员配置表
 * @Title:     
 * @Description:  TODO   
 * @ClassName:  BiVoteDrawUserDao     
 * @author: liuyang  
 * @date:   2016年9月30日 下午3:22:48   
 *      
 */
@Repository
public interface BiVoteDrawUserDao extends BaseDao<BiVoteDrawUser,Long>{
	
	/** 
	 * 根据参数查询数据信息
	 * @param biVoteDrawUser
	 * @return List<BiVoteDrawUser>  
	 */ 
	public List<BiVoteDrawUser> queryList(BiVoteDrawUser biVoteDrawUser);
	
	/** 
	 * 批量操作
	 * @param map
	 * @return int  
	 */ 
	public int operaDrawUser(Map<String, Object> map);
	
}
