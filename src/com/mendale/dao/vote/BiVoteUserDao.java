package com.mendale.dao.vote;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mendale.common.annotation.AttributesType;
import com.mendale.common.annotation.SetAttributes;
import com.mendale.common.base.BaseDao;
import com.mendale.vo.vote.BiVoteUser;

@Repository
public interface BiVoteUserDao extends BaseDao<BiVoteUser, Long> {

	/**
	 * 根据参数查询用户列表
	 * 
	 * @param biVoteUser
	 * @return List<BiVoteUser>
	 */
	public List<BiVoteUser> findUserList(BiVoteUser biVoteUser);
	
	/**
	 * 根据参数查询抽奖用户信息
	 * 
	 * @param biVoteUser
	 * @return List<BiVoteUser>
	 */
	public List<BiVoteUser> findDrawUserList(BiVoteUser biVoteUser);
	
	/** 
	 * 查询
	 * @param biVoteUser
	 * @return Integer  
	 */ 
	public Integer findWeightSum(BiVoteUser biVoteUser);

	/**
	 * 根据code获取用户信息
	 * 
	 * @param biVoteUser
	 * @return BiVoteUser
	 */
	public BiVoteUser findVoteUserByCode(BiVoteUser biVoteUser);

	/**
	 * 签到更新
	 * 
	 * @param biVoteUser
	 * @return
	 * @throws DataAccessException
	 *             int
	 */
	@SetAttributes(type = AttributesType.update)
	public int updateIsDelete(BiVoteUser biVoteUser) throws DataAccessException;

	/** 
	 * 批量更新用户权限
	 * @param userId
	 * @return
	 * @throws DataAccessException int  
	 */ 
	@SetAttributes(type = AttributesType.update)
	public int updateUserVotes(Map<String, Object> map)
			throws DataAccessException;
	
	/** 
	 * 批量插入数据
	 * @param:list
	 * @param:     
	 * @return:int  
	 */ 
	public int insertUserList(List<BiVoteUser> list);
	
	/** 
	 * 批量更新用户抽奖权限
	 * @param userId
	 * @return
	 * @throws DataAccessException int  
	 */ 
	@SetAttributes(type = AttributesType.update)
	public int updateUserVotesIsDraw(Map<String, Object> map)
			throws DataAccessException;
	
	/** 
	 * 查询中奖人员信息
	 * @return List<BiVoteUser>  
	 */ 
	public List<BiVoteUser> queryDrawUsersResult();
	
	/** 
	 * 初始化投票人数据
	 * @param biVoteUser
	 * @return int  
	 */ 
	public int initUserData(BiVoteUser biVoteUser);

}
