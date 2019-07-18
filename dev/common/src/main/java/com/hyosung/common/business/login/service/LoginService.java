package com.hyosung.common.business.login.service;

import com.hyosung.common.business.common.vo.ResponseResultVO;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package : com.hyosung.common.business.login.service
 * @FileName : LoginService
 * @Version : 1.0
 * @Date : 2019-04-11
 * @Author : Taehyun Kim
 * @Description : Login에 관련된 서비스를 처리한다.
 */
public interface LoginService {

    ResponseResultVO checkUser(HashMap<String, Object> map) throws Exception;
}
