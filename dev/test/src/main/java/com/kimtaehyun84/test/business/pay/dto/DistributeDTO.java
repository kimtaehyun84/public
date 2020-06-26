package com.kimtaehyun84.test.business.pay.dto;

import com.kimtaehyun84.test.business.common.dto.AbstractDTO;



public class DistributeDTO extends AbstractDTO {
    private String amount;
    private String targetNum;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTargetNum() {
        return targetNum;
    }

    public void setTargetNum(String targetNum) {
        this.targetNum = targetNum;
    }

    @Override
    public String toString() {
        return "DistributeDTO{" +
                "amount='" + amount + '\'' +
                ", targetNum='" + targetNum + '\'' +
                '}';
    }
}
