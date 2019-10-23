package com.hyosung.common.prehandler.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ClickjackingPreventionFilter implements Filter {
	private static Logger log = LoggerFactory.getLogger(ClickjackingPreventionFilter.class);
	private String xFrameOptionMode = "DENY";
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse)response;
        res.addHeader("X-FRAME-OPTIONS", xFrameOptionMode );   
        chain.doFilter(request, response);
    }
    public void destroy() {
    }
     
    public void init(FilterConfig filterConfig) {
    	/*String propertyFile = filterConfig.getInitParameter("propertyFile");
    	log.info(String.format("Web.xml property file:[%s]", propertyFile));
		InputStream is = getClass().getResourceAsStream(propertyFile);
		Properties props = new Properties();
		try {
			props.loadFromXML(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("ClickjackingPreventionFilter :", e);
		}
		Object mode = props.get("xFrame.option.mode");
		this.xFrameOptionMode = mode.toString();
		log.info(String.format("Web.xml xFrameOptionMode:[%s]", this.xFrameOptionMode));*/
		
    }
	

}
