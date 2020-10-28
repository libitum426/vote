/**  
 *Copyright © 2015梦洁. All rights reserved.
 *
 * @Title: VoteService.java
 * @Package com.mendale.service.vote
 * @Description: TODO
 * @author liuyang 
 * @date 2015年11月28日 上午11:49:45
 * @version V1.0  
 */
package com.mendale.service.vote;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mendale.common.base.BaseDao;
import com.mendale.common.base.BaseService;
import com.mendale.dao.vote.BiVoteDrawconfigDao;
import com.mendale.vo.vote.BiVoteDrawconfig;

/**   
 * 奖项配置Service
 * @Title:     
 * @Description:  TODO   
 * @ClassName:  VoteService     
 * @author: liuyang  
 * @date:   2015年11月28日 上午11:49:45   
 *      
 */
@Service
public class BiVoteDrawconfigService extends BaseService<BiVoteDrawconfig, Long>{
	@Autowired
	private BiVoteDrawconfigDao biVoteDrawconfigDao;//奖项配置

	/**   
	 * @return   
	 * @see com.mendale.common.base.BaseService#getBaseDao()   
	 */
	@Override
	protected BaseDao<BiVoteDrawconfig, Long> getBaseDao() {
		// TODO Auto-generated method stub
		return biVoteDrawconfigDao;
	}
	
	/** 
	 * 根据条件查询奖项配置
	 * @param biVoteDrawconfig
	 * @return List<BiVoteDrawconfig>  
	 */ 
	public List<BiVoteDrawconfig> queryList(BiVoteDrawconfig biVoteDrawconfig){
		return biVoteDrawconfigDao.queryList(biVoteDrawconfig);
	}
	
	/** 
	 * 批量操作
	 * @param userIds
	 * @param flag
	 * @return int  
	 */ 
	public int operaDrawconfig(List<String> userIds,String flag){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("drawIds", userIds);
		map.put("flag", flag);
		return biVoteDrawconfigDao.operaDrawconfig(map);
	}
	
	/** 
	 * 根据参数查询数据数量
	 * @param biVoteDrawconfig
	 * @return int  
	 */ 
	public int queryCount(BiVoteDrawconfig biVoteDrawconfig){
		return biVoteDrawconfigDao.queryCount(biVoteDrawconfig);
	}

}
