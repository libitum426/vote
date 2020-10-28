package com.mendale.common.util.dialect;
/**
 * 
* <p> sqlserver Dialect</p>
* <p> Description: </p>
* @作者 xzm
* @创建时间 Jul 27, 2014 3:52:05 PM
* @版本 1.00
* @修改记录
* <pre>
* 版本   修改人    修改时间        修改内容描述
* ----------------------------------------
* 1.00   xzm    Jul 27, 2014 3:52:05 PM   初始化版本
* ----------------------------------------
* </pre>
*
 */
public class SQLServerDialect extends Dialect{

	@Override
    public boolean supportsLimitOffset(){
		return false;
	}
	
	@Override
    public boolean supportsLimit() {
		return true;
	}
	
	static int getAfterSelectInsertPoint(String sql) {
		int selectIndex = sql.toLowerCase().indexOf( "select" );
		final int selectDistinctIndex = sql.toLowerCase().indexOf( "select distinct" );
		return selectIndex + ( selectDistinctIndex == selectIndex ? 15 : 6 );
	}

	@Override
    public String getLimitString(String sql, int offset, int limit) {
		return getLimitString(sql,offset,null,limit,null);
	}
	/**
     * 获取分页字符串
     */
	@Override
    public String getLimitString(String querySelect, int offset,String offsetPlaceholder, int limit, String limitPlaceholder) {
		if ( offset > 0 ) {
			throw new UnsupportedOperationException( "sql server has no offset" );
		}
		return new StringBuffer( querySelect.length() + 8 )
				.append( querySelect )
				.insert( getAfterSelectInsertPoint( querySelect ), " top " + limit )
				.toString();
	}


}
