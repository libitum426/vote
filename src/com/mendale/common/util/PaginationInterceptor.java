package com.mendale.common.util;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.mendale.common.util.dialect.Dialect;
import com.mendale.common.util.page.PageQuery;

/**
* <p>ibatis3提供基于方言(Dialect)的分页查询的插件</p>
* <p>将拦截Executor.query()方法实现分页方言的插入. </p>
* @作者 xzm
* @创建时间 Jul 27, 2014 3:56:15 PM
* @版本 1.00
* @修改记录
* <pre>
* 版本   修改人    修改时间        修改内容描述
* ----------------------------------------
* 1.00   xzm    Jul 27, 2014 3:56:15 PM   初始化版本
* ----------------------------------------
* </pre>
*
 */

@Intercepts({@Signature(type= Executor.class,method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class PaginationInterceptor implements Interceptor{
    static int MAPPED_STATEMENT_INDEX = 0;
    static int PARAMETER_INDEX = 1;
    static int ROWBOUNDS_INDEX = 2;
    static int RESULT_HANDLER_INDEX = 3;
    
    Dialect dialect;
    String _sql_regex;

 
    public Object intercept(Invocation invocation) throws Throwable {
        processIntercept(invocation.getArgs());
        return invocation.proceed();
    }

    void processIntercept(final Object[] queryArgs) {
        //queryArgs = query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler)
        MappedStatement ms = (MappedStatement)queryArgs[MAPPED_STATEMENT_INDEX];
        Object parameter = queryArgs[PARAMETER_INDEX];
        
        boolean interceptor = ms.getId().matches(_sql_regex);
                
        if(interceptor && dialect.supportsLimit()) {
            BoundSql boundSql = ms.getBoundSql(parameter);
            Object parameterObject = boundSql.getParameterObject();
            //获取分页参数对象
            PageQuery page = null;
            if (parameterObject != null) {
                page = convertParameter(parameterObject);
            }
            
            int offset = (page.getPageNumber()-1) * page.getPageSize();
            int limit = page.getPageSize();
            
            String sql = boundSql.getSql().trim();
            if (dialect.supportsLimitOffset()) {
                sql = dialect.getLimitString(sql, offset, limit);
                offset = RowBounds.NO_ROW_OFFSET;
            } else {
                sql = dialect.getLimitString(sql, 0, limit);
            }
            limit = RowBounds.NO_ROW_LIMIT;
            
            queryArgs[ROWBOUNDS_INDEX] = new RowBounds(offset,limit);
            
            BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, sql);
            
            MappedStatement newMs = copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
            queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
        }
    }

    private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql,String sql) {
        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(),sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }
        return newBoundSql;
    }

    //see: MapperBuilderAssistant
    private MappedStatement copyFromMappedStatement(MappedStatement ms,SqlSource newSqlSource) {
        Builder builder = new MappedStatement.Builder(ms.getConfiguration(),ms.getId(),newSqlSource,ms.getSqlCommandType());
        
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        
        String[] keyProperties = ms.getKeyProperties();
        builder.keyProperty(keyProperties == null ? null : keyProperties[0]);
        
        
        //setStatementTimeout()
        builder.timeout(ms.getTimeout());
        
        //setStatementResultMap()
        builder.parameterMap(ms.getParameterMap());
        
        //setStatementResultMap()
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        
        //setStatementCache()
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        
        return builder.build();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
    
    public static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;
        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

    public void setProperties(Properties properties) {
        String dialectClass = new PropertiesLoader(properties).getProperty("dialectClass");
        String sqlRegex = new PropertiesLoader(properties).getProperty("sqlRegex");
        try {
            dialect = (Dialect)Class.forName(dialectClass).newInstance();
            this._sql_regex = sqlRegex;
        } catch (Exception e) {
            throw new RuntimeException("cannot create dialect instance by dialectClass:"+dialectClass,e);
        } 
        System.out.println(PaginationInterceptor.class.getSimpleName()+".dialect="+dialectClass);
        
    }
    
    private PageQuery convertParameter(Object parameterObject) {
        try{
            if (parameterObject instanceof PageQuery) {
                return (PageQuery) parameterObject;
            } else {
                return (PageQuery)Reflections.getFieldValue(parameterObject,"page");
            }
        }catch (Exception e) {
            return null;
        }
    }
}
