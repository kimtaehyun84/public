package com.common.prehandler.interceptor;


import com.common.business.common.bean.Logs;


import com.common.system.utils.StringUtils;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.*;
import java.util.regex.Matcher;

/**
 * @Package : com.common.prehandler.interceptor
 * @FileName : MybatisLogInterceptor
 * @Version : 1.0
 * @Date : 2019-10-24
 * @Author : Taehyun Kim
 * @Description :
 * ========================================================================
 * Date              ||  Name              ||  Descripton
 * 2019-10-24       ||  taehyun.kim       ||  신규 생성
 * ========================================================================
 */


@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "update",
        args = {Statement.class}
), @Signature(
        type = StatementHandler.class,
        method = "query",
        args = {Statement.class, ResultHandler.class}
), @Signature(
        type = StatementHandler.class,
        method = "batch",
        args = {Statement.class}
)})


public class MybatisLogInterceptor implements Interceptor {


    Logger logger = LoggerFactory.getLogger(this.getClass());
    private long startTime = 0L;
    private long endTime = 0L;
    private long execTime = 0L;
    private double execTimeSec = 0.0D;
    private long totSqlTime = 0L;
    private double totSqlTimeSec = 0.0D;
    private String gid;


    public MybatisLogInterceptor() {
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {


        if (this.logger.isDebugEnabled()) {
            return this.printLog(invocation);
        } else {
            this.startTime = System.currentTimeMillis();
            Object result = invocation.proceed();
            this.endTime = System.currentTimeMillis();
            this.execTime = this.endTime - this.startTime;
            this.execTimeSec = (double) this.execTime / 1000.0D;
            return result;
        }

    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    protected String prettySql(StatementHandler statementHandler) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();
        Object param = statementHandler.getParameterHandler().getParameterObject();
        if (param == null) {
            sql = sql.replaceFirst("\\?", "''");
        } else if (!(param instanceof Integer) && !(param instanceof Long) && !(param instanceof Float) && !(param instanceof Double)) {
            if (param instanceof String) {
                sql = sql.replaceFirst("\\?", "'" + param + "'");
            } else {
                List parameterMappings;
                Iterator i$;
                ParameterMapping parameterMapping;
                String propValue;
                if (param instanceof Map) {
                    parameterMappings = boundSql.getParameterMappings();
                    Map<String, ?> mParam = (Map) param;
                    i$ = parameterMappings.iterator();

                    while (i$.hasNext()) {
                        parameterMapping = (ParameterMapping) i$.next();
                        propValue = parameterMapping.getProperty();
                        Object value;
                        if (mParam.containsKey(propValue)) {
                            value = mParam.get(propValue);
                            if (value instanceof String) {
                                sql = sql.replaceFirst("\\?", "'" + Matcher.quoteReplacement((String) value) + "'");
                            } else if (value == null) {
                                sql = sql.replaceFirst("\\?", "{" + propValue + "=NULL}");
                            } else {
                                sql = sql.replaceFirst("\\?", value.toString());
                            }
                        } else {
                            value = boundSql.getAdditionalParameter(propValue);
                            if (value instanceof String) {
                                sql = sql.replaceFirst("\\?", "'" + Matcher.quoteReplacement((String) value) + "'");
                            } else if (value == null) {
                                sql = sql.replaceFirst("\\?", "{" + propValue + "=NULL}");
                            } else {
                                sql = sql.replaceFirst("\\?", value.toString());
                            }
                        }
                    }
                } else {
                    parameterMappings = boundSql.getParameterMappings();
                    Class<? extends Object> paramClass = param.getClass();
                    this.logger.debug("paramClass.getName(); : {}", paramClass.getName());
                    i$ = parameterMappings.iterator();

                    while (i$.hasNext()) {
                        parameterMapping = (ParameterMapping) i$.next();
                        propValue = parameterMapping.getProperty();
                        Field field = paramClass.getDeclaredField(propValue);
                        field.setAccessible(true);
                        Class<?> javaType = parameterMapping.getJavaType();
                        if (String.class == javaType) {
                            sql = sql.replaceFirst("\\?", "'" + field.get(param) + "'");
                        } else {
                            sql = sql.replaceFirst("\\?", field.get(param).toString());
                        }
                    }
                }
            }
        } else {
            sql = sql.replaceFirst("\\?", param.toString());
        }

        return sql;
    }

    private static String getSqlId(StatementHandler statementHandler) throws Exception {
        Field delegateField = RoutingStatementHandler.class.getDeclaredField("delegate");
        delegateField.setAccessible(true);
        Object objHandler = delegateField.get(statementHandler);
        Field mappedStatementField = BaseStatementHandler.class.getDeclaredField("mappedStatement");
        mappedStatementField.setAccessible(true);
        MappedStatement mappedStatement = (MappedStatement) mappedStatementField.get(objHandler);
        return mappedStatement.getId();
    }

    private Object printLog(Invocation invocation) throws Throwable {

        Object result = null;
        StatementHandler handler = (StatementHandler) invocation.getTarget();
        String sqlId = getSqlId(handler);
        String methodName = invocation.getMethod().getName();

        try {
            this.startTime = System.currentTimeMillis();
            result = invocation.proceed();
            this.endTime = System.currentTimeMillis();
            this.execTime = this.endTime - this.startTime;
            this.execTimeSec = (double) this.execTime / 1000.0D;

            String sql = this.prettySql(handler);
            StringBuilder sb = new StringBuilder();
            sb.append(Logs.LOG_NEWLINE);
            sb.append(Logs.LOG_START);
            sb.append(Logs.LOG_NEWLINE);
            sb.append(" SQL ID : [" + sqlId + "]");
            sb.append(Logs.LOG_NEWLINE);
            sb.append(" Execute Time(sec) : [" + execTimeSec + "] sec");
            sb.append(Logs.LOG_NEWLINE);
            sb.append(Logs.LOG_SEP);
            sb.append(Logs.LOG_NEWLINE);
            sb.append(sql);
            sb.append(Logs.LOG_NEWLINE);
            sb.append(Logs.LOG_SEP);
            if (result != null && "query".equals(methodName) && result instanceof List) {
                List<Map<String, ?>> listResult = (List) result;
                sb.append(Logs.LOG_NEWLINE);
                int resultSize = listResult.size();
                sb.append(Logs.LOG_NEWLINE);
                if (resultSize > 0) {
                    sb.append("Total Count : " + resultSize);
                    sb.append(Logs.LOG_NEWLINE);
                    if (listResult.get(0) instanceof Map) {
                        if (resultSize < 10) {
                            sb.append(" display " + resultSize + " Rows");
                        } else {
                            sb.append(" display First 10 Rows");
                        }
                        sb.append(Logs.LOG_NEWLINE);
                        resultSize = resultSize > 10 ? 10 : resultSize;
                        Set<String> keySet = ((Map) listResult.get(0)).keySet();
                        StringBuilder sb2 = new StringBuilder();

                        int ix = 0;

                        for (int _max = keySet.size() * 20 + keySet.size() + 4 + 1; ix < _max; ++ix) {
                            sb2.append("-");
                        }

                        sb.append(sb2);
                        sb.append(Logs.LOG_NEWLINE);
                        sb.append(Logs.LOG_HORIZON_SEP);
                        sb.append("No ");
                        sb.append(Logs.LOG_HORIZON_SEP);
                        Iterator i$ = keySet.iterator();

                        while (i$.hasNext()) {
                            String key = (String) i$.next();
                            sb.append(StringUtils.rightPad(StringUtils.rightTrim(key, "", 20), 20, ' '));
                            sb.append(Logs.LOG_HORIZON_SEP);
                        }

                        sb.append(Logs.LOG_NEWLINE);
                        sb.append(sb2);
                        sb.append(Logs.LOG_NEWLINE);

                        for (ix = 0; ix < resultSize; ++ix) {
                            Map<String, Object> row = (Map) listResult.get(ix);
                            sb.append(Logs.LOG_HORIZON_SEP);
                            sb.append(StringUtils.leftPad(String.valueOf(ix), 3, ' '));
                            sb.append(Logs.LOG_HORIZON_SEP);
                            i$ = keySet.iterator();

                            while (i$.hasNext()) {
                                String key = (String) i$.next();
                                sb.append(StringUtils.rightPad(StringUtils.rightTrim(String.valueOf(row.get(key)), "", 20), 20, ' '));
                                sb.append(Logs.LOG_HORIZON_SEP);
                            }
                            sb.append(Logs.LOG_NEWLINE);


                        }
                        sb.append(sb2);
                    }

                }
            }
            sb.append(Logs.LOG_NEWLINE);
            sb.append(Logs.LOG_END);
            this.logger.debug(sb.toString());
            return result;
        } catch (Exception e) {
            this.execTime = 0L;
            String sql = this.prettySql(handler);
            StringBuilder sb = new StringBuilder();
            sb.append(Logs.LOG_START);
            sb.append(Logs.LOG_NEWLINE);
            sb.append(" SQL ID [" + sqlId + "] ERROR");
            sb.append(Logs.LOG_NEWLINE);
            sb.append(Logs.LOG_SEP);
            sb.append(Logs.LOG_NEWLINE);
            sb.append(sql);
            sb.append(Logs.LOG_NEWLINE);
            sb.append(Logs.LOG_END);
            this.logger.error(sb.toString());
            throw e;
        }

    }
}
