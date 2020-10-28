/**  
 *Copyright © 2016梦洁. All rights reserved.
 *
 * @Title: BiVoteDrawUserService.java
 * @Package com.mendale.service.vote
 * @Description: TODO
 * @author liuyang 
 * @date 2016年9月30日 下午3:26:28
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
import com.mendale.dao.vote.BiVoteDrawUserDao;
import com.mendale.vo.vote.BiVoteDrawUser;

/**   
 *
 * @Title:     
 * @Description:  TODO   
 * @ClassName:  BiVoteDrawUserService     
 * @author: liuyang  
 * @date:   2016年9月30日 下午3:26:28   
 *      
 */
@Service
public class BiVoteDrawUserService extends BaseService<BiVoteDrawUser, Long>{
	@Autowired
	private BiVoteDrawUserDao biVoteDrawUserDao;

	/**   
	 * @return   
	 * @see com.mendale.common.base.BaseService#getBaseDao()   
	 */
	@Override
	protected BaseDao<BiVoteDrawUser, Long> getBaseDao() {
		// TODO Auto-generated method stub
		return biVoteDrawUserDao;
	}
	
	/** 
	 * 根据参数查询信息
	 * @param biVoteDrawUser
	 * @return List<BiVoteDrawUser>  
	 */ 
	public List<BiVoteDrawUser> queryList(BiVoteDrawUser biVoteDrawUser){
		biVoteDrawUser.setIsDelete(new Long(0));
		return biVoteDrawUserDao.queryList(biVoteDrawUser);
	}
	
	/** 
	 * 批量操作
	 * @param userIds
	 * @param flag
	 * @return int  
	 */ 
	public int operaDrawUser(List<String> userIds,String flag){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIds", userIds);
		map.put("flag", flag);
		return biVoteDrawUserDao.operaDrawUser(map);
	}

}
