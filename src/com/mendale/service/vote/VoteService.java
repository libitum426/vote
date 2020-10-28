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
import com.mendale.dao.vote.BiVoteCandidateDao;
import com.mendale.dao.vote.BiVoteDeployDao;
import com.mendale.dao.vote.BiVoteResultDao;
import com.mendale.dao.vote.BiVoteUserDao;
import com.mendale.vo.vote.BiVoteCandidate;
import com.mendale.vo.vote.BiVoteDeploy;
import com.mendale.vo.vote.BiVoteResult;
import com.mendale.vo.vote.BiVoteUser;

/**   
 * 投票service
 * @Title:     
 * @Description:  TODO   
 * @ClassName:  VoteService     
 * @author: liuyang  
 * @date:   2015年11月28日 上午11:49:45   
 *      
 */
@Service
public class VoteService extends BaseService<BiVoteUser, Long>{
	@Autowired
	private BiVoteUserDao biVoteUserDao;
	@Autowired
	private BiVoteDeployDao biVoteDeployDao;
	@Autowired
	private BiVoteCandidateDao biVoteCandidateDao;
	@Autowired
	private BiVoteResultDao biVoteResultDao;

	/**   
	 * @return   
	 * @see com.mendale.common.base.BaseService#getBaseDao()   
	 */
	@Override
	protected BaseDao<BiVoteUser, Long> getBaseDao() {
		// TODO Auto-generated method stub
		return biVoteUserDao;
	}
	
	/** 
	 * 根据参数查询投票列表
	 * @param biVoteDeploy
	 * @return List<BiVoteDeploy>  
	 */ 
	public List<BiVoteDeploy> findVoteDeploys(BiVoteDeploy biVoteDeploy){
		return biVoteDeployDao.findVoteDeploys(biVoteDeploy);
	}
	
	/** 
	 * 根据参数查询投票候选人
	 * @param biVoteCandidate
	 * @return List<BiVoteCandidate>  
	 */ 
	public List<BiVoteCandidate> findVoteCandidates(BiVoteCandidate biVoteCandidate){
		return biVoteCandidateDao.findVoteCandidates(biVoteCandidate);
	}
	
	/** 
	 * 批量保存投票人信息 并将已投票的人员投票权限改为无效
	 * @param biVoteResults
	 * @param userId void  
	 */ 
	public void saveVoteResult(List<BiVoteResult> biVoteResults,Long userId){
		for(BiVoteResult biVoteResult : biVoteResults){
			biVoteResultDao.insert(biVoteResult);
		}
		BiVoteUser biVoteUser = new BiVoteUser(userId);
		biVoteUser.setIsVote("0");
		//将该用户的投票权限修改为无效
		biVoteUserDao.update(biVoteUser);
	}
	
	/** 
	 * 根据参数查询用户列表
	 * @param biVoteUser
	 * @return List<BiVoteUser>  
	 */ 
	public List<BiVoteUser> findUserList(BiVoteUser biVoteUser){
		return biVoteUserDao.findUserList(biVoteUser);
	}
	
	/** 
	 * 根据code获取用户信息
	 * @param biVoteUser
	 * @return BiVoteUser  
	 */ 
	public BiVoteUser findVoteUserByCode(BiVoteUser biVoteUser){
		return biVoteUserDao.findVoteUserByCode(biVoteUser);
	}
	
	/** 
	 * 签到更新
	 * @param biVoteUser
	 * @return int  
	 */ 
	public int updateIsDelete(BiVoteUser biVoteUser){
		return biVoteUserDao.updateIsDelete(biVoteUser);
	}
	
	/** 
	 * 查询部门列表
	 * @return List<String>  
	 */ 
	public List<String> findDeptNameList(BiVoteCandidate biVoteCandidate){
		return biVoteCandidateDao.findDeptNameList(biVoteCandidate);
	}
	
	/** 
	 * 批量更新用户投票权限
	 * @param userId
	 * @param flag
	 * @return int  
	 */ 
	public int updateUserVotes(List<String> userIds,String flag){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIds", userIds);
		map.put("flag", flag);
		return biVoteUserDao.updateUserVotes(map);
	}
	
	/** 
	 * 批量冻结和启用配置
	 * @param userIds
	 * @param flag
	 * @return int  
	 */ 
	public int updateVoteDeploys(List<String> userIds,String flag){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIds", userIds);
		map.put("flag", flag);
		return biVoteDeployDao.updateVoteDeploys(map);
	}
	
	/** 
	 * 保存投票配置信息
	 * @param biVoteDeploy void  
	 */ 
	public void saveDeploy(BiVoteDeploy biVoteDeploy){
		biVoteDeployDao.insert(biVoteDeploy);
	}
	
	/** 
	 * 根据ID查询投票配置信息
	 * @param biVoteDeploy
	 * @return BiVoteDeploy  
	 */ 
	public BiVoteDeploy getVoteDeployById(BiVoteDeploy biVoteDeploy){
		return biVoteDeployDao.getById(biVoteDeploy.getId());
	}
	
	/** 
	 * 根据工号查询图片
	 * @param biVoteCandidate
	 * @return Map<String,Object>  
	 */ 
	public Map<String, Object> queryEmpPhotoByJobNum(BiVoteCandidate biVoteCandidate){
		return biVoteCandidateDao.queryEmpPhotoByJobNum(biVoteCandidate);
	}

}
