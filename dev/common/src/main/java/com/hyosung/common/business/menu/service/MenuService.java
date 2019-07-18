package com.hyosung.common.business.menu.service;

import com.hyosung.common.business.common.vo.ResponseResultVO;

import java.util.HashMap;

public interface MenuService {

    ResponseResultVO getMenuList(HashMap<String, Object> map) throws Exception;
}
