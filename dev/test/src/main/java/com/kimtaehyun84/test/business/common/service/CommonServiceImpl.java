package com.kimtaehyun84.test.business.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @Package : com.hyosung.moniportal.business.com.service
 * @FileName : CommonServiceImpl
 * @Version : 1.0
 * @Date : 2020/05/20
 * @Author : Taehyun.Kim
 * @Description :
 * ========================================================================
 * Date              ||  Name              ||  Descripton
 * 2020/05/20        ||  Taehyun.Kim       ||  신규 생성
 * ========================================================================
 */
@Service("commonService")
public class CommonServiceImpl implements CommonService{
    private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

    private CommonDAO commonDAO;

    public void setCommonDAO(CommonDAO commonDAO) {
        this.commonDAO = commonDAO;
    }

    @Override
    public HashMap<String, String> getServerConfig(String customerType) throws Exception {
        HashMap<String, String> resultMap = commonDAO.selectServerConfig(customerType);
        return resultMap;
    }
}


