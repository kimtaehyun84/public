package com.kimtaehyun84.test.business.pay.controller;


import com.kimtaehyun84.test.business.common.vo.ResponseResultVO;
import com.kimtaehyun84.test.business.pay.service.PayService;
import com.kimtaehyun84.test.business.pay.vo.DistributeVO;
import com.kimtaehyun84.test.business.pay.vo.InquiryVO;
import com.kimtaehyun84.test.business.pay.vo.ReceiveVO;
import com.kimtaehyun84.test.system.util.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;





@RestController
public class PayController {

	private static final Logger logger = LoggerFactory.getLogger(PayController.class);

	@Resource(name = "payService")
	private PayService payService;


	@RequestMapping(value = "/distributte", method = RequestMethod.POST)
	public ResponseResultVO distributte(@RequestBody DistributeVO vo, HttpServletRequest request, HttpSession session) throws Exception {

		HashMap<String,String> inputParam = BeanUtils.beanToHashMap(vo);
		String userId = request.getHeader("X-USER-ID");
		String roomId = request.getHeader("X-ROOM-ID");
		inputParam.put("userId", userId);
		inputParam.put("roomId", roomId);


		ResponseResultVO responseResultVO = payService.distribute(inputParam);


		return responseResultVO ;

	}

	@RequestMapping(value = "/receive", method = RequestMethod.POST)
	public ResponseResultVO receive(@RequestBody ReceiveVO vo, HttpServletRequest request, HttpSession session) throws Exception {
		HashMap<String,String> inputParam = BeanUtils.beanToHashMap(vo);
		String userId = request.getHeader("X-USER-ID");
		String roomId = request.getHeader("X-ROOM-ID");
		inputParam.put("userId", userId);
		inputParam.put("roomId", roomId);

		ResponseResultVO responseResultVO = payService.receive(inputParam);


		return responseResultVO ;

	}

	@RequestMapping(value = "/inquiry", method = RequestMethod.POST)
	public ResponseResultVO inquiry(@RequestBody InquiryVO vo, HttpServletRequest request, HttpSession session) throws Exception {
		HashMap<String,String> inputParam = BeanUtils.beanToHashMap(vo);
		String userId = request.getHeader("X-USER-ID");
		String roomId = request.getHeader("X-ROOM-ID");
		inputParam.put("userId", userId);
		inputParam.put("roomId", roomId);

		ResponseResultVO responseResultVO = payService.inquiry(inputParam);


		return responseResultVO ;

	}

	
}
