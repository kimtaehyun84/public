package com.common.business.login.vo;

/**
 * @Package : common.hyosung.common.business.session.vo
 * @FileName : LoginVO
 * @Version : 1.0
 * @Date : 2019-04-08
 * @Author : Taehyun Kim
 * @Description : Login 정보를 전달하는 VO
 */
public class LoginVO {
    private String loginType;
    private String userId;
    private String userPwd;

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Override
    public String toString() {
        return "LoginVO{" +
                "loginType='" + loginType + '\'' +
                ", userId='" + userId + '\'' +
                ", userPwd='" + userPwd + '\'' +
                '}';
    }
}
