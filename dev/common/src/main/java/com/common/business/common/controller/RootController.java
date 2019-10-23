package com.hyosung.common.business.common.controller;

import com.hyosung.common.business.session.service.SessionService;
import com.hyosung.common.business.session.vo.UserSessionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        sessionService.removeAllSession(request);
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
