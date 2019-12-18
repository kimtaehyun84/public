package com.common.business.common.service;


import java.util.Map;

/**
 * @Package  : com.common.business.common.service
 * @FileName : CommonService
 * @Version : 1.0
 * @Date : 2019-06-21
 * @Author : Taehyun Kim
 * @Description : 공통으로 사용하는 서비스
 * ========================================================================
 * Date              ||  Name              ||  Descripton
 *  2019-06-21       ||  taehyun.kim       ||  신규 생성
 * ========================================================================
 */

public interface CommonService {
    void insertAccessLog(String userNo, String transactionName) throws Exception;

    Map<String, Object> selectServerConfig(String type) throws Exception;
}
