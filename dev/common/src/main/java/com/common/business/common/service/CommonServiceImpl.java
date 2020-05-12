package com.common.business.common.service;

import com.common.business.common.dao.CommonDAO;
import org.apache.ibatis.annotations.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Package : com.common.business.common.service
 * @FileName : CommonServiceImpl
 * @Version : 1.0
 * @Date : 2019-06-21
 * @Author : Taehyun Kim
 * @Description : CommonService 의 Implements
 * ========================================================================
 * Date              ||  Name              ||  Descripton
 * 2019-06-21       ||  taehyun.kim       ||  신규 생성
 * ========================================================================
 */

@Service("commonService")
public class CommonServiceImpl implements CommonService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "commonDAO")
    private CommonDAO commonDAO;

    public void setCommonDAO(CommonDAO commonDAO) {
        this.commonDAO = commonDAO;
    }

    @Override
    public void insertAccessLog(String userNo, String transactionName) throws Exception {
        /**
         * @Name: insertAccessLog
         * @Type : Function
         * @Version : 1.0
         * @Date : 2019-06-21
         * @Author : Taehyun Kim
         * @Param : [userNo, transactionName]
         * @Return : void
         * @Description : Audit trail을 기록한다.
         * ========================================================================
         *  Date              ||  Name              ||  Descripton
         *  2019-06-21       ||  taehyun.kim       ||  신규 생성
         * ========================================================================
         */
        HashMap<String, Object> inputParam = new HashMap<String, Object>();
        inputParam.put("userNo", userNo);
        inputParam.put("transactionName", transactionName);
        commonDAO.insertAccessLog(inputParam);

    }

    @Override
    public Map<String, Object> selectServerConfig(String type) throws Exception {
        /**
         * @Name: selectServerConfig
         * @Type : Function
         * @Version : 1.0
         * @Date : 2019-11-01
         * @Author : Taehyun Kim
         * @Param : [type]
         * @Return : java.util.Map<java.lang.String,java.lang.Object>
         * @Description : Server 초기 설정값을 설정한다.
         * ========================================================================
         *  Date              ||  Name              ||  Descripton
         *  2019-11-01       ||  taehyun.kim       ||  신규 생성
         * ========================================================================
         */
        Map<String, Object> resultMap = new HashMap<>();
        resultMap = commonDAO.selectServerConfig(type);
        return resultMap;
    }
}
