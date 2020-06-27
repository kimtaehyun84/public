package com.kimtaehyun84.test.system.job;

import com.kimtaehyun84.test.business.pay.dao.PayDAO;
import com.kimtaehyun84.test.business.pay.service.PayServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Component
public class DBManager {

    @Resource(name="payDAO")
    private PayDAO payDAO;


    private static final Logger logger = LoggerFactory.getLogger(DBManager.class);

    @Scheduled(cron="0 0 0 * * *")
    @Transactional
    public void removeLeagcyData() {
        logger.info("Start Remove Legacy Data : " + LocalDateTime.now());
        int storagePeriod = 7;
        String limitedDate = LocalDate.now().minusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        payDAO.deleteLegacyDistributeSummary(limitedDate);
        payDAO.deleteLegacyReceiveHistory(limitedDate);
        logger.info("End Remove Legacy Data : " + LocalDateTime.now());


    }

    @Scheduled(cron="0 0/5 * * * *")
    @Transactional
    public void checkExpiredToken(){
        logger.info("Start Check Expired Token : "+  LocalDateTime.now());
        String limitedDateTime = LocalDateTime.now().minusMinutes(10).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        HashMap<String,String> inputParam = new HashMap<>();
        inputParam.put("limitedDateTime", limitedDateTime);
        payDAO.updateDistributeSummaryStatus(inputParam);
        payDAO.insertExpiredHistory(limitedDateTime);
        payDAO.deleteLegacyDistributeDetail(limitedDateTime);
        logger.info("End Check Expired Token : "+  LocalDateTime.now());

    }
}
