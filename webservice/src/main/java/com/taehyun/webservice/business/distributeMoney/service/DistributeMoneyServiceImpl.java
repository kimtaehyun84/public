package com.taehyun.webservice.business.distributeMoney.service;


import com.taehyun.webservice.business.common.bean.Global;
import com.taehyun.webservice.business.distributeMoney.dao.DistributeMoneyDAO;
import com.taehyun.webservice.business.distributeMoney.vo.DistributeMoneyVO;
import com.taehyun.webservice.business.distributeMoney.vo.InquiryDistributedVO;
import com.taehyun.webservice.business.distributeMoney.vo.ReceiveMoneyVO;
import com.taehyun.webservice.business.common.vo.ResponseResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service("distributeMoneyService")
public class DistributeMoneyServiceImpl implements DistributeMoneyService {
    private static final Logger logger = LoggerFactory.getLogger(DistributeMoneyServiceImpl.class);
    
    @Resource(name = "distributeMoneyDAO")
    private DistributeMoneyDAO distributeMoneyDAO;
    
   /**
     * @Name: distribute
     * @Type : Function
     * @Version : 1.0
     * @Date : 2021/01/15
     * @Author : Taehyun.Kim
     * @Param : [distributeMoneyVO]
     * @Return : com.taehyun.webservice.business.common.vo.ResponseResultVO
     * @Description :
     * 1. 문자열 토큰 생성
     * 2. 토큰 중복성 확인 (토큰 중복 조건 : ROOM ID, TOKEN이 같으면 중복)
     *   2-1. 중복시 1. 부터 다시 시작
     *   2-2. 중복이 아닐시 3.으로 진행
     *   2-3. 다시 시도하다가 지정 시간 초과시 실패 Return
     * 3. 인원수에 맞춰 금액 분배
     * 4. 생성된 Token Return
     * ========================================================================
     *  Date              ||  Name              ||  Descripton
     *  2021/01/15        ||  Taehyun.Kim       ||  신규 생성
     * ========================================================================
     */
   @Override
   @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Throwable.class})
   public ResponseResultVO distribute(DistributeMoneyVO distributeMoneyVO) {


        //Random 문자열 토큰 생성
        ResponseResultVO responseResultVO = new ResponseResultVO();
        
        String token = getRandomString(3);
        HashMap<String, Object> inputParam = new HashMap<>();
        inputParam.put("userId", distributeMoneyVO.getUserId());
        inputParam.put("roomId", distributeMoneyVO.getRoomId());
        inputParam.put("token", token);
        inputParam.put("totalAmount", distributeMoneyVO.getTotalAmount());
        
        int result = -1;
        long timeout = System.currentTimeMillis() + 10000;
        //Unique토큰 생성시 까지 반복 Unique 기준 한방에 토큰번호 1개
        while(result == -1){
            //10초동안 Unique토큰을 생성 못할 경우 Timeout 처리
            if(System.currentTimeMillis() > timeout){
                responseResultVO.setResult(Global.RESULT_NOT_OK);
                responseResultVO.setBody(Global.ERROR_MSG_TIMEOUT);
                return responseResultVO;
            }

            try{
                result = distributeMoneyDAO.createNewDistribute(inputParam);

            }catch(DuplicateKeyException e){
                //중복
                token = getRandomString(3);
                inputParam.put("token", token);
                result = -1;
            }
            catch(Exception e ){
                logger.error(e.getMessage());
                responseResultVO.setResult(Global.RESULT_NOT_OK);
                responseResultVO.setBody(Global.ERROR_MSG_COMMON);
                return responseResultVO;
            }
        }
        //Token 이 정상 생성 되면 인원에 맞게 돈 분류
        try{
            List<HashMap<String,Object>> inputParamList = getDivideMoneyList(distributeMoneyVO, token);
            distributeMoneyDAO.createNewDistributeDetail(inputParamList);
            responseResultVO.setResult(Global.RESULT_OK);
            responseResultVO.setBody(token);
        }catch(Exception e){
            logger.error(e.getMessage());
            responseResultVO.setResult(Global.RESULT_NOT_OK);
            responseResultVO.setBody(Global.ERROR_MSG_COMMON);

        }


        return responseResultVO;
    }

    /**
     * @Name: receive
     * @Type : Function
     * @Version : 1.0
     * @Date : 2021/01/15
     * @Author : Taehyun.Kim
     * @Param : [receiveMoneyVO]
     * @Return : com.taehyun.webservice.business.common.vo.ResponseResultVO
     * @Description :
     * 1. Token의 유효성 체크(뿌린지 10분이 넘었는지, 뿌린사람이 자기자신은 아닌지)
     *   1-1. Token이 유효하면 2. 진행
     *   1-2. Token이 유효하지 않으면 실패 Return
     * 2. Receive가 가능한지 체크(이미 받지 않았는지, 받을 금액이 남아있는지)
     *   2-1. Receive가 가능하면 3. 진행
     *   2-2. Receive가 불가능하면 실패 Return
     * 3. Receive한 금액 Return
     * ========================================================================
     *  Date              ||  Name              ||  Descripton
     *  2021/01/15        ||  Taehyun.Kim       ||  신규 생성
     * ========================================================================
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Throwable.class})
    public ResponseResultVO receive(ReceiveMoneyVO receiveMoneyVO) {

        ResponseResultVO responseResultVO = new ResponseResultVO();
        HashMap<String,Object> inputParam = new HashMap<>();
        inputParam.put("userId", receiveMoneyVO.getUserId());
        inputParam.put("roomId", receiveMoneyVO.getRoomId());
        inputParam.put("token", receiveMoneyVO.getToken());
        try {

            //Token이 유효한 경우는 roomId, token이 일치하면서 만료되지 않고 뿌린사람이 내가 아닌 경우

            int isValid = Integer.parseInt(String.valueOf(distributeMoneyDAO.verifyValidToken(inputParam).get("RESULT")));
            logger.debug("isValid : " + isValid);

            if (isValid > 0) {
                //Token이 유효한 경우
                //받기가 가능한 경우는 해당 토큰에 대해 받은 적이 없고 남은 금액이 있는 경우
                int isAvailable = Integer.parseInt(String.valueOf(distributeMoneyDAO.verifyAvailableToken(inputParam)));
                logger.debug("isAvailable : " + isAvailable);
                if (isAvailable > 0) {
                    //받기가 가능한 경우
                    responseResultVO.setResult(Global.RESULT_OK);
                    responseResultVO.setBody(distributeMoneyDAO.inquiryReceiveAmount(inputParam).get("AMOUNT"));

                } else {
                    //받기가 불가능 한 경우
                    responseResultVO.setResult(Global.RESULT_NOT_OK);
                    responseResultVO.setBody(Global.ERROR_MSG_UNAVAILABLE_TOKEN);
                }
            } else {
                //Token이 유효하지 않은 경우
                responseResultVO.setResult(Global.RESULT_NOT_OK);
                responseResultVO.setBody(Global.ERROR_MSG_INVAILD_TOKEN);
            }
        }
        catch(Exception e){
            logger.error(e.getMessage());
            responseResultVO.setResult(Global.RESULT_NOT_OK);
            responseResultVO.setBody(Global.ERROR_MSG_COMMON);
        }
        return responseResultVO;
    }

      /**
     * @Name: inquiry
     * @Type : Function
     * @Version : 1.0
     * @Date : 2021/01/15
     * @Author : Taehyun.Kim
     * @Param : [inquiryDistributedVO]
     * @Return : com.taehyun.webservice.business.common.vo.ResponseResultVO
     * @Description :
     * 1. 조회 가능한 Token인지 확인(뿌린지 7일이 안지났는지, 내가 만든 토큰인지)
     *   1-1. 조회 가능하면 2. 진행
     *   1-2. 조회 불가능하면 조회 실패 Return
     * 2. 뿌린 시간, 뿌린 금액, 받기 완료된 금액,
     *    받기 완료된 정보([받은 금액, 받은 사용자 아이디] 리스트) Return
     * ========================================================================
     *  Date              ||  Name              ||  Descripton
     *  2021/01/15        ||  Taehyun.Kim       ||  신규 생성
     * ========================================================================
     */
      @Override
      @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Throwable.class})
      public ResponseResultVO inquiry(InquiryDistributedVO inquiryDistributedVO) {

        ResponseResultVO responseResultVO = new ResponseResultVO();
        HashMap<String,Object> inputParam = new HashMap<>();
        inputParam.put("userId", inquiryDistributedVO.getUserId());
        inputParam.put("roomId", inquiryDistributedVO.getRoomId());
        inputParam.put("token", inquiryDistributedVO.getToken());

        try {

            List<HashMap<String, Object>> inquiryResultList = distributeMoneyDAO.inquiryDistribute(inputParam);
            if (inquiryResultList.isEmpty()) {
                responseResultVO.setResult(Global.RESULT_NOT_OK);
                responseResultVO.setBody(Global.ERROR_MSG_INVAILD_TOKEN);
            } else {
                //조회 가능
                HashMap<String,Object> body = new HashMap<>();
                List<HashMap<String,Object>> receiveCompleteInfoList = new ArrayList<>();
                int totalReceiveAmount = 0;
                for(int i = 0; i< inquiryResultList.size(); i++){
                    HashMap<String,Object> inquiryResult = inquiryResultList.get(i);
                    HashMap<String,Object> receiveCompleteInfo = new HashMap<>();
                    if(i==0){
                        body.put("regDate", inquiryResult.get("REG_DATE"));
                        body.put("totalAmount", inquiryResult.get("TOTAL_AMOUNT"));
                    }
                    if(inquiryResultList.size() == 1 && null==inquiryResult.get("RECV_AMOUNT") && null==inquiryResult.get("RECV_USER_ID")){

                        totalReceiveAmount=0;

                    }else{
                        receiveCompleteInfo.put("receiveAmount", inquiryResult.get("RECV_AMOUNT"));
                        receiveCompleteInfo.put("recvUserId", inquiryResult.get("RECV_USER_ID"));
                        receiveCompleteInfoList.add(receiveCompleteInfo);
                        totalReceiveAmount+=Integer.parseInt(String.valueOf(inquiryResult.get("RECV_AMOUNT")));
                    }
                }
                body.put("totalReceiveAmount", totalReceiveAmount);
                body.put("receiveCompleteInfoList", receiveCompleteInfoList);

                responseResultVO.setResult(Global.RESULT_OK);
                responseResultVO.setBody(body);
            }
        }
        catch (Exception e){
            logger.error(e.getMessage());
            responseResultVO.setResult(Global.RESULT_NOT_OK);
            responseResultVO.setBody(Global.ERROR_MSG_COMMON);
        }

        return responseResultVO;
    }



    /**
     * @Name: gedDivideMoneyList
     * @Type : Function
     * @Version : 1.0
     * @Date : 2021/01/15
     * @Author : Taehyun.Kim
     * @Param : DistributeMoneyVO
     * @Return : divideMoneyList
     * @Description :
     * ========================================================================
     *  Date              ||  Name              ||  Descripton
     *  2021/01/15        ||  Taehyun.Kim       ||  신규 생성
     * ========================================================================
     */
    private List<HashMap<String,Object>> getDivideMoneyList(DistributeMoneyVO distributeMoneyVO, String token){
        List<HashMap<String,Object>> divideMoneyList = new ArrayList<>();
        int totalAmount = distributeMoneyVO.getTotalAmount();
        int targetNum = distributeMoneyVO.getTargetNum();
        String roomId = distributeMoneyVO.getRoomId();
        Random random = new Random(System.currentTimeMillis());

        while(targetNum > 0){
            HashMap<String, Object> divideMoney = new HashMap<>();
            divideMoney.put("roomId", roomId);
            divideMoney.put("token", token);
            int amount = 0;
            double ratio = 0.0;
            if(targetNum == 1){
                amount = totalAmount;
            }
            else{
                ratio = random.nextDouble();


                amount = (int)(ratio * totalAmount);

            }
            totalAmount -= amount;
            logger.debug("targetNum : " + targetNum + " ratio : " + ratio + " amount : " + amount + " totalAmount : " + totalAmount);
            divideMoney.put("amount", amount);

            divideMoneyList.add(divideMoney);
            targetNum--;
        }

        return divideMoneyList;

    }
    /**
     * @Name: getRandomString
     * @Type : Function
     * @Version : 1.0
     * @Date : 2021/01/15
     * @Author : Taehyun.Kim
     * @Param : digit
     * @Return : random String
     * @Description :
     * ========================================================================
     *  Date              ||  Name              ||  Descripton
     *  2021/01/15        ||  Taehyun.Kim       ||  신규 생성
     * ========================================================================
     */
    private String getRandomString(int digit){
        StringBuilder sb = new StringBuilder();
        String charlist = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_#$!%^@*()+~`-=[{]}|;:',<.>/";
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0; i< digit; i++){
            char c = charlist.charAt(random.nextInt(charlist.length()));
            sb.append(c);
        }
        return sb.toString();
    }
}
