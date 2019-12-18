package com.common.business.menu.service;

import com.common.business.common.service.CommonService;
import com.common.business.common.vo.ResponseResultVO;
import com.common.business.menu.dao.MenuDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @Package  : com.common.business.menu.service
 * @FileName : MenuServiceImpl
 * @Version : 1.0
 * @Date : 2019-06-21
 * @Author : Taehyun Kim
 * @Description :
 * ========================================================================
 * Date              ||  Name              ||  Descripton
 *  2019-06-21       ||  taehyun.kim       ||  신규 생성
 * ========================================================================
 */

@Service("menuService")
public class MenuServiceImpl implements MenuService{



    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name="menuDAO")
    private MenuDAO menuDAO;

    @Resource(name="commonService")
    private CommonService commonService;

    @Override
    public ResponseResultVO getMenuList(HashMap<String, Object> inputParam) throws Exception {
    /**
    * @Name: getMenuList
    * @Type : Function
    * @Version : 1.0
    * @Date : 2019-06-21
    * @Author : Taehyun Kim
    * @Param : [inputParam]
    * @Return : com.common.business.common.vo.ResponseResultVO
    * @Description : menu list를 불러옴
    * ========================================================================
    *  Date              ||  Name              ||  Descripton
    *  2019-06-21       ||  taehyun.kim       ||  신규 생성
    * ========================================================================
    */

        logger.debug("getMenuList start");



        return null;
    }
}
