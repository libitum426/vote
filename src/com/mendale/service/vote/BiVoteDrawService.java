/**  
 *Copyright © 2016梦洁. All rights reserved.
 *
 * @Title: BiVoteDrawService.java
 * @Package com.mendale.service.vote
 * @Description: TODO
 * @author liuyang 
 * @date 2016年9月28日 上午10:48:48
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
import com.mendale.dao.vote.BiVoteDrawDao;
import com.mendale.vo.vote.BiVoteDraw;

/**   
 *
 * @Title:    抽奖配置 
 * @Description:  TODO   
 * @ClassName:  BiVoteDrawService     
 * @author: liuyang  
 * @date:   2016年9月28日 上午10:48:48   
 *      
 */
@Service
public class BiVoteDrawService extends BaseService<BiVoteDraw, Long>{
	@Autowired
	private BiVoteDrawDao biVoteDrawDao;

	/**   
	 * @return   
	 * @see com.mendale.common.base.BaseService#getBaseDao()   
	 */
	@Override
	protected BaseDao<BiVoteDraw, Long> getBaseDao() {
		// TODO Auto-generated method stub
		return biVoteDrawDao;
	}
	
	/** 
	 * 根据参数查询抽奖列表
	 * @param biVoteDraw
	 * @return List<BiVoteDraw>  
	 */ 
	public List<BiVoteDraw> queryList(BiVoteDraw biVoteDraw){
		return biVoteDrawDao.queryList(biVoteDraw);
	}
	
	/** 
	 * 批量操作
	 * @param userIds
	 * @param flag
	 * @return int  
	 */ 
	public int operaDrawz(List<String> drawIds,String flag){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("drawIds", drawIds);
		map.put("flag", flag);
		return biVoteDrawDao.operaDrawz(map);
	}

}
