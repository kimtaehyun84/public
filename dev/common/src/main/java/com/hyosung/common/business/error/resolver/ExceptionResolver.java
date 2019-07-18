package com.hyosung.common.business.error.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Package : com.hyosung.common.business.error.resolver
 * @FileName : ExceptionResolver
 * @Version : 1.0
 * @Date : 2019-04-16
 * @Author : Taehyun Kim
 * @Description : 공통 Error 처리 resolver
 */
public class ExceptionResolver extends SimpleMappingExceptionResolver {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);


    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.error("System Error" ,ex);
        String viewName = determineViewName(ex, request);
        if(viewName != null){
            return getModelAndView(viewName, ex,request);
        }
        else{
            return null;
        }
    }
}
