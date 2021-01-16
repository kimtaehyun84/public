package com.taehyun.webservice.business.distributeMoney.job;

import com.taehyun.webservice.business.distributeMoney.dao.DistributeMoneyDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

public class DistributeMoneyJob {

    private static final Logger logger = LoggerFactory.getLogger(DistributeMoneyJob.class);

    @Resource(name = "distributeMoneyDAO")
    private DistributeMoneyDAO distributeMoneyDAO;

    /**
     * @Name: deleteExpiredTokenInfo
     * @Type : Function
     * @Version : 1.0
     * @Date : 2021/01/15
     * @Author : Taehyun.Kim
     * @Param : []
     * @Return : void
     * @Description : Token을 생성한지 8일째 되는 날 00:00에 해당 Token정보를 삭제한다
     * ========================================================================
     *  Date              ||  Name              ||  Descripton
     *  2021/01/15        ||  Taehyun.Kim       ||  신규 생성
     * ========================================================================
     */
    @Scheduled(cron = "0 0 0 * * *") // every midnight
    private void deleteExpiredTokenInfo(){
        try{
            logger.info("Start Delete Expired Token Info");
            distributeMoneyDAO.deleteExpiredTokenInfo();
            logger.info("End Delete Expired Token Info");
        }catch(Exception e){
            logger.error(e.getMessage());
        }

    }
}
