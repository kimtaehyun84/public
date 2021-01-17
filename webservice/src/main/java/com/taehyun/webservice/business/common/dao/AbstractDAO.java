package com.taehyun.webservice.business.common.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

public class AbstractDAO {
    private static final Logger logger = LoggerFactory.getLogger(AbstractDAO.class);

    @Autowired
    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    private void addQueryLog(String queryType, String queryId) {
        if (logger.isDebugEnabled()) {
            logger.debug("[SQL] Query Type : " + queryType + "  Query ID : " + queryId);
        }
    }

    public int insert(String queryId, Object params) {
        addQueryLog("insert", queryId);
        return sqlSession.insert(queryId, params);
    }

    public int update(String queryId, Object params) {
        addQueryLog("update", queryId);
        return sqlSession.update(queryId, params);
    }

    public int delete(String queryId, Object params) {
        addQueryLog("delete", queryId);
        return sqlSession.delete(queryId, params);
    }

    public int delete(String queryId){
        addQueryLog("delete", queryId);
        return sqlSession.delete(queryId);
    }

    public HashMap<String, Object> selectOne(String queryId, Object params) {
        addQueryLog("select one", queryId);
        return sqlSession.selectOne(queryId, params);
    }

    public HashMap<String, Object> selectOne(String queryId) {
        addQueryLog("select one", queryId);
        return sqlSession.selectOne(queryId);
    }

    public List<HashMap<String, Object>> selectList(String queryId) {
        addQueryLog("select list", queryId);
        return sqlSession.selectList(queryId);
    }

    public List<HashMap<String, Object>> selectList(String queryId, Object params) {
        addQueryLog("select list", queryId);
        return sqlSession.selectList(queryId, params);
    }
}
