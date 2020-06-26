package com.kimtaehyun84.test.business.common.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

/**
 * @Package : com.common.business.common.dao
 * @FileName :AbstractDAO
 * @Version : 1.0
 * @Date : 2019-04-11
 * @Author : Taehyun Kim
 * @Description : DB연결을 위한 공통 모듈
 */
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

    public HashMap<String, String> selectOne(String queryId, Object params) {
        addQueryLog("select one", queryId);
        return sqlSession.selectOne(queryId, params);
    }

    public HashMap<String, String> selectOne(String queryId) {
        addQueryLog("select one", queryId);
        return sqlSession.selectOne(queryId);
    }

    public List<HashMap<String, String>> selectList(String queryId) {
        addQueryLog("select list", queryId);
        return sqlSession.selectList(queryId);
    }

    public List<HashMap<String, String>> selectList(String queryId, Object params) {
        addQueryLog("select list", queryId);
        return sqlSession.selectList(queryId, params);
    }
}
