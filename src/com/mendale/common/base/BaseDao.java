package com.mendale.common.base;

import java.io.Serializable;
import java.util.List;
import org.springframework.dao.DataAccessException;

import com.mendale.common.annotation.AttributesType;
import com.mendale.common.annotation.SetAttributes;

/**
 * 
* <p>DAO的基础类</p>
* <p> Description: </p>
* @作者 xzm
* @创建时间 2014-7-26  3:02:58 PM
* @版本 1.00
* @修改记录
* <pre>
* 版本   修改人    修改时间        修改内容描述
* ----------------------------------------
* 1.00   xzm    2014-7-26 3:02:58 PM   初始化版本
* ----------------------------------------
* </pre>
*
 */
public interface BaseDao <E,PK extends Serializable>{
	public E getById(PK id) throws DataAccessException;
	
	public E findByVO(E entity) throws DataAccessException;

	@SetAttributes(type = AttributesType.update)
	public int deleteById(PK id) throws DataAccessException;
	
	@SetAttributes(type = AttributesType.create)
	public int insert(E entity) throws DataAccessException;
	
	@SetAttributes(type = AttributesType.update)
	public int update(E entity) throws DataAccessException;

	public List<E> findAll() throws DataAccessException;
	
	public List<E> findPageList(BaseVO query) throws DataAccessException;
	
	public int findPageCount(BaseVO query) throws DataAccessException;
	

}
