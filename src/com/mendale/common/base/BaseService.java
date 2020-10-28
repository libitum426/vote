package com.mendale.common.base;

import java.io.Serializable;
import java.util.List;
import com.mendale.common.util.page.Page;
/**
 * 
* <p> Service层基类</p>
* <p> Description: </p>
* @作者 xzm
* @创建时间 Jul 26, 2014 9:00:57 PM
* @版本 1.00
* @修改记录
* <pre>
* 版本   修改人    修改时间        修改内容描述
* ----------------------------------------
* 1.00   xzm    Jul 26, 2014 9:00:57 PM   初始化版本
* ----------------------------------------
* </pre>
*
 */
public abstract class BaseService <E,PK extends Serializable>{
	
    protected abstract BaseDao<E, PK> getBaseDao();
    //根据主键ID查询VO对象
	public E getById(PK id){
		return getBaseDao().getById(id);
	}
	//根据传入的VO对象的属性查询完整的VO
	public E findByVO(E entity){
		return getBaseDao().findByVO(entity);
	}
	//查询所有数据，返回LIST列表
	public List<E> findAll(){
		return getBaseDao().findAll();
	}
	//新增保存
	public boolean save(E entity){
		return getBaseDao().insert(entity)>0?true:false;
	}
	//更新保存
	public boolean update(E entity){
        return getBaseDao().update(entity)>0?true:false;
    }
	//根据主键删除一条数据
	public boolean deleteById(PK id){
		return getBaseDao().deleteById(id)>0?true:false;
	}
	//分页查询
	public Page<E> findPage(BaseVO pageRequest){
	    int totalCount = getBaseDao().findPageCount(pageRequest);
	    Page<E> page = new Page<E>(pageRequest,totalCount);

	    page.setResult(getBaseDao().findPageList(pageRequest));
	    return page;
	}
}