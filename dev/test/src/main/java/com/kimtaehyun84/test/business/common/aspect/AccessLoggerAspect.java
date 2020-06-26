package com.kimtaehyun84.test.business.common.aspect;

import com.hyosung.moniportal.system.bean.RequestWrapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
/**
 * @Package : com.hyosung.moniportal.business.common.aspect
 * @FileName : AccessLoggerAspect
 * @Version : 1.0
 * @Date : 2020/06/15
 * @Author : Taehyun.Kim
 * @Description : Access Log 출력을 위한 AOP
 * ========================================================================
 * Date              ||  Name              ||  Descripton
 * 2020/06/15        ||  Taehyun.Kim       ||  신규 생성
 * ========================================================================
 */


public class AccessLoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger(AccessLoggerAspect.class);



    @Around("execution(* com.hyosung.moniportal..controller.*.*(..))")
    public Object accessLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        /**
         * @Name: accessLogger
         * @Type : Function
         * @Version : 1.0
         * @Date : 2020/06/15
         * @Author : Taehyun.Kim
         * @Param : [proceedingJoinPoint]
         * @Return : java.lang.Object
         * @Description : Controller 진입 시점에 Access Log를 출력한다.
         *                  Access Log를 출력, Debug인 경우 InputParam을 같이 출력한다.
         * ========================================================================
         *  Date              ||  Name              ||  Descripton
         *  2020/06/15        ||  Taehyun.Kim       ||  신규 생성
         * ========================================================================
         */
        try {

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String controllerName = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
            String methodName = proceedingJoinPoint.getSignature().getName();
            String clientIp = (null != request.getHeader("X-FORWARDED-FOR")) ? request.getHeader("X-FORWARDED-FOR") : request.getRemoteAddr();
            String uri = request.getRequestURI();
            String requestMethod = request.getMethod();
            RequestWrapper requestWrapper = new RequestWrapper(request);

            logger.info("Client IP Address : [" + clientIp + "] URI : [" + uri
                    + "] Method : [" + requestMethod + "] Controller : [" + controllerName + "] methodName : [" + methodName +"]");
            logger.debug("Param : [" + requestWrapper.getBody()+"]");


            Object result = proceedingJoinPoint.proceed();
            return result;
        } finally {

        }
    }
}
