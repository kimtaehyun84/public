package com.kimtaehyun84.test.business.common.dto;

public class AbstractDTO {

    private String userId;
    private String roomId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "AbstractDTO{" +
                "userId='" + userId + '\'' +
                ", roomId='" + roomId + '\'' +
                '}';
    }
}
