package com.taehyun.webservice.business.distributeMoney.service;

import com.taehyun.webservice.business.common.bean.Global;
import com.taehyun.webservice.business.common.vo.ResponseResultVO;
import com.taehyun.webservice.business.distributeMoney.dao.DistributeMoneyDAO;
import com.taehyun.webservice.business.distributeMoney.vo.DistributeMoneyVO;
import com.taehyun.webservice.business.distributeMoney.vo.InquiryDistributedVO;
import junit.framework.TestCase;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;


import javax.inject.Inject;
import javax.sql.DataSource;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DistributeMoneyServiceImplTest extends TestCase {



    @Test
    public void testGetRandomString() throws Exception{
        int digits = 3;
        DistributeMoneyService distributeMoneyService = new DistributeMoneyServiceImpl();
        Method method = distributeMoneyService.getClass().getDeclaredMethod("getRandomString", int.class);
        method.setAccessible(true);

        String randomString =  method.invoke(distributeMoneyService,digits).toString();

        assertEquals(digits,randomString.length());

    }

    @Test
    public void testGetDivideMoneyList() throws Exception{
        DistributeMoneyService distributeMoneyService = new DistributeMoneyServiceImpl();

        DistributeMoneyVO distributeMoneyVO = new DistributeMoneyVO();
        String roomId = "nb134";
        int totalAmount = 34567;
        int targetNum = 10;
        distributeMoneyVO.setTargetNum(targetNum);
        distributeMoneyVO.setTotalAmount(totalAmount);
        distributeMoneyVO.setRoomId(roomId);
        String token = "A2V";

        Method method = distributeMoneyService.getClass().getDeclaredMethod("getDivideMoneyList", DistributeMoneyVO.class,String.class);

        method.setAccessible(true);

        List<HashMap<String,Object>> divideMoneyList = (List<HashMap<String, Object>>) method.invoke(distributeMoneyService, distributeMoneyVO,token);

        int sumAmount = 0;
        for(HashMap<String,Object> divideMoney : divideMoneyList){
            sumAmount += Integer.parseInt(divideMoney.get("amount").toString());
        }

        assertEquals(targetNum, divideMoneyList.size()); //의도한 수만큼 나뉘었는지
        assertEquals(totalAmount, sumAmount); //나눠진 금액의 총합과 나누기 전의 금액은 일치하는지
        assertEquals(roomId, divideMoneyList.get(0).get("roomId").toString()); //roomId는 정상적으로 할당 되었는지
        assertEquals(token, divideMoneyList.get(0).get("token").toString()); //token은 정상적으로 할당 되었는지


    }



}

