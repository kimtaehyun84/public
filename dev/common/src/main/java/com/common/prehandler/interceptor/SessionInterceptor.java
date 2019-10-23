package com.hyosung.common.prehandler.interceptor;


import com.hyosung.common.business.common.bean.Globals;
import com.hyosung.common.business.common.vo.ResponseResultVO;
import com.hyosung.common.business.session.service.SessionService;
import com.hyosung.common.business.session.vo.UserSessionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Package : common.hyosung.common.prehandler.interceptor
 * @FileName :SessionInterceptor
 * @Version : 1.0
 * @Date : 2019-04-08
 * @Author : Taehyun Kim
 * @Description : URI 호출전 Session여부를 확인하는 Interceptor
 */




public class SessionInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    /**
     * @Name: preHandle
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-08
     * @Author : Taehyun Kim
     * @Description : Session 존재 여부 확인
     */


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.debug("Interceptor : PreHandle");
        SessionService sessionService = null;
        UserSessionVO userSession;
        userSession = sessionService.getSession(request);

        if(userSession == null){
            logger.info("Session Interceptor : Session has Expired");
            ResponseResultVO responseResult = new ResponseResultVO();
            responseResult.setStatus(Globals.RESULT_FAIL);
            responseResult.setMsg(Globals.SESSION_FAIL_MSG);
            response.getWriter().write(responseResult.toString());
            return false;
        }
        else{
            logger.info("Session Interceptor : Session check Success");
            return super.preHandle(request, response, handler);
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
