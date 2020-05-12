package com.common.business.common.vo;

import com.common.business.common.bean.Globals;

/**
 * @Package : common.hyosung.common.business.common.vo
 * @FileName : ResponseResultVO
 * @Version : 1.0
 * @Date : 2019-04-08
 * @Author : Taehyun Kim
 * @Description : Response 공통 VO
 */
public class ResponseResultVO {
    private String status = Globals.RESULT_OK;
    private String msg;
    private Object body;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", body=" + body +
                '}';
    }
}
