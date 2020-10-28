package com.mendale.common.util.dialect;
/**
 * 
* <p> Title:mysql Dialect</p>
* <p> Description: </p>
* @作者 xzm
* @创建时间 Jul 27, 2014 3:50:42 PM
* @版本 1.00
* @修改记录
* <pre>
* 版本   修改人    修改时间        修改内容描述
* ----------------------------------------
* 1.00   xzm    Jul 27, 2014 3:50:42 PM   初始化版本
* ----------------------------------------
* </pre>
*
 */
public class MySQLDialect extends Dialect{

	@Override
    public boolean supportsLimitOffset(){
		return true;
	}
	
    @Override
    public boolean supportsLimit() {   
        return true;   
    }  
    
    /**
     * 获取分页字符串
     */
	@Override
    public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit, String limitPlaceholder) {
        if (offset > 0) {   
        	return sql + " limit "+offsetPlaceholder+","+limitPlaceholder; 
        } else {   
            return sql + " limit "+limitPlaceholder;
        }  
	}   
  
}
