package com.common.business.login.controller;

import com.common.business.common.bean.Config;
import com.common.business.common.bean.Globals;
import com.common.business.common.vo.RequestParamVO;
import com.common.business.common.vo.ResponseResultVO;
import com.common.business.login.service.LoginService;
import com.common.business.login.vo.LoginVO;
import com.common.business.session.service.SessionService;
import com.common.business.session.vo.UserSessionVO;
import com.common.system.service.SecurityService;
import com.common.system.utils.SecurityUtils;
import com.common.system.utils.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.HashMap;


/**
 * @Package : com.common.business.login.controller
 * @FileName : LoginController
 * @Version : 1.0
 * @Date : 2019-04-11
 * @Author : Taehyun Kim
 * @Description :
 */
@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "loginService")
    private LoginService loginService;

    @Resource(name = "sessionService")
    private SessionService sessionService;

    @Resource(name = "securityService")
    private SecurityService securityService;


    private String loginEnable = Config.getLoginEnable();

    @RequestMapping(value = "/login")
    public @ResponseBody ResponseResultVO login(@RequestBody LoginVO loginVO, HttpServletRequest request, HttpSession session) throws Exception {


        logger.debug("Param : " + loginVO.toString());
        HashMap<String,Object> inputParam = new HashMap<>();
        ResponseResultVO responseResult = new ResponseResultVO();

        HashMap<String, Object> result = new HashMap<String, Object>();
        String userId  = loginVO.getUserId();
        String userPwd  = loginVO.getUserPwd();
        String loginType = loginVO.getLoginType();
        String loginTypeText = (loginType.equals("01") ? "  General Login" : (loginType.equals("02") ? " Single Sign On" : "  Active Directory Login"));


        logger.debug("User ID : " + userId);
        logger.debug("User Pwd : " + userPwd);
        logger.info("Login Type : " + loginType + " " + loginTypeText);




        if(Arrays.stream(Config.getLoginType()).filter(allowedLoginType -> allowedLoginType.contains(loginType)) == null){
            logger.info("Login Type Error, Check Properties File. \n [Login Type : " + loginType +" " + loginTypeText +" is not allowed]");
            responseResult.setStatus(Globals.RESULT_FAIL);
            responseResult.setMsg("Login Type "+ loginType + " " + loginTypeText + "is not allowed");
        }
        if(loginType.equals("01")){


            inputParam.put("userId",userId);
            inputParam.put("userPwd", userPwd);
            responseResult= loginService.checkUser(inputParam);
            logger.info(responseResult.toString());

            if(responseResult.getStatus().equals(Globals.RESULT_OK)){
                result = (HashMap<String,Object>)responseResult.getBody();
                logger.info("Register Session Info");
                logger.debug("Session Info : " + result.toString());
                UserSessionVO userSession = new UserSessionVO();
                userSession.setUserId(result.get("USER_ID").toString());
                userSession.setUserName(result.get("USER_NAME").toString());
                userSession.setUserNo(result.get("USER_NO").toString());
                userSession.setUserGroupNo(result.get("USER_GROUP_NO").toString());
                sessionService.setSession(request,Globals.SESSION_KEY, userSession);
                logger.info("Register Session Info Complete");
                responseResult.setBody(userSession);
            }
        }
        else if(loginType.equals("02")){
            ;
        }
        else{
            logger.info("Login Type Error, Check Properties File. \n [Login Type : " + loginType +" " + loginTypeText +" is not allowed]");
            responseResult.setStatus(Globals.RESULT_FAIL);
            responseResult.setMsg("Login Type "+ loginType + " " + loginTypeText + "is not allowed");
        }

        return responseResult;
    }

    @RequestMapping(value="/logout")
    public @ResponseBody ResponseResultVO logout(@RequestBody LoginVO loginVO, HttpServletRequest request, HttpSession session) throws  Exception{
        logger.debug(loginVO.toString());
        ResponseResultVO responseResult = new ResponseResultVO();
        sessionService.removeAllSession(request);
        responseResult.setStatus(Globals.RESULT_OK);
        responseResult.setMsg(Globals.OK_MSG);
        return responseResult;
    }
}
