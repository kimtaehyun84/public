package com.kimtaehyun84.test.business.pay.service;


import com.kimtaehyun84.test.business.common.vo.GlobalVO;
import com.kimtaehyun84.test.business.common.dto.ResponseResultDTO;
import com.kimtaehyun84.test.business.pay.dao.PayDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service("payService")
public class PayServiceImpl implements PayService {
    
    private static final Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);

    @Resource(name="payDAO")
    private PayDAO payDAO;


    @Transactional
    public ResponseResultDTO distribute(HashMap<String, String> inputParam) throws Exception {
        ResponseResultDTO responseResultDTO = new ResponseResultDTO();
        String userId = inputParam.get("userId");
        String roomId = inputParam.get("roomId");
        String amount = inputParam.get("amount");
        String targetNum = inputParam.get("targetNum");

        logger.info("User ID : " + userId + " Room Id : " + roomId + " Msg : Start Distribute");
        String token = getToken();
        Random rand  = new Random(System.currentTimeMillis());
        int totalAmount = Integer.parseInt(inputParam.get("amount"));
        int totalTargetNum = Integer.parseInt(inputParam.get("targetNum"));
        logger.info("User ID : " + userId + " Room Id : " + roomId + "amount : " + totalAmount + "targetNum : " + totalTargetNum);

        for(int i = 0 ; i<totalTargetNum; i++){
            HashMap<String,String> distributeDetailDTO = new HashMap<>();
            int distributeAmount;
            if(i+1 == totalTargetNum){
                distributeAmount = totalAmount;
            }
            else{
                distributeAmount = (int)Math.floor(totalAmount*rand.nextDouble());
            }
            totalAmount -= distributeAmount;
            distributeDetailDTO.put("regUserId", userId);
            distributeDetailDTO.put("token", token);
            distributeDetailDTO.put("roomId", roomId);
            distributeDetailDTO.put("amount", Integer.toString(distributeAmount));
            payDAO.insertDistributeDetail(distributeDetailDTO);
        }
        HashMap<String,String> distributeSummaryDTO = new HashMap<>();
        distributeSummaryDTO.put("token", token);
        distributeSummaryDTO.put("regUserId", userId);
        distributeSummaryDTO.put("roomId", roomId);
        distributeSummaryDTO.put("totalAmount", amount);
        distributeSummaryDTO.put("totalTargetNum",targetNum);
        distributeSummaryDTO.put("disable", "N");
        payDAO.insertDistributeSummary(distributeSummaryDTO);

        responseResultDTO.setStatus(GlobalVO.RESULT_OK);
        HashMap<String,String> resultMap = new HashMap<>();
        resultMap.put("token", token);
        responseResultDTO.setBody(resultMap);
        logger.info("User ID : " + userId + " Room Id : " + roomId + "Token : " + token + " Msg : End Distribute");
        return responseResultDTO;


    }
    @Transactional
    public ResponseResultDTO receive(HashMap<String, String> inputParam) throws Exception {
        ResponseResultDTO responseResultDTO = new ResponseResultDTO();
        String userId = inputParam.get("userId");
        String token = inputParam.get("token");
        String roomId = inputParam.get("roomId");
        String regUserId;
        inputParam.put("disable", "N");


        HashMap<String,String> distributeSummaryDTO = payDAO.selectDistributeSummary(inputParam);
        logger.info("User ID : " + userId + " Room Id : " + roomId + "Token : " + token + " Msg : Start Receive");


        if(distributeSummaryDTO != null){ // 동일한 대화방에 유효한 토큰이 있는 경우


            LocalDateTime regDate = LocalDateTime.parse(distributeSummaryDTO.get("REG_DATE"), DateTimeFormatter.ofPattern(("yyyy-MM-dd HH:mm:ss")));
            LocalDateTime expireDate = regDate.plusMinutes(10);
            regUserId = distributeSummaryDTO.get("REG_USER_ID");
            if(LocalDateTime.now().isBefore(expireDate)){ //유효시간

                inputParam.clear();
                            inputParam.put("roomId", roomId);
                    inputParam.put("token", token);
                    inputParam.put("regUserId", regUserId);
                    inputParam.put("recvUserId", userId);


                    int distributeHistoryCount = Integer.parseInt(String.valueOf(payDAO.selectReceiveHistoryCount(inputParam).get("COUNT")));
                    if(distributeHistoryCount > 0){//이미 받은 경우
                        logger.info("User ID : " + userId + " Room Id : " + roomId + "Token : " + token + " Msg : Already Receive");
                        responseResultDTO.setStatus(GlobalVO.RESULT_FAIL);
                        responseResultDTO.setMsg("You can only get it once");
                    }
                    else{
                        if(userId.equals(distributeSummaryDTO.get("REG_USER_ID"))){
                        //뿌린 사람이 자기 자신인경우
                        logger.info("User ID : " + userId + " Room Id : " + roomId + "Token : " + token + " Msg : Can't acquire your own");
                        responseResultDTO.setStatus(GlobalVO.RESULT_FAIL);
                        responseResultDTO.setMsg("Can't acquire your own");
                    }
                    else{
                        inputParam.clear();
                        inputParam.put("regUserId", regUserId);
                        inputParam.put("token", token);
                        inputParam.put("roomId", roomId);


                        HashMap<String,String> distributeDetail = payDAO.selectDistributeDetail(inputParam);
                        if(distributeDetail != null){
                            HashMap<String,String> resultMap = new HashMap<>();
                            String amount = distributeDetail.get("AMOUNT");
                            resultMap.put("amount",amount);

                            inputParam.put("amount", amount);
                            inputParam.put("regDate", distributeDetail.get("REG_DATE"));
                            inputParam.put("recvUserId", userId);
                            logger.info("User ID : " + userId + " Room Id : " + roomId + "Token : " + token + " Msg : Receive " +amount);
                            responseResultDTO.setStatus(GlobalVO.RESULT_OK);
                            responseResultDTO.setBody(resultMap);

                            payDAO.insertReceiveHistory(inputParam);
                            payDAO.deleteDistributeDetail(inputParam);
                        }
                        else{
                            logger.info("User ID : " + userId + " Room Id : " + roomId + "Token : " + token + " Msg : Balance has been exhausted");
                            responseResultDTO.setStatus(GlobalVO.RESULT_FAIL);
                            responseResultDTO.setMsg("Balance has been exhausted");
                        }
                    }
                }

            }
            else{ //유효시간 만료
                //TODO : 기존 내용 삭제 후 히스토리 업데이트
                //Summary disable 처리
                payDAO.updateDistributeSummaryStatus(inputParam);

                inputParam.clear();
                inputParam.put("roomId", roomId);
                inputParam.put("token", token);
                inputParam.put("regUserId", regUserId);


                //남은 Detail List 조회
                List<HashMap<String,String>> distributeDetailList = payDAO.selectDistributeDetailList(inputParam);
                if(distributeDetailList.size() > 0){
                    for(HashMap<String,String> distributeDetail : distributeDetailList){

                        inputParam.put("amount", distributeDetail.get("AMOUNT"));
                        inputParam.put("regDate", distributeDetail.get("REG_DATE"));
                        inputParam.put("recvUserId", "None");

                        payDAO.insertReceiveHistory(inputParam);
                        payDAO.deleteDistributeDetail(inputParam);
                    }
                }
                logger.info("User ID : " + userId + " Room Id : " + roomId + "Token : " + token + " Msg : Token has been expired");
                responseResultDTO.setStatus(GlobalVO.RESULT_FAIL);
                responseResultDTO.setMsg("Token has been expired");
            }
        }
        else{
            responseResultDTO.setStatus(GlobalVO.RESULT_FAIL);
            responseResultDTO.setMsg("Token is not available");
            logger.info("User ID : " + userId + " Room Id : " + roomId + "Token : " + token + " Msg : Token is not available");
        }

        logger.info("User ID : " + userId + " Room Id : " + roomId + "Token : " + token + " Msg : End Receive");
        return responseResultDTO;
    }

    public ResponseResultDTO inquiry(HashMap<String, String> inputParam) throws Exception {
        ResponseResultDTO responseResultDTO = new ResponseResultDTO();
        String token = inputParam.get("token");
        String roomId = inputParam.get("roomId");
        String userId = inputParam.get("userId");
        logger.info("User ID : " + userId + " Room Id : " + roomId + "Token : " + token + " Msg : Start Inquiry");


        inputParam.clear();
        String endDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String startDate = LocalDate.now().minusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        inputParam.put("token", token);
        inputParam.put("roomId", roomId);
        inputParam.put("regUserId", userId);
        inputParam.put("startDate", startDate);
        inputParam.put("endDate", endDate);

        HashMap<String,String> distributeSummaryDTO = payDAO.selectDistributeSummary(inputParam);
        if(distributeSummaryDTO != null){
            HashMap<String, Object> resultMap = new HashMap<String,Object>();
            resultMap.put("regDate", distributeSummaryDTO.get("REG_DATE"));
            resultMap.put("totalAmount", distributeSummaryDTO.get("TOTAL_AMOUNT"));

            inputParam.clear();
            inputParam.put("token", token);
            inputParam.put("regUserId", userId);
            inputParam.put("roomId", roomId);
            List<HashMap<String,String>> receiveHistoryList = payDAO.selectReceiveHistoryList(inputParam);
            List<HashMap<String,String>> receiveInfoList = new ArrayList<>();
            int successTotalReceiveAmount = 0;
            for(HashMap<String,String> receiveHistory : receiveHistoryList){
                String recvUserId = receiveHistory.get("RECV_USER_ID");
                String amount = receiveHistory.get("AMOUNT");
                successTotalReceiveAmount+= Integer.parseInt(amount);
                receiveHistory.clear();
                receiveHistory.put("recvUserId", recvUserId);
                receiveHistory.put("amount", amount);
                receiveInfoList.add(receiveHistory);

            }
            resultMap.put("successTotalReceiveAmount", successTotalReceiveAmount);
            resultMap.put("successReceiveInfoList", receiveInfoList);
            logger.info("User ID : " + userId + " Room Id : " + roomId + "Token : " + token +
                    " Msg : " + resultMap.toString());
            responseResultDTO.setStatus(GlobalVO.RESULT_OK);
            responseResultDTO.setBody(resultMap);

        }
        else{
            responseResultDTO.setStatus(GlobalVO.RESULT_FAIL);
            responseResultDTO.setMsg("Token is not available");
            logger.info("User ID : " + userId + " Room Id : " + roomId + "Token : " + token + " Msg : Token is not available");
        }


        logger.info("User ID : " + userId + " Room Id : " + roomId + "Token : " + token + " Msg : End Inquiry");
        return responseResultDTO;
    }


    @Override
    public String getToken(){

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        String token = "";
        for(int i = 0; i < 3; i++){

            int val = rand.nextInt(126);
            if(val < 33)
                val += 33;
            token = token.concat(Character.toString((char)val));
        }

        return token;
    }
}
