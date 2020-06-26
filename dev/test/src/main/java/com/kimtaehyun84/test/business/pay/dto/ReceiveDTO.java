package com.kimtaehyun84.test.business.pay.dto;

import com.kimtaehyun84.test.business.common.dto.AbstractDTO;

public class ReceiveDTO extends AbstractDTO {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ReceiveDTO{" +
                "token='" + token + '\'' +
                '}';
    }
}
