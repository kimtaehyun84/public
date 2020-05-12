/*
 * Copyright (C) 2018 Dominik Schadow, dominikschadow@gmail.common
 *
 * This file is part of the Java Security project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.common.prehandler.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//import javax.servlet.annotation.WebFilter;

/**
 * This servlet filter protects the complete domain by forcing HTTPS usage. The url pattern does not have any
 * influence on this header.
 *
 * @author 서성수 (19.01.29)
 */
//@WebFilter(filterName = "HSTSFilter", urlPatterns = {"/*"})
public class HSTSFilter implements Filter {

    private static Logger log = LoggerFactory.getLogger(HSTSFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.info("Strict-Transport-Security header added to response");

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (servletRequest.isSecure()) {
            response.addHeader("Strict-Transport-Security", "max-age=31556926; includeSubDomains");
        } else {
            response.sendError(406);
        }

        filterChain.doFilter(servletRequest, response);
    }

    @Override
    public void destroy() {

    }
}
