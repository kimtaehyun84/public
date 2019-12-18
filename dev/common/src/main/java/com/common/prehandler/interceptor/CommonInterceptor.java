package com.common.prehandler.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Package  : common.hyosung.common.prehandler.interceptor
 * @FileName :CommonInterceptor
 * @Version : 1.0
 * @Date : 2019-04-08
 * @Author : Taehyun Kim
 * @Description : Logger의 모드가 debug인 경우 해당 컨트롤러의 시작을 로그에 표시한다.
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(logger.isDebugEnabled()){
            logger.debug("================= START =================");
            logger.debug("Request URI \t: " + request.getRequestURI());
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(logger.isDebugEnabled()){
            logger.debug("================ END ===================");
        }
        super.postHandle(request, response, handler, modelAndView);
    }
}
