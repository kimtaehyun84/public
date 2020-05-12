package com.common.prehandler.filter;

import com.common.system.bean.DecryptParamWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DecryptParamFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private FilterConfig fc;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.fc = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /**
         * @Name: doFilter
         * @Type : Function
         * @Version : 1.0
         * @Date : 2019-12-18
         * @Author : Taehyun Kim
         * @Param : [request, response, chain]
         * @Return : void
         * @Description : header의 isEncrypted 값을확인하여 true인 경우 복호화 한다.
         * ========================================================================
         *  Date              ||  Name              ||  Descripton
         *  2019-12-18       ||  taehyun.kim       ||  신규 생성
         * ========================================================================
         */
        logger.info("[Pre Handler] : Decrypt Parameter Filter");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if ("true".equals(httpServletRequest.getHeader("isEncrypted"))) {
            try {
                DecryptParamWrapper decryptParamWrapper = new DecryptParamWrapper((HttpServletRequest) request);
                chain.doFilter(decryptParamWrapper, httpServletResponse);
            } catch (Exception ex) {
                logger.error("Exception in DecryptParamFilter " + ex);
            }

        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
