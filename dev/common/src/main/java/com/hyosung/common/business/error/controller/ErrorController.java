package com.hyosung.common.business.error.controller;

import com.hyosung.common.business.common.bean.Globals;
import com.hyosung.common.business.common.vo.ResponseResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Package  : com.hyosung.common.system
 * @FileName : ErrorController
 * @Version : 1.0
 * @Date : 2019-04-08
 * @Author : Taehyun Kim
 * @Description : Excepction 처리하는 공통 모듈
 */
@Controller
public class ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    /**
     * @Name: error
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-08
     * @Author : Taehyun Kim
     * @Description : Java Exception 처리
     */

    @RequestMapping(value="/error")
    public @ResponseBody ResponseResultVO error(HttpServletRequest request, Exception ex){
        ResponseResultVO responseResult = new ResponseResultVO();
        responseResult.setStatus(Globals.RESULT_FAIL);
        responseResult.setMsg(Globals.ERROR_MSG);
        return responseResult;
    }
}
