package com.common.business.menu.dao;

import com.common.business.common.dao.AbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("menuDAO")
public class MenuDAO extends AbstractDAO {

    public List<HashMap<String,Object>> getMenuList(HashMap<String, Object> map) throws Exception{
        return selectList("menu.selectMenuInfo", map);
    }

}
