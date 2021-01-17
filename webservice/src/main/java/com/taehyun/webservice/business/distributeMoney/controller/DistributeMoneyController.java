package com.taehyun.webservice.business.distributeMoney.controller;


import com.taehyun.webservice.business.distributeMoney.service.DistributeMoneyService;
import com.taehyun.webservice.business.distributeMoney.vo.DistributeMoneyVO;
import com.taehyun.webservice.business.distributeMoney.vo.InquiryDistributedVO;
import com.taehyun.webservice.business.distributeMoney.vo.ReceiveMoneyVO;
import com.taehyun.webservice.business.common.vo.ResponseResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class DistributeMoneyController {

   private static final Logger logger = LoggerFactory.getLogger(DistributeMoneyController.class);

   @Resource(name = "distributeMoneyService")
   private DistributeMoneyService distributeMoneyService;

   @RequestMapping(value = "/distribute", method = RequestMethod.POST)
   public ResponseResultVO distributeMoney(@RequestBody DistributeMoneyVO distributeMoneyVO, HttpServletRequest request){
      ResponseResultVO responseResultVO;
      distributeMoneyVO.setUserId(request.getIntHeader("X-USER-ID"));
      distributeMoneyVO.setRoomId(request.getHeader("X-ROOM-ID"));
      responseResultVO = distributeMoneyService.distribute(distributeMoneyVO);
      return responseResultVO;
   }

   @RequestMapping(value = "/receive", method = RequestMethod.POST)
   public ResponseResultVO receiveMoney(@RequestBody ReceiveMoneyVO receiveMoneyVO, HttpServletRequest request){
      ResponseResultVO responseResultVO;
      receiveMoneyVO.setUserId(request.getIntHeader("X-USER-ID"));
      receiveMoneyVO.setRoomId(request.getHeader("X-ROOM-ID"));
      responseResultVO = distributeMoneyService.receive(receiveMoneyVO);
      return responseResultVO;
   }

   @RequestMapping(value = "/inquiry", method = RequestMethod.POST)
   public ResponseResultVO inquiryDistributed(@RequestBody InquiryDistributedVO inquiryDistributedVO, HttpServletRequest request){

      ResponseResultVO responseResultVO;
      inquiryDistributedVO.setUserId(request.getIntHeader("X-USER-ID"));
      inquiryDistributedVO.setRoomId(request.getHeader("X-ROOM-ID"));
      responseResultVO = distributeMoneyService.inquiry(inquiryDistributedVO);
      return responseResultVO;
   }

   @RequestMapping(value="/help", method = RequestMethod.GET)
   public String help(HttpServletRequest request){
      return "Distribute Money V01";
   }

}
