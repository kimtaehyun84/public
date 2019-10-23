package com.hyosung.common.prehandler.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class CORSFilter implements Filter {
    private static Logger log = LoggerFactory.getLogger(CORSFilter.class);
    private String crossAllowDomain = "*";
    private String crossAllowHeader = "*";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;

//		HttpServletRequest request = (HttpServletRequest) req;

//		String origin = request.getHeader("Origin");
//		if (origin != null) {
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, HEAD");
        response.setHeader("Access-Control-Max-Age", "3600");
        //response.setHeader("Access-Control-Allow-Headers", "Origin, mime-type, Accept, x-auth-token, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
        response.setHeader("Access-Control-Allow-Headers", crossAllowHeader);
        response.setHeader("Access-Control-Allow-Origin", crossAllowDomain);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Vary", "Origin");
//		}

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        String propertyFile = arg0.getInitParameter("propertyFile");
        log.info(String.format("Web.xml property file:[%s]", propertyFile));
        InputStream is = getClass().getResourceAsStream(propertyFile);
        Properties props = new Properties();
        try {
            props.load(is);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error("CORSFilter :", e);
        }
        Object allowDomain = props.get("cross.allow.domain");
        Object allowHeader = props.get("cross.allow.headers");
        this.crossAllowDomain = (allowDomain.toString() != null) ? allowDomain.toString() : "*";
        this.crossAllowHeader = (allowHeader.toString() != null) ? allowHeader.toString() : "*";
        log.info(String.format("Web.xml Allow Domain:[%s]", this.crossAllowDomain));
        log.info(String.format("Web.xml Allow Header:[%s]", this.crossAllowHeader));
    }

    @Override
    public void destroy() {
    }
}