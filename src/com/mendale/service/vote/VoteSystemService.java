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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mendale.common.base.BaseDao;
import com.mendale.common.base.BaseService;
import com.mendale.dao.vote.BiVoteBrandDao;
import com.mendale.dao.vote.BiVoteCandidateDao;
import com.mendale.dao.vote.BiVoteDepartDao;
import com.mendale.dao.vote.BiVoteDeployDao;
import com.mendale.dao.vote.BiVoteLoginDao;
import com.mendale.dao.vote.BiVoteResultDao;
import com.mendale.dao.vote.BiVoteUserDao;
import com.mendale.vo.vote.BiVoteBrand;
import com.mendale.vo.vote.BiVoteCandidate;
import com.mendale.vo.vote.BiVoteDepart;
import com.mendale.vo.vote.BiVoteDeploy;
import com.mendale.vo.vote.BiVoteLogin;
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
public class VoteSystemService extends BaseService<BiVoteUser, Long>{
	@Autowired
	private BiVoteUserDao biVoteUserDao;//投票人
	@Autowired
	private BiVoteDeployDao biVoteDeployDao;//投票配置
	@Autowired
	private BiVoteCandidateDao biVoteCandidateDao;//候选人
	@Autowired
	private BiVoteResultDao biVoteResultDao;//结果
	@Autowired
	private BiVoteBrandDao biVoteBrandDao;//品牌
	@Autowired
	private BiVoteDepartDao biVoteDepartDao;//部门
	@Autowired
	private BiVoteLoginDao biVoteLoginDao;//管理员登陆用户

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
	 * 批量更新候选人权限
	 * @param userId
	 * @param flag
	 * @return int  
	 */ 
	public int updateCandidateVotes(List<String> userIds,String flag){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIds", userIds);
		map.put("flag", flag);
		return biVoteCandidateDao.updateCandidateVotes(map);
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
	 * 根据参数查询抽奖用户信息
	 * @param biVoteUser
	 * @return List<BiVoteUser>  
	 */ 
	public List<BiVoteUser> findDrawUserList(BiVoteUser biVoteUser){
		return biVoteUserDao.findDrawUserList(biVoteUser);
	}
	
	/** 
	 * 根据参数查询抽奖用户信息
	 * @param biVoteUser
	 * @return List<BiVoteUser>  
	 */ 
	public Integer findWeightSum(BiVoteUser biVoteUser){
		return biVoteUserDao.findWeightSum(biVoteUser);
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
	 * 保存或更新投票配置信息
	 * @param biVoteDeploy void  
	 */ 
	public void saveOrUpdateDeploy(BiVoteDeploy biVoteDeploy,BiVoteLogin user){
		if(null == biVoteDeploy.getId()){
			biVoteDeploy.setCreatedBy(user.getCode());
			biVoteDeploy.setLastUpdatedBy(user.getCode());
			biVoteDeploy.setLastUpdateDate(new Date());
			biVoteDeployDao.insert(biVoteDeploy);
		}else{
			biVoteDeploy.setLastUpdatedBy(user.getCode());
			biVoteDeploy.setLastUpdateDate(new Date());
			biVoteDeployDao.update(biVoteDeploy);
		}
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
	 * 查询品牌列表
	 * @param biVoteBrand
	 * @return List<BiVoteBrand>  
	 */ 
	public List<BiVoteBrand> queryBrandList(BiVoteBrand biVoteBrand){
		return biVoteBrandDao.queryBrandList(biVoteBrand);
	}
	
	/** 
	 * 查询品牌条数
	 * @param biVoteBrand
	 * @return int  
	 */ 
	public int queryBrandCount(BiVoteBrand biVoteBrand){
		return biVoteBrandDao.queryBrandCount(biVoteBrand);
	}
	
	/** 
	 * 根据ID查询品牌信息
	 * @param biVoteBrand
	 * @return BiVoteBrand  
	 */ 
	public BiVoteBrand queryBrandById(BiVoteBrand biVoteBrand){
		return biVoteBrandDao.getById(biVoteBrand.getId());
	}
	
	/** 
	 * 编辑品牌
	 * @param biVoteBrand void  
	 */ 
	public void editBrand(BiVoteBrand biVoteBrand,BiVoteLogin user){
		if(null == biVoteBrand.getId()){
			biVoteBrand.setCreatedBy(user.getCode());
			biVoteBrandDao.insert(biVoteBrand);
		}else{
			biVoteBrand.setLastUpdatedBy(user.getCode());
			biVoteBrandDao.update(biVoteBrand);
		}
	}
	
	/** 
	 * 批量删除品牌数据
	 * @param brandIds
	 * @param lastUpdatedBy
	 * @return int  
	 */ 
	public int updateBrands(List<String> brandIds,String lastUpdatedBy){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brandIds", brandIds);
		map.put("lastUpdatedBy", lastUpdatedBy);
		return biVoteBrandDao.updateBrands(map);
	}
	
	/** 
	 * 查询部门列表
	 * @param biVoteDepart
	 * @return List<BiVoteDepart>  
	 */ 
	public List<BiVoteDepart> queryDepartList(BiVoteDepart biVoteDepart){
		return biVoteDepartDao.queryDepartList(biVoteDepart);
	}
	
	/** 
	 * 查询部门条数
	 * @param biVoteDepart
	 * @return int  
	 */ 
	public int queryDepartCount(BiVoteDepart biVoteDepart){
		return biVoteDepartDao.queryDepartCount(biVoteDepart);
	}
	
	/** 
	 * 根据ID查询部门信息
	 * @param biVoteDepart
	 * @return BiVoteDepart  
	 */ 
	public BiVoteDepart queryDepartById(BiVoteDepart biVoteDepart){
		return biVoteDepartDao.getById(biVoteDepart.getId());
	}
	
	/** 
	 * 编辑部门
	 * @param biVoteDepart void  
	 */ 
	public void editDepart(BiVoteDepart biVoteDepart,BiVoteLogin user){
		if(null == biVoteDepart.getId()){
			biVoteDepart.setCreatedBy(user.getCode());
			biVoteDepartDao.insert(biVoteDepart);
		}else{
			biVoteDepart.setLastUpdatedBy(user.getCode());
			biVoteDepartDao.update(biVoteDepart);
		}
	}
	
	/** 
	 * 批量删除部门数据
	 * @param departIds
	 * @param lastUpdatedBy
	 * @return int  
	 */ 
	public int updateDeparts(List<String> departIds,String lastUpdatedBy){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("departIds", departIds);
		map.put("lastUpdatedBy", lastUpdatedBy);
		return biVoteDepartDao.updateDeparts(map);
	}
	
	/** 
	 * 批量插入投票人数据
	 * @param biVoteUsers void  
	 */ 
	public int insertUserList(List<BiVoteUser> biVoteUsers){
		return biVoteUserDao.insertUserList(biVoteUsers);
	}
	
	/** 
	 * 批量插入候选人数据
	 * @param biVoteUsers void  
	 */ 
	public int insertCandidateList(List<BiVoteCandidate> biVoteCandidates){
		return biVoteCandidateDao.insertCandidateList(biVoteCandidates);
	}
	
	/** 
	 * 初始化投票人数据
	 * @param biVoteUser
	 * @return int  
	 */ 
	public int initUserData(BiVoteUser biVoteUser){
		return biVoteUserDao.initUserData(biVoteUser);
	}
	
	/** 
	 * 根据ID查询候选人信息
	 * @param id
	 * @return BiVoteDeploy  
	 */ 
	public BiVoteCandidate getVoteCandidateById(Long id){
		return biVoteCandidateDao.getById(id);
	}
	
	/** 
	 * 编辑候选人信息
	 * @param id
	 * @return BiVoteDeploy  
	 */ 
	public int updateVoteCandidateById(BiVoteCandidate biVoteCandidate){
		return biVoteCandidateDao.update(biVoteCandidate);
	}

}
