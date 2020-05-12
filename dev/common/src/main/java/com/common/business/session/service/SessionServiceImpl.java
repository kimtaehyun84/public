package com.common.business.session.service;

import com.common.business.common.bean.Globals;
import com.common.business.session.vo.UserSessionVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Project : common.hyosung.common.business.session.service
 * @FileName : SessionServiceImpl
 * @Version : 1.0
 * @Date : 2019-04-08
 * @Author : Taehyun Kim
 * @Description : SessionService의 implements
 */
@Service("sessionService")
public class SessionServiceImpl implements SessionService {

    /**
     * @Name: getSession
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-08
     * @Author : Taehyun Kim
     * @Description : 현재 Session 정보 return
     */
    @Override
    public UserSessionVO getSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserSessionVO userSession = (UserSessionVO) session.getAttribute(Globals.SESSION_KEY);
        return userSession;
    }


    /**
     * @Name: setSession
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-08
     * @Author : Taehyun Kim
     * @Description : session 정보 저장
     */
    @Override
    public void setSession(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession();
        session.setAttribute(key, value);
    }


    /**
     * @Name: removeAllSession
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-08
     * @Author : Taehyun Kim
     * @Description : 저장된 모든 Session 정보 삭제
     */
    @Override
    public void removeAllSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    }

}
