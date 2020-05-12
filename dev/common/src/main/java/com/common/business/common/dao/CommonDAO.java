package com.common.business.common.dao;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package : com.common.business.common.dao
 * @FileName : CommonDAO
 * @Version : 1.0
 * @Date : 2019-06-21
 * @Author : Taehyun Kim
 * @Description : 공통으로 사용하는 DB접근 DAO
 * ========================================================================
 * Date              ||  Name              ||  Descripton
 * 2019-06-21       ||  taehyun.kim       ||  신규 생성
 * ========================================================================
 */
@Repository("commonDAO")
public class CommonDAO extends AbstractDAO {

    public int insertAccessLog(HashMap<String, Object> map) throws Exception {
        return insert("common.insertAccessLog", map);
    }

    public Map<String, Object> selectServerConfig(String type) throws Exception {
        return selectOne("common.selectServerConfig", type);
    }
}
