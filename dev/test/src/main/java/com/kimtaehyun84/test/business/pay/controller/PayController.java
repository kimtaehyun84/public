package com.kimtaehyun84.test.business.pay.controller;


import com.kimtaehyun84.test.business.common.dto.ResponseResultDTO;
import com.kimtaehyun84.test.business.pay.service.PayService;
import com.kimtaehyun84.test.business.pay.dto.DistributeDTO;
import com.kimtaehyun84.test.business.pay.dto.InquiryDTO;
import com.kimtaehyun84.test.business.pay.dto.ReceiveDTO;
import com.kimtaehyun84.test.system.util.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;





@RestController
public class PayController {

	private static final Logger logger = LoggerFactory.getLogger(PayController.class);

	@Resource(name = "payService")
	private PayService payService;


	@RequestMapping(value = "/distribute", method = RequestMethod.POST)
	public ResponseResultDTO distributte(@RequestBody DistributeDTO dto, HttpServletRequest request, HttpSession session) throws Exception {

		HashMap<String,String> inputParam = BeanUtils.beanToHashMap(dto);
		String userId = request.getHeader("X-USER-ID");
		String roomId = request.getHeader("X-ROOM-ID");
		inputParam.put("userId", userId);
		inputParam.put("roomId", roomId);


		ResponseResultDTO responseResultDTO = payService.distribute(inputParam);


		return responseResultDTO;

	}

	@RequestMapping(value = "/receive", method = RequestMethod.POST)
	public ResponseResultDTO receive(@RequestBody ReceiveDTO dto, HttpServletRequest request, HttpSession session) throws Exception {
		HashMap<String,String> inputParam = BeanUtils.beanToHashMap(dto);
		String userId = request.getHeader("X-USER-ID");
		String roomId = request.getHeader("X-ROOM-ID");
		inputParam.put("userId", userId);
		inputParam.put("roomId", roomId);

		ResponseResultDTO responseResultDTO = payService.receive(inputParam);


		return responseResultDTO;

	}

	@RequestMapping(value = "/inquiry", method = RequestMethod.POST)
	public ResponseResultDTO inquiry(@RequestBody InquiryDTO vo, HttpServletRequest request, HttpSession session) throws Exception {
		HashMap<String,String> inputParam = BeanUtils.beanToHashMap(vo);
		String userId = request.getHeader("X-USER-ID");
		String roomId = request.getHeader("X-ROOM-ID");
		inputParam.put("userId", userId);
		inputParam.put("roomId", roomId);

		ResponseResultDTO responseResultDTO = payService.inquiry(inputParam);


		return responseResultDTO;

	}

	
}
