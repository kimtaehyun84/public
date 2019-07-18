package com.hyosung.common.business.session.controller;

import com.hyosung.common.business.common.vo.ResponseResultVO;
import com.hyosung.common.business.session.service.SessionService;
import com.hyosung.common.business.login.vo.LoginVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Package  : common.hyosung.common.business.session.controller
 * @FileName :
 * @Version :
 * @Date : 2019-04-08
 * @Author : Taehyun Kim
 * @Description :
 */
@Controller
@RequestMapping("/session")

public class SessionController {

    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

    /**
     * @Name: login
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-08
     * @Author : Taehyun Kim
     * @Description : User login 처리
     */

    @Resource(name="sessionService")
    private SessionService sessionService;

    @RequestMapping(value="/login")
    public @ResponseBody ResponseResultVO login(@RequestBody LoginVO inputParam, HttpSession session, HttpServletRequest request){
        ResponseResultVO responseResult = new ResponseResultVO();
        logger.debug("inputParam : " + inputParam.toString());
        //General Login
        if(inputParam.getKind().equals("01")){



        }
        // AD Login
        else if(inputParam.getKind().equals("02")){

        }


        return responseResult;


    }







}
