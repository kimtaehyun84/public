package com.taehyun.webservice.business.distributeMoney.dao;

import com.taehyun.webservice.business.common.dao.AbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("distributeMoneyDAO")
public class DistributeMoneyDAO extends AbstractDAO {

    public int createNewDistribute(HashMap<String, Object> inputParam){
        return insert("distributeMoney.createNewDistribute", inputParam);
    }

    public int createNewDistributeDetail(List<HashMap<String,Object>> inputParamList){
        return update("distributeMoney.createNewDistributeDetail", inputParamList);
    }

    public HashMap<String, Object> verifyValidToken(HashMap<String, Object> inputParam){
        return selectOne("distributeMoney.verifyValidToken", inputParam);
    }

    public int verifyAvailableToken(HashMap<String,Object> inputParam){
        return update("distributeMoney.verifyAvailableToken", inputParam);
    }

    public HashMap<String,Object> inquiryReceiveAmount(HashMap<String,Object> inputParam){
        return selectOne("distributeMoney.inquiryReceiveAmount", inputParam);
    }

    public List<HashMap<String, Object>>  inquiryDistribute(HashMap<String,Object> inputParam) {
        return selectList("distributeMoney.inquiryDistribute", inputParam);
    }

    public int deleteExpiredTokenInfo(){
        return delete("distributeMoney.deleteExpiredTokenInfo");
    }
}
