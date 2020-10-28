package com.mendale.service.vote;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mendale.common.base.BaseDao;
import com.mendale.common.base.BaseService;
import com.mendale.dao.vote.BiVoteLoginDao;
import com.mendale.vo.vote.BiVoteLogin;

@Service
public class BiVoteLoginService extends BaseService<BiVoteLogin, Long>{
	@Autowired
	private BiVoteLoginDao biVoteLoginDao;

	/**   
	 * @return   
	 * @see com.mendale.common.base.BaseService#getBaseDao()   
	 */
	@Override
	protected BaseDao<BiVoteLogin, Long> getBaseDao() {
		// TODO Auto-generated method stub
		return biVoteLoginDao;
	}
	
	/**
	 * 检查用户是否存在
	 * 
	 * @param user
	 *            用户名
	 * @return boolean true:存在 false:不存在
	 */
	public boolean isExistsUser(BiVoteLogin biVoteLogin) {
		boolean isExists = false;
		List<BiVoteLogin> lstUser = biVoteLoginDao.getByUserName(biVoteLogin);
		if (lstUser != null && lstUser.size() > 0) {
			isExists = true;
		}
		return isExists;
	}

	/**
	 * 检查用户密码是否正确
	 * @param user 用户名、密码
	 * @return List<User> 用户信息查询结果
	 */
	public List<BiVoteLogin> checkUserPassword(BiVoteLogin biVoteLogin) {
		return biVoteLoginDao.getByUserName(biVoteLogin);
	}
	
}
