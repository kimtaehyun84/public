package com.kimtaehyun84.test.business.common.controller;

import com.hyosung.moniportal.business.common.bean.Config;
import com.hyosung.moniportal.business.common.bean.Globals;
import com.hyosung.moniportal.system.util.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/*****************************************************************
 * @Name  : RootController
 * @Type  : Class
 * @Description : Home 페이지에 관련된 처리한다.
 * Copyright : Copyright (c) 2012
 * Company : Daewoo Information Systems Co., Ltd.
 * @Author: Jaemin Jang
 * @version 1.0
 *****************************************************************/
@Controller
public class RootController {

	private static final Logger logger = LoggerFactory.getLogger(RootController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = sra.getRequest();


		if(SessionUtils.checkSession(request, Globals.USER_SESSION_KEY))
			return "redirect:/goMain";

		request.setAttribute("publicExponent", Config.getPublicKeyExponent());
		request.setAttribute("publicModulus", Config.getPublicKeyModulus());

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );

		return "login";
	}
	
	@RequestMapping(value = "/goMain", method = {RequestMethod.GET, RequestMethod.POST})
	public String goMain() {
		logger.info("goMain");
	
		return "index";
	}
	
	@RequestMapping(value = "/loginSSO", method = RequestMethod.GET)
	public String loginSSO() {
		logger.info("loginSSO");
	
		return "redirect:/goMain";
	}
	
}
