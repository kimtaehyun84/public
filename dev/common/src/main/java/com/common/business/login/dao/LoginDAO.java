package com.hyosung.common.business.login.dao;

import com.hyosung.common.business.common.dao.AbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * @Package : com.hyosung.common.business.login.dao
 * @FileName :
 * @Version :
 * @Date : 2019-04-12
 * @Author : Taehyun Kim
 * @Description :
 */

@Repository("loginDAO")
public class LoginDAO extends AbstractDAO {

    public HashMap<String, Object> selectUserInfo(HashMap<String, Object> map) throws Exception {
        return selectOne("login.selectUserInfo", map);
    }

    public int updateUserFailedCount(HashMap<String, Object> map) throws Exception {
        return update("login.updateUserFailedCount", map);
    }
}
