package com.kimtaehyun84.test.business.pay.dao;



import com.kimtaehyun84.test.business.common.dao.AbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("payDAO")
public class PayDAO extends AbstractDAO {


    public int insertDistributeSummary(HashMap<String,String> inputParam){
        return insert("pay.insertDistributeSummary", inputParam);
    }

    public int insertDistributeDetail(HashMap<String,String> inputParam){
        return insert("pay.insertDistributeDetail", inputParam);
    }

    public int insertReceiveHistory(HashMap<String,String> inputParam){
        return insert("pay.insertReceiveHistory", inputParam);
    }

    public HashMap<String, String> selectDistributeSummary(HashMap<String,String> inputParam){
        return selectOne("pay.selectDistributeSummary", inputParam);
    }

    public HashMap<String, String> selectReceiveHistoryCount (HashMap<String,String> inputParam){
        return selectOne("pay.selectReceiveHistoryCount", inputParam);
    }

    public HashMap<String, String> selectDistributeDetail(HashMap<String, String> inputParam){
        return selectOne("pay.selectDistributeDetail", inputParam);
    }

    public int deleteDistributeDetail(HashMap<String,String> inputParam){
        return delete("pay.deleteDistributeDetail", inputParam);
    }

    public int updateDistributeSummaryStatus(HashMap<String,String> inputParam){
        return update("pay.updateDistributeSummaryStatus", inputParam);
    }

    public List<HashMap<String,String>> selectDistributeDetailList(HashMap<String,String> inputParam){
        return selectList("pay.selectDistributeDetailList", inputParam);
    }
    public List<HashMap<String,String>> selectReceiveHistoryList(HashMap<String,String> inputParam){
        return selectList("pay.selectReceiveHistoryList", inputParam);
    }

    public int deleteLegacyReceiveHistory(String limitedDate){
        return delete("pay.deleteLegacyReceiveHistory", limitedDate);
    }

    public int deleteLegacyDistributeSummary(String limitedDate){
        return delete("pay.deleteLegacyDistributeSummary", limitedDate);
    }
    public int insertExpiredHistory(String limtedDateTime){
        return insert("pay.insertExpiredHistory", limtedDateTime);
    }
    public int deleteLegacyDistributeDetail(String limitedDateTime){
        return delete("pay.deleteLegacyDistributeDetail", limitedDateTime);
    }
}
