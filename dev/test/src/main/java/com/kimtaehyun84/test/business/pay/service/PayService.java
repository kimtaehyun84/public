package com.kimtaehyun84.test.business.pay.service;



import com.kimtaehyun84.test.business.common.dto.ResponseResultDTO;

import java.util.HashMap;


public interface PayService {

    ResponseResultDTO distribute(HashMap<String, String> inputParam) throws Exception;
    ResponseResultDTO receive(HashMap<String, String> inputParam) throws Exception;
    ResponseResultDTO inquiry(HashMap<String, String> inputParam) throws Exception;
    String getToken();


}
