package com.common.system.controller;

import com.common.business.common.vo.ResponseResultVO;
import com.common.system.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Package : common.hyosung.common.system.utils.controller
 * @FileName : UtilController
 * @Version : 1.0
 * @Date : 2019-04-08
 * @Author : Taehyun Kim
 * @Description : Utility에 관련된 처리를 담당한다.
 */

@Controller
@RequestMapping("/util")
public class UtilController {
    private static final Logger logger = LoggerFactory.getLogger(UtilController.class);

    @Resource(name="securityService")
    private SecurityService securityService;
    /**
     * @Name: getPublickey
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-08
     * @Author : Taehyun Kim
     * @Description : RSA PublicKey 전달
     */
    @RequestMapping(value="/getPublicKey", method= RequestMethod.POST)
    public @ResponseBody ResponseResultVO getPublicKey(HttpSession session, HttpServletRequest request) throws Exception{
        logger.info("Create RSA Public Key");
        ResponseResultVO responseResult = new ResponseResultVO();
        responseResult.setBody(securityService.createRsaKey(request));
        logger.info("Complete to Create RSA Public Key");
        return responseResult;
    }

}
