package com.common.business.common.controller;

import com.common.business.common.bean.Config;
import com.common.business.session.service.SessionService;
import com.common.business.session.vo.UserSessionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Package : common.hyosung.common.business.common.controller
 * @FileName : RootController
 * @Version : 1.0
 * @Date : 2019-04-08
 * @Author : Taehyun Kim
 * @Description : 초기 메인 페이지를 컨트롤한다.
 */

@Controller
public class RootController {

    private static final Logger logger  = LoggerFactory.getLogger(RootController.class);

    /**
     * @Name: home
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-08
     * @Author : Taehyun Kim
     * @Description : /로 접속시 페이지 컨트롤
     */
    @Resource(name = "sessionService")
    private SessionService sessionService;

    @Autowired
    public Config config;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) throws IOException {
        ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();

        HttpServletRequest request = attr.getRequest();
        HttpServletResponse response = attr.getResponse();
        if(!"Y".equals(Config.getLoginEnable())){
            response.sendError(406);
        }


        logger.info("Welcome home! The client locale is {}.", locale);

        sessionService.removeAllSession(request);
        String publicExponent = config.getPublicKeyExponent();
        String publicModulus = config.getPublicKeyModulus();
        request.setAttribute("publicExponent", publicExponent);
        request.setAttribute("publicModulus", publicModulus);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);

        model.addAttribute("serverTime", formattedDate );

        return "login";
    }

    /**
     * @Name: goMain
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-08
     * @Author : Taehyun Kim
     * @Description : goMain으로 접속시 페이지 컨트롤
     */

    @RequestMapping(value = "/goMain", method = {RequestMethod.GET, RequestMethod.POST})
    public String goMain(){
        logger.info("goMain");
        return "index";
    }
}
