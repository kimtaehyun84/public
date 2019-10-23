package com.hyosung.common.business.login.controller;

import com.hyosung.common.business.common.bean.Globals;
import com.hyosung.common.business.common.vo.ResponseResultVO;
import com.hyosung.common.business.login.service.LoginService;
import com.hyosung.common.business.login.vo.LoginVO;
import com.hyosung.common.business.session.service.SessionService;
import com.hyosung.common.business.session.vo.UserSessionVO;
import com.hyosung.common.system.utils.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


/**
 * @Package : com.hyosung.common.business.login.controller
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

    @Value("#{commonProperties['login.kind']}")
    private String loginKind;

    @RequestMapping(value = "/login")
    public @ResponseBody  ResponseResultVO login(@RequestBody LoginVO loginVO, HttpServletRequest request, HttpSession session) throws Exception {
        logger.debug(loginVO.toString());
        ResponseResultVO responseResult = new ResponseResultVO();
        HashMap<String, Object> inputParam = new HashMap<String, Object>();
        HashMap<String, Object> result = new HashMap<String, Object>();

        String userId = loginVO.getUserId();
        String userPwd = loginVO.getUserPwd();


        logger.info("Kind : " + loginKind + (loginKind.equals("01") ? "  General Login" : (loginKind.equals("02") ? " Single Sign On" : "  Active Directory Login")));
        if(loginKind.equals("01")){
            inputParam.put("userId", userId);
            inputParam.put("userPwd", userPwd);

            logger.debug(inputParam.toString());
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
        else if(loginKind.equals("02")){
            ;
        }
        else if(loginKind.equals("03")){
            ;
        }
        else{
            logger.error("Login Type Error, Check Properties File. \n [Login Kind : " + loginKind +"]");
            responseResult.setStatus(Globals.RESULT_FAIL);
            responseResult.setMsg("Login Type Error");
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
