package com.hyosung.common.business.common.service;

import com.hyosung.common.business.common.dao.CommonDAO;
import org.apache.ibatis.annotations.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @Package  : com.hyosung.common.business.common.service
 * @FileName : CommonServiceImpl
 * @Version : 1.0
 * @Date : 2019-06-21
 * @Author : Taehyun Kim
 * @Description : CommonService 의 Implements
 * ========================================================================
 * Date              ||  Name              ||  Descripton
 *  2019-06-21       ||  taehyun.kim       ||  신규 생성
 * ========================================================================
 */

@Service("commonService")
public class CommonServiceImpl implements CommonService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name="commonDAO")
    private CommonDAO commonDAO;



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
}
