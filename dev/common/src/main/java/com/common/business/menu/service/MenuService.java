package com.common.business.menu.service;

import com.common.business.common.vo.ResponseResultVO;

import java.util.HashMap;

public interface MenuService {

    ResponseResultVO getMenuList(HashMap<String, Object> map) throws Exception;
}
