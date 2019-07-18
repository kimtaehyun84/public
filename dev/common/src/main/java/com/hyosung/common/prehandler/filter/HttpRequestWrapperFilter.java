package com.hyosung.common.prehandler.filter;

import com.hyosung.common.system.utils.bean.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpRequestWrapperFilter implements Filter {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private FilterConfig fc;
	
	public void init(FilterConfig config) throws ServletException{
		this.fc=config;
	}
	
	public void doFilter(ServletRequest arg0, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		logger.info("[Pre Handler] : Validate Parameter");
		
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = getUrl(request);
		if(excludeUrl(uri)){
			chain.doFilter(request, response);
		}
		else{
			RequestWrapper myRequestWrapper = new RequestWrapper((HttpServletRequest) request);
			
			String body = myRequestWrapper.getBody();
			String regHtml = "\\<(/?[^\\>]+)\\>";
			String reg = "^[0-9a-zA-Z ~!&@$*()^_=-`\\-;:\",\\.?/>*+\\[\\]{}]*$";
			String regSQL = "^[0-9a-zA-Z *()_=-`\\-;:\",\\.<>\\[\\]{}]*$";
			
			
			try {
				if(null != body) {
					logger.info("[Request Body] : " + body);
					Pattern patternHtml = Pattern.compile(regHtml);
					Matcher matcherHtml = patternHtml.matcher(body);
					if(matcherHtml.find() != true){
						logger.info("[Pre Handler] : finished");
						chain.doFilter(myRequestWrapper, response); 
					}
					else{
						logger.error("Invalid Parameter");
						res.sendError(406);
					}
					
					/*Pattern pattern = Pattern.compile(reg);
					Matcher matcher = pattern.matcher(body);
					
					
					logger.info("[Body] : " + matcher.matches());
					
					if(matcher.matches()) {
						logger.info("[Pre Handler] : finished");
						chain.doFilter(myRequestWrapper, response); //here replacing request with requestWrapper
					} else {
						if(uri.indexOf("datasets") > -1 || uri.indexOf("gadget") > -1){
							chain.doFilter(myRequestWrapper, response);
							Pattern patternSQL = Pattern.compile(regSQL);
							Matcher matcherSQL = patternSQL.matcher(body);
							logger.info("[Body SQL] : " + matcherSQL.matches());
							if(matcherSQL.matches()){
								logger.info("Pre Handler] : finished");
								chain.doFilter(myRequestWrapper, response);
							}
							else{
								logger.error("Invalid Parameter");
								res.sendError(406);
							}
						}
						else{
							logger.error("Invalid Parameter");
							res.sendError(406);
						}

					}*/
				} else {
					chain.doFilter(myRequestWrapper, response); //here replacing request with requestWrapper
				}
			} catch (Exception e) {
				logger.info("exception in validation filter " + e);
			}
		}
		
		
		
	}

	private boolean excludeUrl(String uri){
		
		logger.info(uri);
		if(uri.indexOf("loginProcess") > -1 || uri.indexOf("user-pw-history") > -1 || uri.indexOf("password")> -1  || uri.indexOf("Img")> -1 /*|| uri.indexOf("authorization") > -1*/){
			logger.info("excludeURL : " + uri);
			return true;
		}
		else
			return false;
	}
	private String getUrl(HttpServletRequest request){
		String uri = request.getRequestURI().toString().trim();
		return uri;
	}
	
	@Override
	public void destroy() {
		
	}
}