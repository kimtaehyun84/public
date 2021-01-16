package com.taehyun.webservice.business.distributeMoney.controller;

import com.taehyun.webservice.business.common.bean.Global;
import com.taehyun.webservice.business.common.vo.ResponseResultVO;
import com.taehyun.webservice.business.distributeMoney.service.DistributeMoneyService;
import com.taehyun.webservice.business.distributeMoney.vo.DistributeMoneyVO;
import com.taehyun.webservice.business.distributeMoney.vo.InquiryDistributedVO;
import com.taehyun.webservice.business.distributeMoney.vo.ReceiveMoneyVO;
import junit.framework.TestCase;
import org.junit.runner.RunWith;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/*.xml",
        "classpath:spring/appServlet/*.xml"})


public class DistributeMoneyControllerTest extends TestCase {

    @Resource(name = "distributeMoneyService")
    DistributeMoneyService distributeMoneyService;



    @Test
    public void testDistribute(){

        DistributeMoneyVO distributeMoneyVO = new DistributeMoneyVO();
        int userId = 123;
        String roomId = "874AA";
        int totalAmount = 987654;
        int targetNum = 7;
        distributeMoneyVO.setRoomId(roomId);
        distributeMoneyVO.setUserId(userId);
        distributeMoneyVO.setTotalAmount(totalAmount);
        distributeMoneyVO.setTargetNum(targetNum);


        ResponseResultVO responseResultVO = distributeMoneyService.distribute(distributeMoneyVO);

        assertEquals(Global.RESULT_OK, responseResultVO.getResult());
        System.out.println("=================================================");
        System.out.println("Token : " + responseResultVO.getBody().toString());
        System.out.println("=================================================");


    }
    @Test
    public void testReceiveDiffUserSameRoom(){ //같은방 다른 유저가 받기
        String token = ""; //testDistribute에서 생성되는 Token사용
        ReceiveMoneyVO receiveMoneyVO = new ReceiveMoneyVO();
        int userId = 54;
        String roomId = "874AA";
        receiveMoneyVO.setUserId(userId);
        receiveMoneyVO.setRoomId(roomId);
        receiveMoneyVO.setToken(token);

        ResponseResultVO responseResultVO = distributeMoneyService.receive(receiveMoneyVO);
        assertEquals(Global.RESULT_OK, responseResultVO.getResult());
    }

    @Test
    public void testReceiveSameUserSameRoom(){ //같은방 뿌린 유저가 받기
        String token = ""; //testDistribute에서 생성되는 Token 사용
        ReceiveMoneyVO receiveMoneyVO = new ReceiveMoneyVO();
        int userId = 123;
        String roomId = "874AA";
        receiveMoneyVO.setUserId(userId);
        receiveMoneyVO.setRoomId(roomId);
        receiveMoneyVO.setToken(token);

        ResponseResultVO responseResultVO = distributeMoneyService.receive(receiveMoneyVO);
        assertEquals(Global.RESULT_NOT_OK, responseResultVO.getResult());
    }

    @Test
    public void testReceiveSameUserDiffRoom(){ //다른방 토큰 받기
        ReceiveMoneyVO receiveMoneyVO = new ReceiveMoneyVO();
        String token = ""; //testDistribute생성된 토큰이나 임의의 토큰 사용
        int userId = 123;
        String roomId = "333444";
        receiveMoneyVO.setUserId(userId);
        receiveMoneyVO.setRoomId(roomId);
        receiveMoneyVO.setToken(token);

        ResponseResultVO responseResultVO = distributeMoneyService.receive(receiveMoneyVO);
        assertEquals(Global.RESULT_NOT_OK, responseResultVO.getResult());
    }
    @Test
    public void testInquiryRegUser() {//등록한 유저가 뿌리기 조회
        InquiryDistributedVO inquiryDistributedVO = new InquiryDistributedVO();
        String token = ""; //testDistribute에서 생성된 토큰 사용
        int userId = 123;
        String roomId = "874AA";
        inquiryDistributedVO.setUserId(userId);
        inquiryDistributedVO.setRoomId(roomId);
        inquiryDistributedVO.setToken(token);
        ResponseResultVO responseResultVO = distributeMoneyService.inquiry(inquiryDistributedVO);
        assertEquals(Global.RESULT_OK, responseResultVO.getResult());
        System.out.println("Distribute Info : " + responseResultVO.getBody().toString());

    }

    @Test
    public void testInquiryDiffUser() {//다른 유저가 뿌리기 조회
        InquiryDistributedVO inquiryDistributedVO = new InquiryDistributedVO();
        String token = ""; //testDistribute에서 생성된 토큰 사용
        int userId = 54;
        String roomId = "874AA";
        inquiryDistributedVO.setUserId(userId);
        inquiryDistributedVO.setRoomId(roomId);
        inquiryDistributedVO.setToken(token);
        ResponseResultVO responseResultVO = distributeMoneyService.inquiry(inquiryDistributedVO);
        assertEquals(Global.RESULT_NOT_OK, responseResultVO.getResult());
        System.out.println("Distribute Info : " + responseResultVO.getBody().toString());

    }




}
