package org.ko.ibatis.plugin;


import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

@Intercepts({
        @Signature(
                type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class, Integer.class}),
        @Signature(
                type = ResultSetHandler.class,
                method = "handleResultSets",
                args = Statement.class
        )
})
public class SupportPlugin implements Interceptor {


    public Object intercept(Invocation invocation) throws Throwable {
        //当前拦截的方法的对象
        Object target = invocation.getTarget();

        //如果是第一次拦截
        if (target instanceof StatementHandler) {
            //获取StatementHandler的对象
            StatementHandler handler = StatementHandler.class.cast(target);
            //取出当前Spring代理的sql对象
            MetaObject meta = SystemMetaObject.forObject(handler);
            MappedStatement statement = MappedStatement.class.cast(meta.getValue("delegate.mappedStatement"));
            //Mybatis 中配置的sql id
            String sqlId = statement.getId();
            //获取sql内容
            BoundSql boundSql = BoundSql.class.cast(meta.getValue("delegate.boundSql"));
            String sql = boundSql.getSql();
            Object params = boundSql.getParameterObject();

            //###------测试输出-------###
            System.out.println("Sql id = " + sqlId);
            System.out.println("sql = " + sql);
            System.out.println("params = " + params);

        }

        return invocation.proceed();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {

    }
}
