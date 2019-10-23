package com.hyosung.common.business.menu.controller;

import com.hyosung.common.business.common.vo.ResponseResultVO;
import com.hyosung.common.business.menu.service.MenuService;
import com.hyosung.common.business.session.vo.UserSessionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @Package  : com.hyosung.common.business.menu.controller
 * @FileName : MenuController
 * @Version : 1.0
 * @Date : 2019-06-21
 * @Author : Taehyun Kim
 * @Description : Menu 조회, 추가, 권환 부여 등 Menu에 관련된 Process 처리
 * ========================================================================
 * Date              ||  Name              ||  Descripton
 *  2019-06-21       ||  taehyun.kim       ||  신규 생성
 * ========================================================================
 */

@Controller
@RequestMapping("/menu")
public class MenuController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "menuService")
    private MenuService menuService;

    @RequestMapping(value="/getMenuList", method= RequestMethod.POST)
    public @ResponseBody ResponseResultVO getMenuList(@RequestBody UserSessionVO userSessionVO, HttpServletRequest request, HttpSession session) throws Exception{
        /**
        * @Name: getMenuList
        * @Type : Function
        * @Version : 1.0
        * @Date : 2019-06-21
        * @Author : Taehyun Kim
        * @Param : [inputParam, request, session]
        * @Return : com.hyosung.common.business.common.vo.ResponseResultVO
        * @Description :
        * ========================================================================
        *  Date              ||  Name              ||  Descripton
        *  2019-06-21       ||  taehyun.kim       ||  신규 생성
        * ========================================================================
        */

        logger.debug(userSessionVO.toString());
        ResponseResultVO responseResult = new ResponseResultVO();

        HashMap<String, Object> inputParam = new HashMap<String,Object>();
        inputParam.put("userNo", userSessionVO.getUserNo());
        inputParam.put("userId", userSessionVO.getUserId());
        inputParam.put("userGroupNo", userSessionVO.getUserGroupNo());

        responseResult = menuService.getMenuList(inputParam);

        return responseResult;
    }
}
