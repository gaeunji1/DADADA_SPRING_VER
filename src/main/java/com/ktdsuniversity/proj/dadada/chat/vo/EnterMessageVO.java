package com.ktdsuniversity.proj.dadada.chat.vo;

public class EnterMessageVO {
    private int roomId;
    private int userId;
    private String nickname;

    public EnterMessageVO() { 
    	
    }

    public int getRoomId() {
        return roomId;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}