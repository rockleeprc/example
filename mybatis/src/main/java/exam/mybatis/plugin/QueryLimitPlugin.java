package exam.mybatis.plugin;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;



@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class,Integer.class }))
public class QueryLimitPlugin implements Interceptor {

	private int limit;
	private String dbType;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// 取出被拦截的对象
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
		// 分离代理对象
		while (metaStatementHandler.hasGetter("h")) {
			Object object = metaStatementHandler.getValue("h");
			metaStatementHandler = SystemMetaObject.forObject(object);
		}
		while (metaStatementHandler.hasGetter("target")) {
			Object object = metaStatementHandler.getValue("target");
			metaStatementHandler = SystemMetaObject.forObject(object);
		}
		// 取出要执行的sql
		String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");

		// 判断是否时mysql数据库,sql是否被重写
		if ("mysql".equals(this.dbType) && sql.indexOf(limit) == -1) {
			String limitSql = sql.trim() + " limit 1,"+limit;
			metaStatementHandler.setValue("delegate.boundSql.sql", limitSql);
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		this.limit = Integer.parseInt(properties.getProperty("limit"));
		this.dbType = properties.getProperty("dbType");
		System.out.println("limit:"+limit);
		System.out.println("dbType:"+dbType);
	}

}
