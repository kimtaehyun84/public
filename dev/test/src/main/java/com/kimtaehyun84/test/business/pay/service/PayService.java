package com.kimtaehyun84.test.business.pay.service;



import com.kimtaehyun84.test.business.common.vo.ResponseResultVO;

import java.util.HashMap;


public interface PayService {

    ResponseResultVO distribute(HashMap<String, String> inputParam) throws Exception;
    ResponseResultVO receive(HashMap<String, String> inputParam) throws Exception;
    ResponseResultVO inquiry(HashMap<String, String> inputParam) throws Exception;


}
