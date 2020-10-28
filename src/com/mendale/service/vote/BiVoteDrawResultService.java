/**  
 *Copyright © 2016梦洁. All rights reserved.
 *
 * @Title: BiVoteDrawResultService.java
 * @Package com.mendale.service.vote
 * @Description: TODO
 * @author liuyang 
 * @date 2016年1月25日 下午5:55:56
 * @version V1.0  
 */
package com.mendale.service.vote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mendale.common.base.BaseDao;
import com.mendale.common.base.BaseService;
import com.mendale.dao.vote.BiVoteDrawResultDao;
import com.mendale.dao.vote.BiVoteDrawconfigDao;
import com.mendale.dao.vote.BiVoteUserDao;
import com.mendale.vo.vote.BiVoteDrawResult;
import com.mendale.vo.vote.BiVoteDrawconfig;
import com.mendale.vo.vote.BiVoteUser;

/**   
 *
 * @Title:     
 * @Description:  TODO   
 * @ClassName:  BiVoteDrawResultService     
 * @author: liuyang  
 * @date:   2016年1月25日 下午5:55:56   
 *      
 */
@Service
public class BiVoteDrawResultService extends BaseService<BiVoteDrawResult, Long>{
	@Autowired
	private BiVoteDrawResultDao biVoteDrawResultDao;
	@Autowired
	private BiVoteUserDao biVoteUserDao;
	@Autowired
	private BiVoteDrawconfigDao biVoteDrawconfigDao;//奖项配置

	/**   
	 * @return   
	 * @see com.mendale.common.base.BaseService#getBaseDao()   
	 */
	@Override
	protected BaseDao<BiVoteDrawResult, Long> getBaseDao() {
		// TODO Auto-generated method stub
		return biVoteDrawResultDao;
	}
	
	/** 
	 * 插入抽奖结果表
	 * @param userIds
	 * @param biVoteDrawconfig
	 * @return List<BiVoteUser>  
	 */ 
	public List<BiVoteUser> insertDrawRest(List<String> userIds,BiVoteDrawconfig biVoteDrawconfig){
		List<BiVoteUser> biVoteUsers = new ArrayList<BiVoteUser>();
		for(String userId: userIds){
			BiVoteUser biVoteUser = biVoteUserDao.getById(new Long(userId));
			if("1".equals(biVoteUser.getIsDraw())){
				return null;
			}else{
				biVoteUsers.add(biVoteUser);
				BiVoteDrawResult biVoteDrawResult = new BiVoteDrawResult();
				biVoteDrawResult.setDrawId(biVoteDrawconfig.getId());
				biVoteDrawResult.setUserId(biVoteUser.getId());
				biVoteDrawResult.setDrawRemark(biVoteDrawconfig.getDrawRemark());
				biVoteDrawResultDao.insert(biVoteDrawResult);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIds", userIds);
		map.put("flag", "1");
		biVoteUserDao.updateUserVotesIsDraw(map);
		//biVoteDrawconfig.setIsActive("1");
		biVoteDrawconfigDao.update(biVoteDrawconfig);
		return biVoteUsers;
	}
	
	/** 
	 * 查询中奖人员信息
	 * @return List<BiVoteUser>  
	 */ 
	public List<BiVoteUser> queryDrawUsersResult(){
		return biVoteUserDao.queryDrawUsersResult();
	}

}
