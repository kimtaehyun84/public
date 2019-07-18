package com.hyosung.common.business.session.vo;

/**
 * @Package : common.hyosung.common.business.session.vo
 * @FileName : UserSessionVO
 * @Version : 1.0
 * @Date : 2019-04-08
 * @Author : Taehyun Kim
 * @Description : User의 세션정보를 전달하는 VO
 */
public class UserSessionVO {

    private String userNo;
    private String userId;
    private String userName;
    private String userIp;
    private String userGroupNo;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getUserGroupNo() {
        return userGroupNo;
    }

    public void setUserGroupNo(String userGroupNo) {
        this.userGroupNo = userGroupNo;
    }

    @Override
    public String toString() {
        return "UserSessionVO{" +
                "userNo='" + userNo + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userIp='" + userIp + '\'' +
                ", userGroupNo='" + userGroupNo + '\'' +
                '}';
    }
}
