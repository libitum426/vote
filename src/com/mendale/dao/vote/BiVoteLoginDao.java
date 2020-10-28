package com.mendale.dao.vote;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mendale.common.base.BaseDao;
import com.mendale.vo.vote.BiVoteLogin;

@Repository
public interface BiVoteLoginDao extends BaseDao<BiVoteLogin,Long>{
	
	/**
	 * 根据用户名、密码查询用户信息
	 * 
	 * @param user
	 *            用户名、密码
	 * @return List<User> 用户数据查询结果
	 */
	public List<BiVoteLogin> getByUserName(BiVoteLogin biVoteLogin) throws DataAccessException;
	
}
