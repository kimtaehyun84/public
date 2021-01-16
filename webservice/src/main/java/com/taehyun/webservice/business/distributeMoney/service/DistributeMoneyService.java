package com.taehyun.webservice.business.distributeMoney.service;

import com.taehyun.webservice.business.distributeMoney.vo.DistributeMoneyVO;
import com.taehyun.webservice.business.distributeMoney.vo.InquiryDistributedVO;
import com.taehyun.webservice.business.distributeMoney.vo.ReceiveMoneyVO;
import com.taehyun.webservice.business.common.vo.ResponseResultVO;

public interface DistributeMoneyService {

    ResponseResultVO distribute(DistributeMoneyVO distributeMoneyVO);
    ResponseResultVO receive( ReceiveMoneyVO receiveMoneyVO);
    ResponseResultVO inquiry(InquiryDistributedVO inquiryDistributedVO);
}
