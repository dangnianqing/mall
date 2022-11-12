/*
package com.mall.common.handler;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.mall.common.model.BaseModel;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.SimpleTypeRegistry;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

@Component
@Intercepts({
        @Signature(method = "update", type = Executor.class, args = {MappedStatement.class, Object.class})
})
public class MybatisParameterHandler implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 1. 获取MappedStatement实例, 并获取当前SQL命令类型
        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType commandType = ms.getSqlCommandType();
        // 2. 获取当前正在被操作的类, 有可能是Java Bean, 也可能是普通的操作对象, 比如普通的参数传递
        // 普通参数, 即是 @Param 包装或者原始 Map 对象, 普通参数会被 Mybatis 包装成 Map 对象
        // 即是 org.apache.ibatis.binding.MapperMethod$ParamMap
        Object parameter = invocation.getArgs()[1];        // 获取拦截器指定的方法类型, 通常需要拦截 update
        String methodName = invocation.getMethod().getName();
        // 3. 获取当前用户信息
        //SecurityProperties.User user = new SecurityProperties.User(1, "yangcai", "sssss");        // 默认测试参数值
        int creator = 2, updater = 3;
        if (  instanceof BaseModel){            // 4. 实体类
            BaseModel entity = (BaseModel) parameter;
           */
/* if (user != null) {
                creator = entity.getCreator();
                updater = entity.getUpdater();
            }*//*

            if (methodName.equals("update")) {
                if (commandType.equals(SqlCommandType.INSERT)) {
                    entity.setCreatedBy("admin");

                } else if (commandType.equals(SqlCommandType.UPDATE)) {

                }
            }
        } else if (parameter instanceof Map) {            // 5. @Param 等包装类
            // 更新时指定某些字段的最新数据值
            if (commandType.equals(SqlCommandType.UPDATE)) {                // 遍历参数类型, 检查目标参数值是否存在对象中, 该方式需要应用编写有一些统一的规范
                // 否则均统一为实体对象, 就免去该重复操作
                Map map = (Map) parameter;
                if (map.containsKey("creator")) {
                    map.put("creator", creator);
                }
                if (map.containsKey("updateTime")) {
                    map.put("updateTime", System.currentTimeMillis());
                }
            }
        }        // 6. 均不是需要被拦截的类型, 不做操作
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }
}
*/
