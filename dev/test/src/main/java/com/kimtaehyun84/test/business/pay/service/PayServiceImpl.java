package com.kimtaehyun84.test.business.pay.service;


import com.kimtaehyun84.test.business.common.vo.ResponseResultVO;
import com.kimtaehyun84.test.business.pay.dao.PayDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Service("payService")
public class PayServiceImpl implements PayService {
    
    private static final Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);

    @Resource(name="payDAO")
    private PayDAO payDAO;
    
    public ResponseResultVO distribute(HashMap<String, String> inputParam) throws Exception {
        return null;
    }

    public ResponseResultVO receive(HashMap<String, String> inputParam) throws Exception {
        return null;
    }

    public ResponseResultVO inquiry(HashMap<String, String> inputParam) throws Exception {
        return null;
    }
}
