package com.kimtaehyun84.test.business.pay.dao;



import com.kimtaehyun84.test.business.common.dao.AbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("payDAO")
public class PayDAO extends AbstractDAO {


    public int deletePreviousAccessAuthority(String userNo){
        return delete("user.deletePreviousAccessAuthority", userNo);
    }

    public int deleteUserInfo(String userNo){
        return delete("user.deleteUserInfo", userNo);
    }

}
