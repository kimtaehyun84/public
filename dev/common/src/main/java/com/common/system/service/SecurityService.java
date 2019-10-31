package com.common.system.service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @Package : com.hyosung.common.system.utils.service
 * @FileName : SecurityService
 * @Version : 1.0
 * @Date : 2019-04-16
 * @Author : Taehyun Kim
 * @Description : 보안 관련 Service를 제공한다.
 */
public interface SecurityService {
    public HashMap<String, String> createRsaKey(HttpServletRequest request) throws Exception;
    public void removeRsaKey(HttpServletRequest request) throws  Exception;
    public String decryptRsa(HttpServletRequest requset, String value) throws Exception;
    public String encryptSha256Repeat(String value, String salt, String repeatCount) throws Exception;
    public String getSaltString() throws Exception;

}
