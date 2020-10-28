package com.mendale.dao.vote;

import java.util.List;
import java.util.Map;

import com.mendale.common.base.BaseDao;
import com.mendale.vo.vote.BiVoteBrand;

import org.springframework.stereotype.Repository;

@Repository
public interface BiVoteBrandDao extends BaseDao<BiVoteBrand,Long>{
	
	/** 
	 * 查询品牌列表
	 * @param biVoteBrand
	 * @return List<BiVoteBrand>  
	 */ 
	public List<BiVoteBrand> queryBrandList(BiVoteBrand biVoteBrand);
	
	/** 
	 * 查询品牌条数
	 * @param biVoteBrand
	 * @return int  
	 */ 
	public int queryBrandCount(BiVoteBrand biVoteBrand);
	
	/** 
	 * 批量删除
	 * @param map
	 * @return int  
	 */ 
	public int updateBrands(Map<String, Object> map);
	
}
