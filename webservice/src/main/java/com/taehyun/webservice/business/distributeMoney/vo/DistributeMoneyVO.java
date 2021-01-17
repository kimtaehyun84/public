package com.taehyun.webservice.business.distributeMoney.vo;


import com.taehyun.webservice.business.common.vo.HeaderVO;

public class DistributeMoneyVO extends HeaderVO {

    private int totalAmount;
    private int targetNum;

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTargetNum() {
        return targetNum;
    }

    public void setTargetNum(int targetNum) {
        this.targetNum = targetNum;
    }
}
