package com.hyosung.common.business.session.service;

import com.hyosung.common.business.session.vo.UserSessionVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @Project : common.hyosung.common.business.session.service
 * @FileName :
 * @Version :
 * @Date : 2019-04-08
 * @Author : Taehyun Kim
 * @Description :
 */
public interface SessionService {
    public UserSessionVO getSession(HttpServletRequest request);
    public void setSession(HttpServletRequest request, String key, Object value);
    public void removeAllSession(HttpServletRequest request);





}
