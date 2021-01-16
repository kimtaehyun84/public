package com.taehyun.webservice.business.distributeMoney.vo;

import com.taehyun.webservice.business.common.vo.HeaderVO;

public class ReceiveMoneyVO extends HeaderVO {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
