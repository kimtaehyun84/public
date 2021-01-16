package com.taehyun.webservice.business.common.vo;

public class ResponseResultVO {


    private String result;
    private Object body;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ResponseResultVO{" +
                "result='" + result + '\'' +
                ", body=" + body.toString() +
                '}';
    }
}
